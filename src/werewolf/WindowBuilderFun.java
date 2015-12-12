package werewolf;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JScrollBar;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

public class WindowBuilderFun {

	private JFrame frame;
	private JTextField txtHelpMe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilderFun window = new WindowBuilderFun();
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
	public WindowBuilderFun() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(50, 50, 200));
		frame.setBackground(new Color(25, 25, 112));
		frame.setBounds(100, 100, 1034, 775);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JLabel lblPlayer = new JLabel();
		lblPlayer.setIcon(new ImageIcon("/Users/gracelee/Desktop/werewolfcard.jpg"));
		frame.getContentPane().add(lblPlayer);
		lblPlayer.setHorizontalAlignment(JLabel.CENTER);
		lblPlayer.setVerticalAlignment(JLabel.CENTER);
//		JPanel panel = new JPanel();
//		
//		JLabel lblVillager = new JLabel("Villager ");
//		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
//		groupLayout.setHorizontalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addGroup(groupLayout.createSequentialGroup()
//							.addGap(140)
//							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//						.addGroup(groupLayout.createSequentialGroup()
//							.addGap(42)
//							.addComponent(lblVillager)))
//					.addContainerGap(507, Short.MAX_VALUE))
//		);
//		groupLayout.setVerticalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addGap(121)
//					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//					.addGap(85)
//					.addComponent(lblVillager)
//					.addContainerGap(204, Short.MAX_VALUE))
//		);
//		frame.getContentPane().setLayout(groupLayout);
//		frame.getContentPane().setLayout(new BorderLayout(0, 0));
//		
//		JPanel panel = new JPanel();
//		panel.setBackground(Color.DARK_GRAY);
//		frame.getContentPane().add(panel, BorderLayout.CENTER);
//		panel.setLayout(new BorderLayout(2, 2));
//		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
//		panel.add(lblNewLabel, BorderLayout.CENTER);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		frame.getContentPane().add(lblNewLabel, "name_896541714981601");
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
//		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		frame.getContentPane().setLayout(gridBagLayout);
//		
//		Panel panel = new Panel();
//		panel.setForeground(Color.RED);
//		panel.setBackground(Color.DARK_GRAY);
//		GridBagConstraints gbc_panel = new GridBagConstraints();
//		gbc_panel.gridwidth = 2;
//		gbc_panel.gridheight = 3;
//		gbc_panel.insets = new Insets(0, 0, 5, 5);
//		gbc_panel.gridx = 3;
//		gbc_panel.gridy = 1;
//		frame.getContentPane().add(panel, gbc_panel);
//		
//		JScrollBar scrollBar = new JScrollBar();
//		GridBagConstraints gbc_scrollBar = new GridBagConstraints();
//		gbc_scrollBar.insets = new Insets(0, 0, 5, 5);
//		gbc_scrollBar.gridx = 0;
//		gbc_scrollBar.gridy = 2;
//		frame.getContentPane().add(scrollBar, gbc_scrollBar);
//		
//		Panel panel_1 = new Panel();
//		panel_1.setForeground(Color.GREEN);
//		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
//		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
//		gbc_panel_1.gridx = 6;
//		gbc_panel_1.gridy = 4;
//		frame.getContentPane().add(panel_1, gbc_panel_1);
//		
//		Button button = new Button("New button");
//		GridBagConstraints gbc_button = new GridBagConstraints();
//		gbc_button.insets = new Insets(0, 0, 5, 5);
//		gbc_button.gridx = 4;
//		gbc_button.gridy = 6;
//		frame.getContentPane().add(button, gbc_button);
//		
//		txtHelpMe = new JTextField();
//		txtHelpMe.setText("Help me");
//		GridBagConstraints gbc_txtHelpMe = new GridBagConstraints();
//		gbc_txtHelpMe.insets = new Insets(0, 0, 5, 0);
//		gbc_txtHelpMe.fill = GridBagConstraints.HORIZONTAL;
//		gbc_txtHelpMe.gridx = 9;
//		gbc_txtHelpMe.gridy = 7;
//		frame.getContentPane().add(txtHelpMe, gbc_txtHelpMe);
//		txtHelpMe.setColumns(10);
//		
//		JLabel lblPlayer = new JLabel("Player 1");
//		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
//		gbc_lblPlayer.insets = new Insets(0, 0, 0, 5);
//		gbc_lblPlayer.gridx = 2;
//		gbc_lblPlayer.gridy = 8;
//		frame.getContentPane().add(lblPlayer, gbc_lblPlayer);
	}
}
