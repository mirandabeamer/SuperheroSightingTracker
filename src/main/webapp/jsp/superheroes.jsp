<%-- 
    Document   : superheroes
    Created on : Aug 23, 2020, 9:23:38 AM
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
        <title>Superoheroes</title>
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
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySuperheroes">Superheroes</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizations">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocations">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperpowers">Superpowers</a></li>
                </ul>    
            </div>
            <div class="row">
                <h2 class="col-sm-4">Superheroes</h2>
                  <form  class ="row form-inline" role="form" id="search-form">
                    <input class="col-sm-2 form-control" name="search" type="text" id="hero-search-term" placeholder="Search Term"/>
                    <select class="col-sm-2 form-control" name="searchTerm" id="hero-search-category"> 
                        <option disable selected>Search Category</option>
                        <option value="name" name="superhero_name" id="search-superhero-name">Superhero Name</option>
                        <option value="type" name="herotype" id="herotype">Hero Type</option>
                        <option value="organization" name="organization" id="search-organization">Organization Name</option>
                        <option value="superpower" name="superpower" id="superpower">Superpower</option>
                    </select>
                    <button id="search-button" type="button" class="col-sm-1 btn btn-default">Search</button>
                </form>
                <div><h5 class="text-center" id="errorMessages">${errorMessage}</h5></div>
                <a class="btn btn-default col-sm-2" href="${pageContext.request.contextPath}/displayAddSuperheroForm">Add new Superhero</a>
                </div>
                 <br/>
            <div>
                
                <table id=superhero_table" class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Superpowers</th>
                        <th>Description</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <tbody id="hero-content-rows">
                    <c:forEach var ="currentSuperhero" items="${superheroList}">
                        <tr>
                            <td>
                                <a href ="displaySuperheroDetails?superheroId=${currentSuperhero.superheroId}">
                                <c:out value="${currentSuperhero.name}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${currentSuperhero.type}"/>
                            </td>                            
                            <td>
                                <c:forEach var="currentSuperpower" items="${currentSuperhero.superpowers}">
                                    <c:out value="${currentSuperpower.superpowerName}"/> 
                                </c:forEach>
                            </td>                            
                            <td>
                                <c:out value="${currentSuperhero.description}"/>
                            </td>                            
                            <td>
                                <a href="displayEditSuperheroForm?superheroId=${currentSuperhero.superheroId}">Edit</a>
                            </td>
                            <td>
                                <a href="deleteSuperhero?superheroId=${currentSuperhero.superheroId}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="${pageContext.request.contextPath}/displaySuperheroes" style="display:none" id="undo-search">Undo Search</a>

        </div>
        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superheroes.js"></script>
    </body>
</html>
