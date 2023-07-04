package modul4;

import java.util.Date;

public class YoungestEldestWorker {
    private String type;
    private String name;
    private Date date;

    public YoungestEldestWorker(String type, String name, Date date) {
        this.type = type;
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "YoungestEldestWorker{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
