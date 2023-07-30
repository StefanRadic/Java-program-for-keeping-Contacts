<%-- 
    Document   : pregled
    Created on : 29-Jul-2023, 23:53:08
    Author     : Stefan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pregled</title>
    </head>
    <body>
        <h1>Kontakti:</h1>
        <table width="25%" border="3">
            <tr>
                <td>Ime:</td>
                <td>Prezime:</td>
                <td>Email:</td>
                <td>Telefon:</td>
            </tr>
            <c:forEach items="${kontakti}" var="kontakt">
                <tr>
                    <td>${kontakt.ime}</td>
                    <td>${kontakt.prezime}</td>
                    <td>${kontakt.email}</td>
                    <td>${kontakt.telefon}</td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="pocetna.jsp">Link ka početnoj stranici</a></p>
    </body>
</html>
