package fr.monolog.game.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {

    public enum State {
        IDLE,
        IN_MOVEMENT,
        ATTACK,
    }

    public State state;

    public StateComponent(){
        this.state = State.IDLE;
    }

}
