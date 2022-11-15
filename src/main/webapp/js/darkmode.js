const colorSwitch = document.querySelector('#switch input[type="checkbox"]');
const switchButton = document.getElementById('switch');

function cambiaTema(ev){
	
	if(ev.target.checked){
		document.documentElement.setAttribute('tema', 'light');
	}
	else {
		document.documentElement.setAttribute('tema', 'dark');
	}
}

colorSwitch.addEventListener('change', cambiaTema);

switchButton.addEventListener('click', () => {
	document.body.classList.toggle('dark'); //toggle the HTML body the class 'dark'
	switchButton.classList.toggle('active');//toggle the HTML button with the id='switch' with the class 'active'
});