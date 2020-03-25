package edu.eci.arsw.parcial.coronavirusAPITest.services;

public interface CoronavirusService {
    public String getAllCountries() throws CoronavirusException;
    public String getCountry(String name) throws CoronavirusException;
}