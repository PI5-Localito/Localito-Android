package mx.pi5.localito.Entitys;

public class Stands {
    private int id;
    private String uid;
    private String name;
    private String info;
    private int city;

    public Stands(int id, String uid, String name, String info, int city) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.info = info;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
