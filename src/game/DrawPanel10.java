package game;
import java.awt.BorderLayout;
import server.Server;
import server.Player;
import server.ServerThread;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/* LOGIN PANEL */
class DrawPanel10 extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;

	private BottomPanel lower;
	private MainPanel center;
	
	private Game game;

	public DrawPanel10(Game game)
	{
		this.game = game;
		this.setLayout(new BorderLayout());
		lower = new BottomPanel(game);
		this.add(lower, BorderLayout.SOUTH);

		center = new MainPanel();
		this.add(center, BorderLayout.CENTER);
	}
	class MainPanel extends JPanel implements ActionListener
	{
		private static final long serialVersionUID = 1L;
		
		JButton LOGIN, REGISTER, SAVE, LOGOUT;
		JLabel label1, label2;
		final JTextField  text1, text2;
		private JPanel panel;
		private ValidationWindow validation;
		private boolean validLogin;
		private boolean validRegister;
		private int buttonPressed = 0; // 1: LOGIN 2: REGISTER 3: SAVE 4: LOGOUT
		private String user = "";
		
		Font myFont = new Font("Futura Lt", Font.PLAIN, 16);

		public MainPanel()
		{
			this.setLayout(null);
			setBackground(new Color(250, 243, 228));
			
			label1 = new JLabel();
			label1.setText("USERNAME");
			label1.setFont(myFont);
			text1 = new JTextField(15);
			
			label2 = new JLabel();
			label2.setText("PASSWORD");
			label2.setFont(myFont);
			text2 = new JPasswordField(15);
			  
			LOGIN = new JButton("LOGIN");
			REGISTER = new JButton("REGISTER");
			SAVE = new JButton("SAVE");
			LOGOUT = new JButton("LOGOUT");
			  
			panel = new JPanel(new GridLayout(4,2,5,5));
			panel.setOpaque(false);
			panel.add(label1);
			panel.add(text1);
			panel.add(label2);
			panel.add(text2);
			panel.add(LOGIN);
			panel.add(REGISTER);
			panel.add(SAVE);
			panel.add(LOGOUT);
			
			// panel.setBackground(new Color(250, 243, 228));
			panel.setLocation(200, 300);
			panel.setSize(400, 150);
			panel.setVisible(true);
			this.add(panel);
			
			LOGIN.addActionListener(this);
			LOGIN.setActionCommand("login"); 
			REGISTER.addActionListener(this);
			REGISTER.setActionCommand("register"); 
			SAVE.addActionListener(this);
			SAVE.setActionCommand("save");
			LOGOUT.addActionListener(this);
			LOGOUT.setActionCommand("logout");
			
			validation = new ValidationWindow();
			validation.setLocation(300, 300);
			validation.setSize(250, 100);
			this.add(validation);
			if(validLogin)
			{
				validation.setVisible(true);
			}
			else
			{
				validation.setVisible(false);
			}		
		}
		class ValidationWindow extends JPanel implements ActionListener
		{
			private static final long serialVersionUID = 1L;
			JButton okay = new JButton(new ImageIcon(game.getImage(25)));

			public ValidationWindow()
			{
				this.setLayout(null);
				setBackground(Color.white);

				//OK BUTTON
				okay.setRolloverIcon(new ImageIcon(game.getImage(26)));
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
				g.fillRoundRect(240, 300, 290, 100, 15, 15);
				g.setColor(Color.black);
				g.drawRoundRect(243, 303, 284, 94, 15, 15);

				g.setColor(new Color(75, 49, 9));
				Font myFont = new Font("Futura Hv", Font.PLAIN, 16);
				g.setFont(myFont);
				if(validLogin && buttonPressed == 1)
				{
					g.drawString("You are now logged in.", 280, 345);
				}
				else if(!validLogin && buttonPressed == 1)
				{
					g.drawString("Invalid username/password.", 280, 345);
				}
				else if(buttonPressed == 2 && validRegister)
				{
					g.drawString("Successfully registered!", 280, 345);
				}
				else if(buttonPressed == 2 && !validRegister)
				{
					g.drawString("Invalid username/password.", 280, 345);
				}
				else if(buttonPressed == 3)
				{
					g.drawString("Saved successfully.", 290, 345);
				}
				else if(buttonPressed == 4)
				{
					g.drawString("You have been logged out.", 280, 345);
				}
			}
			public void actionPerformed(ActionEvent evt)
			{
				String command = evt.getActionCommand();
				if(command.equals("OK"))
				{
					validation.setVisible(false);
				}
			}
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			//TITLE
			g2.drawImage(game.getImage(51), 100, 50, 580, 258, this);
			
			g.setColor(new Color(206, 107, 35));
			g.fillRoundRect(185, 290, 430, 170, 20, 20);
		}
		public void actionPerformed(ActionEvent e)
		{
			// VALIDATE USERNAME/PASSWORD
			boolean validUser = false;
			boolean validPw = false;
			String command = e.getActionCommand();
			
			if(command.equals("login"))
			{		
				buttonPressed = 1;
				// if blank username, login as guest
				if(!text1.getText().equals("")&&!text1.getText().equals("guest")) 
				{
					validPw = game.getServer().validate(text1.getText(),text2.getText());
					//				System.out.println("username: "+text1.getText());
					//				System.out.println("pass: "+ text2.getText());
					if(validUser || validPw) // valid user
					{
						validLogin = true;
						game.getPanel10().setComponentZOrder(validation, 1);
						validation.setVisible(true);
						game.getPanel10().repaint();
						//System.out.println("valid login");
						Data data = game.getServer().load(text1.getText());
						this.user = text1.getText();
						game.setMoney(data.getMoney());
						game.setWins(data.getWins());
						game.setLoses(data.getLosses());
						game.setImageNum(data.getCurrentSprite());
						game.setInvisicount(data.getInvisibility());
						game.setSmallhpcount(data.getWeakPotion());
						game.setBighpcount(data.getStrongPotion());
					}
					else // not valid user
					{
						validLogin = false;
						game.getPanel10().setComponentZOrder(validation, 1);
						validation.setVisible(true);
						game.getPanel10().repaint();
					}
				}
				else // GUEST
				{
					validLogin = true;
					game.getPanel10().setComponentZOrder(validation, 1);
					validation.setVisible(true);
					game.getPanel10().repaint();
					//System.out.println("valid login");
					Data data = game.getServer().load("guest");
					this.user = "guest";
					game.setMoney(data.getMoney());
					game.setWins(data.getWins());
					game.setLoses(data.getLosses());
					game.setImageNum(data.getCurrentSprite());
					//System.out.println("image num: "+ game.getImageNum());
					game.setInvisicount(data.getInvisibility());
					game.setSmallhpcount(data.getWeakPotion());
					game.setBighpcount(data.getStrongPotion());
				}
			}
			// register new player
			else if(command.equals("register")) 
			{
				buttonPressed = 2;
				
				if(text1.getText().equals(""))
				{
					validRegister = false;		
				}
				else
				{
					if(game.getServer().register(text1.getText(), text2.getText())) 
					{
						// if register successfully
						validRegister = true;
					}
					else
					{
						validRegister = false;
					}
				}
				
				game.getPanel10().setComponentZOrder(validation, 1);
				validation.setVisible(true);
				game.getPanel10().repaint();
			}
			// save to database
			else if(command.equals("save")) 
			{
				if(this.user.equals("")||this.user.equals("guest")) 
				{
				    //don't save
				}
				else 
				{
				    buttonPressed = 3;
				    game.getServer().save(text1.getText(), game.getMoney(), game.getWins(), game.getLoses(), game.getInvisicount(),game.getSmallhpcount(), game.getBighpcount(), game.getImageNum(), game.getNickname());
				    
				    game.getPanel10().setComponentZOrder(validation, 1);
				    validation.setVisible(true);
				    game.getPanel10().repaint();
				}
			}
			// save and logout user, reset all values
			else if(command.equals("logout"))
			{
				this.user="";
				buttonPressed = 4;
				game.getServer().save(text1.getText(), game.getMoney(), game.getWins(), game.getLoses(), game.getInvisicount(),game.getSmallhpcount(), game.getBighpcount(), game.getImageNum(), game.getNickname());
				game.setMoney(0);
				game.setWins(0);
				game.setLoses(0);
				game.setImageNum(0);
				game.setInvisicount(0);
				game.setSmallhpcount(0);
				game.setBighpcount(0);
				
				game.setCharHealth(150);
				game.setImageNum(6); // set to guest character
				text1.setText(""); // clear fields
				text2.setText("");
				
				game.getPanel10().setComponentZOrder(validation, 1);
				validation.setVisible(true);
				game.getPanel10().repaint();
			}
		}
	}
}
