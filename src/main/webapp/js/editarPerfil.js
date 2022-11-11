/**
 * 
 */function validar() {
	
	var error = 0;
	var mensajesError = "";

	/** Lógica de validación */

	// Validación para el campo email
	var passwordNueva=document.getElementById("passwordUsuarioNueva").value;
	var passwordAnterior=document.getElementById("passwordAnterior").value;
	if(passwordNueva == passwordAnterior) {
		error++;
		mensajesError+="<p>La nueva contraseñia es igual a la anterior</p>";
	}

	// Validación para el campo contraseña
	var passwordNueva=document.getElementById("passwordNueva").value;
	var passwordUsuarioRepetir=document.getElementById("passwordUsuarioRepetir").value;
	if(passwordNueva != passwordUsuarioRepetir) {
		error++;
		mensajesError+="<p>Las nuevas contraseñas deben ser iguales</p>";
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