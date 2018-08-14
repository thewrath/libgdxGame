package fr.monolog.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import fr.monolog.game.components.PositionComponent;
import fr.monolog.game.components.VelocityComponent;
import fr.monolog.game.utils.Camera;

public class MovementSystem extends EntitySystem implements InputProcessor{
	private ImmutableArray<Entity> entities;
	private Camera camera;

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

	public MovementSystem(Camera camera) {
		this.camera = camera;
		((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(this);
		
	}

	@SuppressWarnings("unchecked")
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
	}
	
	public void update(float deltaTime) {
		for (int i = 0; i < entities.size(); ++i) {
			
			Entity entity = entities.get(i);
			PositionComponent position = pm.get(entity);
			VelocityComponent velocity = vm.get(entity);
			
			if(velocity.x > 0) {
				if(!(position.getX() > camera.position.x-camera.getOffset().x+camera.viewportWidth-16/16))
					position.setX(position.getX() + velocity.x);
			}
			else {
				if(!(position.getX() < camera.position.x-camera.getOffset().x+16/16))
					position.setX(position.getX() + velocity.x);
			}
			
			if(velocity.y > 0) {
				if(!(position.getY() > camera.position.y-camera.getOffset().y+camera.viewportHeight-16/16))
					position.setY(position.getY() + velocity.y);
			}
			else {
				if(!(position.getY() < camera.position.y-camera.getOffset().y+16/16))
					position.setY(position.getY() + velocity.y);
			}
			
		
			
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		for (int i = 0; i < entities.size(); ++i) {
			Entity entity = entities.get(i);
			VelocityComponent velocity = vm.get(entity);
			
			if(keycode == Keys.Z) {
				velocity.y = 0.1f;
			}
			else if(keycode == Keys.S) {
				velocity.y = -0.1f;
			}
			
			if(keycode == Keys.Q) {
				velocity.x = -0.1f;
				//if(!this.left) {
					//this.region.flip(true, false);
					//this.left = true;
				//}
			}
			else if(keycode == Keys.D) {
				velocity.x = 0.1f;
				//if(this.left) {
					//this.region.flip(true, false);
					//this.left = false;
				//}
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for (int i = 0; i < entities.size(); ++i) {
			Entity entity = entities.get(i);
			VelocityComponent velocity = vm.get(entity);
			
				if(keycode == Keys.Z) {
					if(velocity.y > 0) {
						velocity.y = 0;
					}
				}
				else if(keycode == Keys.S) {
					if(velocity.y < 0) {
						velocity.y = 0;
					}
				}
				
				if(keycode == Keys.Q) {
					if(velocity.x < 0) {
						velocity.x = 0;
					}
				}
				else if(keycode == Keys.D) {
					if(velocity.x > 0) {
						velocity.x = 0;
					}
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
}
