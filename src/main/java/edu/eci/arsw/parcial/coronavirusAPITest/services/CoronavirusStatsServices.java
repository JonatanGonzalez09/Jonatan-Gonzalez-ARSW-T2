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

    @Override
    public String getCountry(String name) throws CoronavirusException {
        String data;
        try {
            data = covidHttp.getCountry(name);

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