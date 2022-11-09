package servicios;

import modelo.*;

public interface ServicioGiftcard {

	Integer verificarTarjetaDeGiftcard(Giftcard giftcard, Integer nroTarjeta);

	void verificarSaldoDeGiftcard(Giftcard giftcard, Curso curso_obtenido);

	void sumarPuntos(Giftcard giftcard);



	//Giftcard obtenerGiftcard(Usuario usuario);

}
