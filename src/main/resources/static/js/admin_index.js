$(document).ready(function (){
    $.ajax({

    });
});

function odjava(){
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/odjava",
        dataType: "json",
        success: function () {
            window.location.href = "index.html";
        },
        error: function(){
            alert("Greška!");
        }
    });
}

function provera(){
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/index_provera",
        dataType: "json",
        success: function (data) {
            if(!data['ulogovan']) window.location.href = "index.html";
            if(data['uloga'] != "ADMIN") window.location.href = "no_access.html";
        },
        error: function (data){
            alert("Greška!");
        }
    });
}
