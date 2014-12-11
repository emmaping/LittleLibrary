'use strict';

var bookCtrls = angular.module('bookCtrls', ['bookServices']);
var userId = "" ;

bookCtrls.controller('HelloCtrl', ['$scope',
    function($scope) {
        $scope.greeting = {
            text: 'Hello'
        };
    }
]);

bookCtrls.controller('listBookCtrl',['$scope', '$routeParams','$http','$window', '$location', 
    '$log','shareFactory','bookService', 
    function ($scope, $routeParams, $http, $window, $location,$log,shareFactory,bookService) {
	var userId = shareFactory.getUserId() ;
    
    $scope.listBooks = function(){
    	$scope.showBorrow = true;
    	$http.get('/library/list/').success(function(data){$scope.books = data;});
    };
    
    $scope.listOwnBooks = function(){
    	$scope.showBorrow = false;
    	$http.get('/user/' + userId + '/books/').success(function(data){$scope.books = data;});
    };

    $scope.getBookById = function(id){
        bookService.getBookById(id, function(data){
            shareFactory.setBook(data);
            $location.path("/edit");
        });
    };

    $scope.forwardToAddPage = function(){
        shareFactory.setUpdate(false);
        $location.path("/add");
    };

    $scope.forwardToEditPage = function(id){
        shareFactory.setUpdate(true);
        $scope.getBookById(id);
    };

    $scope.deleteBook = function(id){
        bookService.deleteBook(id, function(){
            $scope.listBooks();
        });
    };
    $scope.borrowBook = function(bookId){
        bookService.borrowBook(bookId, userId, function(){
            $scope.listBooks();
        });
    };
    $scope.returnBook = function(bookId){
        bookService.returnBook(bookId, userId, function(){
            $scope.listBooks();
        });
    };
}]);

bookCtrls.controller('addAndUpdateCtrl', ['$scope', '$http','$window', '$location', 
    '$log','shareFactory','bookService', 
    function ($scope, $http, $window, $location,$log,shareFactory,bookService) {

    var update = shareFactory.getUpdate();
    if(update){
        var data = shareFactory.getBook();
        $scope.bookName = data.bookName;
        $scope.totalNumber  = data.totalNumber;
        $scope.curNumber= data.curNumber;
    }else{
        $scope.bookName = '';
        $scope.totalNumber  = '';
        $scope.curNumber= '';
    }

    $scope.checkNullParam = function(){
        if(!($scope.bookName)){
            alert("Please input Book name");
            return 0;
        }

        if(!($scope.totalNumber)){
            alert("Please input totalNumber");
            return 0;
        }

        return 1;
    };

    $scope.addBook = function(){
        bookService.addBook($scope.bookName, $scope.totalNumber,
         function(){
            $location.path("/listBooks");
         });
    };
    

    $scope.editBook = function() {
        var book = shareFactory.getBook();
        bookService.editBook(book.id, $scope.bookName, $scope.totalNumber, $scope.curNumber,
            function(){
                $location.path("/listBooks");
            });
    };

    $scope.submit = function(){
        if(!($scope.checkNullParam())){
            return;
        }
        var update = shareFactory.getUpdate();
        if(update){
            $scope.editBook();
        }else{
            $scope.addBook();
        }
    };

}]);