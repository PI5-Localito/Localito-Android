package mx.pi5.localito.Modelos;
import mx.pi5.localito.Entitys.Products;

public class ProductsMain {
    public static void main(String[] args) {
        try {
            //Creamos un objeto products
            Products products = new Products(1, 1, "camisa", 455.5F, "Camisa talla grande", "/ubicacion/");
            //convertir a json
            String json = JsonConverter.convertToJson(products);
            System.out.println("OBjeto convertido ajson:\n" + json);

            // convertir desde json
            Products productsfromJson = JsonConverter.convertFromJson(json, Products.class);
            System.out.println("Object converted from json\n" + productsfromJson);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
