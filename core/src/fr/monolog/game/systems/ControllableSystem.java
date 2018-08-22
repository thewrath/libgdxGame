package fr.monolog.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import fr.monolog.game.components.ControllableComponent;
import fr.monolog.game.components.PositionComponent;
import fr.monolog.game.components.StateComponent;
import fr.monolog.game.components.VelocityComponent;
import fr.monolog.game.utils.Camera;
import fr.monolog.game.utils.Mappers;


public class ControllableSystem extends EntitySystem implements InputProcessor{
    private ImmutableArray<Entity> entities;
    private Camera camera;

    public ControllableSystem(Camera camera) {
        this.camera = camera;
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(this);

    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class, ControllableComponent.class, StateComponent.class).get());
    }


    @Override
    public boolean keyDown(int keycode) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            VelocityComponent velocity = Mappers.velocity.get(entity);

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
            VelocityComponent velocity = Mappers.velocity.get(entity);

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
