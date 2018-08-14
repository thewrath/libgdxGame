package fr.monolog.game.levels;

import fr.monolog.game.Main;

public class Level {
	private int status = 0;
	final Main game;

	public Level(final Main game) {
		// TODO Auto-generated method stub
		this.game = game;
		
	}

	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void setStatus(int status) {
		//0 -> OK status 
		//1 -> pause & go next + 
		//2 -> pause & go previous -
		//3 -> destroy & go next + 
		//4 -> destroy & go previous -
		if (status > 4)
			status = 0;
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
}
