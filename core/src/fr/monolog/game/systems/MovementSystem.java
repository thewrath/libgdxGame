package fr.monolog.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.monolog.game.components.PositionComponent;
import fr.monolog.game.components.StateComponent;
import fr.monolog.game.components.VelocityComponent;
import fr.monolog.game.utils.Camera;
import fr.monolog.game.utils.Mappers;

public class MovementSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private Camera camera;

	public MovementSystem(Camera camera) {
		this.camera = camera;
		
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class, StateComponent.class).get());
	}

	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < entities.size(); ++i) {
			
			Entity entity = entities.get(i);
			PositionComponent position = Mappers.position.get(entity);
			VelocityComponent velocity = Mappers.velocity.get(entity);
			StateComponent state = Mappers.state.get(entity);

			boolean asMoved = false;
			
			if(velocity.x > 0) {
				if(!(position.getX() > camera.position.x-camera.getOffset().x+camera.viewportWidth-1)){
					position.setX(position.getX() + velocity.x);
					asMoved = true;
				}
			}
			else {
				if(!(position.getX() < camera.position.x-camera.getOffset().x+1)){
					position.setX(position.getX() + velocity.x);
					asMoved = true;
				}
			}
			
			if(velocity.y > 0) {
				if(!(position.getY() > camera.position.y-camera.getOffset().y+camera.viewportHeight-1)){
					position.setY(position.getY() + velocity.y);
					asMoved = true;
				}
			}
			else {
				if(!(position.getY() < camera.position.y-camera.getOffset().y+1)){
					position.setY(position.getY() + velocity.y);
					asMoved = true;
				}
			}

			if(asMoved) {
                state.state = StateComponent.State.IN_MOVEMENT;
            }else{
			    state.state = StateComponent.State.IDLE;
            }
		
			
		}
	}

}
