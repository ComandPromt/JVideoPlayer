package com.main;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import com.file.nativ.chooser.DemoJavaFxStage;
import com.history.ListHistory;
import com.play_list.PlayListFrame;
import com.play_list.ViewList;
import com.sun.jna.NativeLibrary;
import com.util.Metodos;
import com.views.ControlFrame;
import com.views.DisplayFrame;
import com.views.MyLogo;
import com.views.VideoTime;

import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import util.Mthos;

public class JVideoPlayer {

	public static DisplayFrame frame;

	private static String filePath;

	private static ControlFrame controlFrame;

	private static VideoTime videoTime;

	private static ViewList listView;

	private static ListHistory listHistory;

	public static void escribir() {

		try {

			ListHistory.writeHistory(listView.getList());

			ListHistory.writeHistory(listView.getMap());

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) throws IOException {

		listView = new ViewList();

		Metodos.crearCarpeta();

		setearLista();

		listHistory = new ListHistory();

		filePath = new File(".").getCanonicalPath() + Mthos.saberSeparador() + "lib" + Mthos.saberSeparador() + "VLC";

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), filePath);

		EventQueue.invokeLater(new Runnable() {

			@Override

			public void run() {

				try {

					frame = new DisplayFrame();

					frame.setVisible(true);

					controlFrame = new ControlFrame();

					videoTime = new VideoTime();

					new SwingWorker<String, Integer>() {

						@Override

						protected String doInBackground() throws Exception {

							while (true) {

								long totalTime = frame.getMediaPlayer().getLength();

								long currentTime = frame.getMediaPlayer().getTime();

								videoTime.timeCalculate(totalTime, currentTime);

								frame.getCurrentLabel()
										.setText(videoTime.getMinitueCurrent() + ":" + videoTime.getSecondCurrent());

								frame.getTotalLabel()
										.setText(videoTime.getMinitueTotal() + ":" + videoTime.getSecondTotal());

								controlFrame.getCurrentLabel().setText(frame.getCurrentLabel().getText());

								controlFrame.getTotalLabel().setText(frame.getTotalLabel().getText());

								float percent = (float) currentTime / totalTime;

								publish((int) (percent * 100));

								Thread.sleep(200);

							}

						}

						@Override

						protected void process(java.util.List<Integer> chunks) {

							for (int v : chunks) {

								frame.getProgressBar().setValue(v);

								controlFrame.getProgressBar().setValue(v);

							}

						}

					}.execute();

				}

				catch (Exception e) {

					e.printStackTrace();

				}

			}

		});
	}

	public static void setearLista() {

		try {

			listView.setList(ListHistory.readHistory());

			listView.setMap(ListHistory.readHistoryMap());

		}

		catch (Exception e1) {

		}

	}

	public static void play() {

		frame.getMediaPlayer().play();

		frame.getPlayButton().setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/pause.png")));

	}

	public static void pause() {

		frame.getMediaPlayer().pause();

		frame.getPlayButton().setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/play.png")));

	}

	public static void stop() {

		frame.getMediaPlayer().stop();

		frame.getPlayButton().setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/play.png")));

	}

	public static void forword(float to) {

		frame.getMediaPlayer().setTime((long) (to * frame.getMediaPlayer().getLength()));

	}

	public static void backword() {

		frame.getPlayComponent().backward(frame.getMediaPlayer());

	}

	public static void jumpTo(float to) {

		frame.getMediaPlayer().setTime((long) (to * frame.getMediaPlayer().getLength()));

	}

	public static void setVolum(int v) {

		frame.getMediaPlayer().setVolume(v);

	}

	public static void openVideo(boolean folder) {

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			DemoJavaFxStage test = new DemoJavaFxStage();

			LinkedList<File> lista = new LinkedList<File>();

			if (folder) {

				lista = test.showOpenFileDialog(true, "");

				LinkedList<String> listaVideos = new LinkedList();

				for (int i = 0; i < lista.size(); i++) {

					listaVideos = Mthos.directorio(lista.get(i).getAbsolutePath() + Mthos.saberSeparador(), "videos",
							false);

					for (int y = 0; y < listaVideos.size(); y++) {

						lista.add(new File(listaVideos.get(y)));

						addHistory(
								listaVideos.get(y)
										.substring(listaVideos.get(y).lastIndexOf(Mthos.saberSeparador()) + 1),
								listaVideos.get(y));

					}

				}

				if (!listaVideos.isEmpty()) {

					lista.remove(0);

					DisplayFrame.nombreVideo = listaVideos.get(0)
							.substring(listaVideos.get(0).lastIndexOf(Mthos.saberSeparador()) + 1);

				}

			}

			else {

				lista = test.showOpenFileDialog(false, "videos");

				if (!lista.isEmpty()) {

					for (int i = 0; i < lista.size(); i++) {

						addHistory(lista.get(i).getName(), lista.get(i).getAbsolutePath());

					}

					DisplayFrame.nombreVideo = lista.get(0).getName();

				}

			}

			if (!lista.isEmpty()) {

				frame.getMediaPlayer().playMedia(lista.get(0).getAbsolutePath());

				frame.getPlayButton().setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/pause.png")));

			}

		}

		catch (Exception e1) {

			e1.printStackTrace();

		}

	}

	public static void deleteFromHistory(int index) {

		if (index > -1) {

			PlayListFrame.dlm.removeElementAt(index);

			PlayListFrame.list.setModel(PlayListFrame.dlm);

			escribir();

		}

	}

	public static void addHistory(String name, String path) {

		if (Metodos.esVideo(name) && !listView.search(path)) {

			listView.setList(name);

			listView.setMap(name, path);

			PlayListFrame.dlm.addElement(name);

			PlayListFrame.list.setModel(PlayListFrame.dlm);

			escribir();

		}

	}

	public static void openVideoFromList(String name) {

		DisplayFrame.nombreVideo = name;

		String path = listView.getMap().get(name);

		addHistory(name, path);

		frame.getMediaPlayer().playMedia(path);

		frame.getPlayButton().setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/pause.png")));

	}

	public static void exit() {

		frame.getMediaPlayer().release();

		System.exit(0);

	}

	public static void fullScreen() {

		frame.getMediaPlayer().setFullScreenStrategy(new DefaultAdaptiveRuntimeFullScreenStrategy(frame));

		frame.getProgressBar().setVisible(false);

		frame.getControlPanel().setVisible(false);

		frame.getProgressTimePanel().setVisible(false);

		frame.getJMenuBar().setVisible(false);

		frame.getMediaPlayer().setFullScreen(true);

		controlFrame.getListButton().setText("List>>");

		frame.setFlag(1);

		frame.getPlayComponent().getVideoSurface().addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {

				if (frame.getFlag() == 1) {

					controlFrame.setLocation((frame.getWidth() - controlFrame.getWidth()) / 2,
							frame.getHeight() - controlFrame.getHeight());

					controlFrame.setVisible(true);

					controlFrame.getVolumControlerSlider().setValue(frame.getVolumControlerSlider().getValue());

				}

			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}

		});

	}

	public static void originalScreen() {

		frame.getProgressBar().setVisible(true);

		frame.getControlPanel().setVisible(true);

		frame.getProgressTimePanel().setVisible(true);

		frame.getJMenuBar().setVisible(true);

		frame.getMediaPlayer().setFullScreen(false);

		frame.setFlag(0);

		if (DisplayFrame.getPlayListFrame().getFlag() == 1) {

			frame.getListButton().setText("List>>");

		}

		else if (DisplayFrame.getPlayListFrame().getFlag() == 0) {

			frame.getListButton().setText("<<");

		}

		controlFrame.setVisible(false);

	}

	public static DisplayFrame getFrame() {

		return frame;

	}

	public static ControlFrame getControlFrame() {

		return controlFrame;

	}

	public static void setLogo() {

		MyLogo logo = new MyLogo();

		frame.getMediaPlayer().setLogo(logo.getLogo());

	}

	public static ViewList getListView() {

		return listView;

	}

	public static ListHistory getListHistory() {

		return listHistory;

	}

}
