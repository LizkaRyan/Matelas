<%@ page import="mg.itu.matelas.dto.BeneficeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.matelas.dto.SommeBenefice" %><%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 06/11/2024
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%
    List<BeneficeDTO> benefices=(List<BeneficeDTO>) request.getAttribute("benefices");
    SommeBenefice sommeBenefice=(SommeBenefice) request.getAttribute("sommeBenefice");
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
    <jsp:include page="nav.jsp" />
    <div class="container">
        <div class="row mb-3 mt-3">
            <table class="table table-striped">
                <tr>
                    <th>#</th>
                    <th>Prix de vente</th>
                    <th>Prix de revient</th>
                    <th>Benefice theorique</th>
                </tr>
                <% for (int i = 0; i < benefices.size(); i++) {
                %>
                <tr>
                    <td></td>
                    <td><%= benefices.get(i).getPrixVente() %></td>
                    <td><%= benefices.get(i).getPrixRevient() %></td>
                    <td><%= benefices.get(i).getBeneficeTheorique() %></td>
                </tr>
                <% } %>
                <tr>
                    <td>Somme</td>
                    <td><%= sommeBenefice.getPrixVente() %></td>
                    <td><%= sommeBenefice.getPrixRevient() %></td>
                    <td><%= sommeBenefice.getBeneficeTheorique() %></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
