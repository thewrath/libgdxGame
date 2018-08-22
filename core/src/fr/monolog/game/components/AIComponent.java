package fr.monolog.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

import fr.monolog.game.utils.Helpers;


public class AIComponent implements Component {

    private Vector2 goTo;
    public float speed;

    public AIComponent(){
        this.goTo = new Vector2(0,0);
        this.goTo.x = Helpers.randomFloat(0, 16);
        this.goTo.y = Helpers.randomFloat(0, 16);
        this.speed = 0.01f;
        this.refresh();
    }

    public void refresh(){
        int changeGoTo = Helpers.randomInt(0, 60);
        if(changeGoTo < 2){
            this.goTo.x = Helpers.randomFloat(0, 16);
            this.goTo.y = Helpers.randomFloat(0, 16);
            this.speed = Helpers.randomFloat(0.005f, 0.02f);
        }
    }

    public Vector2 getGoTo(){
        return this.goTo;
    }
}
