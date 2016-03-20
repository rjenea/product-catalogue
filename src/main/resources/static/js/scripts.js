var productCatalogue = angular.module('productCatalogue',[]);

productCatalogue.controller("products", function ($scope, $http, $window) {
   $http.get('api/catalogue/products',{ withCredentials: true })
       .success(
           function(data) {
                $scope.products = data;
           }
       )
       .error(
           function(data, status, headers, config) {
                $window.location.href = 'err.html';
           }
       );

    $scope.basket = [{}];

    $scope.updateBasket = function(product) {
		var index = $scope.basket.indexOf(product);
    	if (index > -1) {
        	$scope.basket.splice(index, 1);
    	}else{
			$scope.basket.push(product);
    	}
    }

});
