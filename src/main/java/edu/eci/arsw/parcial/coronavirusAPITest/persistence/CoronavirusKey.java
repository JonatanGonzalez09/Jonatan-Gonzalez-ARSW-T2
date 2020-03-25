package edu.eci.arsw.parcial.coronavirusAPITest.persistence;

import java.time.LocalDateTime;

public class CoronavirusKey {
    public String data;
    public LocalDateTime tiempoDeCreacion;

    public CoronavirusKey(String data) {
        this.data = data;
        tiempoDeCreacion = LocalDateTime.now();
    }

	public String getData(){
        return data;
    }
    
}