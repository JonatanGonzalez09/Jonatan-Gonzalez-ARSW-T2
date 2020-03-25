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
        coordenada: function(callback,name) {
            jQuery.ajax({
                url: 'https://jonatan-gonzalez-arsw-t2.herokuapp.com/getcoordenada/'+name,
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        }
    };
})();