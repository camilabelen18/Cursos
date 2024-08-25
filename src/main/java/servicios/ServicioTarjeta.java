package servicios;

import modelo.*;

public interface ServicioTarjeta {

	Integer verificarTarjetaDePuntos(Tarjeta tarjeta, Integer nroTarjeta);

	void verificarSaldoDeTarjetaDePuntos(Tarjeta tarjeta, Curso curso_obtenido);

    int sumarPuntos(Tarjeta tarjeta);
		
	void enviarPuntos(Tarjeta gc1, Tarjeta gc2, Integer puntos);

	void verificarPuntosDeTarjeta(Tarjeta tarjeta, Integer puntos);

}
