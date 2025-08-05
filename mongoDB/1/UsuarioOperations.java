import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UsuarioOperations {
    private MongoDBConnection mdbConnection;
    private MongoCollection<Document> collection;
    private static final String COLLECTION_NAME = "usuarios";

    public UsuarioOperations(MongoDBConnection mdbConnection, MongoCollection<Document> collection) {
        this.mdbConnection = mdbConnection;
        this.collection = collection;
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

    //CRUD - Read
    public List<Usuario> readAllUsuarios() {
        try {
            return collection.find()
                    .into(new ArrayList<>())
                    .stream()
                    .map(Usuario::fromDocument)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("Erro ao consultar usuários: " + e.getMessage());
            return new ArrayList<>();
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

}
