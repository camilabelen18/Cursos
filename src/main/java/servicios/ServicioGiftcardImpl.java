package servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Curso;
import modelo.Giftcard;
import modelo.Usuario;
import repositorios.*;

@Service("servicioGiftcard")
@Transactional
public class ServicioGiftcardImpl implements ServicioGiftcard {
	
	private RepositorioGiftcard repositorioGiftcard;

	@Autowired
	public ServicioGiftcardImpl(RepositorioGiftcard repositorioGiftcard) {
		this.repositorioGiftcard = repositorioGiftcard;
	}

	@Override
	public Integer verificarTarjetaDeGiftcard(Giftcard giftcard, Integer nroTarjeta) {
		
		// Se valida el numero de tarjeta de la giftcard
		if (nroTarjeta.equals(giftcard.getNumTarjeta())) {
			
			return nroTarjeta;
		}
		else {
			throw new TarjetaInvalidaException();
		}
	}

	@Override
	public void verificarSaldoDeGiftcard(Giftcard giftcard, Curso curso) {
		
		Double saldoActual = giftcard.getSaldoActual();
		
		// Se valida que el saldo actual de la gitcard sea mayor al precio del curso
		if (saldoActual >= curso.getPrecio()) {
			
			saldoActual = saldoActual - curso.getPrecio();
			giftcard.setSaldoActual(saldoActual);
			repositorioGiftcard.actualizarGiftcard(giftcard);
		}
		else {
			throw new SaldoInsuficienteException();
		}
	}

	@Override
	public void sumarPuntos(Giftcard giftcard) {
		
		Integer puntos = giftcard.getMisPuntos();
		Double saldoActual = giftcard.getSaldoActual();
		
		puntos += 1250;
		
		Double saldo = (double) (puntos / 10);
		
		saldoActual = saldo;
		
		giftcard.setMisPuntos(puntos);
		giftcard.setSaldoActual(saldoActual);
		
		repositorioGiftcard.actualizarGiftcard(giftcard);
	}

	/*
	@Override
	public Giftcard obtenerGiftcard(Usuario usuario) {
		return repositorioGiftcard.obtenerGiftcard(usuario);
	}*/
	

}
