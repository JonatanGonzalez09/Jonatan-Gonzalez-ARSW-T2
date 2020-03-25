package edu.eci.arsw.parcial.coronavirusAPITest.persistence;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class CoronavirusStatusCache implements CoronavirusCache {
    private ConcurrentHashMap<String, CoronavirusKey> coroCache;

    /**
     * Constructor de la clase CoronavirusStatusCache
     */
    public CoronavirusStatusCache() {
        coroCache = new ConcurrentHashMap<>();
    }

    /**
     * @param key Es el nombre del pais que se usara como llave para buscarlo en cache.
     * @return Retorna si el aeropuerto ya se encuentra en caché
     */
    public boolean exist(String key){
        CoronavirusKey temporal = coroCache.get(key);
        if (temporal!=null && LocalDateTime.now().isAfter(temporal.tiempoDeCreacion.plusMinutes(5))){
            coroCache.remove(key);
        }
        return coroCache.get(key)!=null;
    }

    /**
     *
     * @param key Es el nombre del pais el cual se esta buscando. 
     * @param data String que se guardara en cache.
     */
    public void loadC(String key,String data){
        CoronavirusKey temporal = new CoronavirusKey(data);
        coroCache.put(key,temporal);
    }

    /**
     * @param key Es el nombre del pais el cual se esta buscando.
     * @return Devuelve el objeto JSON en un String.
     */
    public CoronavirusKey returnC(String key){
        return coroCache.get(key);
    }
    
}
