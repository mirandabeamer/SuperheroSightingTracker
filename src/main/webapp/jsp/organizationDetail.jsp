<%-- 
    Document   : organizationDetail
    Created on : Aug 23, 2020, 10:14:31 AM
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
        <title>Organization Detail</title>
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
                    <c:out value="${organization.name}"/>
                </h2>
                <br/>
                <p>
                    Description: <c:out value="${organization.description}"/>
                </p>
                <br/>
                <p>
                    <c:out value="${address.street}"/>
                </p>
                <p>
                <c:out value="${address.city}"/>, <c:out value="${address.state}"/>
                 <c:out value="${address.zip}"/>
                </p>
                <p>
                <c:out value="${organization.phone}"/>
                </p>
                <p>
                    MEMBER LIST: 
                </p>
                <br/>
                <a href="${pageContext.request.contextPath}/displayOrganizations">Return to Organizations</a>
            </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
