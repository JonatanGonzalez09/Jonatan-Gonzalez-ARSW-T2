package edu.eci.arsw.parcial.coronavirusAPITest.services;

public interface CoodenadaServer {
    public String getCoordenada(String name) throws CoronavirusException;
    public String getCordenadaProvince(String name) throws CoronavirusException;
}