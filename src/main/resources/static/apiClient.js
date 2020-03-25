apiClient = (function() {
    return {
        getallcountries: function(callback) {
            jQuery.ajax({
                url: 'http://localhost:8080/getallcountries',
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        },
        getCountry: function(callback,name) {
            jQuery.ajax({
                url: 'http://localhost:8080/getcountry/'+name,
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        },
    };
})();