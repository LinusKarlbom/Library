$(document).ready( function() {

		$("#isbn").parent().submit(function(event) {
			var isbn = $("#isbn").val();
			var title = $("#title").val();
			var author = $("#author").val();
			var genre = $("#genre").val();
			var shelf = $("#shelf").val();
			var pages = $("#pages").val();
			event.preventDefault();
			
			if(isbn.length > 0 && title.length > 0 && author.length > 0 && genre.length > 0 && shelf.length > 0 && pages.length > 0){
				var url = "rest/books/";
				var data = '{"isbn":"' + isbn +
					'","title":"' + title +
					'","author":"' + author +
					'","genre":"' + genre +
					'","shelfNumber":"' + shelf +
					'","numberOfPages":"' + pages +
					'","loaned": false}';
				
				$.ajaxSetup({contentType: "application/json; charset=utf-8"});
				$.post(url, data, function (data, status) {
					window.location.href = "bookadded.html"
				}, "json");
			}
			else{
				document.getElementById("resultMessage").innerHTML = "All fields must be filled.";
			}
		});
		
});