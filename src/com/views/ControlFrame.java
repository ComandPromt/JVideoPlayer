package com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.main.JVideoPlayer;
import com.slider.tipo2.JsliderCustom;

@SuppressWarnings("serial")
public class ControlFrame extends JFrame {

	private JPanel contentPane;

	static JButton playButton;

	private JButton backwordButton;

	private JProgressBar progressBar;

	private JSlider volumControlerSlider;

	private JButton smallButton;

	private JPanel progressTimepanel;

	private JLabel currentLabel;

	private JLabel totalLabel;

	private JButton listButton;
	private JButton btnNewButton;

	public ControlFrame() {

		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		setType(Type.UTILITY);

		setResizable(false);

		setUndecorated(true);

		setOpacity(0.5f);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 623, 66);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		contentPane.add(panel, BorderLayout.CENTER);

		backwordButton = new JButton("");

		backwordButton.setContentAreaFilled(false);

		backwordButton.setBorder(null);

		backwordButton.setIcon(new ImageIcon(ControlFrame.class.getResource("/images/prev.png")));

		backwordButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				JVideoPlayer.jumpTo((float) ((progressBar.getPercentComplete() * progressBar.getWidth() - 5)
						/ progressBar.getWidth()));

			}

		});

		playButton = new JButton("");
		playButton.setIcon(new ImageIcon(ControlFrame.class.getResource("/images/pause.png")));

		playButton.setContentAreaFilled(false);

		playButton.setBorder(null);

		playButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				DisplayFrame.playPause();

			}

		});

		panel.add(playButton);

		panel.add(backwordButton);

		JButton stopButton = new JButton("");

		stopButton.setContentAreaFilled(false);

		stopButton.setBorder(null);

		stopButton.setIcon(new ImageIcon(ControlFrame.class.getResource("/images/stop.png")));

		stopButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				JVideoPlayer.stop();

				playButton.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/play.png")));

			}

		});

		panel.add(stopButton);

		JButton forwardButton = new JButton("");

		forwardButton.setContentAreaFilled(false);

		forwardButton.setBorder(null);

		forwardButton.setIcon(new ImageIcon(ControlFrame.class.getResource("/images/rew.png")));

		forwardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JVideoPlayer.jumpTo((float) (((progressBar.getPercentComplete() * progressBar.getWidth() + 15))
						/ progressBar.getWidth()));

			}

		});

		btnNewButton = new JButton("");

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DisplayFrame.reiniciarVideo();

			}

		});

		btnNewButton.setIcon(new ImageIcon(ControlFrame.class.getResource("/images/replay.png")));

		btnNewButton.setContentAreaFilled(false);

		btnNewButton.setBorder(null);

		panel.add(btnNewButton);

		panel.add(forwardButton);

		smallButton = new JButton("");

		smallButton.setContentAreaFilled(false);

		smallButton.setBorder(null);

		smallButton.setIcon(new ImageIcon(ControlFrame.class.getResource("/images/full_screen.png")));

		smallButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				JVideoPlayer.originalScreen();

			}

		});

		panel.add(smallButton);

		volumControlerSlider = new JsliderCustom();

		volumControlerSlider.setForeground(new Color(115, 118, 120));

		volumControlerSlider.setBackground(Color.BLUE);

		volumControlerSlider.setValue(100);

		volumControlerSlider.setMaximum(120);

		panel.add(volumControlerSlider);

		volumControlerSlider.addChangeListener(new ChangeListener() {

			@Override

			public void stateChanged(ChangeEvent e) {

				JVideoPlayer.setVolum(volumControlerSlider.getValue());

			}

		});

		volumControlerSlider.setMaximum(120);

		listButton = new JButton();

		progressTimepanel = new JPanel();

		progressTimepanel.setBackground(Color.WHITE);

		contentPane.add(progressTimepanel, BorderLayout.NORTH);

		progressTimepanel.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();

		progressBar.setBackground(Color.WHITE);

		progressTimepanel.add(progressBar, BorderLayout.CENTER);

		progressBar.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				int x = e.getX();

				JVideoPlayer.jumpTo(((float) x / progressBar.getWidth()));

			}

		});

		currentLabel = new JLabel("00:00");
		currentLabel.setBackground(Color.WHITE);

		progressTimepanel.add(currentLabel, BorderLayout.WEST);

		totalLabel = new JLabel("00:00");
		totalLabel.setBackground(Color.WHITE);

		progressTimepanel.add(totalLabel, BorderLayout.EAST);

		volumControlerSlider.addChangeListener(new ChangeListener() {

			@Override

			public void stateChanged(ChangeEvent e) {

				JVideoPlayer.setVolum(volumControlerSlider.getValue());

			}

		});

	}

	public JProgressBar getProgressBar() {

		return progressBar;

	}

	public void setProgressBar(JProgressBar progressBar) {

		this.progressBar = progressBar;

	}

	public JButton getPlayButton() {

		return playButton;

	}

	public JSlider getVolumControlerSlider() {

		return volumControlerSlider;

	}

	public JLabel getCurrentLabel() {

		return currentLabel;

	}

	public JLabel getTotalLabel() {

		return totalLabel;

	}

	public JButton getListButton() {

		return listButton;

	}

}
