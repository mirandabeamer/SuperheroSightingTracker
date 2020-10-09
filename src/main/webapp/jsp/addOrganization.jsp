<%-- 
    Document   : addOrganization
    Created on : Aug 22, 2020, 1:21:25 PM
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
        <title>Add Organization</title>
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
                <h2>Add Organization</h2>
                <form class="form-horizontal col-lg-4" role="form" method="POST" action="addOrganization">
                    <div class="form-group row">
                        <label for = "organization_name">Name: </label>
                        <div>
                            <input class="form-control" type="text" name="organization_name" placeholder="Name" required/>
                        </div>
                    </div>
                    <div  class="form-group row">
                        <label for="address">Phone:</label>
                        <div>
                            <input class="form-control"  type="tel" name="phone" placeholder="Phone" pattern="[0-9]{10}" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for = "address">Address:</label>
                        <div>
                            <input class="form-control"  type="text" name="address" placeholder="Street Address" required/>
                        </div>
                    </div>                    
                    <div  class="form-group row">
                        <label for = "city">City:</label>
                        <div>
                            <input class="form-control"  type="text" name="city" placeholder="City" required/>
                        </div>
                    </div>
                    <div  class="form-group row">
                        <label for = "state">State:</label>
                        <div>
                            <input class="form-control"  type="text" name="state" placeholder="State" required maxlength="2"/>
                        </div>
                    </div>     
                    <div  class="form-group row">
                        <label for = "zip">Zip:</label>
                        <div>
                            <input class="form-control" type="text" name="zip" placeholder="Zip" pattern="[0-9]{5}" required/>
                        </div>
                    </div>                                     
                    <div  class="form-group row">
                         <label for = "organization_desc">Description: </label>
                        <div>
                            <input class="form-control" type="text" name="organization_desc" placeholder="Organization Description"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Add Organization"/>
                        <a href="${pageContext.request.contextPath}/displayOrganizations" class="btn btn-default">Cancel</a>
                    </div>    
                    </div>  
                </form>
            </div>
   
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
