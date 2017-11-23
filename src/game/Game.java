package game;
/* 201 Final Project Game 
 * Group: Yunji, Christie, Kyle, Emy, Annie */

import java.awt.BorderLayout;
import server.ServerThread;
import server.Player;
import server.Server;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Game implements KeyListener, Serializable
{
	public static final long serialVersionUID =1; 
	
	private Server sr;
	private JFrame frame;
	private File [] sound = new File[50];
	private Image [] image = new Image[100];
	private String [] imageName = new String[100];
	private Font smallFont1 = new Font("Futura Lt", Font.BOLD, 14);
	private int imageNum;

	private int wins = 0;
	private int loses = 0;

	// GAME VARIABLES
	private int[][] pacMan = new int[10][10]; 
	private int [] cheeseX = new int[30]; // Locations of cakes
	private int [] cheeseY = new int[30];
	private int [] ghostsX = new int[30];
	private int [] ghostsY = new int[30];
	private int paclocX, paclocY; //final locations
	int animate = 0;
	private Font bigBoldFont = new Font("Futura Hv", Font.BOLD, 40);
	private Font smallFont = new Font("Futura Hv", Font.BOLD, 12);
	private char move = 'h';
	private boolean reset = true;
	private boolean count = false;
	private boolean go = true; // ghosts move or not (upgrade activation)
	private boolean moved = false; // check for victory
	private int cheeseC = 0;
	private int victory = -1; // victory int
	private boolean battle = false;
	private int money = 0;
	private int moneyPlus = 0;
	
	// IMAGES:
	private String [] charName = new String[10];

	// DIFF. LEVELS:
	private boolean easy = true;
	private boolean medium = false;
	private boolean hard = false;

	private String medStart, medHover, hStart, hHover; // CHANGED FROM IMAGE TO STRING
	private JButton med, hardb;

	// ENCOUNTERS:
	private int charHealth = 150;
	private int enemyHealth = 50;
	private int myDamage;
	private boolean miss = false;
	private boolean enemyTurn = false;
	private boolean myTurn = true;
	private boolean reset1 = false;
	private boolean winner = false;
	private boolean loser = false;
	private int counterspec = 1;
	private int counteritem = 1;

	private String [] attack1 = new String[30];
	private String [] attack2 = new String[30];
	private String [] attack3 = new String[30];
	private int specialattcount = 5;

	private String [] question = new String[100];
	private String [][] answer = new String[100][100];
	private int [] correct = new int[100];
	private boolean answeris = false;
	private boolean answerisnot = false;
	private int quizCounter = 1;
	
	private JButton ok1, ok2;

	// UPGRADES:
	private boolean invisible;
	private int invisicount;
	private boolean bomb;
	private int smallhpcount = 0;
	private int bighpcount = 0;

	// BOTTOMLAYOUT
	private boolean pan2, pan3, pan4, pan5;

	// CARD
	private CardLayout cards;

	// GRID
	private GridLayout grid;

	// BORDER
	private BorderLayout border;

	private Container c, g, n, b, f;

	private DrawPanel1 panel1;
	private DrawPanel2 panel2;
	private DrawPanel3 panel3;
	private DrawPanel4 panel4;
	private DrawPanel5 panel5;
	private DrawPanel6 panel6;
	private DrawPanel8 panel8; //upgrades2
	private DrawPanel9 panel9;
	private DrawPanel10 panel10; // login screen
	
	private SoundThread musicHandler;

	private JButton special1, special2, special3;

	public Game(Server sr)
	{
		this.sr = sr;
		sound[1] = new File("sfx/bg.wav");
		sound[2] = new File("sfx/move.wav");
		sound[3] = new File("sfx/attack.wav");
		sound[4] = new File("sfx/getcake.wav");
		sound[5] = new File("sfx/cheer.wav");
		
		musicHandler = new SoundThread(this.getSound(1));
		
		for(int i = 1; i < 53; i++)
		{
			imageName[i] = "img/";
		}
		
		imageNum = 1;
		imageName[1] += "Char1.png"; // 1-6 char's
		imageName[2] += "Char2.png";
		imageName[3] += "Char3.png";
		imageName[4] += "Char4.png";
		imageName[5] += "Char5.png";
		imageName[6] += "Guest.png";
		
		imageName[7] += "Upgrades.png";
		imageName[8] += "Arrow.png";
		imageName[9] += "MrsQuiz.png";
		imageName[10] += "QuizMonster.png";

		imageName[11] += "Char1Back.png"; //11-14 backs of char's
		imageName[12] += "Char2Back.png";
		imageName[13] += "Char3Back.png";
		imageName[14] += "Char4Back.png";
		imageName[15] += "Char5Back.png";
		imageName[16] += "Guest.png";

		imageName[17] += "BuyButton.png";
		imageName[18] += "BuyButtonHover.png";
		imageName[19] += "NextPage.png";
		imageName[20] += "NextPageHover.png";
		imageName[21] += "NextPage2.png";
		imageName[22] += "NextPageHover2.png";
		imageName[23] += "Potion1.png";
		imageName[24] += "Potion2.png";
		imageName[25] += "OkButton.png";
		imageName[26] += "OkButtonHover.png";
		imageName[27] += "Easy.png";
		imageName[28] += "Medium.png";
		imageName[29] += "Hard.png";
		imageName[30] += "EasyHover.png";
		imageName[31] += "MediumHover.png";
		imageName[32] += "HardHover.png";

		imageName[33] += "StartButton.png";
		imageName[34] += "InstructionsButton.png";
		imageName[35] += "OptionsButton.png";
		imageName[36] += "UpgradesButton.png";
		imageName[37] += "UpgradesButtonHover.png";
		
		imageName[38] += "StartButtonHover.png";
		
		imageName[39] += "InstructionsButtonHover.png";
		imageName[40] += "OptionsButtonHover.png";

		imageName[41] += "MrDong.png";
		imageName[42] += "MediumLocked.png";
		imageName[43] += "HardLocked.png";
		imageName[44] += "MediumLockedHover.png";
		imageName[45] += "HardLockedHover.png";
		
		imageName[46] += "LoginButton.png";
		imageName[47] += "LoginButtonHover.png";
		
		imageName[48] += "Title.png"; // was 5
		imageName[49] += "Options.png"; // was 6
		imageName[50] += "Instructions.png"; // was 15
		imageName[51] += "Login.png";
		imageName[52] += "Cake.png"; // was 16

		charName[1] = "CHRISTIE";
		charName[2] = "ANNIE";
		charName[3] = "KYLE";
		charName[4] = "EMY";
		charName[5] = "YUNJI";
		charName[6] = "GUEST";

		bomb = invisible = false;
		invisicount = 0;

		pan2 = pan3 = pan4 = pan5 = false;
		easy = true;
		medium = hard = false;

		attack1[1] = "CHRISTIE BEAM: 2SA";
		attack2[1] = "CHRISTIE PUNT: 1SA";
		attack3[1] = "CHRISTIE SLAP: 1SA";

		attack1[2] = "ANNIE RAM: 2SA";
		attack2[2] = "ANNIE SLAP: 1SA";
		attack3[2] = "ANNIE KICK: 1SA";
		
		attack1[3] = "KYLE SLAP: 2SA";
		attack2[3] = "KYLE KICK: 1SA";
		attack3[3] = "KYLE PUNCH: 1SA";

		attack1[4] = "EMY SQUISH: 2SA";
		attack2[4] = "EMY PINCH: 1SA";
		attack3[4] = "EMY MUNCH: 1SA";
		
		attack1[5] = "YUNJI PUNCH: 2SA";
		attack2[5] = "YUNJI SLAP: 1SA";
		attack3[5] = "YUNJI KICK: 1SA";
		
		attack1[6] = "GUEST PUNCH: 2SA";
		attack2[6] = "GUEST SLAP: 1SA";
		attack3[6] = "GUEST KICK: 1SA";

	}
	
	public void main (String [] args)
	{
		run();
	}
	
	public void run()
	{
		frame = new JFrame("Disneymon Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 700);
		frame.setVisible(true);

		getMyImage();

		special1 = new JButton(attack1[imageNum]);
		special2 = new JButton(attack2[imageNum]);
		special3 = new JButton(attack3[imageNum]);

		// CARDLAYOUT
		c = frame.getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);

		panel1 = new DrawPanel1(this); //MAINMENU
		panel1.setBackground(new Color(153, 255, 255));
		c.add(panel1, "Panel 1");

		panel2 = new DrawPanel2(this); //PACMAN PLAY
		panel2.setBackground(new Color(233, 224, 219));
		panel2.addKeyListener(this);//adds keylistener
		panel2.requestFocus();
		c.add(panel2, "Panel 2");

		panel3 = new DrawPanel3(this); //OPTIONS
		panel3.setBackground(new Color(222, 237, 189));
		c.add(panel3, "Panel 3");

		panel4 = new DrawPanel4(this); //UPGRADES 1
		panel4.setBackground(Color.white);
		c.add(panel4, "Panel 4");

		panel5 = new DrawPanel5(this); //INSTRUCTIONS
		panel5.setBackground(Color.white);
		c.add(panel5, "Panel 5");

		panel6 = new DrawPanel6(this); //BATTLE
		c.add(panel6, "Panel 6");

		panel8 = new DrawPanel8(this); //UPGRADES 2
		panel8.setBackground(Color.white);
		c.add(panel8, "Panel 8");

		panel9 = new DrawPanel9(this); //UPGRADES 2
		panel9.setBackground(Color.white);
		c.add(panel9, "Panel 9");
		
		panel10 = new DrawPanel10(this); // LOGIN
		panel10.setBackground(Color.white);
		c.add(panel10, "Panel 10");
	}
	
	public void getMyImage()
	{
		for(int i = 1; i < 53; i++)
		{
			try
			{
				image[i] = ImageIO.read(new File(imageName[i]));

			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void keyTyped(KeyEvent e)
	{}
	public void keyPressed(KeyEvent e)//Movement
	{
		move = e.getKeyChar();
		if(move == 'r')
		{
			reset = true;
		}
		if(victory < 1 && (move == 'w' || move == 'a' || move == 's' || move == 'd' || move == 'i'))
		{
			musicHandler.playSoundLoop(getSound(2));
			move = e.getKeyChar();
			switch(move)
			{
				case 'w':
				paclocY--;
				moved = true;
				if(paclocY == -1)
				{
					paclocY = 9;
				}
				go = true;
				break;
				case 'a':
				moved = true;
				if(paclocX == 0)
				{
					paclocX = 10;
				}
				paclocX--;
				go = true;
				break;
				case 'd':
				moved = true;
				if(paclocX == 9)
				{
					paclocX = -1;
				}
				paclocX++;
				go = true;
				break;
				case 's':
				moved = true;
				paclocY++;
				if(paclocY == 10)
				{
					paclocY =0;
				}
				go = true;
				break;
				
				//UPGRADES:
				case 'i': //invisible
				if(invisicount > 0)
				{
					invisible = true;
					System.out.print("invisible true\n");
				}
				else if(invisicount == 0)
				{
					invisible = false;
				}
				go = false;
				break;
				case 'b': //invisible
				bomb = true;
				go = false;
				break;


			}
			for (int i = 0; i < 6; i++)//checks for cheese consumption
			{
				if(cheeseX[i] == paclocX && cheeseY[i] == paclocY)
				{
					musicHandler.playSoundLoop(getSound(4));
					cheeseX[i] = cheeseY[i] = 100;
					cheeseC++;
				}
			}
			if(cheeseC == 6)//victory condition
			{
				musicHandler.playSoundLoop(getSound(5));
				victory = 2;
				wins++;
				panel4.repaint();
				if(wins >= 5) //FIXTHIS!!!!!
				{
					med.setIcon(new ImageIcon(imageName[28]));
					med.setRolloverIcon(new ImageIcon(imageName[31]));
					med = new JButton(new ImageIcon(medStart));
					med.revalidate();
					panel3.revalidate();

				}
				if(wins >= 10)
				{
					hardb.setIcon(new ImageIcon(imageName[29]));
					hardb.setRolloverIcon(new ImageIcon(imageName[32]));
					hardb = new JButton(new ImageIcon(hStart));
					hardb.revalidate();
					panel3.revalidate();
				}
				if(wins == 10)
				{
					cards.show(c, "Panel 9");
				}
			}
			if(go)
			{
				int shift;
				int num = 6;
				if(easy)
				{
					num = 6;
				}
				else if(medium)
				{
					num = 10;
				}
				else if(hard)
				{
					num = 20;
				}
				for (int i = 0; i < num; i++) //Monsters movement is completely random
				{
					shift = (int)(Math.random()*4+1);
					switch(shift)
					{
						case 1:
						if(ghostsY[i] == 0)
						{
							ghostsY[i] = 9;
						}
						ghostsY[i]--;
						break;
						case 2:
						if(ghostsY[i] == 9)
						{
							ghostsY[i] = 0;
						}
						ghostsY[i]++;
						break;
						case 3:
						if(ghostsX[i] == 0)
						{
							ghostsX[i] = 9;
						}
						ghostsX[i]--;
						break;
						case 4:
						if(ghostsX[i] == 9)
						{
							ghostsX[i] = 0;
						}
						ghostsX[i]++;
						break;
					}
					
					//win/lose invisi
					if(ghostsX[i] == paclocX && ghostsY[i] == paclocY && invisible == false)
					{
						battle = true;
						ghostsX[i] = (int)(Math.random()*9)+1;
						ghostsY[i] = (int)(Math.random()*9)+1;
						cards.show(c, "Panel 6");
					}
				}
			}
		}
		med.revalidate();
		hardb.revalidate();
	}
	public void keyReleased(KeyEvent e)
	{}
	
	// GETTERS & SETTERS
	public File getSound(int i) {
		return sound[i];
	}
	public SoundThread getMusicHandler() {
		return musicHandler;
	}
	public Image[] getImage() {
		return image;
	}
	public Image getImage(int i) {
		return image[i];
	}
	public void setImage(Image[] image) {
		this.image = image;
	}
	public String[] getImageName() {
		return imageName;
	}
	
	public String getImageName(int i) {
		return imageName[i];
	}
	public void setImageName(String[] imageName) {
		this.imageName = imageName;
	}
	
	public CardLayout getCards() {
		return cards;
	}
	public void setCards(CardLayout cards) {
		this.cards = cards;
	}
	public Container getC() {
		return c;
	}
	public void setC(Container c) {
		this.c = c;
	}
	public DrawPanel1 getPanel1() {
		return panel1;
	}
	public void setPanel1(DrawPanel1 panel1) {
		this.panel1 = panel1;
	}
	public DrawPanel2 getPanel2() {
		return panel2;
	}
	public void setPanel2(DrawPanel2 panel2) {
		this.panel2 = panel2;
	}
	public DrawPanel3 getPanel3() {
		return panel3;
	}
	public void setPanel3(DrawPanel3 panel3) {
		this.panel3 = panel3;
	}
	public DrawPanel4 getPanel4() {
		return panel4;
	}
	public void setPanel4(DrawPanel4 panel4) {
		this.panel4 = panel4;
	}
	public DrawPanel5 getPanel5() {
		return panel5;
	}
	public void setPanel5(DrawPanel5 panel5) {
		this.panel5 = panel5;
	}
	public DrawPanel6 getPanel6() {
		return panel6;
	}
	public void setPanel6(DrawPanel6 panel6) {
		this.panel6 = panel6;
	}
	public DrawPanel8 getPanel8() {
		return panel8;
	}
	public void setPanel8(DrawPanel8 panel8) {
		this.panel8 = panel8;
	}
	public DrawPanel9 getPanel9() {
		return panel9;
	}
	public void setPanel9(DrawPanel9 panel9) {
		this.panel9 = panel9;
	}
	public DrawPanel10 getPanel10() {
		return panel10;
	}
	public void setPanel10(DrawPanel10 panel10) {
		this.panel10 = panel10;
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public Font getSmallFont1() {
		return smallFont1;
	}
	public void setSmallFont1(Font smallFont1) {
		this.smallFont1 = smallFont1;
	}
	public int getImageNum() {
		return imageNum;
	}
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLoses() {
		return loses;
	}
	public void setLoses(int loses) {
		this.loses = loses;
	}
	public int[][] getPacMan() {
		return pacMan;
	}
	public void setPacMan(int x, int y, int pacMan) {
		this.pacMan[x][y] = pacMan;
	}
	public int getCheeseX(int i) {
		return cheeseX[i];
	}
	public void setCheeseX(int i, int cheeseX) {
		this.cheeseX[i] = cheeseX;
	}
	public int getCheeseY(int i) {
		return cheeseY[i];
	}
	public void setCheeseY(int i, int cheeseY) {
		this.cheeseY[i] = cheeseY;
	}
	public int getGhostsX(int i) {
		return ghostsX[i];
	}
	public void setGhostsX(int i, int ghostsX) {
		this.ghostsX[i] = ghostsX;
	}
	public int getGhostsY(int i) {
		return ghostsY[i];
	}
	public void setGhostsY(int i, int ghostsY) {
		this.ghostsY[i] = ghostsY;
	}
	public int getPaclocX() {
		return paclocX;
	}
	public void setPaclocX(int paclocX) {
		this.paclocX = paclocX;
	}
	public int getPaclocY() {
		return paclocY;
	}
	public void setPaclocY(int paclocY) {
		this.paclocY = paclocY;
	}
	public int getAnimate() {
		return animate;
	}
	public void setAnimate(int animate) {
		this.animate = animate;
	}
	public Font getBigBoldFont() {
		return bigBoldFont;
	}
	public void setBigBoldFont(Font bigBoldFont) {
		this.bigBoldFont = bigBoldFont;
	}
	public Font getSmallFont() {
		return smallFont;
	}
	public void setSmallFont(Font smallFont) {
		this.smallFont = smallFont;
	}
	public char getMove() {
		return move;
	}
	public void setMove(char move) {
		this.move = move;
	}
	public boolean isReset() {
		return reset;
	}
	public void setReset(boolean reset) {
		this.reset = reset;
	}
	public boolean isCount() {
		return count;
	}
	public void setCount(boolean count) {
		this.count = count;
	}
	public boolean isGo() {
		return go;
	}
	public void setGo(boolean go) {
		this.go = go;
	}
	public boolean isMoved() {
		return moved;
	}
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	public int getCheeseC() {
		return cheeseC;
	}
	public void setCheeseC(int cheeseC) {
		this.cheeseC = cheeseC;
	}
	public int getVictory() {
		return victory;
	}
	public void setVictory(int victory) {
		this.victory = victory;
	}
	public boolean isBattle() {
		return battle;
	}
	public void setBattle(boolean battle) {
		this.battle = battle;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoneyPlus() {
		return moneyPlus;
	}
	public void setMoneyPlus(int moneyPlus) {
		this.moneyPlus = moneyPlus;
	}
	public String getCharName(int i) {
		return charName[i];
	}
	public void setCharName(int i, String charName) {
		this.charName[i] = charName;
	}
	public boolean isEasy() {
		return easy;
	}
	public void setEasy(boolean easy) {
		this.easy = easy;
	}
	public boolean isMedium() {
		return medium;
	}
	public void setMedium(boolean medium) {
		this.medium = medium;
	}
	public boolean isHard() {
		return hard;
	}
	public void setHard(boolean hard) {
		this.hard = hard;
	}
	public String getMedStart() {
		return medStart;
	}
	public void setMedStart(String medStart) {
		this.medStart = medStart;
	}
	public String getMedHover() {
		return medHover;
	}
	public void setMedHover(String medHover) {
		this.medHover = medHover;
	}
	public String gethStart() {
		return hStart;
	}
	public void sethStart(String hStart) {
		this.hStart = hStart;
	}
	public String gethHover() {
		return hHover;
	}
	public void sethHover(String hHover) {
		this.hHover = hHover;
	}
	public JButton getMed() {
		return med;
	}
	public void setMed(JButton med) {
		this.med = med;
	}
	public JButton getHardb() {
		return hardb;
	}
	public void setHardb(JButton hardb) {
		this.hardb = hardb;
	}
	public int getCharHealth() {
		return charHealth;
	}
	public void setCharHealth(int charHealth) {
		this.charHealth = charHealth;
	}
	public int getEnemyHealth() {
		return enemyHealth;
	}
	public void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}
	public int getMyDamage() {
		return myDamage;
	}
	public void setMyDamage(int myDamage) {
		getMusicHandler().playSoundLoop(getSound(3));
		this.myDamage = myDamage;
	}
	public boolean isMiss() {
		return miss;
	}
	public void setMiss(boolean miss) {
		this.miss = miss;
	}
	public boolean isEnemyTurn() {
		return enemyTurn;
	}
	public void setEnemyTurn(boolean enemyTurn) {
		this.enemyTurn = enemyTurn;
	}
	public boolean isMyTurn() {
		return myTurn;
	}
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
	public boolean isReset1() {
		return reset1;
	}
	public void setReset1(boolean reset1) {
		this.reset1 = reset1;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	public boolean isLoser() {
		return loser;
	}
	public void setLoser(boolean loser) {
		this.loser = loser;
	}
	public int getCounterspec() {
		return counterspec;
	}
	public void setCounterspec(int counterspec) {
		this.counterspec = counterspec;
	}
	public int getCounteritem() {
		return counteritem;
	}
	public void setCounteritem(int counteritem) {
		this.counteritem = counteritem;
	}
	public String[] getAttack1() {
		return attack1;
	}
	public void setAttack1(String[] attack1) {
		this.attack1 = attack1;
	}
	public String[] getAttack2() {
		return attack2;
	}
	public void setAttack2(String[] attack2) {
		this.attack2 = attack2;
	}
	public String[] getAttack3() {
		return attack3;
	}
	public void setAttack3(String[] attack3) {
		this.attack3 = attack3;
	}
	public int getSpecialattcount() {
		return specialattcount;
	}
	public void setSpecialattcount(int specialattcount) {
		this.specialattcount = specialattcount;
	}
	public String[] getQuestion() {
		return question;
	}
	public void setQuestion(String[] question) {
		this.question = question;
	}
	public String[][] getAnswer() {
		return answer;
	}
	public void setAnswer(String[][] answer) {
		this.answer = answer;
	}
	public int[] getCorrect() {
		return correct;
	}
	public void setCorrect(int[] correct) {
		this.correct = correct;
	}
	public boolean isAnsweris() {
		return answeris;
	}
	public void setAnsweris(boolean answeris) {
		this.answeris = answeris;
	}
	public boolean isAnswerisnot() {
		return answerisnot;
	}
	public void setAnswerisnot(boolean answerisnot) {
		this.answerisnot = answerisnot;
	}
	public int getQuizCounter() {
		return quizCounter;
	}
	public void setQuizCounter(int quizCounter) {
		this.quizCounter = quizCounter;
	}
	public JButton getOk1() {
		return ok1;
	}
	public void setOk1(JButton ok1) {
		this.ok1 = ok1;
	}
	public JButton getOk2() {
		return ok2;
	}
	public void setOk2(JButton ok2) {
		this.ok2 = ok2;
	}
	public boolean isInvisible() {
		return invisible;
	}
	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}
	public int getInvisicount() {
		return invisicount;
	}
	public void setInvisicount(int invisicount) {
		this.invisicount = invisicount;
	}
	public boolean isBomb() {
		return bomb;
	}
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}
	public int getSmallhpcount() {
		return smallhpcount;
	}
	public void setSmallhpcount(int smallhpcount) {
		this.smallhpcount = smallhpcount;
	}
	public int getBighpcount() {
		return bighpcount;
	}
	public void setBighpcount(int bighpcount) {
		this.bighpcount = bighpcount;
	}
	public boolean isPan2() {
		return pan2;
	}
	public void setPan2(boolean pan2) {
		this.pan2 = pan2;
	}
	public boolean isPan3() {
		return pan3;
	}
	public void setPan3(boolean pan3) {
		this.pan3 = pan3;
	}
	public boolean isPan4() {
		return pan4;
	}
	public void setPan4(boolean pan4) {
		this.pan4 = pan4;
	}
	public boolean isPan5() {
		return pan5;
	}
	public void setPan5(boolean pan5) {
		this.pan5 = pan5;
	}
	public GridLayout getGrid() {
		return grid;
	}
	public void setGrid(GridLayout grid) {
		this.grid = grid;
	}
	public BorderLayout getBorder() {
		return border;
	}
	public void setBorder(BorderLayout border) {
		this.border = border;
	}
	public Container getG() {
		return g;
	}
	public void setG(Container g) {
		this.g = g;
	}
	public Container getN() {
		return n;
	}
	public void setN(Container n) {
		this.n = n;
	}
	public Container getB() {
		return b;
	}
	public void setB(Container b) {
		this.b = b;
	}
	public Container getF() {
		return f;
	}
	public void setF(Container f) {
		this.f = f;
	}
	public JButton getSpecial1() {
		return special1;
	}
	public void setSpecial1(JButton special1) {
		this.special1 = special1;
	}
	public JButton getSpecial2() {
		return special2;
	}
	public void setSpecial2(JButton special2) {
		this.special2 = special2;
	}
	public JButton getSpecial3() {
		return special3;
	}
	public void setSpecial3(JButton special3) {
		this.special3 = special3;
	}
	public Server getServer() {
		return sr;
	}
	public String getNickname() {
		return panel3.getNickname();
	}
	public void setNickname(String name) {
		panel3.setNickname(name);
	}
}
