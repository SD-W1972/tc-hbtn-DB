import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


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
        }
    }


}
