<%-- 
    Document   : pocetna
    Created on : 29-Jul-2023, 23:52:44
    Author     : Stefan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Početna stranica</title>
    </head>
    <body>
        <h1>Dodaj novi kontakt:</h1>
        <form action="Unos" method="POST">
            <lable>Ime:</lable>
            <input type="text" name="ime">
            <br/>
            <br/>
            <lable>Prezime:</lable>
            <input type="text" name="prezime">
            <br/>
            <br/>
            <lable>Email:</lable>
            <input type="text" name="email">
            <br/>
            <br/>
            <lable>Telefon:</lable>
            <input type="text" name="telefon">
            <br/>
            <br/>
            <input type="submit" value="Unesi"> 
        </form>
        <hr/>
        <p>${(msg != null) ? msg : "Dobrodošli!"}</p>
        <p><a href="Pregled">Pregled svih kontakata</a></p>
    </body>
</html>
