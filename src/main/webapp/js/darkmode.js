
const switchButton = document.getElementById('switch');

document.body.classList.add(localStorage.getItem("mode"));
switchButton.classList.add(localStorage.getItem("btn_status"));

switchButton.addEventListener('click', () => {
	switchButton.classList.toggle('active');
	document.body.classList.toggle('dark');
	localStorage.setItem("mode", document.body.classList.contains('dark') ? 'dark' : 'light');
	localStorage.setItem("btn_status", switchButton.classList.contains('active') ? 'active' : 'none');
});

