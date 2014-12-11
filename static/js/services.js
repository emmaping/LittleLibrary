var bookServices = angular.module('bookServices', []);

bookServices.service('bookService', ['$scope',function($scope) {

}]);

bookServices.service('shareFactory', [function() {

	var update = false;
	var book;
	var userId;

	this.getUpdate = function(){
		return this.update;
	};

	this.setUpdate = function(update){
		this.update = update;
	};

	this.getBook = function(){
		return this.book;
	};

	this.setBook = function(book){
		this.book = book;
	};
	
	this.getUserId = function(){
		return this.userId;
	};
	
	this.setUserId = function(userId){
		this.userId = userId;
	};
}]);

bookServices.service('bookService', ['$http',function ($http) {
	
	this.addBook = function(name,tn,callback){
		$http.post("/library/add", {
            bookName : name,
            totalNumber  : tn,
            curNumber : tn,
        }).success(callback);
	};

	this.editBook = function(id,name,tn,cn,callback){
		$http.put("/library/update", {
			id        : id,
            bookName : name,
            totalNumber  : tn,
            curNumber: cn,
		}).success(callback);
	};
	
	this.borrowBook = function(bookId, userId,callback){
		$http.put("/user/" + userId + "/borrow/" + bookId).success(callback);
	};
	
	this.returnBook = function(bookId, userId,callback){
		$http.put("/user/" + userId + "/return/" + bookId).success(callback);
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

