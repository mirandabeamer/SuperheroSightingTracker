<%-- 
    Document   : editLocation
    Created on : Aug 27, 2020, 6:08:35 PM
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
        <title>Edit Location</title>
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
                
            <div class="col-lg-6">
                <h2>Edit <c:out value="${location.locationName}"/></h2>
                <sf:form class="form-horizontal" role="form" modelAttribute="location" action="editLocation" method="POST">
                    <div class="form-group">
                        <label for="editLocationName">Name: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="editLocationName" path="locationName" placeholder="Location Name"/>
                            <sf:errors path="locationName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editStreet">Street Address: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="editStreet" path="address.street" placeholder="Street Address" required="required"/>
                            <sf:errors path="address.street" cssclass="error"></sf:errors>
                        </div>
                    </div>              
                    <div class="form-group">
                        <label for="editCity">City: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="editCity" path="address.city" placeholder="City" required="required"/>
                            <sf:errors path="address.city" cssclass="error"></sf:errors>
                        </div>
                    </div>
                                        
                    <div class="form-group">
                        <label for="editState">State: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="editState" path="address.state" placeholder="State" required="required"  maxlength="2"/>
                            <sf:errors path="address.state" cssclass="error"></sf:errors>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="editZip">Zip: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="editZip" path="address.zip" placeholder="Zip" required="required"/>
                            <sf:errors path="address.zip" cssclass="error"></sf:errors>
                        </div>
                    </div>
                        <div class="form-group">
                            <label for ="editCoordinates">Coordinates</label>
                            <div>
                            <sf:input type="test" id="editLongitude" path="longitude" placeholder="Longitude"/>
                            <sf:errors path="longitude" cssclass="error"></sf:errors>
                            
                            <sf:input type="test" id="editLatitude" path="latitude" placeholder="Latitude"/>
                            <sf:errors path="latitude" cssclass="error"></sf:errors>
                            </div>
                        </div>
                    <div class="form-group">
                        <label for="editDescription">Description: </label>
                        <div>
                            <sf:input type="text" class="form-control" id="editDescription" path="description" placeholder="Description"/>
                            <sf:errors path="address.street" cssclass="error"></sf:errors>
                            <sf:hidden path="locationId"/>
                            <sf:hidden path="address.addressId"/>
                        </div>
                    </div>
                        <br/>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Edit Location">
                        <a href="${pageContext.request.contextPath}/displayLocations" class="btn btn-default">Cancel</a>
                    </div>
                </sf:form>
            </div>
   
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
