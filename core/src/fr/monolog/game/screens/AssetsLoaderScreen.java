package fr.monolog.game.screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import fr.monolog.game.Main;

public class AssetsLoaderScreen implements Screen {

	private Main game;
	
	public AssetsLoaderScreen(final Main game) {
		this.game = game;
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		this.loadAssets();	
		if(game.manager.update()) {
			game.setScreen(new MainMenuScreen(game));
		 }
		this.loaderAnimation();
		
	}
	
	private void loadAssets() {
		game.manager.load("sprites.png", Texture.class);
		game.manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		game.manager.load("map.tmx", TiledMap.class);
	}
	
	private void loaderAnimation() {
		float progress = game.manager.getProgress();
		System.out.println(progress); 
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
