function validateForm() {	
	var login = document.forms["frm"]["login"].value;
	var password = document.forms["frm"]["password"].value;
	
	valid=true;	

	if (login.length == 0) {
		var input = document.getElementById('login');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (login.length != 0) {
		var input = document.getElementById('login');
		input.style.border = "";
	}
	
	if (password.length == 0) {
		var input = document.getElementById('password');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (password.length != 0) {
		var input = document.getElementById('password');
		input.style.border = "";
	}	
	
	return valid;

}