package ru.alexandertsebenko.controller;

import ru.alexandertsebenko.db.*;
import ru.alexandertsebenko.datamodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class SatelliteModemController {

    @Autowired
    private SatelliteModemRepository repository;

    //Получаем список всех объектов
    //curl -H "Content-Type: application/json" -u user:userpass -X GET  http://localhost:8080/satmodems
    @RequestMapping(value = "/satmodems", method = RequestMethod.GET)
    public ResponseEntity<List<SatelliteModem>> getAll(){
	List<SatelliteModem> list = repository.findAll();
	if(list.size() > 0 ) {
	    return new ResponseEntity<List<SatelliteModem>>(list, HttpStatus.OK);
	} else {
	    return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
    }

    //Передаём объект json(Id нам не известен). Объект сохраниться в БД, контроллер вернёт 
    //сохранённый объект, содержащий поле Id 
    @RequestMapping(value = "/satmodems", method = RequestMethod.POST)
    public ResponseEntity<SatelliteModem> postSatModem(@RequestBody SatelliteModem satModem){

	repository.save(satModem);

        return new ResponseEntity<SatelliteModem>(satModem, HttpStatus.OK);
    }

    //Передаём объект json c Id. В БД этот объект обновляется 
    //curl -H "Content-Type: application/json" -u admin:adminpass -d '{"id":3,"ipAddress":"172.16.0.1","name":"sat1","model":"VSAT5"}' -X PUT  http://localhost:8080/satmodems
    @RequestMapping(value = "/satmodems", method = RequestMethod.PUT)
    public ResponseEntity updateSatModem(@RequestBody SatelliteModem satModem){

	repository.save(satModem);

        return new ResponseEntity(HttpStatus.OK);
    }

    //Передаём объект json c Id. На стороне БД объект удаляется 
    //curl -H "Content-Type: application/json" -u admin:adminpass -d '{"id":3,"ipAddress":"172.16.0.1","name":"sat1","model":"VSAT5"}' -X DELETE  http://localhost:8080/satmodems
    @RequestMapping(value = "/satmodems", method = RequestMethod.DELETE)
    public ResponseEntity delSatModem(@RequestBody SatelliteModem satModem){

	repository.delete(satModem);

        return new ResponseEntity(HttpStatus.OK);
    }

}
