<%-- 
    Document   : editSighting
    Created on : Aug 25, 2020, 6:54:53 PM
    Author     : mirandabeamer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Sighting</title>
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
                
            <div>
                <h2>Edit Sighting</h2>
                <div>
                <sf:form class="form-horizontal col-lg-5" role="form" modelAttribute="sighting" action="editSighting" method="POST">
                   <div class="form-group">
                        <label for ="supeheroes">Who?</label>
                        <div>
                            <sf:select class="form-control" id="superheroes" path="superheroes" items="${superheroes}" itemValue="superheroId" itemLabel="name" required="required">
                                    <sf:options value="${name}"/>
                            </sf:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="date">When? </label>
                        <div>
                            <sf:input class="form-control" name="date" type="text" value="${dateString}" id="date" path="date" placeholder="date" required="required"/>
                            <sf:errors path="date" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <sf:hidden path="sightingId" name="sightingId"/>
                    <sf:hidden path="location.locationId" name="locationId"/>
                    <sf:hidden path="location.address.addressId" name="addressId"/>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Edit Sighting">
                        <a href="${pageContext.request.contextPath}/displaySightings" class="btn btn-default">Cancel</a>
                    </div>
                </sf:form>
                </div>
                <div class="col-lg-5 text-center">
                    <h4> Where? </h4>
                    <p>${location.locationName}</p>
                    <p>${address.street}</p>
                    <p>${address.city}, ${address.state} ${address.zip}</p>
                    <br/>
                    <p>Longitude: ${location.longitude} Latitude: ${location.latitude}</p>
                    <p>Description: </p>
                    <p>${location.description}</p>
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/displayEditLocationForm?locationId=${location.locationId}">Edit Location</a>
                </div>
            </div>
                
   
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

