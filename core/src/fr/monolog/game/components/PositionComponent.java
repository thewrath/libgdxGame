package fr.monolog.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
	private float x = 0.0f; 
	private float y = 0.0f;
	public float width = 0.0f;
	public float height = 0.0f;
	private Vector2 depthCenter;
	
	public PositionComponent(float x, float y, float width, float height){
		this.depthCenter = new Vector2();
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		this.depthCenter.x = this.x + width/2;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		this.depthCenter.y = this.y;
	}

	public Vector2 getDepthCenter() {
		return depthCenter;
	}

	

}


