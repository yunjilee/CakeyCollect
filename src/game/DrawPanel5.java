package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/* INSTRUCTIONS */
class DrawPanel5 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Game game;
	private BottomPanel lower;
	private CenterPanel center;

	public DrawPanel5(Game game)
	{
		this.game = game;
		game.setPan5(true);
		this.setLayout(new BorderLayout());
		lower = new BottomPanel(game);
		this.add(lower, BorderLayout.SOUTH);
		game.setPan5(false);

		center = new CenterPanel(); //adding a panel
		this.add(center, BorderLayout.CENTER);
	}
	class CenterPanel extends JPanel //class for new panel
	{
		private static final long serialVersionUID = 1L;
		private JTextArea text1;
		private JScrollPane scroll;

		public CenterPanel()
		{
			this.setLayout(null); //NULL LAYOUT
			setBackground(new Color(210, 190, 170));

			text1 = new JTextArea(100, 50);
			text1.setLineWrap(true);
			text1.setWrapStyleWord(true);
			text1.setText("\nPLAY MODE INSTRUCTIONS:\n\n- Use the 'wasd' key controls to move your character.Press 'r' to restart your game, and corresponding letters to activate your upgrade/powerups.\n- To win, your character must eat all the cakes on the gameplay board, while defeating any monsters that comes across your way. When you coincide with a monster, your screen will switch to the 'encounter' mode.\n- The side panel on the right will show your character's health, money, the amount of special attack points you have left, and the number of upgrades you have.\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\nENCOUNTER MODE INSTRUCTIONS:\n\n- Clicking attack deals regular damage, whereas special attacks inflict significantly more. SA stands for how many special attack points the attack will use. The more SA used, the more damage. If you have no SA left, clicking a special attack button will do nothing. For each new game, you recieve 5 SA. \n- During encounters, you can use items which include potions from the upgrades store. These increase your health points.\n- If you WIN an encounter, your character is sent back to the regular game play panel, and you may continue playing.\n- If you LOSE your battle, an option to take a quiz to revive is available. If you click OK, a quiz panel shows up and you must answer correctly in order to continue playing. Answering incorrectly will cause you to have to completely restart your game.\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\nLEVEL DIFFERENCES:\n\n- Easy: There are six monsters, and this unlocks automatically.\n- Medium: There are ten monsters, and you need 5 wins to unlock.\n- Hard: There are twenty monsters, you need 10 wins to unlock, and the cakes become invisible.\n\n");
			Font myFont = new Font("Futura Lt", Font.PLAIN, 14);
			text1.setFont(myFont);
			text1.setMargin(new Insets(20,20,20,20));
			text1.setEditable(false);
			this.add(text1);
			text1.setSize(520, 380);

			scroll = new JScrollPane(text1);
			scroll.setBounds(80, 210, 570, 380);
			scroll.setBorder(BorderFactory.createEmptyBorder()); //invisible borders
			this.add(scroll);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.black);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// TITLE
			g2.drawImage(game.getImage(50), 70, 5, 540, 200, this);

			g.setColor(new Color(135, 69, 56));
			g.fillRect(70, 200, 590, 400);
			g.setColor(Color.white);
			g.fillRect(80, 210, 570, 380);
		}
	}
}