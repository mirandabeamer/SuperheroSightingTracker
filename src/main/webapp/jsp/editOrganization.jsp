<%-- 
    Document   : editOrganization
    Created on : Aug 23, 2020, 12:40:57 PM
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
        <title>Edit Organization</title>
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
                <h2>Edit <c:out value="${organization.name}"/> Organization</h2>
                <sf:form class="form-horizontal col-lg-5" role="form" modelAttribute="organization" action="editOrganization" method="POST">
                    <div class="form-group">
                        <label for="addOrganizationame">Name: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addOrganizationame" path="name" placeholder="Name"/>
                            <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addOrganizationPhone">Phone: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addOrganizationPhone" path="phone" placeholder="Phone"/>
                            <sf:errors path="phone" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="organization_address">Street Address: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addOrganizationAddress" path="address.street" placeholder="Street Address"/>
                            <sf:errors path="address.street" cssclass="error"></sf:errors>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="organization_city">City: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addOrganizationCity" path="address.city" placeholder="City"/>
                            <sf:errors path="address.city" cssclass="error"></sf:errors>
                        </div>
                    </div>
                                        
                    <div class="form-group">
                        <label for="organization_state">State: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addOrganizationState" path="address.state" placeholder="State" maxlength="2"/>
                            <sf:errors path="address.state" cssclass="error"></sf:errors>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="organization_state">Zip: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addOrganizationZip" path="address.zip" placeholder="Zip"/>
                            <sf:errors path="address.zip" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="organization_description">Description: </label>
                        <div>
                            <sf:input class="form-control" type="text" id="addOrganizationDescription" path="description" placeholder="Description"/>
                            <sf:errors path="description" cssclass="error"></sf:errors>
                            <sf:hidden path="organizationId"/>
                            <sf:hidden path="address.addressId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-default" value="Edit Organization">
                        <a href="${pageContext.request.contextPath}/displayOrganizations" class="btn btn-default">Cancel</a>
                    </div>
                </sf:form>
            </div>
   
                    
                    

                
                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
