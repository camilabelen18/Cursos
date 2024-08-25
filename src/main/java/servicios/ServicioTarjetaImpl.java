package servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.*;
import repositorios.*;

@Service("servicioTarjeta")
@Transactional
public class ServicioTarjetaImpl implements ServicioTarjeta {

	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioTarjetaImpl(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public Integer verificarTarjetaDePuntos(Tarjeta tarjeta, Integer nroTarjeta) {

		// Se valida el numero de tarjeta de la tarjeta
		if (nroTarjeta.equals(tarjeta.getNumTarjeta())) {
			return nroTarjeta;
		}
		else {
			throw new TarjetaInvalidaException();
		}
	}

	@Override
	public void verificarSaldoDeTarjetaDePuntos(Tarjeta tarjeta, Curso curso) {

		Integer puntos = tarjeta.getMisPuntos();
		Double saldoActual = tarjeta.getSaldoActual();
		Double precioCurso = curso.getPrecio();

		// Se valida que el saldo actual de la tarjeta sea mayor al precio del curso
		if (saldoActual >= precioCurso) {

			puntos -= precioCurso.intValue() * 10;

			saldoActual = saldoActual - precioCurso;

			tarjeta.setMisPuntos(puntos);
			tarjeta.setSaldoActual(saldoActual);

			repositorioUsuario.actualizarTarjeta(tarjeta);
		}
		else {
			throw new SaldoInsuficienteException();
		}
	}

	@Override
	public int sumarPuntos(Tarjeta tarjeta) {

		Integer puntosDeLaGiftCard = tarjeta.getMisPuntos();
		Double saldoActual = tarjeta.getSaldoActual();
		Integer puntos = 0;
		Double saldo = 0.0;
		puntos += 2500;
		puntosDeLaGiftCard += puntos;
		saldo = (double) (puntos / 10);
		saldoActual += saldo;
		tarjeta.setMisPuntos(puntosDeLaGiftCard);
		tarjeta.setSaldoActual(saldoActual);
		repositorioUsuario.actualizarTarjeta(tarjeta);
		return puntos;
	}
	
	@Override
	public void enviarPuntos(Tarjeta gc1, Tarjeta gc2, Integer puntos) {
		descontarPuntos(gc1, puntos);
		agregarPuntos(gc2, puntos);

		repositorioUsuario.actualizarTarjeta(gc1);
		repositorioUsuario.actualizarTarjeta(gc2);
	}

	private void agregarPuntos(Tarjeta tarjeta, Integer puntos) {

		Integer pv = tarjeta.getMisPuntos();
		Double saldoActual = tarjeta.getSaldoActual();
		pv += puntos;
		
		Double saldo = (double) (pv / 10);

		saldoActual = saldo;

		tarjeta.setMisPuntos(pv);
		tarjeta.setSaldoActual(saldoActual);
	}

	private void descontarPuntos(Tarjeta tarjeta, Integer puntos) {

		Integer pv = tarjeta.getMisPuntos();
		Double saldoActual = tarjeta.getSaldoActual();
		pv -= puntos;

		Double saldo = (double) (pv / 10);

		saldoActual = saldo;
		tarjeta.setMisPuntos(pv);
		tarjeta.setSaldoActual(saldoActual);
	}

	@Override
	public void verificarPuntosDeTarjeta(Tarjeta tarjeta, Integer puntos) {

		Integer puntosActuales = tarjeta.getMisPuntos();

		// Se valida que el saldo actual de la tarjeta sea mayor al precio del curso
		if (puntosActuales >= puntos) {

		}
		else {
			throw new PuntosInsuficientesException();
		}
	}

}
