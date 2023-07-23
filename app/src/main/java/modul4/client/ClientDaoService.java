package modul4.client;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClientDaoService {
    private PreparedStatement createSt;
    private PreparedStatement getById;
    private PreparedStatement setName;
    private PreparedStatement deleteById;
    private PreparedStatement listAll;
    private PreparedStatement selectMaxId;
    private PreparedStatement deleteAll;

    public ClientDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement("INSERT INTO client (name) VALUES ?");
        getById = connection.prepareStatement("SELECT name FROM client WHERE id = ?");
        setName = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
        deleteById = connection.prepareStatement("DELETE FROM client WHERE id = ?");
        listAll = connection.prepareStatement("SELECT id,name FROM client ");
        selectMaxId = connection.prepareStatement("SELECT MAX(id) AS maxId FROM client");
        deleteAll = connection.prepareStatement("DELETE FROM project_worker;\n" +
                "DELETE FROM project;\n" +
                "DELETE FROM worker; DELETE FROM client;");
    }

    public void clearAll() throws SQLException {
        deleteAll.executeUpdate();
    }

    public long maxId() throws SQLException {
        long id = -1;
        try (ResultSet rs = selectMaxId.executeQuery()) {
            if (rs.next()) {
                id = rs.getLong("maxId");
            }
        }
        return id;
    }

    public long create(String name) throws SQLException {
        if (name == null || name.length() < 2 || name.length() > 1000) {
            throw new SQLClientInfoException();
        } else {
            createSt.setString(1, name);
            createSt.executeUpdate();
            return maxId();
        }
    }

    public String getById(long id) throws SQLException {
        String name;
        getById.setLong(1, id);
        try (ResultSet rs = getById.executeQuery()) {
            if (rs.next()) {
                name = rs.getString("name");
                return name;
            } else {
                return null;
            }

        }
    }

    public void setName(long id, String name) throws SQLException {

        setName.setString(1, name);
        setName.setLong(2, id);
        setName.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteById.setLong(1, id);
        deleteById.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        List<Client> result = new LinkedList<>();
        try (ResultSet rs = listAll.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                result.add(client);
            }
        }
        return result;
    }

}
