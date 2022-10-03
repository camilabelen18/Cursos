function validar() {
	
	var error = 0;
	var mensajesError = "";

	/** Lógica de validación */

	// Validación para el campo nombre
	if(document.getElementById("nombre").value == '') {
		error++;
		mensajesError+="<p>El campo nombre es obligatorio</p>";
	}

	// Validación para el campo email
	var email=document.getElementById("email").value;
	if(email.length == 0) {
		error++;
		mensajesError+="<p>El campo email es obligatorio</p>";
	}

	// Validación para el campo contraseña
	var contrasenia=document.getElementById("contrasenia").value;
	if(contrasenia.length == 0) {
		error++;
		mensajesError+="<p>El campo contraseña es obligatorio</p>";
	}
	
	// Validación para el campo confirmar contraseña
	var repetirContrasenia=document.getElementById("repetirContrasenia").value;
	if(repetirContrasenia.length == 0) {
		error++;
		mensajesError+="<p>El campo confirmar contraseña es obligatorio</p>";
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