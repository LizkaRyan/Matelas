<%@ page import="java.util.List" %>
<%@ page import="mg.itu.matelas.entity.MvtStock" %>
<%@ page import="mg.itu.matelas.dto.EtatStock" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 06/11/2024
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%
  List<MvtStock> mvtStocks=(List<MvtStock>) request.getAttribute("mvtStocks");
  List<EtatStock> etatStocks=(List<EtatStock>) request.getAttribute("etatStocks");
  DecimalFormat df = new DecimalFormat("#");
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
  <div class="row mb-3 mt-3">
    <table class="table table-striped">
      <tr>
        <th>#</th>
        <th>Entree</th>
        <th>Sortie</th>
        <th>Prix de revient</th>
        <th>Prix de vente</th>
        <th>Matelas</th>
      </tr>
      <% for (int i = 0; i < mvtStocks.size(); i++) {
      %>
      <tr>
        <td></td>
        <td><%= mvtStocks.get(i).getEntree() %></td>
        <td><%= mvtStocks.get(i).getSortie() %></td>
        <td><%= df.format(mvtStocks.get(i).getPrixRevient()) %></td>
        <td><%= df.format(mvtStocks.get(i).getPrixUnitaire()) %></td>
        <td><%= mvtStocks.get(i).getMatelas().getMatelas() %></td>
      </tr>
      <% } %>
    </table>
  </div>
  <div class="row mb-3 mt-3">
    <table class="table table-striped">
      <tr>
        <th>#</th>
        <th>Nombre</th>
        <th>Matelas</th>
        <th>Prix de revient</th>
      </tr>
      <% for (int i = 0; i < etatStocks.size(); i++) {
      %>
      <tr>
        <td></td>
        <td><%= etatStocks.get(i).getEtat() %></td>
        <td><%= etatStocks.get(i).getMatelas() %></td>
        <td><%= etatStocks.get(i).getPrixRevient() %></td>
      </tr>
      <% } %>
    </table>
  </div>
</div>
</body>
</html>
