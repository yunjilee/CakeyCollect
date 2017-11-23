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

/* UNLOCK HARD LEVEL PANEL */
class DrawPanel9 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	Game game;
	private BottomPanel lower;
	private CenterPanel center;

	public DrawPanel9(Game game)
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
			setBackground(new Color(234, 237, 176));

			text1 = new JTextArea(100, 50);
			text1.setLineWrap(true);
			text1.setWrapStyleWord(true);
			text1.setText("\nFINAL GAME CSCI201");
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

			g.setColor(new Color(54, 50, 88));
			g.fillRect(70, 200, 590, 400);
			g.setColor(Color.white);
			g.fillRect(80, 210, 570, 380);
		}
	}
}
