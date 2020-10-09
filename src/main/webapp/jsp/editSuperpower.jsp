<%-- 
    Document   : editSuperpower
    Created on : Aug 23, 2020, 4:19:14 PM
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
        <title>Edit Superpower</title>
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
                <h2>Edit <c:out value="${superpower.superpowerName}"/> Superpower</h2>
                <sf:form class="form-horizontal col-lg-4" role="form" modelAttribute="superpower" action="editSuperpower" method="POST">
                    <div class="form-group">
                        <label for="addSuperpowerName">Name: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addSuperpowerName" path="superpowerName" placeholder="Name"/>
                            <sf:errors path="superpowerName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addSuperpowerDesc">Description</label>
                        <div>
                            <sf:input class="form-control" type="text" id="addSuperpowerDesc" path="superpowerDesc" placeholder="Description"/>
                            <sf:errors path="superpowerDesc" cssclass="error"></sf:errors>
                            <sf:hidden path="superpowerId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Edit Superpower">
                        <a href="${pageContext.request.contextPath}/displaySuperpowers" class="btn btn-default">Cancel</a>
                    </div>
                </sf:form>
            </div>
        </div>
   
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

