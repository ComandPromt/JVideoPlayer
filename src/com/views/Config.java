package com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;

import com.file.nativ.chooser.DemoJavaFxStage;
import com.spinner.simple.Spinner;

@SuppressWarnings("all")

public class Config extends javax.swing.JFrame {

	private Spinner volumen;

	private JTextField ruta;

	public Config() throws IOException {

		setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/images/settings.png")));

		getContentPane().setBackground(Color.WHITE);

		setTitle("JVideoPlayer  - Config");

		initComponents();

		setVisible(true);

		try {

			File archivo = new File("config.dat");

			if (archivo.exists()) {

				ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream("config.dat"));

				DisplayFrame.config = leyendoFichero.readObject().toString().split(",");

				leyendoFichero.close();

				if (DisplayFrame.config[0] != null) {

					volumen.setValor(Integer.parseInt(DisplayFrame.config[0]));

				}

				if (DisplayFrame.config[1] != null) {

					ruta.setText(DisplayFrame.config[1]);

				}

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void abrirCarpeta(String ruta) throws IOException {

		if (ruta != null && !ruta.equals("") && !ruta.isEmpty()) {

			try {

				if (System.getProperty("os.name").contains("Linux")) {

					Runtime.getRuntime().exec("xdg-open " + ruta);

				}

				else if (System.getProperty("os.name").contains("Mac")) {

					Runtime.getRuntime().exec("open " + ruta);

				}

				else {

					Runtime.getRuntime().exec("cmd /c C:\\Windows\\explorer.exe " + "\"" + ruta + "\"");

				}

			}

			catch (IOException e) {

			}
		}

	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		JButton btnNewButton = new JButton("");

		btnNewButton.setContentAreaFilled(false);

		btnNewButton.setBorder(null);

		btnNewButton.setIcon(new ImageIcon(Config.class.getResource("/images/save.png")));

		btnNewButton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				try {

					ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream("config.dat"));

					escribiendoFichero.writeObject(new Objeto(volumen.getValor() + "," + ruta.getText()));

					escribiendoFichero.close();

					DisplayFrame.config = (volumen.getValor() + "," + ruta.getText()).split(",");

					dispose();

				}

				catch (Exception e1) {

				}

			}

		});

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setBackground(Color.WHITE);

		lblNewLabel.setIcon(new ImageIcon(Config.class.getResource("/images/sound.png")));

		volumen = new Spinner();

		volumen.setMaxValor(120);

		volumen.setMinValor(0);

		JLabel lblNewLabel_1 = new JLabel("");

		lblNewLabel_1.setBackground(Color.WHITE);

		lblNewLabel_1.setIcon(new ImageIcon(Config.class.getResource("/images/photo.png")));

		ruta = new JTextField();

		ruta.setBackground(Color.WHITE);

		ruta.setEditable(false);

		ruta.setColumns(10);

		JButton btnNewButton_1 = new JButton("");

		btnNewButton_1.setContentAreaFilled(false);

		btnNewButton_1.setBorder(null);

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				try {

					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					DemoJavaFxStage test = new DemoJavaFxStage();

					LinkedList<File> lista = new LinkedList<File>();

					lista = test.showOpenFileDialog(true, "");

					LinkedList<String> listaVideos;

					if (!lista.isEmpty()) {

						ruta.setText(lista.get(0).toString());

					}

				}

				catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {

				}

			}

		});

		btnNewButton_1.setBackground(Color.WHITE);

		btnNewButton_1.setIcon(new ImageIcon(Config.class.getResource("/images/abrir.png")));

		JButton btnNewButton_2 = new JButton("");

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				if (!ruta.getText().isEmpty()) {

					try {

						abrirCarpeta(ruta.getText());

					}

					catch (IOException e1) {

					}

				}

			}

		});

		btnNewButton_2.setContentAreaFilled(false);

		btnNewButton_2.setBorder(null);

		btnNewButton_2.setIcon(new ImageIcon(Config.class.getResource("/images/folder.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(19).addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(volumen, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(ruta, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6).addComponent(btnNewButton_2))
								.addGroup(layout.createSequentialGroup().addGap(184).addComponent(btnNewButton)))
						.addContainerGap(34, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(38)
						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ruta, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE).addComponent(btnNewButton)
						.addGap(19))
				.addGroup(layout.createSequentialGroup().addGap(30)
						.addComponent(volumen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(69, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addGap(40).addComponent(lblNewLabel).addContainerGap(77,
						Short.MAX_VALUE)));

		getContentPane().setLayout(layout);

		setSize(new Dimension(424, 188));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}

}
