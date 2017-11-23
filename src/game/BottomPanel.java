package game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;

class BottomPanel extends JPanel implements ActionListener, Serializable
{
	// MENU (JPANEL1)
	private static final long serialVersionUID = 1L;
	
	Game game;
	JButton options, play, login, upgrades, instructions;
	JButton mainmenuu;

	public BottomPanel(Game game)
	{
		this.game=game;
		setBackground(new Color(204, 204, 204));

		mainmenuu = new JButton("<- MAIN MENU");
		mainmenuu.addActionListener(this);
		this.add(mainmenuu);

		play = new JButton("PLAY");
		play.addActionListener(this);
		this.add(play);
		
		login = new JButton("LOGIN/SIGNUP");
		login.addActionListener(this);
		this.add(login);

		options = new JButton("OPTIONS");
		options.addActionListener(this);
		this.add(options);

		upgrades = new JButton("UPGRADES");
		upgrades.addActionListener(this);
		this.add(upgrades);

		instructions = new JButton("INSTRUCTIONS");
		instructions.addActionListener(this);
		this.add(instructions);

	}
	public void actionPerformed(ActionEvent evt)
	{
		String command = evt.getActionCommand();
		if(command.equals("OPTIONS"))
		{
			game.getCards().show(game.getC(), "Panel 3");
		}
		else if(command.equals("PLAY"))
		{
			game.getCards().show(game.getC(), "Panel 2");
			game.getPanel2().requestFocus();
		}
		else if(command.equals("LOGIN/SIGNUP"))
		{
			game.getCards().show(game.getC(), "Panel 10");
		}
		else if(command.equals("UPGRADES"))
		{
			game.getCards().show(game.getC(), "Panel 4");
		}
		else if(command.equals("<- MAIN MENU"))
		{
			game.getCards().show(game.getC(), "Panel 1");
		}
		else if(command.equals("INSTRUCTIONS"))
		{
			game.getCards().show(game.getC(), "Panel 5");
		}
	}
}