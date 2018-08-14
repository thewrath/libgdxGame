package fr.monolog.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import fr.monolog.game.Main;
import fr.monolog.game.levels.Menu;
import fr.monolog.game.managers.LevelManager;
import fr.monolog.game.utils.Constants;

public class GameScreen implements Screen {
    final Main game;
    private LevelManager levelManager;

    public GameScreen(final Main game) {
        this.game = game;
        
        this.levelManager = new LevelManager();
        this.levelManager.addLevel(new Menu(game));
    }

    @Override
    public void render(float delta) {
        //all draw thing are deleg to level 
    	Gdx.gl.glClearColor(Constants.CLEAR_COLOR.x, Constants.CLEAR_COLOR.y, Constants.CLEAR_COLOR.z, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	levelManager.render(delta);
    }

    @Override
    public void resize(int width, int height) {
    	game.camera.resize(width, height);
    	game.textCamera.resize(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}

