$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/get_filmovi",
        dataType: "json",
        success: function(data){
            console.log("SUCCESS : ", data);
            for(var i = 0; i < data.length; i++){
                var row = "<tr>";
                row += "<td>" + data[i]['naziv'] + "</td>";
                row += "<td>" + data[i]['opis'] + "</td>";
                row += "<td>" + data[i]['zanr'] + "</td>";
                row += "<td>" + data[i]['trajanje'] + "</td>";
                row += "<td>" + data[i]['prosecnaocena'] + "</td>";
                var btn = "<button class='btnSeeMore' id = " + data[i]['id'] + ">Detalji</button>";
                row += "<td>" + btn + "</td>";

                $('#moviesTable').append(row);
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
            alert("Usao sam u error");
        }
    });
});

$(document).on('click', '.btnSeeMore', function(){
    $('#movies').hide();
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/get_projekcije/" + this.id,
        dataType: "json",
        success: function(data){
            console.log("SUCCESS : ", data);
            //alert("Broj projekcija: " + data.length);
            var row = "<tr>";
            row += "<th>Naziv</th>";
            row += "<th>Opis</th>";
            row += "<th>Žanr</th>";
            row += "<th>Trajanje</th>";
            row += "<th>Prosečna ocena</th>";
            row += "<th>Cena</th>";
            row += "<th>Datum i vreme</th>";
            row += "<th>Bioskop</th>";
            row += "<th>Sala</th>";
            row += "<th>Broj slobodnih karata</th></tr>";
            $('#oneFilmTable').append(row);
            for(var i = 0; i < data.length; i++){
                //row += "<tr>";
                var row1 = "<tr>";
                row1 += "<td>" + data[i]['naziv'] + "</td>";
                row1 += "<td>" + data[i]['opis'] + "</td>";
                row1 += "<td>" + data[i]['zanr'] + "</td>";
                row1 += "<td>" + data[i]['trajanje'] + "</td>";
                row1 += "<td>" + data[i]['prosecnaocena'] + "</td>";
                row1 += "<td>" + data[i]['cena'] + "</td>";
                row1 += "<td>" + data[i]['datumvreme'] + "</td>";
                row1 += "<td>" + data[i]['bioskop'] + "</td>";
                row1 += "<td>" + data[i]['sala'] + "</td>";
                row1 += "<td>" + data[i]['broj_preostalih_mesta'] + "</td></tr>";
                $('#oneFilmTable').append(row1);
               }
        },
        error: function (data) {
            console.log("ERROR : ", data);
            alert("Usao sam u error");
        }
    });

    $("#oneFilm").show();
});

$(document).on('click', '.btnBack', function (){
    $('#oneFilm').hide();
    $('#oneFilmTable').text(" ");
    $('#movies').show();
});