package mx.pi5.localito.Entidades;

//    ACTIVIDA DEL PI
//    cambiar nombre del package 'Modelos' y cambiar a 'Entidades'
//    Creo otro package 'Modelos'
//    Crear un JSON para convertir a entidades.
public class Cities {
    private int id;
    private String cCode;
    private String name;
    private float lon;
    private float lat;

    public Cities(int id, String cCode, String name, float lon, float lat) {
        this.id = id;
        this.cCode = cCode;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
