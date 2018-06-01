$(document).ready( function() {

		$("#name").parent().submit(function(event) {
			var name = $("#name").val();
			var email = $("#email").val();
			var phonenumber = $("#phonenumber").val();
			var adress = $("#adress").val();
			event.preventDefault();
			
			if(name.length > 0 && email.length > 0 && phonenumber.length > 0 && adress.length > 0){
				var url = "rest/members/";
				var data = '{"name":"' + name +
					'","email":"' + email +
					'","phonenumber":"' + phonenumber +
					'","adress":"' + adress + '"}';
				
				$.ajaxSetup({contentType: "application/json; charset=utf-8"});
				$.post(url, data, function (data, status) {
					window.location.href = "memberregistered.html"
				}, "json");
			}
			else{
				document.getElementById("resultMessage").innerHTML = "All fields must be filled.";
			}
		});
		
});