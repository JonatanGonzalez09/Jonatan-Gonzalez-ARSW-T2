var app = (function () {
    var solopaises;
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
              <td>`+ solopaises[i].country+`</td>
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
        init:init
    }

})();