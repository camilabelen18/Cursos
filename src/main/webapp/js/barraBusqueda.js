const equisInput = document.getElementById("equis-input");
const inputBuscador = document.getElementById("input-buscador");
const btnCerrar = document.getElementById("btn-cerrar");
const buscador = document.querySelector(".contenedor-buscador");
const btnAbrir = document.getElementById("btn-abrir");

inputBuscador.addEventListener("keyup",()=>{
    if(inputBuscador.value.length>0){
        equisInput.style.display="block";
    }
    else{
        equisInput.style.display="none";
    }
});

equisInput.addEventListener("click",()=>{
    inputBuscador.value = "";
    equisInput.style.display="none";
});

btnCerrar.addEventListener("click",()=>{
    buscador.style.display = "none";
    background.style.display = "none";
});

btnAbrir.addEventListener("click",()=>{
    buscador.style.display = "flex";
    background.style.display = "block";
});
