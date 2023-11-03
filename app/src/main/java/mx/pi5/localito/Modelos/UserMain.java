package mx.pi5.localito.Modelos;

import mx.pi5.localito.Entitys.User;

public class UserMain {

    public static void main(String[] args){
        try{
            // creamos el objeto User

            User usuario = new User(1,"malejandre@ucol.mx",
                                "contrasena123", "123456789",
                                    "Miguel","Alejandre");

            // Convertir a JSON
            String json = JsonConverter.convertToJson(usuario);
            System.out.println("Objeto convertido a json:\n"+ json);

            // Convertir desde JSON
            User usuarioDesdeJson = JsonConverter.convertFromJson(json, User.class);
            System.out.println("Objeto convertido desde JSON:\n" + usuarioDesdeJson.toString());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
