package fr.monolog.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;

import fr.monolog.game.utils.Camera;
import fr.monolog.game.utils.Constants;
import fr.monolog.game.utils.Helpers;
import fr.monolog.game.utils.TextCamera;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.monolog.game.screens.AssetsLoaderScreen;

public class Main extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public Camera camera;
	public TextCamera textCamera;
	public AssetManager manager;
	public Constants constants;
	
	@Override
	public void create () {
		InputMultiplexer inputMulti = new InputMultiplexer();
		Gdx.input.setInputProcessor( inputMulti );
		
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
		this.camera = new Camera(); 
		this.textCamera = new TextCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.manager = new AssetManager();
		this.constants = new Constants(Helpers.xmlReader("../datas/settings.xml"));
		this.setScreen(new AssetsLoaderScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}

