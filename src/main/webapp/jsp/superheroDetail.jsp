<%-- 
    Document   : superheroDetail
    Created on : Aug 24, 2020, 7:14:53 PM
    Author     : mirandabeamer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Superhero Detail</title>
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
                <h2>
                    <c:out value="${superhero.name}"/>
                </h2>
                <br/>
                <div class="col-lg-6">
                <h5>
                    Description:
                </h5>
                <p>
                    <c:out value="${superhero.description}"/>
                </p>
                <br/>
                <h5>
                   Superpowers: 
                </h5>
                <p>
                <ul>
                    <c:forEach var="currentSuperpower" items="${superhero.superpowers}">
                        <li> <c:out value="${currentSuperpower.superpowerName}"/> </li>
                    </c:forEach>
                </ul>
                </p>
                <h5>
                    Organizations: 
                </h5>
                <p>
                <ul>
                    <c:forEach var ="currentOrganization" items="${superhero.organizations}">
                        <li><a href = "${pageContext.request.contextPath}/displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                <c:out value="${currentOrganization.name}"/></a></li>
                    </c:forEach>
                </ul>
                </p>
                <h5>
                    Sightings:
                </h5>
                <div>
                    <c:if test = "${fn:length(sightings) < 1}">
                        <ul><li>None</li></ul>
                    </c:if>
                </div>
                <ul>
                    <c:forEach var="currentSighting" items="${sightings}">
                        <li><c:out value ="${currentSighting.date}"/>
                            at <c:out value="${currentSighting.location.locationName}"/></li>
                    </c:forEach>
                </ul>
                </p>
                <br/>
                <a href="${pageContext.request.contextPath}/displaySuperheroes" class="btn btn-default">Return to Superheroes</a>
                </div>
                <div col-lg-6>
                    <c:if test = "${superhero.picture !=null}">
                    <img src="${pageContext.request.contextPath}/${picture.filename}"
                     class="img-thumbnail" alt="image" width="300" height="300">
                    </c:if>
                </div>
            </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
