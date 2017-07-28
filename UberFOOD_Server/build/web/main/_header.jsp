<%-- 
    Document   : _header
    Created on : 27 Jul 2017, 1:57:38 PM
    Author     : StrettO
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<div class="w3-container w3-green">
 
    <div class="w3-container w3-light-grey">Online Shop</div>
 
    <div class="w3-container w3-green">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
        Hello
           <a href="${pageContext.request.contextPath}/accountInfo">
                ${pageContext.request.userPrincipal.name} </a>
         &nbsp;|&nbsp;
           <a href="${pageContext.request.contextPath}/logout">Logout</a>
 
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </c:if>
    </div>
</div>
