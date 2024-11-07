let TransformationApp = angular.module("TransformationApp",[]);

TransformationApp.controller('TransformationController', function($scope,$window, $http) {

    $scope.message="";
    $http.get("/matelas/rest/usuel")
        .then(function(response) {
            $scope.matelas = response.data;
            $scope.initialize();
            console.log("VITA")
            console.log($scope);
        });

    $scope.initialize=function(){
        $scope.transformation={};
        $scope.transformation.transformationProduits=[];
        for (let i = 0; i < $scope.matelas.length; i++) {
            $scope.transformation.transformationProduits.push({idProduit:$scope.matelas[i].idMatelas,nombre:0,matelas:$scope.matelas[i].matelas});
        }
        console.log($scope.transformation.transformationProduits);
    }

    $scope.submitForm = function() {
        console.log("Sending data:", JSON.stringify($scope.transformation)); // Afficher le JSON dans la console
        $http.post('/transformation/rest', $scope.transformation)
            .then(function(response) {
                $scope.reponse = response.data;
                console.log($scope);
                if($scope.reponse.status==500){
                    $scope.message="Erreur :"+$scope.reponse.answer;
                }
                else{
                    $window.location.href = '/transformation/resultat/'+$scope.reponse.answer.idTransformation;
                }
                }, function(error) {
                $scope.error = error.error || "Une erreur est survenue.";
            });
    };
});