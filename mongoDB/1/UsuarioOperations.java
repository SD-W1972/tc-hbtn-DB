import com.mongodb.client.MongoCollection;

import javax.swing.text.Document;

public class UsuarioOperations {
    private MongoDBConnection mdbConnection;
    private MongoCollection<Document> collection;
    private static final String COLLECTION_NAME = "usuarios";

    public UsuarioOperations(MongoDBConnection mdbConnection, MongoCollection<Document> collection) {
        this.mdbConnection = mdbConnection;
        this.collection = collection;
    }


}
