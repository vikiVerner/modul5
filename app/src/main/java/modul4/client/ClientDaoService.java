package modul4.client;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClientDaoService {
    private PreparedStatement createSt;
    private PreparedStatement getById;
    private PreparedStatement setName;
    private PreparedStatement deleteById;
    private PreparedStatement getAll;
    private PreparedStatement selectMaxId;


    public ClientDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement("INSERT INTO client (name) VALUES ?");
        getById = connection.prepareStatement("SELECT name FROM client WHERE id = ?");
        setName = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
        deleteById = connection.prepareStatement("DELETE FROM client WHERE id = ?");
        getAll = connection.prepareStatement("SELECT id,name FROM client ");
        selectMaxId = connection.prepareStatement("SELECT MAX(id) AS maxId FROM client");

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
        nameValidation(name);
        createSt.setString(1, name);
        createSt.executeUpdate();
        return maxId();

    }

    public String getById(long id) throws SQLException {
        String name = null;
        if(idValidation(id)) {
            getById.setLong(1, id);
            try (ResultSet rs = getById.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalArgumentException("The Client with id " + id + " does not exist in the database");
                }
                name = rs.getString("name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else {
                throw new IllegalArgumentException(id + " введено не коректно");
        }
            return name;
        }


    public void setName(long id, String name) throws SQLException {
        nameValidation(name);
        if(idValidation(id)){
            setName.setString(1, name);
            setName.setLong(2, id);
            final int i = setName.executeUpdate();
            if(i<=0){
                throw new IllegalArgumentException("The Client with id " + id + " does not exist in the database");
            }
        }else{
            throw new IllegalArgumentException(id + " введено не коректно");
        }

    }

    public void deleteById(long id) throws SQLException {
        if (idValidation(id)) {
            deleteById.setLong(1, id);
            final int i = deleteById.executeUpdate();
            if (i <= 0) {
                throw new IllegalArgumentException("The Client with id " + id + " does not exist in the database");
            }
        } else {
            throw new IllegalArgumentException(id + " введено не коректно");
        }

    }

    public List<Client> listAll() throws SQLException {
        List<Client> result = new LinkedList<>();
        try (ResultSet rs = getAll.executeQuery()) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                result.add(client);
            }
        }
        return result;
    }
    private void nameValidation(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        if (name.length() > 1000 || name.length() < 2) {
            throw new IllegalArgumentException("The name is incorrect, the number of characters in" +
                    " the name must not be less than 2 or more than 1000");
        }
    }

    private boolean idValidation(long id) throws IllegalArgumentException {
        return id > 0 && id == Long.parseLong(String.valueOf(id));
    }

}
