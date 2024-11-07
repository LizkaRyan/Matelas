<%@ page import="java.util.List" %>
<%@ page import="mg.itu.matelas.entity.Matelas" %><%
    List<Matelas> blocs=(List<Matelas>)request.getAttribute("blocs");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/public/bootstrap-5/css/bootstrap.min.css">
    <title>Formulaire bloc</title>
</head>
<style>
    .form{
      border-radius: 15px;
      background-color: white;
      box-shadow: rgba(71, 70, 70, 0.4) 0px 2px 4px, rgba(0, 0, 0, 0.3) 0px 7px 13px -3px, rgba(0, 0, 0, 0.2) 0px -3px 0px inset;
      height: auto;
      width: auto;
      padding: 5%;
      margin-top: 5%;
    }
</style>
<script src="/public/angularJS/angular.min.js"></script>
<body>
    <jsp:include page="../nav.jsp" />
    <div class="container">
        <div class="form" ng-app="TransformationApp" ng-controller="TransformationController">
            <form ng-submit="submitForm()" method="post">
                <div class="row mb-3">
                    <h6 style="color: red">{{message}}</h6>
                </div>
                <div class="row mb-3">
                    <h5>Formulaire d'insertion de transformation</h5>
                </div>
                <div class="row">
                    <div class="form-floating mb-3">
                        <select ng-model="transformation.idBloc" class="form-select" aria-label="Default select example">
                            <% for(int i=0;i<blocs.size();i++){ %>
                            <option value="<%= blocs.get(i).getIdMatelas() %>"><%= blocs.get(i).getMatelas() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-floating mb-3">
                        <input type="text" ng-model="transformation.remarque" class="form-control" id="floatingInput" placeholder="designation">
                        <label for="floatingInput">Remarque</label>
                    </div>
                </div>
                <div class="row">
                    <div class="form-floating mb-3">
                        <input type="number" step="0.01" ng-model="transformation.longueurReste" class="form-control" id="floatingInput" placeholder="designation">
                        <label for="floatingInput">Longueur reste</label>
                    </div>
                </div>
                <div class="row">
                    <div class="form-floating mb-3">
                        <input type="number" step="0.01" ng-model="transformation.largeurReste" class="form-control" id="floatingInput" placeholder="designation">
                        <label for="floatingInput">Largeur reste</label>
                    </div>
                </div>
                <div class="row">
                    <div class="form-floating mb-3">
                        <input type="number" step="0.01" ng-model="transformation.epaisseurReste" class="form-control" id="floatingInput" placeholder="designation">
                        <label for="floatingInput">Epaisseur reste</label>
                    </div>
                </div>
                <div class="row">
                    <div class="form-floating mb-3">
                        <input type="date" id="date" ng-model="transformation.dateTransformation" class="form-control" id="floatingInput" placeholder="designation">
                        <label for="floatingInput">Date transformation</label>
                    </div>
                </div>
                <div class="row" ng-repeat="transformationProduit in transformation.transformationProduits">
                    <div class="form-floating mb-3">
                        <input type="number" ng-model="transformationProduit.nombre" class="form-control" id="floatingInput" placeholder="designation">
                        <label for="floatingInput">{{ transformationProduit.matelas }}</label>
                    </div>
                </div>
                <div class="row">
                    <button class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
</body>
<script src="/public/jsapplication/transformation.js"></script>
</html>