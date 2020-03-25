package edu.eci.arsw.parcial.coronavirusAPITest.persistence;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class CoronavirusStatusCache implements CoronavirusCache {
    private ConcurrentHashMap<String, CoronavirusKey> coroCache;

    public CoronavirusStatusCache() {
        coroCache = new ConcurrentHashMap<>();
    }

    public boolean exist(String key){
        CoronavirusKey temporal = coroCache.get(key);
        if (temporal!=null && LocalDateTime.now().isAfter(temporal.tiempoDeCreacion.plusMinutes(5))){
            coroCache.remove(key);
        }
        return coroCache.get(key)!=null;
    }

    public void loadC(String key,String data){
        CoronavirusKey temporal = new CoronavirusKey(data);
        coroCache.put(key,temporal);
    }

    
    public CoronavirusKey returnC(String key){
        return coroCache.get(key);
    }
    
}
