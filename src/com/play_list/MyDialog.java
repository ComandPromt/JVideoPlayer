package com.play_list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MyDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtAreYouSure;

	private JButton okButton;

	private JButton cancelButton;

	public MyDialog() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(MyDialog.class.getResource("/images/icon.png")));

		setAlwaysOnTop(true);

		setSize(350, 115);

		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.setLayout(new BorderLayout(0, 0));

		txtAreYouSure = new JTextField();

		txtAreYouSure.setEditable(false);

		txtAreYouSure.setForeground(new Color(255, 0, 0));

		txtAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);

		contentPanel.add(txtAreYouSure);

		txtAreYouSure.setColumns(10);

		JPanel buttonPane = new JPanel();

		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		buttonPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();

		buttonPane.add(panel);

		okButton = new JButton("OK");

		panel.add(okButton);

		okButton.setActionCommand("OK");

		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");

		panel.add(cancelButton);

		cancelButton.setActionCommand("Cancel");

	}

	public JButton getOkButton() {

		return okButton;

	}

	public JButton getCancelButton() {

		return cancelButton;

	}

	public void setText(String string) {

		txtAreYouSure.setText(string);

	}

}
