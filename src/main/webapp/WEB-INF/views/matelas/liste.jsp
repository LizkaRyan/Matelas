<%@ page import="java.util.List" %>
<%@ page import="mg.itu.matelas.entity.Matelas" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 06/11/2024
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%
  List<Matelas> blocs=(List<Matelas>) request.getAttribute("blocs");
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
        <th>Matelas</th>
        <th>Prix de revient</th>
        <th>Parent</th>
        <th>Ancetre</th>
        <th>Modifier</th>
      </tr>
      <% for (int i = 0; i < blocs.size(); i++) {
      %>
      <tr>
        <td><%= blocs.get(i).getMatelas() %></td>
        <td><%= df.format(blocs.get(i).getPrixUnitaire()) %></td>
        <% if(blocs.get(i).getOrigine()!=null) {%>
        <td><%= blocs.get(i).getOrigine().getMatelas() %></td>
        <% } %>
        <% if(blocs.get(i).getOrigine()==null) {%>
        <td>Ancetre</td>
        <% } %>
        <% if(blocs.get(i).getAncestor()!=null) {%>
        <td><%= blocs.get(i).getAncestor().getMatelas() %></td>
        <% } %>
        <% if(blocs.get(i).getAncestor()==null) {%>
        <td>Ancetre</td>
        <% } %>
        <td><a href="/matelas/update/<%= blocs.get(i).getIdMatelas() %>">Modifier prix</a></td>
      </tr>
      <% } %>
    </table>
  </div>
</div>
</body>
</html>
