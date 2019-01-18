package me.dlovan.findhotelsonmap;


public class Hotels {

    private int ID;
    private String Name;
    private String Address;
    private String Phone;
    private Double Lat;
    private Double Lng;

    public Hotels() {
    }

    public Hotels(int id, String name, String address, String phone, Double lat, Double lng) {

        this.ID = id;
        this.Name = name;
        this.Address = address;
        this.Phone = phone;
        this.Lat = lat;
        this.Lng = lng;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLng() {
        return Lng;
    }

    public void setLng(Double lng) {
        Lng = lng;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
