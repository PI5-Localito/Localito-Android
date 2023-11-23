package mx.pi5.localito.entity;

public class Product {
    public int id;
    public int stand_id;
    public String name;
    public String price;
    public String info;
    public String image;

    public Product(int sid, String name, String price, String description, String image) {
        this.stand_id = sid;
        this.name = name;
        this.price = price;
        this.info = description;
        this.image = image;
    }
}
