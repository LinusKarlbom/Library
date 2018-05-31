$(document).ready( function() {

		function displayResult(data) {
			var table = document.getElementById("table");
			var nextLine;
			if(table === null) {
				table = $('<table id="table"></table>');
				$("footer").before(table);
				table = document.getElementById("table");
			}
			
			if(data.books.length > 0) {
				table.innerHTML = "<th>Title</th><th>Author</th><th>ISBN</th>";
				for (var i = 0; i < data.books.length; i++) {
					$("#table").append($("<tr><th>"+data.books[i].title+"</th><th>"+data.books[i].author+"</th><th>"+data.books[i].isbn+"</th></tr>"));
				}
			}
			else{
				table.innerHTML = "<th>No results found.</th>";
			}
		}

		$("#title").parent().submit(function(event) {
			event.preventDefault();
			var fieldContent = $("#title").val();
			if(fieldContent.length > 0){
				var url = "rest/books/title/" + fieldContent;
			}
			else{
				var url = "rest/books/";
			}
			var get = $.get(url, function (data) {
				displayResult(data);
			}, "json");
		});
		
		$("#author").parent().submit(function(event) {
			event.preventDefault();
			var fieldContent = $("#author").val();
			if(fieldContent.length > 0){
				var url = "rest/books/author/" + fieldContent;
			}
			else{
				var url = "rest/books/";
			}
			var get = $.get(url, function (data) {
				displayResult(data);
			}, "json");
		});
		
		$("#isbn").parent().submit(function(event) {
			event.preventDefault();
			var fieldContent = $("#isbn").val();
			if(fieldContent.length > 0){
				var url = "rest/books/isbn/" + fieldContent;
			}
			else{
				var url = "rest/books/";
			}
			var get = $.get(url, function (data) {
				displayResult(data);
			}, "json");
		});
		
});