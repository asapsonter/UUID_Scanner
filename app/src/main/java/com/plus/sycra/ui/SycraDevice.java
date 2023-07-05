package com.plus.sycra.ui;

public class SycraDevice {
    //declare instances
    private String name;
    private String uuid;

    //declare constructor for name & uuid
    public SycraDevice( String name, String uuid){
        this.name = name;
        this.uuid = uuid;
    }
    //getters and setters
    public void setName(String name) {this.name = name;}
    public String getName() {    return name;}
    public void setUuid(String uuid) {this.uuid = uuid;}
    public String getUuid() { return uuid;}

}
