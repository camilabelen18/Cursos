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
		} else {
			throw new TarjetaInvalidaException();
		}
	}

	@Override
	public void verificarSaldoDeGiftcard(Giftcard giftcard, Curso curso) {

		Integer puntos = giftcard.getMisPuntos();
		Double saldoActual = giftcard.getSaldoActual();
		Double precioCurso = curso.getPrecio();

		// Se valida que el saldo actual de la gitcard sea mayor al precio del curso
		if (saldoActual >= precioCurso) {

			puntos -= precioCurso.intValue() * 10;

			saldoActual = saldoActual - precioCurso;

			giftcard.setMisPuntos(puntos);
			giftcard.setSaldoActual(saldoActual);

			repositorioGiftcard.actualizarGiftcard(giftcard);
		} else {
			throw new SaldoInsuficienteException();
		}
	}

	@Override
	public int sumarPuntos(Giftcard giftcard,int notaSacada) {

		Integer puntosDeLaGiftCard = giftcard.getMisPuntos();
		Double saldoActual = giftcard.getSaldoActual();
		Integer puntos = 0;
		Double saldo = 0.0;
	//	System.out.println("ACA MIRA EL SALDO QUE TENIAS: ");
	//	System.out.println(saldoActual);

		if(notaSacada >= 6 && notaSacada <= 7) {
			
			puntos += 1100;
			
			puntosDeLaGiftCard += puntos;
			
			 saldo = (double) (puntos / 10);
			
		//	System.out.println("ACA MIRA lo que conseguiste de saldo ");
		//	System.out.println(saldo);
			
			saldoActual += saldo;
		}  
		if (notaSacada == 8 ) {
			
	        puntos += 1200;
			
	        puntosDeLaGiftCard += puntos;
	        
		    saldo = (double) (puntos / 10);
			
			saldoActual += saldo;
		} 
		else if(notaSacada >= 9 && notaSacada <= 10) {
			
			puntos += 1300;
			
			puntosDeLaGiftCard += puntos;
				
			 saldo = (double) (puntos / 10);
			
		//	System.out.println("ACA MIRA lo que conseguiste de saldo ");
		//	System.out.println(saldo);
				
			saldoActual += saldo;
		}
		
	//	System.out.println("ACA MIRA EL SALDO ACTUAL: ");
	//	System.out.println(saldoActual);
		
		giftcard.setMisPuntos(puntosDeLaGiftCard);
		giftcard.setSaldoActual(saldoActual);

		repositorioGiftcard.actualizarGiftcard(giftcard);
		
		return puntos;
	}

	@Override
	public void agregarPuntos(Giftcard giftcard, Integer puntos) {

		Integer pv = giftcard.getMisPuntos();
		Double saldoActual = giftcard.getSaldoActual();
		pv += puntos;
		
		Double saldo = (double) (pv / 10);

		saldoActual = saldo;

		giftcard.setMisPuntos(pv);
		giftcard.setSaldoActual(saldoActual);
	}

	@Override
	public void descontarPuntos(Giftcard giftcard, Integer puntos) {

		Integer pv = giftcard.getMisPuntos();
		Double saldoActual = giftcard.getSaldoActual();
		pv -= puntos;

		Double saldo = (double) (pv / 10);

		saldoActual = saldo;
		giftcard.setMisPuntos(pv);
		giftcard.setSaldoActual(saldoActual);
	}
	
	@Override
	public void enviarPuntos(Giftcard gc1, Giftcard gc2, Integer puntos) {
		descontarPuntos(gc1, puntos);
		agregarPuntos(gc2, puntos);
		
		repositorioGiftcard.actualizarGiftcard(gc1);
		repositorioGiftcard.actualizarGiftcard(gc2);

	}
	@Override
	public void verificarSaldoDeGiftcard(Giftcard giftcard, Integer puntos) {

		Integer puntosActuales = giftcard.getMisPuntos();


		// Se valida que el saldo actual de la gitcard sea mayor al precio del curso
		if (puntosActuales >= puntos) {

			
		} else {
			throw new PuntosInsuficientesException();
		}
	}

	/*
	 * @Override public Giftcard obtenerGiftcard(Usuario usuario) { return
	 * repositorioGiftcard.obtenerGiftcard(usuario); }
	 */

}
