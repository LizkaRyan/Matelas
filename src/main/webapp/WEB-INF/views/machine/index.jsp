<%@ page import="java.util.List" %>
<%@ page import="mg.itu.matelas.entity.MvtStock" %>
<%@ page import="mg.itu.matelas.entity.fabrication.Machine" %><%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 06/11/2024
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%
    List<Machine> machines=(List<Machine>) request.getAttribute("machines");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/public/bootstrap-5/css/bootstrap.min.css">
    <title>Benefice</title>
</head>
<body>
<jsp:include page="../nav.jsp" />
<div class="container">
    <form action="/machine/filter">
        <select name="annee">
            <option value="0">Tous</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
        </select>
        <button class="btn-primary">Filtrer</button>
    </form>
    <h1>ETU002459</h1>
    <div class="row mb-3 mt-3">
        <table class="table table-striped">
            <tr>
                <th>Position</th>
                <th>Machine</th>
                <th>Quantite</th>
                <th>Prix de revient pratique</th>
                <th>Prix de revient theorique</th>
                <th>Ecart/m^3</th>
            </tr>
            <% for (int i = 0; i < machines.size(); i++) {
            %>
            <tr>
                <td><%= (i+1) %></td>
                <td><%= machines.get(i).getMachine() %></td>
                <td><%= machines.get(i).getQuantite() %></td>
                <td><%= machines.get(i).getPrixRevient() %></td>
                <td><%= machines.get(i).getPrixRevientTheorique() %></td>
                <td><%= machines.get(i).getEcart() %></td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
