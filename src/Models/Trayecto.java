package Models;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import Containers.Container;

public class Trayecto {
	private LocalTime TInicioTrayecto;
	private LocalTime TFinTrayecto;
	private LocalDate DInicio;
	private boolean bolSuplementoNoche;
	private boolean bolSuplementoAeropuerto;
	private boolean bolFestivo;
	private float flPrecioTotal;

	public Trayecto(LocalTime dInicioTrayecto) {
		super();
		TInicioTrayecto = dInicioTrayecto;
		this.bolFestivo = Container.esDiaFestivo;
		boolean despuesHoraInicioNoche = (TInicioTrayecto.isAfter(new LocalTime(21, 00)));
		boolean antesHoraFinNoche = (TInicioTrayecto.isBefore(new LocalTime(06, 00)));
		this.bolSuplementoNoche = despuesHoraInicioNoche || antesHoraFinNoche;

		this.DInicio = new LocalDate();

	}

	public LocalTime getDInicioTrayecto() {
		return TInicioTrayecto;
	}

	public void setDInicioTrayecto(LocalTime dInicioTrayecto) {
		TInicioTrayecto = dInicioTrayecto;
	}

	public LocalTime getDFinTrayecto() {
		return TFinTrayecto;
	}

	public void setDFinTrayecto(LocalTime dFinTrayecto) {
		TFinTrayecto = dFinTrayecto;
	}

	public boolean isBolSuplementoNoche() {
		return bolSuplementoNoche;
	}

	public void setBolSuplementoNoche(boolean bolSuplementoNoche) {
		this.bolSuplementoNoche = bolSuplementoNoche;
	}

	public boolean isBolSuplementoAeropuerto() {
		return bolSuplementoAeropuerto;
	}

	public void setBolSuplementoAeropuerto(boolean bolSuplementoAeropuerto) {
		this.bolSuplementoAeropuerto = bolSuplementoAeropuerto;
	}

	public boolean isBolFestivo() {
		return bolFestivo;
	}

	public void setBolFestivo(boolean bolFestivo) {
		this.bolFestivo = bolFestivo;
	}

	public float getFlPrecioTotal() {
		return flPrecioTotal;
	}

	public void setFlPrecioTotal(float flPrecioTotal) {
		this.flPrecioTotal = flPrecioTotal;
	}

	public LocalDate getDInicio() {
		return DInicio;
	}

	public void setDInicio(LocalDate dInicio) {
		DInicio = dInicio;
	}

}
