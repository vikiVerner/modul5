package modul4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {

    public static void main(String[] args) throws IOException {

        Database database = Database.getInstance();
        new DatabaseInitService().createTable(database);

    }

    public void createTable(Database database)  {
        try {
            String initDBUrl = "C:\\Users\\alexa\\IdeaProjects\\modul4\\sql\\init_db.sql";
            String sql = String.join("\n", Files.readAllLines(Paths.get(initDBUrl)));
            database.executeUpdate(sql);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
