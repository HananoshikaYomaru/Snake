package UI;

import javax.swing.JFrame;

/**
 * 
 * @author Yomaru
 *
 */
public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5956781369257734126L;

	public GUI() {
		setTitle("snake");
		setContentPane(new Board());
		pack();
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
