package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import Containers.Container;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal {

	private JFrame frame;
	private JButton btnNuevoTayecto, btnCajaDelDia;
	private JToggleButton tglbtnEsfestivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal window = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		btnNuevoTayecto = new JButton("Nuevo Tayecto");

		btnCajaDelDia = new JButton("Caja del Dia");

		tglbtnEsfestivo = new JToggleButton("Hoy es festivo");

		setComponetProperties();
		setComponentAdapters();
	}

	private void setComponentAdapters() {
		btnNuevoTayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NuevoTayecto().getFrame().setVisible(true);
				frame.dispose();
				Container.esDiaFestivo = tglbtnEsfestivo.isSelected();
			}
		});
	}

	private void setComponetProperties() {
		frame.setBounds(500, 500, 556, 436);
		btnNuevoTayecto.setBounds(12, 13, 240, 234);
		frame.getContentPane().add(btnNuevoTayecto);

		btnCajaDelDia.setBounds(264, 13, 268, 234);
		frame.getContentPane().add(btnCajaDelDia);

		tglbtnEsfestivo.setBounds(12, 260, 520, 121);
		frame.getContentPane().add(tglbtnEsfestivo);

	}
}
