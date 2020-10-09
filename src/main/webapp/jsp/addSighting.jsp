<%-- 
    Document   : addSighting
    Created on : Aug 22, 2020, 11:38:29 AM
    Author     : mirandabeamer
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Sighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySightings">Sightings</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperheroes">Superheroes</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocations">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperpowers">Superpowers</a></li>
                </ul>    
            </div>
               <div id="errorMessage"><c:out value="${errorMessage}"/></div>
            <div class="col-lg-4">
                <h2>Report New Sighting</h2>
                <form class="form-horizontal" role="form" method="POST" action="addSighting">
                    <div>
                        <label for = "name">WHO? </label>
                        <div class="form-group row">
                        <select class="browser-default custom-select form-control" name="name">
                            <c:forEach var="currentSuperhero" items="${superheroes}">
                                <option value="${currentSuperhero.superheroId}"> ${currentSuperhero.name} </option>
                            </c:forEach>
                        </select>
                        </div>
                    <div  class="form-group row">
                        <label for="date">WHEN?</label>
                        <div>
                            <input class="form-control" type="date" name="date" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for = "superhero_name">WHERE? </label>
                        <div>
                        <select class="browser-default custom-select form-control" name="locationName" required>
                            <c:forEach var="currentLocation" items="${locations}">
                                <option value="${currentLocation.locationId}">${currentLocation.locationName}</option>
                            </c:forEach>
                        </select>
                        </div><br/>
                            <a href="${pageContext.request.contextPath}/displayAddLocationForm">New Location</a>
                    </div>
                    <br/>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Add Sighting"/>
                        <a href="${pageContext.request.contextPath}/displaySightings" class="btn btn-default">Cancel</a>
                    </div>    
                    
                        

                    </div>  
                </form>
            </div>
        </div>
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
