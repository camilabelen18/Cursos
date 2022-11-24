package servicios;

import modelo.*;

public interface ServicioGiftcard {

	Integer verificarTarjetaDeGiftcard(Giftcard giftcard, Integer nroTarjeta);

	void verificarSaldoDeGiftcard(Giftcard giftcard, Curso curso_obtenido);

	void sumarPuntos(Giftcard giftcard);
		
	void agregarPuntos(Giftcard giftcard, Integer puntos);

	void enviarPuntos(Giftcard gc1, Giftcard gc2, Integer puntos);

	void descontarPuntos(Giftcard giftcard, Integer puntos);

	void verificarSaldoDeGiftcard(Giftcard giftcard, Integer puntos);


	//Giftcard obtenerGiftcard(Usuario usuario);

}
