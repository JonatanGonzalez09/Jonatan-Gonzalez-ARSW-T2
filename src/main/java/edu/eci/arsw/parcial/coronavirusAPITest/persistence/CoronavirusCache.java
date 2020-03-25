package edu.eci.arsw.parcial.coronavirusAPITest.persistence;

public interface CoronavirusCache {
    public abstract boolean exist(String key);
    public abstract void loadC(String key, String data);
    public abstract CoronavirusKey returnC(String key);
}