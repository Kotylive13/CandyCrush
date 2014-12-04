package graphics.window;

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
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.add(gameManager.getGameScene());
        frame.pack();
        frame.setVisible(true);
	}
}
