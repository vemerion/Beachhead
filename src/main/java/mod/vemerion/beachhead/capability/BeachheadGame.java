package mod.vemerion.beachhead.capability;

public class BeachheadGame {
	private int score;
	private int turtleMadeItTimer;
	private int cycleOfLifeTimer;
	private int turtleCooldown;

	public int getTurtleCooldown() {
		return turtleCooldown;
	}

	public void setTurtleCooldown(int turtleCooldown) {
		this.turtleCooldown = turtleCooldown;
	}

	public int getEnemyCooldown() {
		return enemyCooldown;
	}

	public void setEnemyCooldown(int enemyCooldown) {
		this.enemyCooldown = enemyCooldown;
	}

	private int enemyCooldown;

	public void saveTurtle() {
		score++;
		if (Math.random() < 0.3 && turtleMadeItTimer < 0) {
			turtleMadeItTimer = 60;
		}
	}

	public void killTurtle() {
		if (Math.random() < 0.3 && cycleOfLifeTimer < 0 && turtleMadeItTimer < 0) {
			cycleOfLifeTimer = 60;
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTurtleMadeItTimer() {
		return turtleMadeItTimer;
	}

	public void setTurtleMadeItTimer(int timer) {
		this.turtleMadeItTimer = timer;
	}

	public int getCycleOfLifeTimer() {
		return cycleOfLifeTimer;
	}

	public void setCycleOfLifeTimer(int timer) {
		this.cycleOfLifeTimer = timer;
	}

	public void tick() {
		turtleMadeItTimer--;
		cycleOfLifeTimer--;
		turtleCooldown--;
		enemyCooldown--;
	}

}
