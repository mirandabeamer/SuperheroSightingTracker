/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var locations = [];


$(document).ready(function () {
    $('#sighting-search-button').click(function (event) {
        $('#errorMessages').empty();
        var category = $('#search-category').val();
        var searchTerm = $('#search-term').val();

        if (category == "" || category == null || searchTerm == "" || searchTerm == null) {
            $('#errorMessages').append("Both search term and category required")
        } else {

            if (category == "name") {
                locationName = $('#search-term').val();
            } else {
                locationName = "";
            }
            if (category == "state") {
                state = $('#search-term').val();
            } else {
                state = "";
            }
            if (category == "city") {
                city = $('#search-term').val();
            } else {
                city = "";
            }
            if (category == "zip") {
                zip = $('#search-term').val();
            } else {
                zip = "";
            }
            if (category == "date") {
                date = $('#search-term').val();
            } else {
                date = "";
            }
            if (category == "superhero") {
                superhero = $('#search-term').val();
            } else {
                superhero = "";
            }



            $.ajax({
                type: 'POST',
                url: 'search/sightings',
                data: JSON.stringify({
                    locationName: locationName,
                    state: state,
                    city: city,
                    zip: zip,
                    date: date,
                    superhero: superhero
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json',
                success: function (data) {
                    fillSightingTable(data);
                    $('#undo-search').show();
                },
                error: function () {
                    alert("error");
                }
            });
        }
    });


    $('#search-button').click(function (event) {
        $('#errorMessages').val('');
        var category = $('#hero-search-category').val();
        var searchTerm = $('#hero-search-term').val();

        if (category == "" || category == null || searchTerm == "" || searchTerm == null) {
            $('#errorMessages').append("Both search term and category required")
        } else {
            if (category == "name") {
                superhero_name = $('#hero-search-term').val();
            } else {
                superhero_name = "";
            }
            if (category == "type") {
                herotype = $('#hero-search-term').val();
            } else {
                herotype = "";
            }
            if (category == "organization") {
                organization = $('#hero-search-term').val();
            } else {
                organization = "";
            }
            if (category == "superpower") {
                superpower = $('#hero-search-term').val();
            } else {
                superpower = "";
            }





            $.ajax({
                type: 'POST',
                url: 'search/superheroes',
                data: JSON.stringify({
                    superhero_name: superhero_name,
                    herotype: herotype,
                    organization: organization,
                    superpower: superpower
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json',
                success: function (data) {
                    fillHeroTable(data);
                    $('#undo-search').show();
                },
                error: function () {
                    alert("error");
                }
            });
        }
    });



    $('#get-location-button').click(function (event) {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            console.log("Geolocation is not supported by this browser.");
        }
    });
    
    
    

    $.ajax({
        type: 'GET',
        url: 'getRecentLocations',
        headers: {
            'Accept': 'application/json'
        },
        success: function(data) {
            $.each(data, function(index, item){
                locations.push(item);
                initMap();
            });
        },
        error: function() {
            alert("error");
        }
    });

});

function fillSightingTable(data) {
    clearSightingTable();

    var contentRows = $('#contentRows');

    $.each(data, function (index, sighting) {
        var locationName = sighting.location.locationName;
        var state = sighting.location.address.state;
        var city = sighting.location.address.city;
        var zip = sighting.location.address.zip;
        var date = sighting.date;
        var superheroes = sighting.superheroes.values();

        var Str = "";
        for (let elements of superheroes) {
            Str += elements.name + " ";
        }

        var row = '<tr>';
        row += '<td>' + date.month + " " + date.dayOfMonth + ", " + date.year + '</td>';
        row += '<td>' + locationName + '</td>';
        row += '<td>' + city + '</td>';
        row += '<td>' + state + '</td>';
        row += '<td>' + zip + '</td>';
        row += '<td>' + Str + '</td>';
        contentRows.append(row);
    });
}

function fillHeroTable(data) {
    clearHeroTable();

    var contentRows = $('#hero-content-rows');

    $.each(data, function (index, superhero) {
        var superhero_name = superhero.name;
        var herotype = superhero.type;
        var organization = organization;
        var superpowers = superhero.superpowers.values();
        var description = superhero.description;
        var superheroId = superhero.superheroId

        var Str = "";

        for (let elements of superpowers) {
            Str += elements.superpowerName + " ";
        }

        var row = '<tr>';
        row += '<td>' + '<a href ="displaySuperheroDetails?superheroId=' + superheroId + '">' + superhero_name + '</a></td>'
        row += '<td>' + herotype + '</td>';
        row += '<td>' + Str + '</td>';
        row += '<td>' + description + '<td>';
        contentRows.append(row);
    })
}

function clearSightingTable() {
    $('#contentRows').empty();
}

function clearHeroTable() {
    $('#hero-content-rows').empty();
}

function showPosition(position) {
    var p = document.getElementById("currentLocation");
    var lat = position.coords.latitude;
    document.getElementById("latitude").value = lat;
    var long = position.coords.longitude;
    document.getElementById("longitude").value = long;
}

function initMap() {


    var center = {lat: 41.8781, lng: -87.6298};
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 2,
        center: center
    });
    var infowindow = new google.maps.InfoWindow({});
    var marker, count;
    for (count = 0; count < locations.length; count++) {
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(locations[count]["latitude"], locations[count]["longitude"]),
            map: map,
            title: locations[count]["locationName"]
        });
        
        google.maps.event.addListener(marker, 'click', (function (marker, count) {
        return function () {
        infowindow.setContent(locations[count]["locationName"]);
        infowindow.open(map, marker);
      }
    })(marker, count));
    }
}
