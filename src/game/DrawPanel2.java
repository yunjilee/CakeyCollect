package game;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
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

import javax.swing.JPanel;
import javax.swing.Timer;

/* GAMEPLAY PANEL */
class DrawPanel2 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	Game game;
	
	private BottomPanel lower;
	private Pacman play;
	private int counterr = 0;
	private int tempcount = 5;

	public DrawPanel2(Game game)
	{
		this.game = game;
		game.setPan2(true);
		this.setLayout(new BorderLayout());
		lower = new BottomPanel(game);
		this.add(lower, BorderLayout.SOUTH);
		game.setPan2(false);

		play = new Pacman(); //adding a panel
		this.add(play, BorderLayout.CENTER);
	}
	class Pacman extends JPanel//class for new panel
	{
		private static final long serialVersionUID = 1L;
		private MyPanel mypanel;

		public Pacman()
		{
			this.setLayout(new BorderLayout());

			mypanel = new MyPanel();
			mypanel.setSize(615, 660);
			mypanel.setBackground(new Color(233, 224, 219));
			this.add(mypanel, BorderLayout.CENTER);
			//this.requestFocus();
		}
		class MyPanel extends JPanel//Drawing the UI
		{
			public MyPanel()//Roughly 60 fps for rendering
			{
				Mover mover = new Mover();
				Timer timer = new Timer(100, mover);//Rate in milliseconds
				timer.start();
			}
			public void paintComponent(Graphics g)//Pacman's original position
			{
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				if(game.isReset())
				{
					game.setPaclocX((int)(Math.random()*9)+1);
					game.setPaclocY((int)(Math.random()*9)+1);
					game.setPacMan(game.getPaclocX(), game.getPaclocY(), 1);

					for (int i = 0; i < 30; i++)
					{
						game.setCheeseX(i, (int)(9*Math.random()+1));
						game.setCheeseY(i, (int)(9*Math.random()+1));
						game.setGhostsX(i, (int)(9*Math.random()+1));
						game.setGhostsY(i, (int)(9*Math.random()+1));
					}
					game.setVictory(0);
					game.setCheeseC(0);
					game.setMove('h');
					game.setMoved(false);
					game.setReset(false);
					game.setInvisible(false);
					game.setWinner(false);
					game.setLoser(false);

					//HEALTHS
					game.setEnemyHealth(50);
					game.setCharHealth(150);
					game.setSpecialattcount(5);
				}
				drawUI(g);
				if(game.isEasy())
				{
					for(int i = 0; i < 6; i++)
					{
						g.drawImage(game.getImage(10), (game.getGhostsX(i) * 50)+57-20, (game.getGhostsY(i) * 50)+57+10, 40, 45, this);
					}
				}
				else if(game.isMedium())
				{
					for(int i = 0; i < 10; i++)
					{
						g.drawImage(game.getImage(10), (game.getGhostsX(i)*50)+57-20, (game.getGhostsY(i)*50)+57+10, 40, 45, this);
					}
				}
				else if(game.isHard())
				{
					for(int i = 0; i < 20; i++)
					{
						g.drawImage(game.getImage(10), (game.getGhostsX(i)*50)+57-20, (game.getGhostsY(i)*50)+57+10, 40, 45, this);
					}
				}
				for (int i = 0; i < 6; i++) // Monsters and character
				{
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setStroke(new BasicStroke(3));

					g.setColor(new Color(79, 36, 18));

					g.setColor(new Color(204, 204, 204));
					//CAKES
					if(game.isEasy() || game.isMedium())
					{
						g.drawImage(game.getImage(52), (game.getCheeseX(i)*50)+57-18,(game.getCheeseY(i)*50)+57+8, 33, 33, this);
					}
					g.setColor(new Color(197, 154, 111));

					if(!game.isInvisible() || game.getInvisicount() == 0)
					{
						g.drawImage(game.getImage(game.getImageNum()), (50*game.getPaclocX())+57-20,(50*game.getPaclocY())+57+10, 40, 42, this);
					}
					else if(game.isInvisible() && game.getInvisicount() > 0)
					{
						float alpha = 0.1f;
						int rule = AlphaComposite.SRC_OVER;
						Composite comp = AlphaComposite.getInstance(rule , alpha );
						g2.setComposite(comp);

						g2.drawImage(game.getImage(game.getImageNum()), (50*game.getPaclocX())+57-20,(50*game.getPaclocY())+57+10, 40, 42, this);
						comp = AlphaComposite.getInstance(rule , 1);
						g2.setComposite(comp);

						Font newFont = new Font("Futura Hv", Font.PLAIN, 12);
						g.setFont(newFont);
						g.setColor(Color.red);
						g.drawString(Integer.toString(tempcount), 630, 230);

					}
					if(game.getVictory()%2.0 == 0 && game.getVictory() != 0)
					{
						g.setColor(new Color(197, 154, 111));
						g.setColor(Color.black);
						g.setFont(game.getBigBoldFont());
						g.drawString("CONGRATULATIONS!", 65, 270);
						g.drawString("YOU WON THE GAME.", 60, 330);
					}
				}
			}
			public void drawUI(Graphics g)//UI set up
			{
				g.setColor(Color.black);
				g.drawRect(52-20, 52+10,500,500);//Lines on the side

				g.setColor(new Color(79, 36, 18)); // SIDE PANEL
				g.fillRect(550, 62, 165, 510);
				g.setColor(Color.white);
				g.setFont(game.getSmallFont());
				g.drawString("Character Health", 560, 100);
				g.drawRect(555, 110, 155, 20);
				g.setColor(new Color(185, 50, 65));
				g.fillRect(558, 113, game.getCharHealth(), 15);
				g.setColor(Color.white);
				Font aFont = new Font("Futura Lt", Font.BOLD, 10);
				g.setFont(aFont);
				g.drawString(Integer.toString(game.getCharHealth())+" / 150", 605, 125);

				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// CURRENCY
				g.setColor(Color.white);
				g.setFont(game.getSmallFont());
				g.drawString("Money: $" + Integer.toString(game.getMoney()), 560, 150);
				g.drawString("Special Attacks Left: " + Integer.toString(game.getSpecialattcount()), 560, 170);

				// UPGRADES
				g.drawString("Inventory", 560, 200);
				g.drawRect(555, 210, 150, 80);
				Font newFont = new Font("Futura Lt", Font.PLAIN, 12);
				g.setFont(newFont);
				g.drawString("Invisibility: " + Integer.toString(game.getInvisicount()), 560, 230);
				g.drawString("Weak HP Potion: " + Integer.toString(game.getSmallhpcount()), 560, 250);
				g.drawString("Strong HP Potion: " + Integer.toString(game.getBighpcount()), 560, 270);

				//LEVEL
				g.setFont(game.getSmallFont());
				g.drawString("Current Difficulty: ", 560, 320);
				g.setFont(newFont);
				if(game.isEasy())
				{
					g.setColor(new Color(79, 36, 18));
					g.fillRect(560, 340, 100, 20);
					g.setColor(Color.white);
					g.drawString("EASY", 560, 340);
				}

				else if(game.isMedium())
				{
					g.setColor(new Color(79, 36, 18));
					g.fillRect(560, 340, 100, 20);
					g.setColor(Color.white);
					g.drawString("MEDIUM", 560, 340);
				}
				else if(game.isHard())
				{
					g.setColor(new Color(79, 36, 18));
					g.fillRect(560, 340, 100, 20);
					g.setColor(Color.white);
					g.drawString("HARD", 560, 340);
				}

				//DIALOGUE BOX
				g.setFont(game.getSmallFont());
				g.setColor(Color.white);
				g.drawString("Cake Found: " + Integer.toString(game.getCheeseC()) + " / 6", 560, 400);
				g.drawString("Wins: " + Integer.toString(game.getWins()), 560, 420);
				g.drawString("Losses: " + Integer.toString(game.getLoses()/3), 560, 440);

				newFont = new Font("Futura Lt", Font.PLAIN, 12);
				g.setFont(newFont);
				g.drawString("You unlocked difficulties:", 560, 470);
				g.setFont(game.getSmallFont());
				g.drawString("EASY", 560, 490);
				if(game.getWins() >= 5)
				{
					g.drawString("MEDIUM", 560, 510);
				}
				if(game.getWins() >= 10)
				{
					g.drawString("HARD", 560, 530);
				}

				g.setColor(Color.white);
				g.fillRect(48-20, 573+10, 509, 22);
				g.setColor(new Color(204, 204, 204));
				g.fillRect(50-20, 575+10, 505, 20);
				Font tFont = new Font("Futura Lt", Font.PLAIN, 12);
				g.setFont(tFont);
				g.setColor(new Color(79, 36, 18));
				g.drawString("Directions: a = left, d = right, w = up, s = down, r = reset board", 110, 600 ); //Directions for the user
				g.setColor(Color.white);
				Font myFont = new Font("Futura Hv", Font.BOLD, 40);
				g.setFont(myFont);
				g.drawString("C A K E Y", 187, 45);
				g.setColor(new Color(79, 36, 18));
				g.drawString("C A K E Y", 185, 43);
			}
			class Mover implements ActionListener //Timer method helps animate pacman's mouth
			{
				public void actionPerformed(ActionEvent e)
				{
					repaint();
					if(!game.isCount())
					{
						game.setAnimate(game.getAnimate()+1);
						if(game.getAnimate() == 5)
						{
							game.setCount(true);
						}
					}
					else if(game.isCount())
					{
						game.setAnimate(game.getAnimate()-1);
						if(game.getAnimate() == 0)
						{
							game.setCount(false);
						}
					}
					if(game.isInvisible())
					{
						counterr++;
						if(game.isInvisible() && counterr%10==0)
						{
							tempcount--;
							if(tempcount == 0)
							{
								tempcount = 5;
							}
						}
					}
					if(game.isInvisible() && counterr%50==0) //invisicount depletes every 5 seconds
					{
						game.setInvisicount(game.getInvisicount()-1);
						if(game.getInvisicount() == 0)
						{
							game.setInvisible(false);
						}
					}
				}
			}
		}
	}
}
