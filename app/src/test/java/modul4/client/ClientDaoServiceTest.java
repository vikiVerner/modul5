package modul4.client;

import modul4.DatabaseInitService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;

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

        Assertions.assertThrows(SQLClientInfoException.class,()->{
            daoService.create(null);
        });
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
        long newClientId = daoService.create("Anna");
        daoService.deleteById(newClientId);
        String newClientName = daoService.getById(newClientId);
        assertNull(newClientName);
    }
    @Test
    public void testClearAll() throws SQLException {
        daoService.clearAll();
        long id = daoService.maxId();

        Assertions.assertEquals(0,id);
    }


    @AfterEach
    public  void afterEach() throws SQLException {
        daoService.clearAll();
        connection.close();
    }
}