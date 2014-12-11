var libraryApp= angular.module('libraryApp', [
    'ngRoute', 'ngAnimate', 'bookCtrls','userCtrls'
]);

libraryApp.config(function($routeProvider) {
    $routeProvider.when('/hello', {
        templateUrl: 'html/hello.html',
        controller: 'HelloCtrl'
    }).when('/listOwnBooks/:userId',{
    	templateUrl:'html/books.html',
    	controller:'listBookCtrl'
    }).when('/listBooks/',{
    	templateUrl:'html/books.html',
    	controller:'listBookCtrl'
    }).when('/add', {
        templateUrl: 'html/addbook.html',
        controller: 'addAndUpdateCtrl'
    }).when('/edit', {
        templateUrl: 'html/editbook.html',
        controller: 'addAndUpdateCtrl'
    }).when('/logon', {
        templateUrl: 'html/logon.html',
        controller: 'LogonCtrl'
    }).when('/registry', {
        templateUrl: 'html/registry.html',
        controller: 'RegistryCtrl'
    }).otherwise({
        redirectTo: '/logon'
    })
});
