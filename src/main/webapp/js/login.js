function validar() {
	
	var error = 0;
	var mensajesError = "";

	/** Lógica de validación */

	// Validación para el campo email
	var email=document.getElementById("email").value;
	if(email.length == 0) {
		error++;
		mensajesError+="<p>El campo email es obligatorio</p>";
	}

	// Validación para el campo contraseña
	var password=document.getElementById("password").value;
	if(password.length == 0) {
		error++;
		mensajesError+="<p>El campo contraseña es obligatorio</p>";
	}

	// Validación de errores encontrados
	if(error == 0) {
		return true;
	}
	else {
		document.getElementById("mensaje").innerHTML = mensajesError;
		return false;
	}
}