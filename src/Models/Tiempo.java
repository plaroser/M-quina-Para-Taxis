package Models;

import java.awt.TextField;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Containers.Container;

public class Tiempo {
	private static Timer timer = new Timer();
	private static int segundos = 0;
	private static int horas = 0;
	private static int minutos = 0;
	private static JLabel lblTiempo;
	private static JTextField txtDineroPagado;

	// Clase interna que funciona como contador
	static class Contador extends TimerTask {
		public void run() {
			int diaDeSemana = Container.trayectoActivo.getDInicio().getDayOfWeek();
			float precioFinal = 0.0f;
			if (segundos >= 59) {
				segundos = 0;
				minutos++;
			}
			if (minutos >= 59) {
				horas++;
			}
			if (diaDeSemana >= 6 || Container.trayectoActivo.isBolFestivo()) {
				precioFinal += Container.BANDERA_FESTIVOS;
				precioFinal += minutos * Container.PRECIO_MINUTO_FESTIVOS;
			} else {
				precioFinal += Container.BANDERA_NORMALES;
				precioFinal += minutos * Container.PRECIO_MINUTO_NORMALES;
			}
			if (Container.trayectoActivo.isBolSuplementoNoche())
				precioFinal += 2;
			if (Container.trayectoActivo.isBolSuplementoAeropuerto())
				precioFinal += 5.5f;
			precioFinal = (float) Math.floor(precioFinal * 100) / 100;
			segundos++;
			Container.trayectoActivo.setFlPrecioTotal(precioFinal);
			txtDineroPagado.setText(String.valueOf(precioFinal) + " Eur");
			lblTiempo.setText(String.valueOf(horas) + ":" + String.valueOf(minutos) + ":" + String.valueOf(segundos));
		}
	}

	// Crea un timer, inicia segundos a 0 y comienza a contar
	public void Contar(JLabel lblTiempo, JTextField txtDineropagado) {
		segundos = 0;
		timer = new Timer();
		timer.schedule(new Contador(), 0, 1000);
		this.lblTiempo = lblTiempo;
		this.txtDineroPagado = txtDineropagado;
	}

	// Detiene el contador
	public void Detener() {
		timer.cancel();
	}

	// Metodo que retorna los segundos transcurridos
	public double getMin() {
		return segundos / 60;
	}

	public int getSegundos() {
		return segundos;
	}
}
