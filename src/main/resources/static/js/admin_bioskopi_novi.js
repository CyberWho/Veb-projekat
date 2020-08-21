

function odjava(){
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/odjava",
        dataType: "json",
        success: function () {
            window.location.href = "index.html";
        },
        error: function(){
            alert("Greška! (admin_index.js/odjava)");
        }
    });
}

function provera(){
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/index_provera",
        dataType: "json",
        success: function (data) {
            alert(data['uloga']);
            if(!data['ulogovan']) window.location.href = "index.html";
            else if(data['uloga'] !== "ADMIN") window.location.href = "no_access.html";
        },
        error: function (){
            alert("Greška! (admin_index.js/provera)");
        }
    });
}

function formToJSON( naziv, adresa, br_telefona, email, inicijalni_menadzer) {
    return JSON.stringify({
        "naziv": naziv,
        "adresa": adresa,
        "br_telefona": br_telefona,
        "email": email,
        "inicijalni_menadzer": inicijalni_menadzer
    });
}