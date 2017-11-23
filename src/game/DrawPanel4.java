package game;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
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

/* UPGRADES PANEL 1 */
class DrawPanel4 extends JPanel implements Serializable
	{
		private static final long serialVersionUID = 1L;
		private BottomPanel lower;
		private MainPanel center;
		private Game game;

		public DrawPanel4(Game game)
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
		class MainPanel extends JPanel implements ActionListener
		{
			private boolean stop;

			String startButton = game.getImageName(17);
			String startButtonHover = game.getImageName(18);
			JButton buyButton1 = new JButton(new ImageIcon(startButton));
			JButton buyButton2 = new JButton(new ImageIcon(startButton));

			String pageButton = game.getImageName(19);
			String pageHover = game.getImageName(20);
			JButton nextPage = new JButton(new ImageIcon(pageButton));

			private JTextArea text1, text2;
			private boolean canBuy;
			private BuyWindow buy;

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

				buyButton2.setRolloverIcon(new ImageIcon(startButtonHover));
				buyButton2.setBorder(BorderFactory.createEmptyBorder());
				buyButton2.setContentAreaFilled(false);
				buyButton2.setFocusable(false);
				buyButton2.addActionListener(this);
				this.add(buyButton2);
				buyButton2.setBounds(515, 550, 90, 40);
				buyButton2.setActionCommand("BuyButton2");

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
				text1.setText("This grants the user invisibility for 5 seconds. Player is immune to ghosts during that time period.\n\nPrice: $300\n-> Press 'i' to use powerup.");
				Font myFont = new Font("Futura Lt", Font.PLAIN, 12);
				text1.setFont(myFont);
				text1.setEditable(false);
				this.add(text1);
				text1.setBounds(350, 230, 240, 110);

				text2 = new JTextArea(10, 10);
				text2.setLineWrap(true);
				text2.setWrapStyleWord(true);
				text2.setText("This weak health potion will heal your character by 40 HP.\n\nPrice: $100\n- > This can be used during encounters.");
				text2.setEditable(false);
				this.add(text2);
				text2.setFont(myFont);
				text2.setBounds(350, 430, 240, 110);
			}
			class BuyWindow extends JPanel implements ActionListener
			{
				String okStart = game.getImageName(25);
				String okHover = game.getImageName(26);
				JButton okay = new JButton(new ImageIcon(okStart));

				public BuyWindow()
				{
					this.setLayout(null);
					setBackground(Color.white);

					//OK BUTTON
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

				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g.setColor(new Color(193, 165, 125));
				g.drawRect(344, 224, 252, 122);

				g.drawRect(344, 424, 252, 122);

				//TITLE
				g2.drawImage(game.getImage(7), 120, 0, 450, 200, this);
				//regular character image:
				g2.drawImage(game.getImage(1), 50, 250, 130, 130, this);
				//arrow
				g2.drawImage(game.getImage(8), 150, 265, 80, 80, this);

				//smallpotion
				g2.drawImage(game.getImage(23), 100, 430, 139, 170, this);

				g.setColor(Color.black);
				Font aFont = new Font("Futura Hv", Font.PLAIN, 30);
				g.setFont(aFont);
				g.drawString("1. INVISIBILITY", 50, 230);
				g.drawString("2. WEAK POTION", 50, 430);

				//INVISIBLE (TRANSPARENCY)
				float alpha = 0.4f;
				int rule = AlphaComposite.SRC_OVER;
				Composite comp = AlphaComposite.getInstance(rule , alpha );
				g2.setComposite(comp );

				g2.drawImage(game.getImage(1), 210, 250, 130, 130, this);
				comp = AlphaComposite.getInstance(rule , 1);
				g2.setComposite(comp );
				//g2.dispose();
			}
			public void actionPerformed(ActionEvent e)
			{
				String command = e.getActionCommand();
				if(command.equals("BuyButton1"))
				{
					if(game.getMoney() >= 300)
					{
						canBuy = true;
						game.getPanel4().setComponentZOrder(buy, 1);
						buy.setVisible(true);
						game.getPanel4().repaint();
						game.setInvisicount(game.getInvisicount() + 1);
						game.setMoney(game.getMoney() - 300);
					}
					else if(game.getMoney() < 300)
					{
						canBuy = false;
						game.getPanel4().setComponentZOrder(buy, 1);
						buy.setVisible(true);
						game.getPanel4().repaint();
					}
					stop = true;
				}
				else if(command.equals("BuyButton2"))
				{
					if(game.getMoney() >= 100)
					{
						canBuy = true;
						game.getPanel4().setComponentZOrder(buy, 1);
						buy.setVisible(true);
						game.getPanel4().repaint();
						game.setSmallhpcount(game.getSmallhpcount() + 1);
						game.setMoney(game.getMoney() - 100);
						System.out.print(game.getSmallhpcount() + "\n");
						System.out.println("smallhp+");
					}
					else
					{
						canBuy = false;
						game.getPanel4().setComponentZOrder(buy, 1);
						buy.setVisible(true);
						game.getPanel4().repaint();
					}
					stop = true;
				}
				else if(command.equals("NextPage") && stop == false)
				{
					game.getCards().show(game.getC(), "Panel 8");
				}
			}
		}
	}
