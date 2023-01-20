package com.views;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

import com.main.JVideoPlayer;

/**
 * Key board listener
 * 
 * @author ganyee
 *
 */

public class KeyBordListenerEvent {

	public void keyBordListner() {

		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

			@Override

			public void eventDispatched(AWTEvent event) {

				if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {

					switch (((KeyEvent) event).getKeyCode()) {

					case KeyEvent.VK_RIGHT:

						int a = JVideoPlayer.getFrame().getVolumControlerSlider().getValue();

						JVideoPlayer.getFrame().getVolumControlerSlider().setValue(a);

						JVideoPlayer.forword((float) (((JVideoPlayer.getFrame().getProgressBar().getPercentComplete()
								* JVideoPlayer.getFrame().getProgressBar().getWidth() + 10))
								/ JVideoPlayer.getFrame().getProgressBar().getWidth()));

						break;

					case KeyEvent.VK_LEFT:

						JVideoPlayer.jumpTo((float) ((JVideoPlayer.getFrame().getProgressBar().getPercentComplete()
								* JVideoPlayer.getFrame().getProgressBar().getWidth() - 5)
								/ JVideoPlayer.getFrame().getProgressBar().getWidth()));

						break;

					case KeyEvent.VK_ESCAPE:

						if (!JVideoPlayer.getFrame().getMediaPlayer().isFullScreen())

							JVideoPlayer.fullScreen();

						else
							JVideoPlayer.originalScreen();

						break;

					case KeyEvent.VK_UP:

						JVideoPlayer.getFrame().getVolumControlerSlider()
								.setValue(JVideoPlayer.getFrame().getVolumControlerSlider().getValue() + 1);

						JVideoPlayer.getControlFrame().getVolumControlerSlider()
								.setValue(JVideoPlayer.getFrame().getVolumControlerSlider().getValue());

						break;

					case KeyEvent.VK_DOWN:

						JVideoPlayer.getFrame().getVolumControlerSlider()
								.setValue(JVideoPlayer.getFrame().getVolumControlerSlider().getValue() - 1);

						JVideoPlayer.getControlFrame().getVolumControlerSlider()
								.setValue(JVideoPlayer.getFrame().getVolumControlerSlider().getValue());

						break;

					case KeyEvent.VK_SPACE:

						DisplayFrame.playPause();

						break;

					}

				}

			}

		}, AWTEvent.KEY_EVENT_MASK);

	}

}
