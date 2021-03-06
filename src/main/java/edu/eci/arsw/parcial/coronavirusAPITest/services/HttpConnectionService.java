package edu.eci.arsw.parcial.coronavirusAPITest.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class HttpConnectionService {
    /**
     * 
     * @return Trae el JSON de todos los paises.
     * @throws UnirestException En caso de que no exista los paises.
     */
    public String getAllCountries() throws UnirestException {
        String jsonString = null;

        HttpResponse < String > response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
            .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
            .header("x-rapidapi-key", "4a71d98902mshebb28f37a4e303ep14be52jsn5e65fb31a9e2")
            .asString();
        jsonString = response.getBody();

        return jsonString;
    }

    /**
     * 
     * @param name Nombre del pais a buscar.
     * @return El pais encontrado.
     * @throws UnirestException En caso de que no se encuentre el pais.
     */
    public String getCountry(String name) throws UnirestException {
        String jsonString = null;
        
        HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country="+name)
        .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
        .header("x-rapidapi-key", "4a71d98902mshebb28f37a4e303ep14be52jsn5e65fb31a9e2")
        .asString();
        
        jsonString = response.getBody();
        
        return jsonString;
    }
    /**
     * 
     * @param name Nombre del pais a buscar.
     * @return El Json con las cordenadas de la busqueda por el pais
     * @throws UnirestException En caso de que no se encuentre el pais.
     */
    public String getCordenadaContry(String name) throws UnirestException {
        String jsonString = null;
        
        HttpResponse<String> response = Unirest.get("https://restcountries-v1.p.rapidapi.com/name/"+name)
        .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
        .header("x-rapidapi-key", "4a71d98902mshebb28f37a4e303ep14be52jsn5e65fb31a9e2")
        .asString();
        
        jsonString = response.getBody();
        
        return jsonString;
    }

    /**
     * 
     * @param name Nombre del pais y provincia a buscar.
     * @return El Json con las cordenadas de la busqueda por el pais y provincia
     * @throws UnirestException En caso de que no se encuentre el pais y provincia.
     */
    public String getCordenadaProvince(String name) throws UnirestException {
        String jsonString = null;
        
        HttpResponse<String> response = Unirest.get("https://trueway-geocoding.p.rapidapi.com/Geocode?language=en&country=US&address="+name)
            .header("x-rapidapi-host", "trueway-geocoding.p.rapidapi.com")
            .header("x-rapidapi-key", "b4115ee3e5msh1d0a76872e5ca5cp1b2332jsn94d2aa2481c3")
            .asString();
        
        jsonString = response.getBody();
        
        return jsonString;
    }
}