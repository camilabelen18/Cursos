$(document).ready(function () {
    /*
         Acá se crearía un formulario con selectores, se agrega la funcionalidad de que si aprobas con 7 (son 10 puntos) te dan los puntos, 
         si desaprobas no te da los puntos 
        Puntos atributo en usuario
        Estos puntos te los da la pagina al finalizar los cursos y habiendo hecho un examen que te valide haber terminado los cursos
         */

    //Variables para hacer la funcion      
    var resultado = "";
    var puntosDeLaPagina = 0;
    var variableDeSumaDeNota = 0;
    var nota = 0;
    var resultadoTotal = document.createElement("div");
    var cambiar = document.getElementById("cambiar");


    //Funcion para sumar la nota cuando tocas un input   
    function sumarNota() {

        if ($("input[name=respuesta]:checked").val()) {

            variableDeSumaDeNota = (Math.random() * 100);

            if (variableDeSumaDeNota > 25) {
                nota += 1;
            }
            else {
                nota += 0;
            }



        }

        if (nota >= 7) {
            resultado = "Aprobado"
            puntosDeLaPagina += 125;
            resultadoTotal.innerHTML = `<h3>Nota : </h3>${nota} <h4>Puntos adquiridos por hacer bien el examen : </h4> ${puntosDeLaPagina} <h4>Estado : </h4>  ${resultado} `;
        }
        else {
            resultado = "Desaprobado"
            puntosDeLaPagina += 0;
            resultadoTotal.innerHTML = `<h3>Nota : </h3>${nota} <h4>Puntos adquiridos por hacer el examen : </h4> ${puntosDeLaPagina} <h4>Estado : </h4>  ${resultado} `;

        }


        //console.log(resultado);
        //  console.log(variableDeSumaDeNota);
        console.log(nota);
        console.log(puntosDeLaPagina);
        console.log(resultadoTotal);

    }



    //El ciclo for que saca todos los input name respuesta y a cada uno le agrega el evento click y la funcion sumarNota 
    document.querySelectorAll("input[name=respuesta]").forEach(function (elemento) {
        //console.log(elemento);
        elemento.addEventListener("click", sumarNota);

    });

    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    //Agrega el div de resultadoTotal a la pagina, esto deberia funcionar una ves que uno toca en finalizado, pero puedo usar un hide creo
    function mostrarResultado() {

        var contenido = document.querySelector("#contenido");

        contenido.innerHTML = "";

        contenido.appendChild(resultadoTotal);

    }


    mostrarResultado();



    cambiar.onclick = function () {
        if (cambiar.value == "Finalizado") {
            document.getElementById("contenido").style.display = "none";
            cambiar.value = "Finalizar";
        } else {
            cambiar.value = "Finalizado";
            document.getElementById("contenido").style.display = "block";
        }
    }












});

