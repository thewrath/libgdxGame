package fr.monolog.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import fr.monolog.game.Main;

public class MainMenuScreen implements Screen {
    final Main game;

    public MainMenuScreen(final Main game) {
        this.game = game;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        this.game.batch.setProjectionMatrix(game.camera.combined);

        this.game.batch.begin();
        this.game.font.draw(this.game.batch, "Welcome to Drop!!! ", 100, 150);
        this.game.font.draw(this.game.batch, "Tap anywhere to begin!", 100, 100);
        this.game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    	game.camera.resize(width, height);
    	System.out.println("Camera size : "+game.camera.viewportWidth + " "+ game.camera.viewportHeight);
    	System.out.println("Camera position :"+game.camera.position.x +" " + game.camera.position.y);
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