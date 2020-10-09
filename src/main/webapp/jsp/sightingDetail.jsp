<%-- 
    Document   : sightingDetail
    Created on : Aug 25, 2020, 7:00:47 PM
    Author     : mirandabeamer
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sighting Detail</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/displaySightings">Sightings</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperheroes">Superheroes</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocations">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperpowers">Superpowers</a></li>
                </ul>    
            </div>
                <div class = col-lg-4>
                <h2>
                    Superhero Sighting 
                </h2>
                <p>
                     <c:out value="${sighting.date.month}"/> <c:out value="${sighting.date.dayOfMonth}"/>, <c:out value="${sighting.date.year}"/> 
                </p>
                <br/>
                <h5>
                   Superheroes spotted:  
                </h5>
                <p>
                    <c:forEach var="currentSuperhero" items="${superheroes}">
                        <a href="${pageContext.request.contextPath}/displaySuperheroDetails?superheroId=${currentSuperhero.superheroId}">
                           <c:out value="${currentSuperhero.name}"/> 
                        </a>
                    </c:forEach>
                </p>
                <h5>
                    Location: 
                </h5>
                <div>
                    <c:out value="${location.locationName}"/>
                </div>
                <div>
                    <c:out value="${address.street}"/>
                </div>
                <div>
                    <c:out value="${address.city}"/>, <c:out value="${address.state}"/> <c:out value="${address.zip}"/>
                </div>
                <br/>
                <div>
                    Longitude: <c:out value="${location.longitude}"/> Latitude: <c:out value="${location.latitude}"/>
                </div>
                <br/>
                    <h5>Description: </h5>
                    <p><c:out value="${location.description}"/></p>

                <br/><br/>
                <a href="${pageContext.request.contextPath}/displaySightings" class="btn btn-default">Return to Sightings</a>
            </div>
            <div class="col-lg-8" id="googleMap">
                <!--The div element for the map -->
                <div id="map" style="width: 100%; height: 400px"></div>
                <script>
            // Initialize and add the map
            function initMap() {
              // The location of Uluru
              var ${address.city} = {lat: ${location.latitude}, lng: ${location.longitude}};
              // The map, centered at Uluru
              var map = new google.maps.Map(
                  document.getElementById('map'), {zoom: 4, center: ${address.city}});
              // The marker, positioned at Uluru
              var marker = new google.maps.Marker({position: ${address.city}, map: map});
            }
                </script>
            </div>
            
            
        </div>

        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDzRCsVv91yQiCJh3XCjT2yN-7chcQwSGk&callback=initMap"></script>
    </body>
</html>