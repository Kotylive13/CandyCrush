package graphics.window;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import manager.GameManager;

/**
 * 
 * @author Philippe & Marcel
 *
 */

public class Window {

	private Title title;
	private GameManager gameManager;

	public Window() {
	}

	public Window(Title title) {
		this.title = title;
		try {
			this.gameManager = GameManager.getInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void addFrame() {
		Frame frame = new Frame(title.getTitle());
		frame.setSize(getPreferredSize());
		frame.setMinimumSize(getPreferredSize());
		frame.setMaximumSize(getPreferredSize());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(gameManager.getGameScene());
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	// taille de la fen�tre
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}

}
