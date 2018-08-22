package fr.monolog.game.utils;

import com.badlogic.ashley.core.ComponentMapper;
import fr.monolog.game.components.*;

public class Mappers {

    public static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static ComponentMapper<ControllableComponent> controllable = ComponentMapper.getFor(ControllableComponent.class);
    public static ComponentMapper<StateComponent> state = ComponentMapper.getFor(StateComponent.class);
    public static ComponentMapper<AIComponent> ai = ComponentMapper.getFor(AIComponent.class);
    public static ComponentMapper<VisualComponent> visual = ComponentMapper.getFor(VisualComponent.class);

}
