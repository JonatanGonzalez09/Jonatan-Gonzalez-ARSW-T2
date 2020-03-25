package edu.eci.arsw.parcial.coronavirusAPITest.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class HttpConnectionService {
    public String getAllCountries() throws UnirestException {
        String jsonString = null;

        HttpResponse < String > response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
            .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
            .header("x-rapidapi-key", "4a71d98902mshebb28f37a4e303ep14be52jsn5e65fb31a9e2")
            .asString();
        jsonString = response.getBody();

        return jsonString;
    }
}