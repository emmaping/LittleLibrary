'use strict';

var userCtrls = angular.module('userCtrls', []);


userCtrls.controller('LogonCtrl', ['$scope','$http','$location','shareFactory', function ($scope,$http,$location, shareFactory) {
    $scope.user = {
        name     : "emmaping",
        password : ""
    };

    $scope.logon = function(){
        $http.get('/user/logon/' + $scope.user.name + "/" + $scope.user.password).success(function(data){
            if(data){
                $location.path("/listOwnBooks/" + data.userId);
//            	$location.path("/listBooks");
                shareFactory.setUserId(data.userId);
            }else{
                alert("Login Failed!");
            }
        });
    };

    $scope.reset = function(){
        $scope.user = {
            name     : "emmaping",
            password : ""
        };
    };
    
}])

userCtrls.controller('RegistryCtrl', ['$scope','$http','$location', function ($scope,$http,$location) {    
    $scope.registry = function(){
        $http.post('/user/add/' ,{
        	userName : $scope.userName,
        	password : $scope.password1,
        	email : $scope.email
        });
        $location.path("/logon");
    };
    
    
    $scope.forwardToRegPage = function(){
        $location.path("/registry");
    };
}])
