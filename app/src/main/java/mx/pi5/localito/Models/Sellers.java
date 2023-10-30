package mx.pi5.localito.Models;

public class Sellers {

    private int id;
    private int uid;
    private boolean state;

    public Sellers(int id, int uid, boolean state) {
        this.id = id;
        this.uid = uid;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
