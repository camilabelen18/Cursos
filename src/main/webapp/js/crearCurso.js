function validar() {

	var error = 0;
	var mensajesError = "";



	// Validación para el campo nombre
	if(document.getElementById("nombre").value == '') {
		error++;
		mensajesError+="<p>El campo nombre es obligatorio</p>";
	}

	// Validación para el campo categoria
	var categoria=document.getElementById("categoria").value;
	if(categoria.length == 0) {
		error++;
		mensajesError+="<p>El campo categoria es obligatorio</p>";
	}

	// Validación para el campo descripcion
	var descripcion=document.getElementById("descripcion").value;
	if(descripcion.length == 0) {
		error++;
		mensajesError+="<p>El campo descripcion es obligatorio</p>";
	}

	// Validación para el campo precio
	var precio=document.getElementById("precio").value;
	if(precio.length == 0) {
		error++;
		mensajesError+="<p>El campo precio es obligatorio</p>";
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
