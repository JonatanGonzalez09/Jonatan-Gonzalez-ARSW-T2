apiClient = (function() {
    return {
        getallcountries: function(callback) {
            jQuery.ajax({
                url: 'https://jonatan-gonzalez-arsw-t2.herokuapp.com//getallcountries',
                success: function (result) {
                    callback(result);
                },
                async: true
            });
        },
    };
})();