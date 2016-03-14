function products($scope, $http) {
    $http.get('http://localhost:8080/api/catalogue/products').
        success(function(data) {
            $scope.products = data;
        });
}