package modul4;

public class MaxProjectsCountClient {
    private String name;
    private int id;

    public MaxProjectsCountClient(String name, int projectCount) {
        this.name = name;
        this.id = projectCount;
    }

    @Override
    public String toString() {
        return "MaxProjectsCountClient{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
