package fr.monolog.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

import fr.monolog.game.components.AIComponent;
import fr.monolog.game.components.PositionComponent;
import fr.monolog.game.components.StateComponent;
import fr.monolog.game.components.VelocityComponent;
import fr.monolog.game.utils.Mappers;

public class AISystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    public AISystem(){

    }


    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class, AIComponent.class, StateComponent.class).get());
    }

    @Override
    public void update(float delta){
        for (Entity entity : entities){
            PositionComponent position = Mappers.position.get(entity);
            VelocityComponent velocity = Mappers.velocity.get(entity);
            AIComponent ai = Mappers.ai.get(entity);

            //AI movement here
            velocity.x = 0;
            velocity.y = 0;


            if(position.getX() < ai.getGoTo().x){
                velocity.x = ai.speed;
            }
            else{
                velocity.x = -ai.speed;
            }

            if(position.getY() < ai.getGoTo().y){
                velocity.y = ai.speed;
            }
            else{
                velocity.y = -ai.speed;
            }

            ai.refresh();

        }
    }
}
