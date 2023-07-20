package modul4;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DatabasePopulateService {
    private PreparedStatement insertWorker;
    private PreparedStatement insertClient;
    private PreparedStatement insertProject;
    private PreparedStatement insertProjectWorker;
    public static void main(String[] args) throws SQLException {
        String sql = "./sql/populate_db.sql";
        Database database = Database.getInstance();
        new DatabasePopulateService(database).insertValues(sql);

    }

    public DatabasePopulateService(Database database) throws SQLException {
        Connection connection = database.getConnection();
        insertWorker = connection.prepareStatement("INSERT INTO worker (name,birthday,level,salary) VALUES(?,?,?,?)");
        insertClient = connection.prepareStatement("INSERT INTO client (name) VALUES(?)");
        insertProject = connection.prepareStatement("INSERT INTO project (client_id,start_date,finish_date) VALUES(?,?,?)");
        insertProjectWorker = connection.prepareStatement("INSERT INTO project_worker(project_id,worker_id) VALUES(?,?)");
    }
    public void insertValues(String sql) throws SQLException {

        try (BufferedReader reader = new BufferedReader(new FileReader(sql))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name= data[0];
                    Date birthday =Date.valueOf(data[1].trim());
                    String level = data[2].trim();
                    int salary= Integer.parseInt(data[3].trim());

                    insertWorker.setString(1, name);
                    insertWorker.setDate(2, birthday);
                    insertWorker.setString(3, level);
                    insertWorker.setInt(4, salary);

                    insertWorker.executeUpdate();
                } else if(data.length == 1){
                    String name = data[0];
                    insertClient.setString(1,name);

                    insertClient.executeUpdate();
                } else if(data.length == 3){
                    int clientId = Integer.parseInt(data[0].trim());
                    Date startDate = Date.valueOf(data[1].trim());
                    Date finishDate = Date.valueOf(data[2].trim());
                    insertProject.setInt(1,clientId);
                    insertProject.setDate(2,startDate);
                    insertProject.setDate(3,finishDate);

                    insertProject.executeUpdate();

                } else {
                    int projectId = Integer.parseInt(data[0]);
                    int workerId = Integer.parseInt(data[1]);
                    insertProjectWorker.setInt(1, projectId);
                    insertProjectWorker.setInt(2, workerId);

                    insertProjectWorker.executeUpdate();
                }
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }

    }

}
