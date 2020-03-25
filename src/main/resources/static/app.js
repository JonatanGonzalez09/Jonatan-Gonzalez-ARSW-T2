var app = (function () {
    var solopaises;
    var map;
    var init = function () {
        apiClient.getallcountries(tabla)
    }
    var tabla = function(data){
        var table = $('#table');
        var datos= JSON.parse(data);
        var paises =datos.data.covid19Stats;
        solopaises= sortJSON(Object.values(groupBy(paises,'country')),'deaths');
        table.empty();
        var contentTable =`
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Contry</th>
                    <th>Num deaths</th>
                    <th>Num confirmed</th>
                    <th>Num recovered</th>
                </tr>
            </thead>
            <tbody>
      `;
        for (var i = 0; i < solopaises.length; i++) {
            contentTable +=`
          <tr>
              <td><a href= "#" onclick="app.getContry('`+ solopaises[i].country+`','`+solopaises[i].deaths+`','`+solopaises[i].confirmed+`','`+solopaises[i].recovered+`')">` + solopaises[i].country + `</a></td>
              <td>` + solopaises[i].deaths + `</td>
              <td>` + solopaises[i].confirmed + `</td>
              <td>` + solopaises[i].recovered + `</td>         
          </tr>
          `;
        }
        contentTable+='</tbody> </table>';
        table.append(contentTable);
    }

    var getContry = function (pais,deaths,confirmed,recovered) {
        var table = $('#resum');
        table.empty();
        $('#title').empty();
        $('#title').html(`<h1 class="text-center">`+pais+`</h1>`);
        var contentTable =`
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Birth Day</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Num deaths</td>
                    <td>`+deaths+`</td>
                </tr>    
                <tr>
                    <td>Num confirmed</td>
                    <td>`+confirmed+`</td>
                </tr>    
                <tr>
                    <td>Num recovered</td>
                    <td>`+recovered+`</td>
                </tr> 
            </tbody>
        </table>       
        `;
        table.append(contentTable);
        apiClient.getCountry(tablaC,pais)
    }
/* keyId */
    var tablaC = function(data){
        var table = $('#contry');
        var datos= JSON.parse(data);
        var paises =datos.data.covid19Stats;
        solopaises= sortJSON(paises,'deaths');
        console.log(solopaises)
        console.log("arreglados")
        table.empty();
        var contentTable =`
        <table class="table table-bordered">
        <thead>
          <tr>
              <th>Province</th>
              <th>Num deaths</th>
              <th>Num confirmed</th>
              <th>Num recovered</th>
          </tr>
      </thead>
      </tbody>
      `;
        for (var i = 0; i < solopaises.length; i++) {
            contentTable+=`
          <tr>
              <td>` + solopaises[i].province + `</td>
              <td>` + solopaises[i].deaths + `</td>
              <td>` + solopaises[i].confirmed + `</td>
              <td>` + solopaises[i].recovered + `</td>         
          </tr>
          `;
        }
        contentTable+='</tbody> </table>';
        table.append(contentTable);
    }        

    var groupBy = function (miarray, prop) {
        return miarray.reduce(function(groups, item) {
            var val = item[prop];
            groups[val] = groups[val] || {country: item.country, deaths: 0, confirmed: 0,recovered: 0};
            groups[val].deaths += item.deaths;
            groups[val].confirmed += item.confirmed;
            groups[val].recovered += item.recovered;
            return groups;
        }, {});
    }
    function sortJSON(data, key) {
        return data.sort(function (a, b) {
            var x = a[key], y = b[key];
            return ((x > y) ? -1 : ((x < y) ? 1 : 0));
        });
    }
    return {
        init:init,
        getContry:getContry
    }

})();