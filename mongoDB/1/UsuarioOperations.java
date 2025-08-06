import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UsuarioOperations {
    public static void main(String[] args){
        MongoDBConnection connection = new MongoDBConnection();
        MongoDatabase database = connection.getDatabase();

        // Exemplo de uso
        if (database != null) {
            System.out.println("Banco de dados: " + database.getName());
        }


        try {
            // Aguarde para garantir que os processos internos sejam concluídos
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UsuarioOperations usuarioOperations = new UsuarioOperations(database);

        Usuario u1 = new Usuario("Alice", 25);
        Usuario u2 = new Usuario("Bob", 30);
        Usuario u3 = new Usuario("Charlie", 35);

        System.out.println("Criando usuarios Alice, Bob e Charlie");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);

        usuarioOperations.insertUsuariosList(usuarios);


        System.out.println("Alterando a idade de Bob de 30 para 32 anos:");
        u2.setIdade(32);
        usuarioOperations.updateUsuario(u2);

        System.out.println("Consultando todos os registros: ");
        List<Usuario> todosUsuarios = usuarioOperations.readAllUsuarios();
        todosUsuarios.forEach(u -> System.out.println(u.toString()));

        System.out.println("Deletando Charlie");
        usuarioOperations.deleteUsuario(u3);

        System.out.println("Consultando todos os registros: ");
        todosUsuarios = usuarioOperations.readAllUsuarios();
        todosUsuarios.forEach(u -> System.out.println(u.toString()));

        connection.closeConnection();
    }

    private static MongoCollection<Document> collection;

    public UsuarioOperations(MongoDatabase mongoDatabase) {
        this.collection = mongoDatabase.getCollection("usuarios");
    }

    //CRUD - Create
    public void insertUsuario(Usuario usuario){
        try {
            Document document = usuario.toDocument();
            collection.insertOne(document);
            System.out.println("Usuario inserido com sucesso: " + usuario.getNome());

        }catch(Exception e) {
            System.err.println("Erro ao inserir usuario: " + usuario.getNome());

        }
    }

    //CRUD - Create many
    public void insertUsuariosList(List<Usuario> usuarios){
        List<Document> documentos = new ArrayList<>();
        try {
            for (Usuario u : usuarios) {
                documentos.add(u.toDocument());
            }
            collection.insertMany(documentos);
        }catch(Exception e){
            System.err.println("Erro ao inserir lista de usuarios no banco de dados: ");
            for(Usuario u : usuarios){
                System.out.println(u.getNome());
            }
            System.err.println(e.getMessage());
        }
    }

    //CRUD - Read All
    public List<Usuario> readAllUsuarios(){
        try{
            return collection.find().into(new ArrayList<>())
                    .stream()
                    .map(Usuario::fromDocument)
                    .collect(Collectors.toList());

        }catch(Exception e){
            System.err.println("Erro ao consultar usuários: " + e.getMessage());
            return null;
        }
    }

    //CRUD - Update
    public void updateUsuario(Usuario usuario){
        try{
            Document documento = usuario.toDocument();
            BsonDocument bsonDocument = usuario.toDocument().toBsonDocument();
            collection.updateOne(bsonDocument, documento);
            System.out.println("Usuario atualizado com sucesso: " + usuario.getNome());
        }catch (Exception e){
         System.err.println("Erro ao fazer update do usuario: " + usuario.getNome() + " " + e.getMessage());
        }
    }

    //CRUD - Delete
    public void deleteUsuario(Usuario usuario){
        try{
            BsonDocument bsonDocument = usuario.toDocument().toBsonDocument();
            collection.deleteOne(bsonDocument);
            System.out.println("Usuario deletado com sucesso: " + usuario.getNome());
        }catch(Exception e){
            System.err.println("Erro ao deletar usuario: " + usuario.getNome() + " " + e.getMessage());
        }
    }


}
