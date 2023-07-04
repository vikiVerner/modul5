package modul4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        new DatabasePopulateService().insertValues(database);
    }
    public void insertValues(Database database)  {
        try {
            String insertDBUrl = "C:\\Users\\alexa\\IdeaProjects\\modul4\\sql\\populate_db.sql";
            String sql = String.join("\n", Files.readAllLines(Paths.get(insertDBUrl)));
            database.executeUpdate(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
