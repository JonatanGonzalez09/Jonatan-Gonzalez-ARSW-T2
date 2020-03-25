package edu.eci.arsw.parcial.coronavirusAPITest.services;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class CoronavirusStatsServices implements CoronavirusService{
    
    @Autowired
    private HttpConnectionService covidHttp;

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

}