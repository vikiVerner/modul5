package modul4.client;

import modul4.DatabaseInitService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



class ClientDaoServiceTest {
    private Connection connection;
    private ClientDaoService daoService;

    @BeforeEach
    public void beforeEach() throws SQLException {
        String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        new DatabaseInitService().createTable(connectionUrl);
        connection = DriverManager
                .getConnection(connectionUrl);
        daoService = new ClientDaoService(connection);

    }


    @Test
    public void testThatClientCreatedCorrectly() throws SQLException {
        long id = daoService.create("test");

        Assertions.assertEquals(7,id);
    }

    @Test
    public void testThatGetIdWorkCorrectly() throws SQLException {
        String name = daoService.getById(1);
        String expected = "Daniel Johnson";
        Assertions.assertEquals(expected,name);
    }
    @Test
    public void testThatCreateNullWorkedCorrectly() {

        Assertions.assertThrows(IllegalArgumentException.class,()-> daoService.create(null));
    }
    @Test
    public void testThatSetNameWorkedCorrectly() throws SQLException {
        long newClientId = daoService.create("Anna");

        daoService.setName(newClientId,"Enni");

        String changedName = daoService.getById(newClientId);
        Assertions.assertEquals("Enni",changedName);

    }
    @Test
    public void testThatDeleteClientWorkedCorrectly() throws SQLException {

        Assertions.assertThrows(IllegalArgumentException.class,()->{
            long newClientId = daoService.create("Anna");
            daoService.deleteById(newClientId);
            String newClientName = daoService.getById(newClientId);
        });
    }

    @AfterEach
    public  void afterEach() throws SQLException {

        connection.close();
        File file = new File("./sql/test1.mv.db");
        if (!file.exists()) {
            file.delete();
        }
    }
}