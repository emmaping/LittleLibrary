var userServices = angular.module('bookServices', []);

userServices.service('bookService', ['$scope',function($scope) {

}]);

userServices.service('shareFactory', [function() {

	var update = false;
	var user;

	this.getUpdate = function(){
		return this.update;
	};

	this.setUpdate = function(update){
		this.update = update;
	};

	this.getBook = function(){
		return this.book;
	};

	this.setbook = function(book){
		this.book = book;
	};
}]);

userServices.service('bookService', ['$http',function ($http) {
	
	this.addBook = function(name,tn, callback){
		$http.post("/library/add", {
            bookName : name,
            totalNumber  : tn,
        }).success(callback);
	};

	this.editBook = function(id,name,tn,cn,callback){
		$http.put("/library/update", {
			bookId  : id,
            bookName  : name,
            totalNumber  : tn,
            curNumber  : cn
		}).success(callback);
	};

	this.deleteBook = function(id, callback){
		$http.delete('/library/delete/' + id).success(callback);
	};

	this.listBooks = function(callback){
		$http.get('/library/list').success(callback);
	};

	this.getBookById = function(id, callback){
		$http.get('/library/get/' + id).success(callback);
	};

}])

