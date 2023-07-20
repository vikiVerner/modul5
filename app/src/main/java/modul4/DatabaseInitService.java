package modul4;

import java.io.IOException;


public class DatabaseInitService {

    public static void main(String[] args) throws IOException {

        Database database = Database.getInstance();
        new DatabaseInitService().createTable(database);

    }

    public void createTable(Database database)  {

        String  sql = ReadSQLFile.getSqlFromFile("./sql/init_db.sql");

        database.executeUpdate(sql);

    }

}
