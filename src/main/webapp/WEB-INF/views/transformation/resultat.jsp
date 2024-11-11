<%@ page import="mg.itu.matelas.dto.Prediction" %>
<%@ page import="mg.itu.matelas.dto.BeneficeDTO" %><%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 06/11/2024
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Prediction minPerte=(Prediction)request.getAttribute("minPerte");
    Prediction optimiste=(Prediction) request.getAttribute("optimiste");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/public/bootstrap-5/css/bootstrap.min.css">
    <title>Resultat</title>
</head>
<body>
    <jsp:include page="../nav.jsp" />
    <div class="container">
        <div class="row mb-3 mt-3">
            <table class="table table-striped">
                <tr>
                    <th>Mode</th>
                    <th>Usuel</th>
                    <th>Prix rapport volume</th>
                    <th>Nombre créé</th>
                    <th>Reste en volume</th>
                    <th>Prix de vente théorique</th>
                </tr>
                <tr>
                    <td>Minimum de perte</td>
                    <td><%= minPerte.getUsuel().getMatelas() %></td>
                    <td><%= minPerte.getPrixRapportVolume() %></td>
                    <td><%= minPerte.getNombreCreer() %></td>
                    <td><%= minPerte.getVolumeRestant() %></td>
                    <td><%= minPerte.getNombreCreer()*minPerte.getUsuel().getPrixUnitaire() %></td>
                </tr>
                <tr>
                    <td>Optimiste</td>
                    <td><%= optimiste.getUsuel().getMatelas() %></td>
                    <td><%= optimiste.getPrixRapportVolume() %></td>
                    <td><%= optimiste.getNombreCreer() %></td>
                    <td><%= optimiste.getVolumeRestant() %></td>
                    <td><%= optimiste.getNombreCreer()*optimiste.getUsuel().getPrixUnitaire() %></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
