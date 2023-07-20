package modul4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadSQLFile {
    public static  String getSqlFromFile(String filePath) {
        String sql="";
        try {
            sql=String.join("\n", Files.readAllLines(Paths.get(filePath) ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
