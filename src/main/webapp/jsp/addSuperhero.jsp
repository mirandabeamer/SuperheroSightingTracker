<%-- 
    Document   : addSuperhero
    Created on : Aug 23, 2020, 9:23:50 AM
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
        <title>Add Superhero</title>
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
                <h2>Add Superhero</h2>
                <br/>
                <form class="form-horizontal col-lg-4" role="form" method="POST" action="addSuperhero" enctype="multipart/form-data">
                    <div class="form-group row">
                        <label for = "name">Name: </label>
                        <div>
                            <input class="form-control" type="text" name="name" placeholder="Name" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for = "heroType">Hero Type: </label>
                        <div>
                            <select  class="form-control" name="heroType">
                                <option value="Hero">Hero</option>
                                <option value="Villain">Villain</option>
                            </select>
                        </div>
                    </div>                    
                    <div  class="form-group row">
                        <label for = "superpower">Superpower: </label>
                        <div>
                        <select class="custom-select form-control" name="superpowers" multiple required>
                        <c:forEach var="currentSuperpower" items="${superpowers}">
                            <option value="${currentSuperpower.superpowerId}">
                                ${currentSuperpower.superpowerName} | ${currentSuperpower.superpowerDesc}</option>
                        </c:forEach>
                        </select>
                        </div>
                    </div>
                    <div  class="form-group row">
                        <label for = "organization">Organization:</label>
                        <div>
                        <select name="organizations" class="custom-select form-control" multiple required>
                            <c:forEach var="currentOrganization" items="${organizations}">
                                <option value="${currentOrganization.name}">${currentOrganization.name} </option>
                            </c:forEach>
                        </select>
                        </div>
                    </div>     
                    <div  class="form-group row">
                        <label for = "description">Description:</label>
                        <div>
                            <input  class="form-control"  type="text" name="description" placeholder="Description"/>
                        </div>
                    </div>  
                    <div class="form-group">
                        <label for="picture">Upload Photo: </label>
                        <input type="file" id="picture" name="picture"/> 
                    </div>
                    <br/>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Add Superhero"/>
                        <a href="${pageContext.request.contextPath}/displaySuperheroes" class="btn btn-default">Cancel</a>
                    </div>  
                </form>
  

                 <br/>

            </div>
            </div>
        </div>
   
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
