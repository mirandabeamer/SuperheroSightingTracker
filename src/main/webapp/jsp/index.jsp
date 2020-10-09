<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">     
        <link href="${pageContext.request.contextPath}/css/superheroesStyle.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <nav class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightings">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperheroes">Superheroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperpowers">Superpowers</a></li>
                </ul>    
            </nav>

            <div>
                <div class="col-sm-4">
                    <h4>ABOUT SUPERHERO SIGHTINGS:</h4>
                    <p>
                        Record all hero sightings! Track their most frequent locations, their organizations, and superpower abilities. 
                    </p>
                    <p>
                        Upload images of your favorite superhero whether their saving a fellow citizen from crime or at the grocery store. 
                        See their most recent sightings displayed on our map. Keep track of every detail of your heroes (or villians)! 
                    </p>
                </div>  

                <div class="col-sm-8" id="googleMap">
                    <!--The div element for the map -->
                    <div id="map" style="width: 100%; height: 350px"></div>
                </div>
            </div>
            <br/>


            <div class="table-wrapper-scroll-y my-custom-scrollbar" id="newsfeed-table">
                <hr/>  
                <table class="table table-striped" id="recentSightingTable">
                    <caption>Sightings News Feed: </caption>
                    <thead>
                        <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Location</th>
                            <th scope="col">Heroes Sighted</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="currentSighting" items="${sightings}">
                            <tr>
                                <td><c:out value="${currentSighting.date.month}"/> <c:out value="${currentSighting.date.dayOfMonth}"/>,
                                    <c:out value="${currentSighting.date.year}"/></td>
                                <td><c:out value="${currentSighting.location.locationName}"/></td>
                                <td>
                                    <c:forEach var="currentHero" items="${currentSighting.superheroes}">
                                        <c:out value="${currentHero.name}"/> 
                                    </c:forEach>
                                </td>
                            </tr>

                        </c:forEach>


                    </tbody>
                </table>
            </div>     
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superheroes.js"></script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDzRCsVv91yQiCJh3XCjT2yN-7chcQwSGk&callback=initMap"></script>
    </body>
</html>

