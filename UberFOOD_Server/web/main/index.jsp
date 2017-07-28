<%-- 
    Document   : index
    Created on : 27 Jul 2017, 1:53:20 PM
    Author     : StrettO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>UberFOOD Restaurant</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/w3.css">
 
</head>
<body>
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <div class="w3-container w3-green">
       <h1>UberFOOD Restaurant</h1>
   </div>
  
   <div class="w3-container w3-light-grey">
   <h3>Some content</h3>
  
   <ul>
      <li>Buy online</li>
      <li>Admin pages</li>
      <li>Reports</li>
   </ul>
   </div>
  
  
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
