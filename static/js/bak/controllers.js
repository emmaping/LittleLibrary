'use strict';

var bookCtrls = angular.module('bookCtrls', ['bookServices']);

bookCtrls.controller('HelloCtrl', ['$scope',
    function($scope) {
        $scope.greeting = {
            text: 'Hello'
        };
    }
]);

bookCtrls.controller('LogonCtrl', ['$scope','$http','$location', function ($scope,$http,$location) {
    $scope.user = {
        name     : "emmaping",
        password : ""
    };

    $scope.logon = function(){
        $http.get('/user/logon/' + $scope.user.name + "/" + $scope.user.password).success(function(data){
            if(data){
                $location.path("/list");
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

bookCtrls.controller('listBookCtrl',['$scope', '$http','$window', '$location', 
    '$log','shareFactory','bookService', 
    function ($scope, $http, $window, $location,$log,shareFactory,bookService) {
    
    $scope.listusers = function(){
        userService.listBooks(function(data){$scope.books = data;});
    };

    $scope.getBookByBookId = function(bookId){
        userService.getBookByBookId(bookId, function(data){
            shareFactory.setBook(data);
            $location.path("/add");
        });
    };

    $scope.forwardToAddPage = function(){
        shareFactory.setUpdate(false);
        $location.path("/add");
    };

    $scope.forwardToEditPage = function(userId){
        shareFactory.setUpdate(true);
        $scope.getUserByUserId(userId);
    };

    $scope.deletebook = function(bookId){
        bookService.deleteBook(bookId, function(){
            $scope.listbooks();
        });
    };
}]);

bookCtrls.controller('addAndUpdateCtrl', ['$scope', '$http','$window', '$location', 
    '$log','shareFactory','bookService', 
    function ($scope, $http, $window, $location,$log,shareFactory,bookService) {

    var update = shareFactory.getUpdate();
    if(update){
        var data = shareFactory.getbook();
        $scope.bookName = data.bookName;
        $scope.totalNumber  = data.totalNumber;
    }else{
        $scope.bookName = '';
        $scope.totalNumber = '';
    }

    $scope.checkNullParam = function(){
        if(!($scope.bookName)){
            alert("Please insert book name");
            return 0;
        }

        if(!($scope.lastName)){
            alert("Please insert Total Number");
            return 0;
        }

        return 1;
    };

    $scope.addbook = function(){
        bookService.addbook($scope.bookName, $scope.totalNumber,
         function(){
            $location.path("/list");
         });
    };

    $scope.editbook = function() {
        var user = shareFactory.getbook();
        $log.log("update an book with id:" + book.bookId + " book name:"+ $scope.bookName);
        bookService.editbook(book.bookId, $scope.bookName, $scope.totalNumber, 
            function(){
                $location.path("/list");
            });
    };

    $scope.submit = function(){
        if(!($scope.checkNullParam())){
            return;
        }

        var update = shareFactory.getUpdate();
        if(update){
            $scope.editbook();
        }else{
            $scope.addbook();
        }
    };

}]);