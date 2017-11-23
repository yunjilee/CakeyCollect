package game;

public class Data {
	private int money;
	private int wins;
	private int losses;
	private int invisibility;
	private int weakPotion;
	private int strongPotion;
	private int currentSprite;
	private String nickname;
	
	public Data(int money, int wins, int losses, int invis, int weakPotion, int strongPotion,
			int currentSprite, String nickname) {
		this.money = money;
		this.wins = wins;
		this.losses = losses;
		this.invisibility = invis;
		this.weakPotion = weakPotion;
		this.strongPotion = strongPotion;
		this.currentSprite = currentSprite;
		this.nickname = nickname;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getInvisibility() {
		return invisibility;
	}
	public void setInvisibility(int invisibility) {
		this.invisibility = invisibility;
	}
	public int getWeakPotion() {
		return weakPotion;
	}
	public void setWeakPotion(int weakPotion) {
		this.weakPotion = weakPotion;
	}
	public int getStrongPotion() {
		return strongPotion;
	}
	public void setStrongPotion(int strongPotion) {
		this.strongPotion = strongPotion;
	}
	public int getCurrentSprite() {
		return currentSprite;
	}
	public void setCurrentSprite(int currentSprite) {
		this.currentSprite = currentSprite;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
