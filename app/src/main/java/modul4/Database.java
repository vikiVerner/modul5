package modul4;

import java.sql.*;


public class Database {
    private static final Database INSTANCE = new Database();
    private Connection connection;
    private Database() {
        try{
            String connectionUrl = "jdbc:h2:./test";
            connection = DriverManager.getConnection(connectionUrl);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public static Database getInstance(){

        return INSTANCE;
    }
    public Connection getConnection(){

        return connection;
    }

    public int executeUpdate(String sql){
        try(Statement st = connection.createStatement();){
            return st.executeUpdate(sql);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return  -1;
    }


    public void close(){
    try {
        connection.close();
    }
    catch (Exception ex){
        ex.printStackTrace();
    }
    }

}
