package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/* MAIN MENU */
class DrawPanel1 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	Game game;
	private ButtonPanel center;

	String pStart;
	String pHover;
	JButton play;
	
	String lStart;
	String lHover;
	JButton login;

	String iStart;
	String iHover;
	JButton instructions;

	String oStart;
	String oHover;
	JButton options;

	String uStart;
	String uHover;
	JButton upgrades;

	public DrawPanel1(Game game)
	{
		// DESCRIPTION:
		// Have title, menu buttons and when clicked will lead to diff
		// panel (options, play, upgrades)
		this.game = game;
		
		pStart = game.getImageName(33);
		pHover = game.getImageName(38);
		play = new JButton(new ImageIcon(pStart));
		
		lStart = game.getImageName(46);
		lHover = game.getImageName(47);
		login = new JButton(new ImageIcon(lStart));

		iStart = game.getImageName(34);
		iHover = game.getImageName(39);
		instructions = new JButton(new ImageIcon(iStart));

		oStart = game.getImageName(35);
		oHover = game.getImageName(40);
		options = new JButton(new ImageIcon(oStart));

		uStart = game.getImageName(36);
		uHover = game.getImageName(37);
		upgrades = new JButton(new ImageIcon(uStart));
		
		this.setLayout(null);
		setBackground(Color.gray);
		center = new ButtonPanel();
		center.setLocation(160, 300); //null
		center.setSize(400, 200);
		center.setBackground(Color.black);
		center.setOpaque(false);
		this.add(center);

		//length: 46 width: 400
	}
	class ButtonPanel extends JPanel implements ActionListener
	{
		private static final long serialVersionUID = 1L;
		public ButtonPanel()
		{
			this.setLayout(new GridLayout(5, 1, 0, 5));

			play.setRolloverIcon(new ImageIcon(pHover));
			play.setBorder(BorderFactory.createEmptyBorder());
			play.setContentAreaFilled(false);
			play.setFocusable(false);
			play.addActionListener(this);
			this.add(play);
			play.setBounds(0, 0, 400, 46);
			play.setActionCommand("PLAY");
			
			login.setRolloverIcon(new ImageIcon(lHover));
			login.setBorder(BorderFactory.createEmptyBorder());
			login.setContentAreaFilled(false);
			login.setFocusable(false);
			login.addActionListener(this);
			this.add(login);
			login.setBounds(0, 0, 400, 46);
			login.setActionCommand("LOGIN");

			instructions.setRolloverIcon(new ImageIcon(iHover));
			instructions.setBorder(BorderFactory.createEmptyBorder());
			instructions.setContentAreaFilled(false);
			instructions.setFocusable(false);
			instructions.addActionListener(this);
			this.add(instructions);
			instructions.setBounds(0, 0, 400, 46);
			instructions.setActionCommand("INSTRUCTIONS");

			options.setRolloverIcon(new ImageIcon(oHover));
			options.setBorder(BorderFactory.createEmptyBorder());
			options.setContentAreaFilled(false);
			options.setFocusable(false);
			options.addActionListener(this);
			this.add(options);
			options.setBounds(0, 0, 400, 46);
			options.setActionCommand("OPTIONS");

			upgrades.setRolloverIcon(new ImageIcon(uHover));
			upgrades.setBorder(BorderFactory.createEmptyBorder());
			upgrades.setContentAreaFilled(false);
			upgrades.setFocusable(false);
			upgrades.addActionListener(this);
			this.add(upgrades);
			upgrades.setBounds(0, 0, 400, 46);
			upgrades.setActionCommand("UPGRADES");

		}
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if(command.equals("OPTIONS"))
			{
				game.getCards().show(game.getC(), "Panel 3");
			}
			if(command.equals("INSTRUCTIONS"))
			{
				game.getCards().show(game.getC(), "Panel 5");
			}
			if(command.equals("LOGIN"))
			{
				game.getCards().show(game.getC(), "Panel 10");
			}
			if(command.equals("PLAY"))
			{
				game.getCards().show(game.getC(), "Panel 2");
				game.getPanel2().requestFocus();
			}
			if(command.equals("UPGRADES"))
			{
				game.getCards().show(game.getC(), "Panel 4");
			}
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(game.getImage(48), 160, 105, 398, 200, this);

		g.setColor(Color.black);
		Font oFont = new Font("Futura Hv", Font.PLAIN, 15);
		g.setFont(oFont);
		g.drawString("CSCI 201 FINAL PROJECT", 550, 650);
	}
}