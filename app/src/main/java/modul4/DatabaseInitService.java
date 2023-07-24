package modul4;

import org.flywaydb.core.Flyway;

import java.io.IOException;


public class DatabaseInitService {

    public static void main(String[] args) {
        String connectionUrl = "jdbc:h2:./test1";
        new DatabaseInitService().createTable(connectionUrl);
    }

    public void createTable(String connectionUrl)  {

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }

}
