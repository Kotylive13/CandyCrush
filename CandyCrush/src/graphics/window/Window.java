package graphics.window;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import manager.GameManager;

public class Window {

	private Title title;
	private GameManager gameManager;
	public Window(){}
	public Window (Title title){
		this.title = title;
		try {
			this.gameManager = GameManager.getInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addFrame(){
		Frame frame = new Frame(title.getTitle());
		frame.setSize(getPreferredSize());
		frame.setMaximumSize(getPreferredSize());
		frame.setMinimumSize(getPreferredSize());
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
			return new Dimension(32 * 9 + 1, 32 * 9 + 25);
		}
		
}
