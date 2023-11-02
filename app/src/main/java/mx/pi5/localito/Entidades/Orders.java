package mx.pi5.localito.Entidades;

public class Orders {

    private int id;
    private int sid;
    private int stid;
    private String state;

    public Orders(int id, int sid, int stid, String state) {
        this.id = id;
        this.sid = sid;
        this.stid = stid;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
