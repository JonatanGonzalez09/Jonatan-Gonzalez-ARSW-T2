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

    };
})();