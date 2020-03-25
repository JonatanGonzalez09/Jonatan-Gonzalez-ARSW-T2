package edu.eci.arsw.parcial.coronavirusAPITest.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.parcial.coronavirusAPITest.CoronavirusApiTestApplication;
import edu.eci.arsw.parcial.coronavirusAPITest.services.CoodenadaServer;
import edu.eci.arsw.parcial.coronavirusAPITest.services.CoronavirusException;
import edu.eci.arsw.parcial.coronavirusAPITest.services.CoronavirusStatsServices;

@RestController
public class CoronavirusStatsController {
    @Autowired
    private CoronavirusStatsServices covidServices;
    
    @Autowired
    private CoodenadaServer  coodenadaServer;

    /**
     * Funcion que me muestra todos los paises.
     * @return El JSON de todos los paises.
     */
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

    /**
     * Funcion que me retorna el pais especificado.
     * @param name El pais a buscar. 
     * @return El JSON del pais seleccionado.
     */
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

    /**
     * Funcion que me retorna las coordenadas de un pais.
     * @param name El pais a buscar. 
     * @return El JSON de las coordenadas del pais seleccionado.
     */
    @RequestMapping(value = "/getcoordenada/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> getCoordenada(@PathVariable ("name")String name){
        try {
            String consulta = coodenadaServer.getCoordenada(name);
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } catch (CoronavirusException ex) {
            Logger.getLogger(CoronavirusApiTestApplication.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Funcion que me retorna las coordenadas de un pais y provincia.
     * @param name El pais a buscar. 
     * @return El JSON de las coordenadas del pais y provincia seleccionado.
     */
    @RequestMapping(value = "/getprovincia/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> getCordenadaProvince(@PathVariable ("name")String name){
        try {
            String consulta = coodenadaServer.getCordenadaProvince(name);
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } catch (CoronavirusException ex) {
            Logger.getLogger(CoronavirusApiTestApplication.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}