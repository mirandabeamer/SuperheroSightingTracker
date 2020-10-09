<%-- 
    Document   : addLocation
    Created on : Aug 25, 2020, 9:26:58 PM
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
        <title>Add Location</title>
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
                <h2>Add New Location</h2>
                <div class="col-lg-4">    
                <div id="errorMessage"><h5><c:out value="${errorMessage}"/></h5></div>
                <button id="get-location-button">Use current location</button>
                <br/> <br/>
                <sf:form class="form-horizontal" role="form" modelAttribute="location" method="POST" action="addLocation">
                    <div class="form-group row">
                        <label for = "locationName">Location Name: </label>
                        <div>
                            <sf:input class="form-control" type="text" name="locationName" path="locationName" placeholder="Location Name"/>
                             <sf:errors path="locationName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div  class="form-group row">
                        <label for="street">Street Address:</label>
                        <div>
                            <sf:input class="form-control" type="text" name="street" path="address.street" placeholder="Address" required="required"/>
                             <sf:errors path="address.street" cssclass="error"></sf:errors>
                        </div>
                    </div>                    
                    <div  class="form-group row">
                        <label for="city">City:</label>
                        <div>
                            <sf:input class="form-control" type="text" name="city" path="address.city" placeholder="City" required="required"/>
                             <sf:errors path="address.city" cssclass="error"></sf:errors>
                        </div>
                    </div>                    
                    <div  class="form-group row">
                        <label for="street">State:</label>
                        <div>
                            <sf:input  class="form-control" type="text" name="state" path="address.state" placeholder="State" required="required" maxlength="2"/>
                             <sf:errors path="address.state" cssclass="error"></sf:errors>
                        </div>
                    </div>                    
                    <div  class="form-group row">
                        <label for="street">Zip code:</label>
                        <div>
                            <sf:input class="form-control" type="text" name="zip" path="address.zip" placeholder="Zip Code" required="required" pattern="[0-9]{5}"/>
                             <sf:errors path="address.zip" cssclass="error"></sf:errors>
                        </div>
                    </div>        
                        
                    <div  class="form-group row">
                        <label for="latitude">Coordinates:</label> 
                        <p id="currentLocation"></p>
                        <div>
                            <sf:input type="text" name="latitude" id="latitude" placeholder="Latitude" path="latitude"/>
                             <sf:errors path="latitude" cssclass="error"></sf:errors>
                            <sf:input type="text" name="longitude" id="longitude" placeholder="Longitude" path="longitude"/>
                             <sf:errors path="longitude" cssclass="error"></sf:errors>
                        </div>
                    </div>                    
                    <div  class="form-group row">
                        <label for="description">Description of location:</label>
                        <div>
                            <sf:input type="text" class="form-control" name="description" path="description" placeholder="Location Description"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Add Location"/>
                        <a href="${pageContext.request.contextPath}/displayLocations" class="btn btn-default">Cancel</a>
                    </div> 
                </sf:form>
                </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superheroes.js"></script>
    </body>
</html>
