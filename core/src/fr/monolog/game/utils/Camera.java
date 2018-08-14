package fr.monolog.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Camera extends OrthographicCamera implements InputProcessor {
	
	private Vector2 velocity;
	private FitViewport viewport;
	private Vector3 futurPosition;
	private Vector3 offset;
	
	public Camera() {
		super(0, 0);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		this.futurPosition = new Vector3(this.position);
		this.setToOrtho(false, (w / h) *  16, 16);
		this.viewport = new FitViewport(w, h);
		this.velocity = new Vector2(0.5f,0);
		this.zoom = 1f;
		this.offset = new Vector3(this.position);
		this.update();
		
		((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(this);
	}

	public void move() {
		if(this.position.x < this.futurPosition.x) {
			this.position.x = this.position.x + this.velocity.x;
		}
		
	}
	
	public void goRigth() {
		this.futurPosition.set(this.position.x + this.viewportWidth, 0, 0);
		System.out.println("Camera size : "+this.viewportWidth + " "+ this.viewportHeight);
		
	}
	
	public void resize(int width, int height) {
		viewport.update(width, height);
		this.update();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.UP) {
			velocity.y = 1;
		}
		else if(keycode == Keys.DOWN) {
			velocity.y = -1;
		}
		
		if(keycode == Keys.RIGHT) {
			velocity.x = 1;
		}
		else if(keycode == Keys.LEFT) {
			velocity.x = -1;
		}
		
		if(keycode == Keys.SPACE) {
			this.goRigth();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.UP) {
			if(velocity.y > 0) {
				this.velocity.y = 0;
			}
		}
		else if(keycode == Keys.DOWN) {
			if(velocity.y < 0) {
				this.velocity.y = 0;
			}
		}
		
		if(keycode == Keys.LEFT) {
			if(velocity.x < 0) {
				this.velocity.x = 0;
			}
		}
		else if(keycode == Keys.RIGHT) {
			if(velocity.x > 0) {
				this.velocity.x = 0;
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public Vector3 getOffset() {
		return offset;
	}

	public void setOffset(Vector3 offset) {
		this.offset.set(offset);
	}
	
	
}
