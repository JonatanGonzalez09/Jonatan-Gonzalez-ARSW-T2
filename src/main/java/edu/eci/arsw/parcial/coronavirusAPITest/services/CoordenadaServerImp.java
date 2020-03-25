package edu.eci.arsw.parcial.coronavirusAPITest.services;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
@Configuration
public class CoordenadaServerImp implements CoodenadaServer {
    
    @Autowired
    private HttpConnectionService covidHttp;
    /**
     * Retorna el JSON de las coordenadas de un pais 
     * @param name que se debe buscar en la API.
     * @return Retorna el Json de las coordenadas.
     */
    @Override
    public String getCoordenada(String name) throws CoronavirusException {
        String data;
        try {
                data = covidHttp.getCordenadaContry(name);
        } catch(UnirestException e){
            throw new CoronavirusException("Country not found");
        }
        return data;
    }

}