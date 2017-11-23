package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/* UPGRADES PANEL 2 */
class DrawPanel8 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Game game;
	private BottomPanel lower;
	private MainPanel center;
	private boolean stop;

	public DrawPanel8(Game game)
	{
		this.game = game;
		game.setPan4(true);
		this.setLayout(new BorderLayout());
		lower = new BottomPanel(game);
		this.add(lower, BorderLayout.SOUTH);
		game.setPan4(false);

		center = new MainPanel();
		this.add(center, BorderLayout.CENTER);
	}
	class MainPanel extends JPanel implements ActionListener//class for new panel
	{
		private static final long serialVersionUID = 1L;

		private JTextArea text1;
		private boolean canBuy;
		private BuyWindow buy;

		String startButton = game.getImageName(17);
		String startButtonHover = game.getImageName(18);
		JButton buyButton1 = new JButton(new ImageIcon(startButton));

		String pageButton = game.getImageName(21);
		String pageHover = game.getImageName(22);
		JButton nextPage = new JButton(new ImageIcon(pageButton));

		public MainPanel()
		{
			this.setLayout(null);
			setBackground(new Color(250, 243, 228));

			buyButton1.setRolloverIcon(new ImageIcon(startButtonHover));
			buyButton1.setBorder(BorderFactory.createEmptyBorder());
			buyButton1.setContentAreaFilled(false);
			buyButton1.setFocusable(false);
			buyButton1.addActionListener(this);
			this.add(buyButton1);
			buyButton1.setBounds(515, 350, 90, 40);
			buyButton1.setActionCommand("BuyButton1");

			nextPage.setRolloverIcon(new ImageIcon(pageHover));
			nextPage.setBorder(BorderFactory.createEmptyBorder());
			nextPage.setContentAreaFilled(false);
			nextPage.setFocusable(false);
			nextPage.addActionListener(this);
			this.add(nextPage);
			nextPage.setBounds(650, 40, 50, 550);
			nextPage.setActionCommand("NextPage");

			buy = new BuyWindow();
			buy.setLocation(300, 300);
			buy.setSize(250, 100);
			this.add(buy);
			if(canBuy)
			{
				buy.setVisible(true);
			}
			else
			{
				buy.setVisible(false);
			}

			text1 = new JTextArea(10, 10);
			text1.setLineWrap(true);
			text1.setWrapStyleWord(true);
			text1.setText("This strong health potion will heal your character by 60 HP.\n\nPrice: $200\n- > This can be used during encounters.");
			Font myFont = new Font("Futura Lt", Font.PLAIN, 12);
			text1.setFont(myFont);
			text1.setEditable(false);
			this.add(text1);
			text1.setBounds(350, 230, 240, 110);
		}
		class BuyWindow extends JPanel implements ActionListener
		{
			private static final long serialVersionUID = 1L;
			String okStart = game.getImageName(25);
			String okHover = game.getImageName(26);
			JButton okay = new JButton(new ImageIcon(okStart));

			public BuyWindow()
			{
				this.setLayout(null);
				setBackground(Color.white);

				okay.setRolloverIcon(new ImageIcon(okHover));
				okay.setBorder(BorderFactory.createEmptyBorder());
				okay.setContentAreaFilled(false);
				okay.setFocusable(false);
				okay.addActionListener(this);
				this.add(okay);
				okay.setBounds(340, 360, 55, 25);
				okay.setActionCommand("OK");
			}
			public void paintComponent(Graphics g)
			{
				g.setColor(Color.white);
				g.fillRoundRect(240, 300, 250, 100, 15, 15);
				g.setColor(Color.black);
				g.drawRoundRect(243, 303, 244, 94, 15, 15);

				g.setColor(new Color(75, 49, 9));
				Font myFont = new Font("Futura Hv", Font.PLAIN, 16);
				g.setFont(myFont);
				if(canBuy)
				{
					g.drawString("You've bought this item.", 280, 345);
				}
				else
				{
					g.drawString("You can't afford this item.", 277, 345);
				}
			}
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals("OK"))
				{
					buy.setVisible(false);
					stop = false;
				}
			}
		}
		public void paintComponent(Graphics g)
		{
			//IF RUN OUT OF ROOM, MAKE PAGE 2
			super.paintComponent(g);
			g.setColor(Color.black);

			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(new Color(193, 165, 125));
			g.drawRect(344, 224, 252, 122);
			g.setColor(Color.black);

			//TITLE
			g2.drawImage(game.getImage(7), 120, 0, 450, 200, this);
			g2.drawImage(game.getImage(24), 120, 240, 139, 170, this);

			Font aFont = new Font("Futura Hv", Font.PLAIN, 30);
			g.setFont(aFont);
			g.drawString("3. STRONG POTION", 50, 230);
		}
		public void actionPerformed(ActionEvent e)
		{
			String command = e.getActionCommand();
			if(command.equals("BuyButton1"))
			{
				if(game.getMoney() >= 200)
				{
					canBuy = true;
					game.getPanel8().setComponentZOrder(buy, 1);
					buy.setVisible(true);
					game.getPanel8().repaint();
					game.setBighpcount(game.getBighpcount() + 1);
					game.setMoney(game.getMoney() - 200);
				}
				else
				{
					canBuy = false;
					game.getPanel8().setComponentZOrder(buy, 1);
					buy.setVisible(true);
					game.getPanel8().repaint();
				}
				stop = true;
				System.out.println("bighp+");
			}
			else if(command.equals("NextPage") && stop == false)
			{
				game.getCards().show(game.getC(), "Panel 4");
			}
		}
	}
}