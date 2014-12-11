var libraryApp = angular.module('libraryApp', [
    'ngRoute', 'ngAnimate', 'userCtrls','bookCtrls'
]);

libraryApp.config(function($routeProvider) {
    $routeProvider.when('/hello', {
        templateUrl: 'html/hello.html',
        controller: 'HelloCtrl'
    }).when('/list',{
    	templateUrl:'html/books.html',
    	controller:'listBookCtrl'
    }).when('/add', {
        templateUrl: 'html/addbook.html',
        controller: 'addAndUpdateCtrl'
    }).when('/logon', {
        templateUrl: 'html/logon.html',
        controller: 'LogonCtrl'
    }).otherwise({
        redirectTo: '/logon'
    })
});
