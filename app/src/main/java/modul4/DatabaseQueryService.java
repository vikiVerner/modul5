package modul4;

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

        List<LongestProject> longestProjects = new ArrayList<>();
        String  sql = ReadSQLFile.getSqlFromFile("./sql/find_longest_project.sql");
        Statement st = database.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            LongestProject result = new LongestProject(rs.getInt("id"), rs.getInt("month_count"));
            longestProjects.add(result);
        }
        rs.close();
        st.close();
        System.out.println("longestProjects = " + longestProjects);
        return longestProjects;

    }

    public List<MaxProjectsCountClient> findMaxProjectsClient(Database database) throws SQLException {
        List<MaxProjectsCountClient> projectsCount = new ArrayList<>();
        String  sql = ReadSQLFile.getSqlFromFile("./sql/find_max_projects_client.sql");

        Statement st = database.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            MaxProjectsCountClient result = new MaxProjectsCountClient(rs.getString("name"), rs.getInt("id"));
            projectsCount.add(result);
        }
        rs.close();
        st.close();
        System.out.println("projectsCount = " + projectsCount);
        return projectsCount;

    }

    public List<SalaryWorker> findMaxSalaryWorker(Database database) throws SQLException {
        List<SalaryWorker> salaryWorkers = new ArrayList<>();
        String  sql = ReadSQLFile.getSqlFromFile("./sql/find_max_salary_worker.sql");
        Statement st = database.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            SalaryWorker result = new SalaryWorker(rs.getString("name"), rs.getInt("salary"));
            salaryWorkers.add(result);
        }
        rs.close();
        st.close();
        System.out.println("salaryWorkers = " + salaryWorkers);
        return salaryWorkers;

    }

    public List<YoungestEldestWorker> printProjectPrices(Database database) throws SQLException {
        List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();
        String  sql = ReadSQLFile.getSqlFromFile("./sql/find_youngest_eldest_workers.sql");
        Statement st = database.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            YoungestEldestWorker result = new YoungestEldestWorker(rs.getString("type"), rs.getString("name"), rs.getDate("birthday"));
            youngestEldestWorkers.add(result);
        }
        rs.close();
        st.close();
        System.out.println("youngestEldestWorkers = " + youngestEldestWorkers);
        return youngestEldestWorkers;

    }
}
