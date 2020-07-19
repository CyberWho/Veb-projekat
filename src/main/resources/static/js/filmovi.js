$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/get_filmovi",
        dataType: "json",
        success: function(data){
            console.log("SUCCESS : ", data);
            for(var i = 0; i < data.length; i++){
                var row = "<tr>";
                row += "<td>" + data[i]['naziv'] + "</td>";
                row += "<td>" + data[i]['zanr'] + "</td>";
                row += "<td>" + data[i]['trajanje'] + "</td>";
                row += "<td>" + data[i]['prosecna_ocena'] + "</td>";
                var btn = "<button class='btnSeeMore' id = " + data[i]['id'] + ">See more</button>";
                row += "<td>" + btn + "</td>";

                $('#movies').append(row);
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});
