
const switchButton = document.getElementById('switch');

switchButton.addEventListener('click', () => {
	switchButton.classList.toggle('active');
	document.body.classList.toggle('dark');
	document.getElementById("cont-header").classList.toggle('dark');
	document.getElementById("cont-contacto").classList.toggle('dark');
	document.getElementById("contMiGiftcard-2").classList.toggle('dark');
});