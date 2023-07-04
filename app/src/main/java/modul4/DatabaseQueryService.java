package modul4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {

    public static void main(String[] args) throws SQLException {
        Database database = Database.getInstance();

        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        databaseQueryService.findLongestProject(database);
        databaseQueryService.findMaxProjectsClient(database);
        databaseQueryService.findMaxSalaryWorker(database);
        databaseQueryService.printProjectPrices(database);
    }

    public List<LongestProject> findLongestProject(Database database) throws SQLException {

        try {
            List<LongestProject> longestProjects = new ArrayList<>();
            String url = "C:\\Users\\alexa\\IdeaProjects\\modul4\\sql\\find_longest_project.sql";
            String sql = String.join("\n", Files.readAllLines(Paths.get(url)));
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                LongestProject result = new LongestProject(rs.getInt("id"), rs.getInt("month_count"));
                longestProjects.add(result);
            }
            rs.close();
            st.close();
            return longestProjects;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MaxProjectsCountClient> findMaxProjectsClient(Database database) throws SQLException {
        try {
            List<MaxProjectsCountClient> projectsCount = new ArrayList<>();
            String url = "C:\\Users\\alexa\\IdeaProjects\\modul4\\sql\\find_max_projects_client.sql";
            String sql = String.join("\n", Files.readAllLines(Paths.get(url)));
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                MaxProjectsCountClient result = new MaxProjectsCountClient(rs.getString("name"), rs.getInt("id"));
                projectsCount.add(result);
            }
            rs.close();
            st.close();
            return projectsCount;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SalaryWorker> findMaxSalaryWorker(Database database) throws SQLException {
        try {
            List<SalaryWorker> salaryWorkers = new ArrayList<>();
            String url = "C:\\Users\\alexa\\IdeaProjects\\modul4\\sql\\find_max_salary_worker.sql";
            String sql = String.join("\n", Files.readAllLines(Paths.get(url)));
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SalaryWorker result = new SalaryWorker(rs.getString("name"), rs.getInt("salary"));
                salaryWorkers.add(result);
            }
            rs.close();
            st.close();
            return salaryWorkers;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<YoungestEldestWorker> printProjectPrices(Database database) throws SQLException {
        try {
            List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();
            String url = "C:\\Users\\alexa\\IdeaProjects\\modul4\\sql\\find_youngest_eldest_workers.sql";
            String sql = String.join("\n", Files.readAllLines(Paths.get(url)));
            Statement st = database.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                YoungestEldestWorker result = new YoungestEldestWorker(rs.getString("type"), rs.getString("name"), rs.getDate("birthday"));
                youngestEldestWorkers.add(result);
            }
            rs.close();
            st.close();
            return youngestEldestWorkers;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
