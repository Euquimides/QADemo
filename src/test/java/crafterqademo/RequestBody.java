package crafterqademo;

public class RequestBody {
    private Integer id;
    private String name;
    private String desc;

    public RequestBody(Integer id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", desc='" + this.desc + '\'' +
                '}';
    }
}