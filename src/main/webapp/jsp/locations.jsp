<%-- 
    Document   : locations
    Created on : Aug 23, 2020, 9:22:53 AM
    Author     : mirandabeamer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locations</title>
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
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayLocations">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperpowers">Superpowers</a></li>
                </ul>    
            </div>
            <div class="row">
                <h2 class="col-sm-10">Locations</h2>
                <a href = "${pageContext.request.contextPath}/displayAddLocationForm" class="col-sm-2 btn btn-default">Add a new location</a>
            </div>
                <br/>
                 <div id="errorMessage"><c:out value="${errorMessage}"/></div>
            <div>
                <table id="locationTable" class="table table-striped">
                    <tr>
                        <th>Location Name</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Recent sightings</th>
                        <th></th>
                        <th></th>
                    </tr>
                    
                        <c:forEach var="currentLocation" items="${locations}">
                            <tr>
                            <td><c:out value="${currentLocation.locationName}"/></td>
                            <td><c:out value="${currentLocation.address.street}"/></td>
                            <td><c:out value="${currentLocation.address.city}"/></td>
                            <td><c:out value="${currentLocation.address.state}"/></td>
                            <td><c:forEach var="currentSighting" items="${currentLocation.sightings}">
                                    <a href="${pageContext.request.contextPath}/displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                    ${currentSighting.date.month} ${currentSighting.date.year}</a> |
                            </c:forEach>
                            <c:if test = "${fn:length(currentLocation.sightings) < 1}">
                                None
                            </c:if>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/displayEditLocationForm?locationId=${currentLocation.locationId}">Edit</a> | 
                                <a href="${pageContext.request.contextPath}/deleteLocation?locationId=${currentLocation.locationId}">Delete </a>
                            </td>
                            </tr>
                        </c:forEach>
                </table>
            </div>

        
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
