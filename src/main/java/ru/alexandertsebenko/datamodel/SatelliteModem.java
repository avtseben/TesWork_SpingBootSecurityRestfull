package ru.alexandertsebenko.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SatelliteModem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String ipAddress;
    private String name;
    private String model;

    protected SatelliteModem() {}

    public SatelliteModem(String ipAddress, String name, String model) {
	this.ipAddress = ipAddress;
	this.name = name;
	this.model = model;
    }

    public Long getId(){
	return id;
    }

    public void setId(){
	this.id = id;
    }

    public String getIpAddress(){
	return ipAddress;
    }

    public void setIpAddress(){
	this.ipAddress = ipAddress;
    }

    public String getName(){
	return name;
    }

    public void setName(){
	this.name = name;
    }

    public String getModel(){
	return model;
    }

    public void setModel(){
	this.model = model;
    }

    @Override
    public String toString() {
	return String.format(
		"SateliteModem[id=%d, ipAddress='%s', name='%s', model='%s']",
		id, ipAddress, name, model);
    }

}
