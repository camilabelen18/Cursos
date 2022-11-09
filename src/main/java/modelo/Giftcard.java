package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Gifcards")
public class Giftcard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Identificador")
	private int id;
	
	@Column(name ="numero_tarjeta")
	private Integer numTarjeta;
	
	@Column(name ="puntos")
	private Integer misPuntos;
	
	@Column(name ="saldo")
	private Double saldoActual;
	
	
	public Giftcard() {}

	public Giftcard(Integer numTarjeta, Integer misPuntos, Double saldoActual) {
		this.numTarjeta = numTarjeta;
		this.misPuntos = misPuntos;
		this.saldoActual = saldoActual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(Integer numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public Integer getMisPuntos() {
		return misPuntos;
	}

	public void setMisPuntos(Integer misPuntos) {
		this.misPuntos = misPuntos;
	}

	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}

	@Override
	public String toString() {
		return "Giftcard [id=" + id + ", numTarjeta=" + numTarjeta + ", misPuntos=" + misPuntos + ", saldoActual="
				+ saldoActual + "]";
	}
	
}
