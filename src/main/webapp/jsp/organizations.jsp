<%-- 
    Document   : organizations
    Created on : Aug 22, 2020, 1:15:23 PM
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
        <title>Organizations</title>
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
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocations">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperpowers">Superpowers</a></li>
                </ul>    
            </div>
            <div class="row">
                <h2 class="col-sm-10">Organizations</h2>
                <a class="btn btn-default col-sm-2 offset-sm-2" href="${pageContext.request.contextPath}/displayAddOrganizationForm">Add new organization</a>
            </div>
                <br/>
            <div>
                <table id="organization_table" class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>State</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var ="currentOrganization" items="${organizationList}">
                        <tr>
                            <td>
                                <a href ="displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                <c:out value="${currentOrganization.name}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${currentOrganization.phone}"/>
                            </td>                            
                            <td>
                                <c:out value="${currentOrganization.address.street}"/>
                            </td>                            
                            <td>
                                <c:out value="${currentOrganization.address.city}"/>
                            </td>                            
                            <td>
                                <c:out value="${currentOrganization.address.state}"/>
                            </td>
                            <td>
                                <a href="displayEditOrganizationForm?organizationId=${currentOrganization.organizationId}">Edit</a>
                            </td>
                            <td>
                                <a href="deleteOrganization?organizationId=${currentOrganization.organizationId}">Delete</a>
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
