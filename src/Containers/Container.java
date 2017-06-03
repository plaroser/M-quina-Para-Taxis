package Containers;

import Models.Cola;
import Models.Trayecto;

public class Container {
	public static final float BANDERA_FESTIVOS = 4.55F;
	public static final float BANDERA_NORMALES = 3.65F;
	public static final float PRECIO_MINUTO_NORMALES = 0.18F;
	public static final float PRECIO_MINUTO_FESTIVOS = 0.26F;

	public static Cola<Trayecto> listaTrayectos = new Cola<Trayecto>(
			(Trayecto t1, Trayecto t2) -> t1.getDInicioTrayecto().compareTo(t2.getDInicioTrayecto()));

	public static boolean esDiaFestivo = false;

	public static Trayecto trayectoActivo;

}
