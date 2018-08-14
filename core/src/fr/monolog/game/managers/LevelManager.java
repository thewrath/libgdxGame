package fr.monolog.game.managers;

import java.util.ArrayList;

import fr.monolog.game.levels.Level;

public class LevelManager {
	//gere l'enchainement des niveaux etc 
	//list en mode pile contenant les niveaux, handler de message permettant de switch de niveaux 
	private ArrayList<Level> levels;
	private int currentLevel;
	
	public LevelManager() {
		this.levels = new ArrayList<Level>();
		this.currentLevel = 0;
	}
	
	public void addLevel(Level level) {
		this.levels.add(level);
	}
	
	public void render(float delta) {
		this.levels.get(this.currentLevel).render(delta);
		this.checkStatus(this.levels.get(this.currentLevel).getStatus());
	}
	
	public void checkStatus(int status) {
		//0 -> OK status 
		//1 -> pause & go next + 
		//2 -> pause & go previous -
		//3 -> destroy & go next + 
		//4 -> destroy & go previous -
		switch(status) {
			case 0 :
				break;
			case 1 :
				this.currentLevel += 1; 
				if(this.currentLevel >= this.levels.size())
					this.currentLevel = 0;
				break;
			case 2 :
				this.currentLevel -= 1;
				if(this.currentLevel < 0)
					this.currentLevel = this.levels.size()-1;
				break;
			case 3 :
				this.levels.remove(this.currentLevel);
				if(this.currentLevel >= this.levels.size())
					this.currentLevel = 0;
				break;
			case 4 :
				this.levels.remove(this.currentLevel);
				this.currentLevel -= 1;
				if(this.currentLevel < 0)
					this.currentLevel = this.levels.size()-1;
				break;
			default :
				break;
		}
	}
}
