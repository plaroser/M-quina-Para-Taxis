package UI;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.text.Element;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import Containers.Container;
import Models.*;

import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NuevoTayecto {

	private JFrame frame;
	private JTextField textNumeroTrayecto, textDiaActual, txtHoraInicio, txtHoraFin, txtPreciototal, txtDineropagado;

	private JPanel panelTiempo;

	private JLabel lblNTrayectoDel, lblHoraInicio, lblHoraFin, lblDiaActual, lblSuplementos, lblPreciototal,
			lblDineropagado;

	private JButton btnInicio, btnFin, btnPagado;

	private JPanel panelSuplementos, panelPrecio;

	private JToggleButton tglbtnNoche, tglbtnAeropuerto;
	private JButton btnCalcularPrecio;
	private JButton btnSeleccionarSuplementos;
	private Trayecto trayectoActual;
	private JLabel lblContador;
	private Tiempo t;

	private int minutosTrancurridos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoTayecto window = new NuevoTayecto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevoTayecto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		panelTiempo = new JPanel();
		lblNTrayectoDel = new JLabel("N\u00BA Trayecto del dia");
		textNumeroTrayecto = new JTextField();
		textDiaActual = new JTextField();

		txtHoraInicio = new JTextField();
		lblHoraInicio = new JLabel("Hora Inicio");
		txtHoraFin = new JTextField();
		lblHoraFin = new JLabel("Hora Fin");
		btnInicio = new JButton("Inicio");

		btnFin = new JButton("Fin");

		btnFin.setEnabled(false);
		panelSuplementos = new JPanel();
		lblSuplementos = new JLabel("Suplementos");
		tglbtnNoche = new JToggleButton("Noche");

		tglbtnAeropuerto = new JToggleButton("Aeropuerto");

		btnCalcularPrecio = new JButton("Calcular Precio");

		lblDiaActual = new JLabel("Dia Actual");
		panelPrecio = new JPanel();
		txtPreciototal = new JTextField();
		lblPreciototal = new JLabel("PrecioTotal");
		txtDineropagado = new JTextField();

		lblDineropagado = new JLabel("DineroPagado");
		btnPagado = new JButton("Pagado");

		btnSeleccionarSuplementos = new JButton("Seleccionar Suplementos");

		lblContador = new JLabel("Contador");

		Container.trayectoActivo = new Trayecto(new LocalTime());
		setComponetProperties();
		setComponentAdapters();
	}

	private void setComponentAdapters() {
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnInicio.isEnabled()) {

					Container.trayectoActivo = new Trayecto(new LocalTime());
					textDiaActual.setText(Container.trayectoActivo.getDInicio().toString());
					tglbtnNoche.setSelected(Container.trayectoActivo.isBolSuplementoNoche());

					txtHoraInicio.setText(Container.trayectoActivo.getDInicioTrayecto().toString());
					btnFin.setEnabled(true);
					btnInicio.setEnabled(false);

					t = new Tiempo();
					t.Contar(lblContador, txtPreciototal);
					txtPreciototal.setText(String.valueOf(minutosTrancurridos));

				}
			}
		});

		btnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnFin.isEnabled()) {
					t.Detener();
					enabledSuplementos(true);
					enabledTiempo(false);
					tglbtnNoche.setSelected(Container.trayectoActivo.isBolSuplementoNoche());
					LocalTime tiempoFin = new LocalTime();
					Container.trayectoActivo.setDFinTrayecto(tiempoFin);
					txtHoraFin.setText(tiempoFin.toString());
				}
			}
		});

		tglbtnAeropuerto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglbtnAeropuerto.isEnabled()) {
					if (Container.trayectoActivo.isBolSuplementoAeropuerto()) {
						Container.trayectoActivo.setFlPrecioTotal(Container.trayectoActivo.getFlPrecioTotal() - 5.5f);
					} else {
						Container.trayectoActivo.setFlPrecioTotal(Container.trayectoActivo.getFlPrecioTotal() + 5.5f);
					}
					txtPreciototal.setText(String
							.valueOf((float) Math.floor(Container.trayectoActivo.getFlPrecioTotal() * 100) / 100));
					Container.trayectoActivo
							.setBolSuplementoAeropuerto(!Container.trayectoActivo.isBolSuplementoAeropuerto());
				}
			}
		});

		tglbtnNoche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglbtnNoche.isEnabled()) {
					if (Container.trayectoActivo.isBolSuplementoNoche()) {
						Container.trayectoActivo.setFlPrecioTotal(Container.trayectoActivo.getFlPrecioTotal() - 2.0f);
					} else {
						Container.trayectoActivo.setFlPrecioTotal(Container.trayectoActivo.getFlPrecioTotal() + 2.0f);
					}
					txtPreciototal.setText(String
							.valueOf((float) Math.floor(Container.trayectoActivo.getFlPrecioTotal() * 100) / 100));
					Container.trayectoActivo.setBolSuplementoNoche(!Container.trayectoActivo.isBolSuplementoNoche());
				}
			}
		});

		btnCalcularPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnCalcularPrecio.isEnabled()) {
					enabledSuplementos(false);
					enabledPrecio(true);
				}
			}
		});

		btnSeleccionarSuplementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnSeleccionarSuplementos.isEnabled()) {
					enabledSuplementos(true);
					enabledPrecio(false);
				}
			}
		});

		btnPagado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnPagado.isEnabled()) {
					if (Container.REGEX_EUR.matcher(txtDineropagado.getText()).matches()) {
						String strAux = "";
						float total = Container.trayectoActivo.getFlPrecioTotal();
						float pagado = Float.parseFloat(txtDineropagado.getText());
						if (pagado >= total) {
							strAux += "El importe total es:\t" + String.valueOf(total) + " Eur\n";
							strAux += "El cliente le ha dado:\t" + String.valueOf(pagado) + " Eur\n";
							strAux += "El cambio es:\t\t"
									+ String.valueOf(Math.floor(((pagado - total) + 0.01f) * 100) / 100) + "Eur\n";
							strAux += "¿Es todo correcto?";
							if (JOptionPane.showConfirmDialog(frame, strAux, null,
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								Container.listaTrayectos.add(Container.trayectoActivo);
								Container.trayectoActivo = null;
								new PantallaPrincipal().getFrame().setVisible(true);
								frame.dispose();
							}
						} else {
							JOptionPane.showMessageDialog(frame,
									"No ha introducido dinero suficiente para pagar el trayecto.");
						}
					} else {
						JOptionPane.showMessageDialog(frame, "No ha introducido un importe valido.");
					}
				}
			}
		});

		txtDineropagado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && caracter != (char) 46 && (caracter != '\b')) {
					e.consume();
				}
			}
		});

	}

	private void setComponetProperties() {
		frame.setBounds(100, 100, 864, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelTiempo.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelTiempo.setBounds(12, 13, 822, 241);
		frame.getContentPane().add(panelTiempo);
		panelTiempo.setLayout(null);

		lblNTrayectoDel.setBounds(169, 36, 129, 16);
		panelTiempo.add(lblNTrayectoDel);

		textNumeroTrayecto.setBounds(12, 13, 129, 63);
		panelTiempo.add(textNumeroTrayecto);
		textNumeroTrayecto.setEditable(false);
		textNumeroTrayecto.setColumns(10);

		textDiaActual.setBounds(335, 23, 158, 53);
		panelTiempo.add(textDiaActual);
		textDiaActual.setEditable(false);
		textDiaActual.setColumns(10);

		txtHoraInicio.setBounds(12, 89, 129, 60);
		panelTiempo.add(txtHoraInicio);
		txtHoraInicio.setEditable(false);
		txtHoraInicio.setColumns(10);

		lblHoraInicio.setBounds(169, 111, 83, 16);
		panelTiempo.add(lblHoraInicio);

		txtHoraFin.setBounds(12, 162, 129, 60);
		panelTiempo.add(txtHoraFin);
		txtHoraFin.setEditable(false);
		txtHoraFin.setColumns(10);

		lblHoraFin.setBounds(169, 184, 56, 16);
		panelTiempo.add(lblHoraFin);

		btnInicio.setBounds(335, 87, 158, 64);
		panelTiempo.add(btnInicio);

		btnFin.setBounds(333, 161, 160, 63);
		panelTiempo.add(btnFin);

		lblDiaActual.setBounds(510, 36, 92, 16);
		panelTiempo.add(lblDiaActual);

		panelSuplementos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSuplementos.setBounds(12, 267, 265, 343);
		frame.getContentPane().add(panelSuplementos);
		panelSuplementos.setLayout(null);

		lblSuplementos.setBounds(12, 13, 142, 16);
		panelSuplementos.add(lblSuplementos);

		tglbtnNoche.setBounds(12, 58, 241, 80);
		panelSuplementos.add(tglbtnNoche);

		tglbtnAeropuerto.setBounds(12, 151, 241, 87);
		panelSuplementos.add(tglbtnAeropuerto);

		btnCalcularPrecio.setBounds(12, 251, 241, 80);
		panelSuplementos.add(btnCalcularPrecio);

		panelPrecio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelPrecio.setBounds(289, 267, 545, 343);
		frame.getContentPane().add(panelPrecio);
		panelPrecio.setLayout(null);

		txtPreciototal.setEditable(false);
		txtPreciototal.setText("PrecioTotal");
		txtPreciototal.setBounds(12, 20, 176, 58);
		panelPrecio.add(txtPreciototal);
		txtPreciototal.setColumns(10);

		lblPreciototal.setBounds(200, 41, 110, 16);
		panelPrecio.add(lblPreciototal);

		txtDineropagado.setText("");
		txtDineropagado.setBounds(12, 103, 176, 58);
		panelPrecio.add(txtDineropagado);
		txtDineropagado.setColumns(10);

		lblDineropagado.setBounds(200, 124, 110, 16);
		panelPrecio.add(lblDineropagado);

		btnPagado.setBounds(364, 26, 169, 212);
		panelPrecio.add(btnPagado);

		btnSeleccionarSuplementos.setBounds(12, 174, 271, 64);
		panelPrecio.add(btnSeleccionarSuplementos);

		textDiaActual.setText(String.valueOf(new LocalDate()));

		textNumeroTrayecto.setText(String.valueOf(Container.listaTrayectos.size() + 1));

		lblContador.setBounds(546, 111, 56, 16);
		panelTiempo.add(lblContador);

		enabledPrecio(false);
		enabledSuplementos(false);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void enabledSuplementos(boolean activo) {
		for (Component e : panelSuplementos.getComponents()) {
			e.setEnabled(activo);
		}
	}

	public void enabledTiempo(boolean activo) {
		for (Component e : panelTiempo.getComponents()) {
			e.setEnabled(activo);
		}
	}

	public void enabledPrecio(boolean activo) {
		for (Component e : panelPrecio.getComponents()) {
			e.setEnabled(activo);
		}
	}
}
