<%-- 
    Document   : sightings
    Created on : Aug 22, 2020, 11:13:47 AM
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
        <title>Sightings</title>
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
            <div class="row">
                <h2>Sightings</h2>
                <form  class ="form-inline col-lg-5" role="form" id="search-form">
                    <input class="col-sm-2 form-control" name="search" type="text" id="search-term" placeholder="Search Term"/>
                    <select class="col-sm-2 form-control" name="searchTerm" id="search-category"> 
                        <option disable selected>Search Category</option>
                        <option value="name" name="location-name" id="search-location-name">Location Name</option>
                        <option value="state" name="state" id="search-state">State</option>
                        <option value="city" name="city" id="search-city">City</option>
                        <option value="zip" name="zip" id="search-zip">Zip Code</option>
                        <option value="date" name="date" id="search-date">Date</option>
                        <option value="superhero" name ="superhero" id="search-superhero">Superhero sighted</option>
                    </select>
                    <button id="sighting-search-button" type="button" class="col-sm-1 btn btn-default form-control">Search</button>
                </form>
                <a href = "${pageContext.request.contextPath}/displayAddSightingForm" class="btn btn-default col-lg-2">Report a Sighting!</a>
            </div>
                <br/>
            <div>
                <div class="text-center" id="errorMessages"></div>
                <table id="sightingTable" class="table table-striped">
                    <tr>
                        <th>Date</th>
                        <th>Location Name</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Heroes Sighted</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <tbody id="contentRows"/>
                        <c:forEach var="currentSighting" items="${sightings}">
                            <tr>
                            <td><c:out value="${currentSighting.date.month}"/> <c:out value="${currentSighting.date.dayOfMonth}"/>,
                                <c:out value="${currentSighting.date.year}"/>
                            </td>
                            <td><c:out value="${currentSighting.location.locationName}"/></td>
                            <td><c:out value="${currentSighting.location.address.street}"/>
                            <td><c:out value="${currentSighting.location.address.city}"/>
                            <td><c:out value="${currentSighting.location.address.state}"/>
                            <td>
                                <c:forEach var="currentSuperhero" items="${currentSighting.superheroes}">
                                    <c:out value="${currentSuperhero.name}"/>
                                </c:forEach>

                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/displaySightingDetails?sightingId=${currentSighting.sightingId}">Details</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/displayEditSightingForm?sightingId=${currentSighting.sightingId}">Edit</a> | 
                                <a href="${pageContext.request.contextPath}/deleteSighting?sightingId=${currentSighting.sightingId}">Delete </a>
                            </td>
                            </tr>
                        </c:forEach>
                </tbody>
                </table>
                <a href="${pageContext.request.contextPath}/displaySightings" style="display:none" id="undo-search">Undo Search</a>
            </div>
                

        
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superheroes.js"></script>
    </body>
</html>
