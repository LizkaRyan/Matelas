<%@ page import="mg.itu.matelas.dto.BeneficeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.matelas.dto.SommeBenefice" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 06/11/2024
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%
    List<BeneficeDTO> benefices=(List<BeneficeDTO>) request.getAttribute("benefices");
    SommeBenefice sommeBenefice=(SommeBenefice) request.getAttribute("sommeBenefice");
    List<SommeBenefice> predictions=(List<SommeBenefice>) request.getAttribute("predictions");
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
                    <td><%= benefices.get(i).getRemarque() %></td>
                    <td><%= df.format(benefices.get(i).getPrixVente()) %></td>
                    <td><%= df.format(benefices.get(i).getPrixRevient()) %></td>
                    <td><%= df.format(benefices.get(i).getBeneficeTheorique()) %></td>
                </tr>
                <% } %>
                <tr>
                    <td>Somme</td>
                    <td><%= df.format(sommeBenefice.getPrixVente()) %></td>
                    <td><%= df.format(sommeBenefice.getPrixRevient()) %></td>
                    <td><%= df.format(sommeBenefice.getBeneficeTheorique()) %></td>
                </tr>
            </table>
        </div>
        <div class="row mb-3 mt-3">
            <table class="table table-striped">
                <tr>
                    <th>#</th>
                    <th>Prix de vente</th>
                    <th>Prix de revient</th>
                    <th>Benefice theorique</th>
                </tr>
                <% for (int i = 0; i < predictions.size(); i++) { %>
                <tr>
                    <td><%= predictions.get(i).getRemarque() %></td>
                    <td><%= df.format(predictions.get(i).getPrixVente()) %></td>
                    <td><%= df.format(predictions.get(i).getPrixRevient()) %></td>
                    <td><%= df.format(predictions.get(i).getBeneficeTheorique()) %></td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</body>
</html>
