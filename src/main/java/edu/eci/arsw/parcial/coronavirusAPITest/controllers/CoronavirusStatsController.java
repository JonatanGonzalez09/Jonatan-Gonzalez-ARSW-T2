package edu.eci.arsw.parcial.coronavirusAPITest.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.parcial.coronavirusAPITest.CoronavirusApiTestApplication;
import edu.eci.arsw.parcial.coronavirusAPITest.services.CoronavirusException;
import edu.eci.arsw.parcial.coronavirusAPITest.services.CoronavirusStatsServices;

@RestController
public class CoronavirusStatsController {
    @Autowired
    private CoronavirusStatsServices covidServices;

    @RequestMapping(value = "/getallcountries",method = RequestMethod.GET)
    public ResponseEntity<?> getallContries(){
        try {
            String consulta = covidServices.getAllCountries();
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } catch (CoronavirusException ex) {
            Logger.getLogger(CoronavirusApiTestApplication.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getcountry/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> getcontry(@PathVariable ("name")String name){
        try {
            String consulta = covidServices.getCountry(name);
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } catch (CoronavirusException ex) {
            Logger.getLogger(CoronavirusApiTestApplication.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}