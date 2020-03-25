package edu.eci.arsw.parcial.coronavirusAPITest.services;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import edu.eci.arsw.parcial.coronavirusAPITest.persistence.CoronavirusCache;

@Service
@Configuration
public class CoronavirusStatsServices implements CoronavirusService{
    
    @Autowired
    private HttpConnectionService covidHttp;

    @Autowired
    private CoronavirusCache covidCache;

    /**
     * Retorna todas las ciudades que se encuentran en la API.
     */
    @Override
    public String getAllCountries() throws CoronavirusException {
        String data;
        try {
            data = covidHttp.getAllCountries();
        
        } catch(UnirestException e){
            throw new CoronavirusException("Country not found");
        }
        return data;
    }

    /**
     * Retorna una ciudad especificada
     * @param Ciudad que se debe buscar en la API.
     * @return Retorna la ciudad que se mando por parametro.
     */
    @Override
    public String getCountry(String name) throws CoronavirusException {
        String data;
        try {
            if (covidCache.exist(name)) {
                data = covidCache.returnC(name).getData();
            } else {
                data = covidHttp.getCountry(name);
                covidCache.loadC(name, data);
            }
        
        } catch(UnirestException e){
            throw new CoronavirusException("Country not found");
        }
        return data;
    }

}