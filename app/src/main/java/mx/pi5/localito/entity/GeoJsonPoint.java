package mx.pi5.localito.entity;

public class GeoJsonPoint {
    public String type = "Point";
    public double[] coordinates;

    GeoJsonPoint() {
        coordinates = new double[] {0, 0};
    }

    public void setCoordinates(double latitude, double longitude) {
        coordinates = new double[] {longitude, latitude};
    }

    public void setLatitude(double latitude) {
        coordinates[1] = latitude;
    }

    public void setLongitude(double longitude) {
        coordinates[0] = longitude;
    }
}
