package mx.pi5.localito.entity;

public class Products {
    private int id;
    private int stand_id;
    private String name;
    private String price;
    private String info;
    private String image;

    public Products(int sid, String name, String price, String description, String image) {
        this.stand_id = sid;
        this.name = name;
        this.price = price;
        this.info = description;
        this.image = image;
    }
}
