$(document).ready( function() {

		function displayResult(data) {
			var table = document.getElementById("table");
			event.preventDefault();
			
			if(data.members.length > 0) {
				table.innerHTML = "<th>Name</th><th>Email</th><th>Adress</th>";
				for (var i = 0; i < data.members.length; i++) {
					$("#table").append($("<tr><th>"+data.members[i].name+"</th><th>"+data.members[i].email+"</th><th>"+data.members[i].adress+"</th></tr>"));
				}
			}
			else{
				table.innerHTML = "<th>No results found.</th>";
			}
		}

		$("#name").parent().submit(function(event) {
			var fieldContent = $("#name").val();
			event.preventDefault();
			
			if(fieldContent.length > 0){
				var url = "rest/members/name/" + fieldContent;
			}
			else{
				var url = "rest/members/";
			}
			$.get(url, function (data) {
				displayResult(data);
			}, "json");
		});
		
});