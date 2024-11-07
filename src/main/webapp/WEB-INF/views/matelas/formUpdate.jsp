<%@ page import="mg.itu.matelas.entity.Matelas" %>
<%@ page import="java.util.List" %>
<%
  Matelas bloc=(Matelas)request.getAttribute("bloc");
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
    <form action="/matelas/update" method="post">
      <div class="row mb-3">
        <h5>Formulaire d'insertion de bloc</h5>
      </div>
      <div class="row">
        <input type="hidden" value="<%= bloc.getIdMatelas() %>" name="idMatelas">
        <div class="form-floating mb-3">
          <input type="number" name="prixUnitaire" class="form-control" id="floatingInput" placeholder="designation">
          <label for="floatingInput">Prix unitaire</label>
        </div>
      </div>
      <div class="row">
        <button class="btn btn-primary">Modifier</button>
      </div>
    </form>
  </div>
</div>
</body>
</html>