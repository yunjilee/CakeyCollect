package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;

/* ENCOUNTERS PANEL */
class DrawPanel6 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Game game;
	private ButtonPanel button;
	private CharsPanel chars;
	private EnemyHit enemy;
	private WinBattle win;
	private SpecialAttack special;
	private LoseBattle lose;
	private UseItem itemgrid;
	private NoMore none;
	private NoRetreat noretreat;

	public DrawPanel6(Game game)
	{
		this.game = game;
		this.setLayout(null);
		setBackground(new Color(233, 224, 219));

		lose = new LoseBattle();
		lose.setLocation(250, 200);
		lose.setSize(250, 100);
		this.add(lose);
		if(game.isLoser())
		{
			lose.setVisible(true);
		}
		else
		{
			lose.setVisible(false);
		}

		special = new SpecialAttack();
		special.setLocation(529, 370);
		special.setSize(170, 100);
		this.add(special);
		special.setVisible(false);

		none = new NoMore();
		none.setLocation(250, 200);
		none.setSize(250, 100);
		this.add(none);
		none.setVisible(false);

		noretreat = new NoRetreat();
		noretreat.setLocation(250, 200);
		noretreat.setSize(250, 100);
		this.add(noretreat);
		noretreat.setVisible(false);

		itemgrid = new UseItem();
		itemgrid.setLocation(197, 557);
		itemgrid.setSize(150, 71);
		this.add(itemgrid);
		itemgrid.setVisible(false);

		enemy = new EnemyHit();
		enemy.setLocation(250, 200);
		enemy.setSize(250, 100);
		this.add(enemy);
		if(game.isEnemyTurn())
		{
			enemy.setVisible(true);
			enemy.requestFocus();
			System.out.print("enemy\n");
		}
		else
		{
			enemy.setVisible(false);
		}

		chars = new CharsPanel();
		chars.setLocation(0, 0);
		chars.setSize(800, 450);
		this.add(chars);

		button = new ButtonPanel();
		button.setLocation(0, 450);
		button.setSize(800, 300);
		this.add(button);

		win = new WinBattle();
		win.setLocation(250, 200);
		win.setSize(250, 100);
		this.add(win);
		this.requestFocus();
		if(game.isWinner())
		{
			win.setVisible(true);
		}
		if(!game.isWinner())
		{
			win.setVisible(false);
		}
	}
	class CharsPanel extends JPanel
	{
		public CharsPanel()
		{
			this.setLayout(null);
			setBackground(new Color(233, 224, 219));
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.black);

			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Font myFont = new Font("Futura Lt", Font.BOLD, 15);
			g.setFont(myFont);

			//ME
			g.setColor(new Color(200, 200, 176));
			g.fillOval(20, 357, 264, 72);
			g.setColor(new Color(176, 176, 144));
			g.fillOval(23, 360, 256, 64);

			g.setColor(Color.black);
			g.drawString(game.getCharName(game.getImageNum()), 70, 180);
			if(game.getImageNum() == 6)
			{
				g2.drawImage(game.getImage(game.getImageNum()), 80, 200, 200, 200, this);
			}
			else
			{
				g2.drawImage(game.getImage(game.getImageNum()+10), 80, 200, 200, 200, this);
			}
			
			//ENEMY
			g.setColor(new Color(200, 200, 176)); //60, 30
			g.fillOval(510, 237, 176, 40);
			g.setColor(new Color(176, 176, 144));
			g.fillOval(513, 240, 170, 34);

			g.setColor(Color.black);
			g.drawString("ENEMY", 510, 60);
			g2.drawImage(game.getImage(10), 500, 50, 210, 238, this);


			//HEALTH BARS + STUFF
			//mine
			g.setColor(new Color(64, 64, 80));
			g.fillRoundRect(30, 10, 250, 100, 10, 10);
			myFont = new Font("Futura Lt", Font.PLAIN, 13);
			g.setFont(myFont);
			g.setColor(Color.white);
			g.drawString("HEALTH: ", 45, 85);

			g.drawLine(30, 50, 280, 50);

			g.setColor(new Color(207, 81, 50));
			myFont = new Font("Futura Lt", Font.BOLD, 20);
			g.setFont(myFont);
			g.drawString(game.getCharName(game.getImageNum()) + "'S STATS", 45, 40);

			//enemy's
			g.setColor(new Color(64, 64, 80));
			g.fillRoundRect(455, 330, 250, 100, 10, 10);
			myFont = new Font("Futura Lt", Font.PLAIN, 13);
			g.setFont(myFont);
			g.setColor(Color.white);
			g.drawString("HEALTH: ", 468, 405);

			g.drawLine(452, 370, 702, 370);

			g.setColor(new Color(207, 81, 50));
			myFont = new Font("Futura Lt", Font.BOLD, 20);
			g.setFont(myFont);
			g.drawString("ENEMY'S STATS", 468, 360);

			//HEALTH BARS
			//mine
			g.setColor(Color.white);
			g.drawRect(110, 70, 155, 20);
			g.setColor(new Color(185, 50, 65));
			g.fillRect(113, 73, game.getCharHealth(), 15);
			g.setColor(Color.white);
			Font aFont = new Font("Futura Lt", Font.BOLD, 10);
			g.setFont(aFont);
			g.drawString(Integer.toString(game.getCharHealth()) + " / 150", 160, 85);

			//enemy's
			g.setFont(myFont);
			g.setColor(Color.white);
			g.drawRect(535, 390, 155, 20);
			g.setColor(new Color(185, 50, 65));
			g.fillRect(538, 393, game.getEnemyHealth()*3, 15);
			g.setColor(Color.white);
			g.setFont(aFont);
			g.drawString(Integer.toString(game.getEnemyHealth())+" / 50", 590, 405);
		}
	}
	class EnemyHit extends JPanel implements ActionListener
	{
		private int enemyHit;
		private JButton back;

		public EnemyHit()
		{
			this.setLayout(null);
			enemyHit = (int)((Math.random() * 3)*10);

			back = new JButton("OK");
			back.addActionListener(this);
			back.setLocation(160, 65);
			back.setSize(70, 25);
			this.add(back);
		}
		public void paintComponent(Graphics g)
		{
			enemyHit = (int)((Math.random() * 3)*10);

			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRoundRect(0, 0, 250, 100, 15, 15);
			g.setColor(Color.black);
			Font myFont = new Font("Futura Lt", Font.BOLD, 15);
			g.setFont(myFont);
			if(game.isMiss())
			{
				g.drawString("Your attack missed!", 24, 30);
			}
			else
			{
				
				g.drawString("You have dealt " + game.getMyDamage() + " damage.", 24, 30);
			}
			g.drawString("Enemy has dealt " + enemyHit + " damage.", 24, 50);
		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("OK"))
			{
				enemy.setVisible(false);
				game.setCharHealth(game.getCharHealth() - enemyHit);
				game.setMyTurn(true);
				game.setEnemyTurn(false);
				game.getPanel6().repaint();

				if(game.getCharHealth() <= 0)
				{
					game.setMyTurn(false);
					lose.setVisible(true);
					lose.requestFocus();
				}
			}
		}
	}
	class WinBattle extends JPanel implements ActionListener
	{
		private JButton back;

		public WinBattle()
		{
			this.setLayout(null);
			this.requestFocus();

			back = new JButton("OK");
			back.addActionListener(this);
			back.setLocation(160, 65);
			back.setSize(70, 25);
			this.add(back);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRoundRect(0, 0, 250, 100, 15, 15);
			g.setColor(new Color(207, 81, 50));

			game.setMoneyPlus((int)((Math.random() * 3)*10)+10);
			game.setMoney(game.getMoney() + game.getMoneyPlus());

			g.drawRoundRect(4, 4, 242, 92, 15, 15);
			g.drawRoundRect(5, 5, 240, 90, 15, 15);
			g.setColor(Color.black);
			Font myFont = new Font("Futura Lt", Font.BOLD, 15);
			g.setFont(myFont);
			g.drawString("Congratulations!", 60, 35);
			myFont = new Font("Futura Lt", Font.PLAIN, 15);
			g.setFont(myFont);
			g.drawString("You won the battle and $" + Integer.toString(game.getMoneyPlus()) + ".", 30, 55);
			this.requestFocus();
		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("OK"))
			{
				win.setVisible(false);
				game.setWinner(false);
				game.setMyTurn(true);
				game.setEnemyTurn(false);
				game.setEnemyHealth(50);
				
				game.setEnemyTurn(false);
				game.setMyTurn(true);
				game.setCounterspec(1);
				game.setCounteritem(1);
				
				game.getCards().show(game.getC(), "Panel 2");
				game.getPanel2().requestFocus();
				game.setWinner(false);
			}
		}
	}

	class LoseBattle extends JPanel implements ActionListener
	{
		private JButton ok, no;

		public LoseBattle()
		{
			this.setLayout(null);
			this.setBackground(Color.white);
			this.requestFocus();

			no = new JButton("QUIT");
			no.addActionListener(this);
			no.setLocation(145, 65);
			no.setSize(70, 25);
			this.add(no);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRoundRect(0, 0, 250, 100, 15, 15);
			g.setColor(Color.black);
			Font myFont = new Font("Futura Lt", Font.BOLD, 13);
			g.setFont(myFont);
			g.drawString("You lost the battle!", 60, 30);
		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("QUIT"))
			{
				game.getCards().show(game.getC(), "Panel 1");
				game.setLoser(false);
			}
		}
	}
	class NoMore extends JPanel implements ActionListener
	{
		private JButton back;

		public NoMore()
		{
			this.setLayout(null);
			this.requestFocus();

			back = new JButton("OK");
			back.addActionListener(this);
			back.setLocation(160, 65);
			back.setSize(70, 25);
			this.add(back);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRoundRect(0, 0, 250, 100, 15, 15);
			g.setColor(new Color(207, 81, 50));

			g.drawRoundRect(4, 4, 242, 92, 15, 15);
			g.drawRoundRect(5, 5, 240, 90, 15, 15);
			g.setColor(Color.black);
			Font myFont = new Font("Futura Lt", Font.PLAIN, 15);
			g.setFont(myFont);
			g.drawString("You don't have this item.", 40, 45);
			this.requestFocus();

		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("OK"))
			{
				none.setVisible(false);
			}
		}
	}
	class NoRetreat extends JPanel implements ActionListener
	{
		private JButton back;

		public NoRetreat()
		{
			this.setLayout(null);
			this.requestFocus();

			back = new JButton("OK");
			back.addActionListener(this);
			back.setLocation(160, 65);
			back.setSize(70, 25);
			this.add(back);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.white);
			g.fillRoundRect(0, 0, 250, 100, 15, 15);
			g.setColor(new Color(207, 81, 50));

			g.drawRoundRect(4, 4, 242, 92, 15, 15);
			g.drawRoundRect(5, 5, 240, 90, 15, 15);
			g.setColor(Color.black);
			Font myFont = new Font("Futura Lt", Font.PLAIN, 15);
			g.setFont(myFont);
			g.drawString("You don't have enough $ to retreat.", 18, 45);
			this.requestFocus();

		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("OK"))
			{
				noretreat.setVisible(false);
			}
		}
	}
	class UseItem extends JPanel implements ActionListener
	{
		private JButton smallhp, bighp;

		public UseItem()
		{
			this.setLayout(new GridLayout(2, 1, 5, 5));
			this.setBackground(new Color(88, 144, 152));

		//	System.out.println(Integer.toString(game.getSmallhpcount()));
			smallhp = new JButton("WEAK POTION");
			smallhp.addActionListener(this);
			this.add(smallhp);

			bighp = new JButton("STRONG POTION");
			bighp.addActionListener(this);
			this.add(bighp);

		}
		public void paintComponent(Graphics g)
		{
			g.setColor(new Color(88, 144, 152));
		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("WEAK POTION"))
			{
				if(game.getSmallhpcount() > 0)
				{
					if(game.getCharHealth() <= 110)
					{
						game.setCharHealth(game.getCharHealth() + 40);
					}
					else
					{
						game.setCharHealth(150);
					}
					game.setSmallhpcount(game.getSmallhpcount() - 1);
				}
				else
				{
					game.getPanel6().setComponentZOrder(none, 1);
					none.setVisible(true);
				}
				game.getPanel6().repaint();
			}
			else if(command.equals("STRONG POTION"))
			{
				if(game.getSmallhpcount() > 0)
				{
					if(game.getCharHealth() <= 60)
					{
						game.setCharHealth(game.getCharHealth() + 90);
					}
					else
					{
						game.setCharHealth(150);
					}
					game.setBighpcount(game.getBighpcount() - 1);
				}
				else
				{
					game.getPanel6().setComponentZOrder(none, 1);
					none.setVisible(true);
				}
				game.getPanel6().repaint();
			}
		}
	}
	class SpecialAttack extends JPanel implements ActionListener
	{
		public SpecialAttack()
		{
			this.setLayout(new GridLayout(3, 1, 5, 5));
			this.setBackground(new Color(88, 144, 152));

			game.getSpecial1().addActionListener(this);
			this.add(game.getSpecial1());

			game.getSpecial2().addActionListener(this);
			this.add(game.getSpecial2());

			game.getSpecial3().addActionListener(this);
			this.add(game.getSpecial3());

		}
		public void paintComponent(Graphics g)
		{
			g.setColor(new Color(88, 144, 152));
		}
		public void actionPerformed(ActionEvent evt)
		{
			game.getMusicHandler().playSoundLoop(game.getSound(3));
			String [] attack1 = game.getAttack1();
			String [] attack2 = game.getAttack2();
			String [] attack3 = game.getAttack3();
			String command = evt.getActionCommand();
			if(command.equals(attack1[game.getImageNum()]))
			{
				game.setSpecialattcount(game.getSpecialattcount() - 2);
				game.setMyDamage((int)((Math.random() * 5)*10)+40); 
			}
			else if(command.equals(attack2[game.getImageNum()]))
			{
				game.setSpecialattcount(game.getSpecialattcount() -1);
				game.setMyDamage((int)((Math.random() * 5)*10)+20);
			}
			else if(command.equals(attack3[game.getImageNum()]))
			{
				game.setSpecialattcount(game.getSpecialattcount() -1);
				game.setMyDamage((int)((Math.random() * 5)*10)+20);
			}
			if(game.getMyDamage() == 0)
			{
				game.setMiss(true);
			}
			else
			{
				game.setMiss(true);
			}
			game.setEnemyHealth(game.getEnemyHealth() - game.getMyDamage());
			if(game.getEnemyHealth() > 0 && game.getCharHealth() > 0)
			{
				game.setEnemyTurn(true);
				enemy.setVisible(true);
				game.setMyTurn(false);

				game.getPanel6().repaint();
				game.getPanel6().requestFocus();
			}
			else if(game.getEnemyHealth() <= 0)
			{
				game.setEnemyHealth(0);

				game.setWinner(true);
				game.getPanel6().setComponentZOrder(win, 1); //HOORAY SETS ORDER
				win.setVisible(true);
				game.setMyTurn(false);
				game.getPanel6().repaint();
				game.getPanel6().requestFocus();
			}
			else if(game.getCharHealth() <= 0)
			{
				game.setLoser(true);
				lose.setVisible(true);
				game.setMyTurn(false);
				game.getPanel6().repaint();
				game.getPanel6().requestFocus();
			}
			special.setVisible(false);
		}
	}
	class ButtonPanel extends JPanel
	{
		private Buttons button;
		private Background bg;

		public ButtonPanel()
		{
			this.setLayout(null);
			setBackground(new Color(64, 64, 80));

			button = new Buttons();
			button.setLocation(350, 30);
			button.setSize(350, 150);
			this.add(button);

			bg = new Background();
			bg.setLocation(15, 10);
			bg.setSize(710, 200);
			this.add(bg);
		}
		class Background extends JPanel
		{
			public Background()
			{
				this.setLayout(null);
				setBackground(new Color(64, 64, 80));
			}
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(207, 81, 50));
				g.fillRoundRect(0, 0, 710, 200, 15, 15);

				g.setColor(new Color(88, 144, 152));
				g.fillRect(320, 10, 380, 170);

				Font myFont = new Font("Futura Lt", Font.BOLD, 25);
				g.setFont(myFont);
				g.setColor(Color.white);
				g.drawString("What will " + game.getCharName(game.getImageNum()) + " do?", 25, 100);
			}
		}
		class Buttons extends JPanel implements ActionListener
		{
			private JButton attack, specialatt, retreat, item;
			public Buttons()
			{
				this.setLayout(new GridLayout(2, 2, 10, 5));
				setBackground(new Color(88, 144, 152));

				attack = new JButton("ATTACK");
				attack.addActionListener(this);
				this.add(attack);

				specialatt = new JButton("SPECIAL ATT");
				specialatt.addActionListener(this);
				this.add(specialatt);

				item = new JButton("USE ITEM");
				item.addActionListener(this);
				this.add(item);

				retreat = new JButton("$200 RETREAT");
				retreat.addActionListener(this);
				this.add(retreat);
			}
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals("ATTACK") && game.isMyTurn())
				{
					game.setMyDamage((int)((Math.random() * 5)*10));

					if(game.getMyDamage() == 0)
					{
						game.setMiss(true);
					}
					else
					{
						game.setMiss(false);
					}
					game.setEnemyHealth(game.getEnemyHealth() - game.getMyDamage());
					if(game.getEnemyHealth() > 0 && game.getCharHealth() > 0)
					{
						game.setEnemyTurn(true);
						enemy.setVisible(true);
						game.setMyTurn(false);

						game.getPanel6().repaint();
						game.getPanel6().requestFocus();
					}
					else if(game.getEnemyHealth() <= 0)
					{
						game.setEnemyHealth(0);
						
						game.setWinner(true);
						game.getPanel6().setComponentZOrder(win, 1); //HOORAY SETS ORDER
						win.setVisible(true);
						game.setMyTurn(false);
						
						game.getPanel6().repaint();
						game.getPanel6().requestFocus();
					}
					else if(game.getCharHealth() <= 0)
					{
						game.setLoser(true);
						lose.setVisible(true);
						game.setMyTurn(false);
						
						game.getPanel6().repaint();
						game.getPanel6().requestFocus();
					}
				}
				else if(command.equals("SPECIAL ATT") && game.isMyTurn() && game.getSpecialattcount() > 0)
				{
					game.setCounterspec(game.getCounterspec() + 1);

					if(game.getCounterspec() % 2 == 0)
					{
						special.setVisible(true);
					}
					else if(game.getCounterspec() % 2 != 0)
					{
						special.setVisible(false);
					}
				}
				else if(command.equals("$200 RETREAT"))
				{
					if(game.getMoney() >= 200)
					{
						game.setMyTurn(true);
						game.setEnemyTurn(false);
						game.setEnemyHealth(50);
						game.getCards().show(game.getC(), "Panel 2");
						
						game.getPanel2().requestFocus();
						game.setMoney(game.getMoney() - 200);
					}
					else
					{
						game.getPanel6().setComponentZOrder(noretreat, 1);
						noretreat.setVisible(true);
					}
				}
				else if(command.equals("USE ITEM") && game.isMyTurn())
				{
					game.setCounteritem(game.getCounteritem() + 1);

					if(game.getCounteritem() % 2 == 0)
					{
						game.getPanel6().setComponentZOrder(itemgrid, 1);
						itemgrid.repaint();
					//	System.out.println(Integer.toString(game.getSmallhpcount()));
						itemgrid.setVisible(true);
					}
					else if(game.getCounteritem() % 2 != 0)
					{
						itemgrid.setVisible(false);
					}
				}
			}
		}
	}
}
