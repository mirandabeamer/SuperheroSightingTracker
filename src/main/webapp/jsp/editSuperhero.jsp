<%-- 
    Document   : editSuperhero
    Created on : Aug 25, 2020, 5:09:03 PM
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
        <title>Edit Superhero</title>
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
                <h2>Edit <c:out value="${superhero.name}"/></h2>
                <sf:form class="form-horizontal col-lg-4" role="form" modelAttribute="superhero" action="editSuperhero" method="POST">
                    <div class="form-group">
                        <label for="addSuperheroName">Name: </label>
                        <div>
                            <sf:input  class="form-control" type="text" id="addSuperheroName" path="name" placeholder="Name" required="required"/>
                            <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type"> Hero Type: </label>
                        <div>
                        <sf:select class="form-control" id="type" path="type">
                            <option value="Hero">Hero</option>
                            <option value="Villain">Villain</option>
                        </sf:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for ="description">Description</label>
                        <div>
                        <sf:input class="form-control" type="text" id="description" path="description" placeholder="Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                        </div>
                        <sf:hidden path="superheroId" name="superheroId"/>
                    </div>
                    <div class="form-group">
                        <label for ="organizatons">Organizations</label>
                        <div>
                            <sf:select class="form-control" id="organizations" path="organizations" items="${organizations}" itemValue="name" itemLabel="name">
                                    <sf:options value="${name}"/>
                            </sf:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for ="superpowers">Superpowers</label>
                        <div>
                            <sf:select class="form-control" id="superpowers" path="superpowers" items="${superpowers}" itemValue="superpowerId" itemLabel="superpowerName">
                                    <sf:options value="${superpowerName}"/>
                            </sf:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Edit Superhero">
                        <a href="${pageContext.request.contextPath}/displaySuperheroes" class="btn btn-default">Cancel</a>
                    </div>
                </sf:form>
            </div>
   
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
