package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/* OPTIONS PANEL */
class DrawPanel3 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Game game;
	
	// CHARACTER
	private BottomPanel lower;
	private CenterPanel center;
	private JTextArea name;
	
	public String getNickname() {
		return name.getText();
	}
	public void setNickname(String name) {
		this.name.setText(name);
	}

	public DrawPanel3(Game game)
	{
		this.game = game;
		game.setPan3(true);
		this.setLayout(new BorderLayout());
		lower = new BottomPanel(game);
		this.add(lower, BorderLayout.SOUTH);
		game.setPan3(false);

		center = new CenterPanel(); //adding a panel
		this.add(center, BorderLayout.CENTER);
	}
	class CenterPanel extends JPanel //class for new panel
	{
		private static final long serialVersionUID = 1L;
		private InnerPanel1 inner1;
		private JPanel inner2;
		private InnerPanel3 inner3; //title
		private InnerPanel4 inner4;
		private InnerPanel5 inner5; //circle choosing
		
		public CenterPanel()
		{
			this.setLayout(null); //NULL LAYOUT

			setBackground(new Color(222,237,189));

			inner3 = new InnerPanel3();
			inner3.setLocation(90, 0);
			inner3.setSize(500, 500);
			this.add(inner3);

			// Enter Name Panel
			name = new JTextArea(7, 20);
			name.setText(" ");
			Font myFont = new Font("Futura Hv", Font.PLAIN, 13);
			name.setFont(myFont);
			this.add(name);
			name.setBounds(470, 400, 180, 20);

			// Choose Character Panel
			inner1 = new InnerPanel1();
			inner1.setLocation(70, 240);
			inner1.setSize(240, 90);
			inner1.setOpaque(false);
			this.add(inner1);

			// Character Images
			inner2 = new JPanel(new GridLayout(2, 3));
			inner2.setLocation(50, 350);
			inner2.setSize(360, 250);
			inner2.setOpaque(false);
			this.add(inner2);
			JLabel img1 = new JLabel();
			img1.setIcon(new ImageIcon(game.getImage(1).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			inner2.add(img1);
			JLabel img2 = new JLabel();
			img2.setIcon(new ImageIcon(game.getImage(2).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			inner2.add(img2);
			JLabel img3 = new JLabel();
			img3.setIcon(new ImageIcon(game.getImage(3).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			inner2.add(img3);
			JLabel img4 = new JLabel();
			img4.setIcon(new ImageIcon(game.getImage(4).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			inner2.add(img4);
			JLabel img5 = new JLabel();
			img5.setIcon(new ImageIcon(game.getImage(5).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			inner2.add(img5);
			JLabel img6 = new JLabel();
			img6.setIcon(new ImageIcon(game.getImage(6).getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			inner2.add(img6);

			// Difficulty Levels Panel
			inner4 = new InnerPanel4();
			inner4.setLocation(470, 240);
			inner4.setSize(130, 100);
			inner4.setOpaque(false);
			this.add(inner4);

			// Difficulty Levels Circles
			inner5 = new InnerPanel5();
			inner5.setLocation(610, 250);
			inner5.setSize(50, 100);
			this.add(inner5);
		}
		
		class InnerPanel3 extends JPanel
		{
			private static final long serialVersionUID = 1L;
			public InnerPanel3()
			{}
			public void paintComponent(Graphics g)
			{
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.drawImage(game.getImage(49), 70, 10, 450, 200, this); // TITLE
			}
		}
		class InnerPanel4 extends JPanel implements ActionListener
		{
			private static final long serialVersionUID = 1L;
			
			String ezStart = game.getImageName(27);
			String ezHover = game.getImageName(30);
			JButton okay = new JButton(new ImageIcon(ezStart));

			public InnerPanel4() //130, 100
			{
				this.setLayout(new GridLayout(3, 1, 3, 3));

				if(game.getWins() >= 5)
				{
					game.setMedStart(game.getImageName(28));
					game.setMedHover(game.getImageName(31));
				}
				else if(game.getWins() < 5)
				{
					game.setMedStart(game.getImageName(42));
					game.setMedHover(game.getImageName(44)); //LOCKED WORDS
				}
				
				game.setMed(new JButton(new ImageIcon(game.getMedStart())));

				if(game.getWins() >= 10)
				{
					game.sethStart(game.getImageName(29));
					game.sethHover(game.getImageName(32));
				}
				else if(game.getWins() < 10)
				{
					game.sethStart(game.getImageName(43));
					game.sethHover(game.getImageName(45));
				}

				game.setHardb(new JButton(new ImageIcon(game.gethStart())));

				okay.setRolloverIcon(new ImageIcon(ezHover));
				okay.setBorder(BorderFactory.createEmptyBorder());
				okay.setContentAreaFilled(false);
				okay.setFocusable(false);
				okay.addActionListener(this);
				this.add(okay);
				okay.setBounds(340, 360, 55, 25);
				okay.setActionCommand("EASY");

				game.getMed().setRolloverIcon(new ImageIcon(game.getMedHover()));
				game.getMed().setBorder(BorderFactory.createEmptyBorder());
				game.getMed().setContentAreaFilled(false);
				game.getMed().setFocusable(false);
				game.getMed().addActionListener(this);
				this.add(game.getMed());
				game.getMed().setBounds(340, 360, 55, 25);
				game.getMed().setActionCommand("MEDIUM");


				game.getHardb().setRolloverIcon(new ImageIcon(game.gethHover()));
				game.getHardb().setBorder(BorderFactory.createEmptyBorder());
				game.getHardb().setContentAreaFilled(false);
				game.getHardb().setFocusable(false);
				game.getHardb().addActionListener(this);
				this.add(game.getHardb());
				game.getHardb().setBounds(340, 360, 55, 25);
				game.getHardb().setActionCommand("HARD");
			}
			public void actionPerformed(ActionEvent evt)
			{
				game.getMed().revalidate();
				game.getHardb().revalidate();
				String command = evt.getActionCommand();
				if(command.equals("EASY"))
				{
					game.setEasy(true);
					game.setMedium(false);
					game.setHard(false);
					game.setReset(true);
				}
				if(command.equals("MEDIUM") && game.getWins() >= 5)
				{
					game.setMedium(true);
					game.setEasy(false);
					game.setHard(false);
					game.setReset(true);
				}
				if(command.equals("HARD") && game.getWins() >= 10)
				{
					game.setHard(true);
					game.setEasy(false);
					game.setMedium(false);
					game.setReset(true);
				}
			}
		}
		
		/* CHOOSE CHARACTER PANEL */
		class InnerPanel1 extends JPanel implements ActionListener
		{
			private static final long serialVersionUID = 1L;
			private JRadioButton bubz, russel, woody, sulley, yunji, guest;
			private ButtonGroup characters;

			public InnerPanel1()
			{
				this.setLayout(new GridLayout(3, 2)); // (rows, columns)
				characters = new ButtonGroup();
				
				bubz = new JRadioButton("CHRISTIE");
				characters.add(bubz);
				bubz.setBackground(new Color(153,153,204));
				bubz.addActionListener(this);
				this.add(bubz);

				russel = new JRadioButton("ANNIE");
				characters.add(russel);
				russel.setOpaque(false);
				russel.addActionListener(this);
				this.add(russel);

				woody = new JRadioButton("KYLE");
				characters.add(woody);
				woody.setBackground(new Color(153,153,204));
				woody.addActionListener(this);
				this.add(woody);

				sulley = new JRadioButton("EMY");
				characters.add(sulley);
				sulley.setOpaque(false);
				sulley.addActionListener(this);
				this.add(sulley);
				
				yunji = new JRadioButton("YUNJI");
				characters.add(yunji);
				yunji.setOpaque(false);
				yunji.addActionListener(this);
				this.add(yunji);
				
				guest = new JRadioButton("GUEST");
				characters.add(guest);
				guest.setOpaque(false);
				guest.addActionListener(this);
				this.add(guest);
			}
			public void actionPerformed(ActionEvent e)
			{
				String [] attack1 = game.getAttack1();
				String [] attack2 = game.getAttack2();
				String [] attack3 = game.getAttack3();
				
				if(bubz.isSelected())
				{
					game.setImageNum(1);
					game.getSpecial1().setText(attack1[game.getImageNum()]);
					game.getSpecial2().setText(attack2[game.getImageNum()]);
					game.getSpecial3().setText(attack3[game.getImageNum()]);
				}
				else if(russel.isSelected())
				{
					game.setImageNum(2);
					game.getSpecial1().setText(attack1[game.getImageNum()]);
					game.getSpecial2().setText(attack2[game.getImageNum()]);
					game.getSpecial3().setText(attack3[game.getImageNum()]);
				}
				else if(woody.isSelected())
				{
					game.setImageNum(3);
					game.getSpecial1().setText(attack1[game.getImageNum()]);
					game.getSpecial2().setText(attack2[game.getImageNum()]);
					game.getSpecial3().setText(attack3[game.getImageNum()]);
				}
				else if(sulley.isSelected())
				{
					game.setImageNum(4);
					game.getSpecial1().setText(attack1[game.getImageNum()]);
					game.getSpecial2().setText(attack2[game.getImageNum()]);
					game.getSpecial3().setText(attack3[game.getImageNum()]);
				}
				else if(yunji.isSelected())
				{
					game.setImageNum(5);
					game.getSpecial1().setText(attack1[game.getImageNum()]);
					game.getSpecial2().setText(attack2[game.getImageNum()]);
					game.getSpecial3().setText(attack3[game.getImageNum()]);
				}
				else if(guest.isSelected())
				{
					game.setImageNum(6);
					game.getSpecial1().setText(attack1[game.getImageNum()]);
					game.getSpecial2().setText(attack2[game.getImageNum()]);
					game.getSpecial3().setText(attack3[game.getImageNum()]);
				}
				game.setReset(true);
			}
		}
		
		/* DIFFICULTY PANEL */
		class InnerPanel5 extends JPanel
		{
			private static final long serialVersionUID = 1L;
			public InnerPanel5()
			{
				this.setBackground(new Color(222,237,189));
			}
			public void paintComponent(Graphics g)
			{
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				//marks buttons to show which one's selected
				g.setColor(new Color(70, 45, 115));
				if(game.isEasy())
				{
					g.fillOval(0, 0, 10, 10);
					g.setColor(new Color(153, 255, 255));
					g.fillOval(0, 35, 10, 10);
					g.fillOval(0, 70, 10, 10);
				}
				else if(game.isMedium())
				{
					g.fillOval(0, 35, 10, 10);
					g.setColor(new Color(153, 255, 255));
					g.fillOval(0, 0, 10, 10);
					g.fillOval(0, 70, 10, 10);
				}
				else if(game.isHard())
				{
					g.fillOval(0, 70, 10, 10);
					g.setColor(new Color(153, 255, 255));
					g.fillOval(0, 0, 10, 10);
					g.fillOval(0, 35, 10, 10);
				}
				inner5.repaint();
			}
		}
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(Color.black);
			Font smallFont = new Font("Futura Hv", Font.PLAIN, 15);
			g.setFont(smallFont);
			g.drawString("DIFFICULTY:", 467, 224);

			g.setColor(new Color(153,153,204));
			g.fillRect(466, 396, 187, 27);
			g.setColor(Color.black);
			g.drawString("ENTER YOUR NAME:", 467, 385);

			g.drawString("CHOOSE YOUR CHARACTER:", 67, 224);
			g.setColor(new Color(153,153,204));
			g.drawRect(67, 236, 250, 100);
		}
		public void actionPerformed(ActionEvent evt)
		{
			//inner5.repaint();
		}
		
	}

}
