package werewolf;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class WerewolfTryGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WerewolfTryGUI window = new WerewolfTryGUI();
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
	public WerewolfTryGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 513, 517);
//		frame.getContentPane().setBackground(new Color(50, 50, 200));
//		frame.setLayout(new BorderLayout(0,0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(new Color(50, 50, 200));
		JPanel centerCardPanel = new JPanel();
		panel.add(centerCardPanel, BorderLayout.CENTER);
		JLabel downsideCard = new JLabel();
		downsideCard.setIcon(new ImageIcon("/Users/gracelee/Desktop/werewolfcard.jpg"));
		centerCardPanel.add(downsideCard, BorderLayout.WEST);

	}

}
