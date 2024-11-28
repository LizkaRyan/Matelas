<%@ page import="mg.itu.matelas.entity.Matelas" %>
<%@ page import="java.util.List" %>
<%
    List<Matelas> matelas=(List<Matelas>)request.getAttribute("blocs");
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
<body>
<jsp:include page="../nav.jsp" />
<div class="container">
    <div class="form">
        <form action="/matelas/rest/generate" method="post">
            <div class="row mb-3">
                <h5>Formulaire de generation</h5>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="nombre" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Nombre donnees</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="longueurMin" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Min Longueur</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="longueurMax" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Max Longueur</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="largeurMin" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Min Largeur</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="largeurMax" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Max Largeur</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="epaisseurMin" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Min epaisseur</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="epaisseurMax" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Max epaisseur</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="date" name="dateMin" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Date Min</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="date" name="dateMax" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Date Max</label>
                </div>
            </div>
            <div class="row">
                <div class="form-floating mb-3">
                    <input type="number" name="pourcentage" class="form-control" id="floatingInput" placeholder="designation">
                    <label for="floatingInput">Pourcentage</label>
                </div>
            </div>
            <div class="row">
                <button class="btn btn-primary">Valider</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>