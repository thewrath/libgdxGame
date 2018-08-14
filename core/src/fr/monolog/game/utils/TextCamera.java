package fr.monolog.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TextCamera extends OrthographicCamera {
	
	private FitViewport viewport;
	
	public TextCamera(int width, int height) {
		super(width, height);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		this.position.set(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 0);
		this.viewport = new FitViewport(w, h); 
	
	}
	
	public void resize(int width, int height) {
		this.viewport.update(width, height); 
		this.update();
	}
	
}
