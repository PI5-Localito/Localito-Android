package mx.pi5.localito.entity;

public class ProductsInOrder {
    private int oid;
    private int pid;

    public ProductsInOrder(int oid, int pid) {
        this.oid = oid;
        this.pid = pid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
