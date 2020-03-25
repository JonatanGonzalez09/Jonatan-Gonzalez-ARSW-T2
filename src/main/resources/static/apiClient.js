/* Link Heroku app:  https://jonatan-gonzalez-arsw-t2.herokuapp.com */
apiClient = (function() {
    return {
        getallcountries: function(callback) {
            jQuery.ajax({
                url: 'https://jonatan-gonzalez-arsw-t2.herokuapp.com/getallcountries',
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        },
        getCountry: function(callback,name) {
            jQuery.ajax({
                url: 'https://jonatan-gonzalez-arsw-t2.herokuapp.com/getcountry/'+name,
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        },

        coordenada: function(callback, name){
            var request_url = 'https://api.opencagedata.com/geocode/v1/json?key=8d388400a1cb414589486916a02166ed&q='+ name+'&pretty=1&no_annotations=1';
            var request = new XMLHttpRequest();
            request.open('GET', request_url, true);
            request.onload = function() {
                if (request.status == 200){ 
                    var data = JSON.parse(request.responseText);
                    callback(data.results[0]);
                } else {
                    console.log("server error");
                }
            };
            request.onerror = function() {
                console.log("unable to connect to server");        
            };
    
            request.send();
        
        }
    };
})();