package fr.monolog.game.components;

import java.util.ArrayList;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class VisualComponent implements Component {
	public ArrayList<TextureRegion> regions;

	public VisualComponent (TextureRegion region) {
		this.regions = new ArrayList<TextureRegion>();
		this.regions.add(region);
	}
	
	public VisualComponent (ArrayList<TextureRegion> regions) {
		this.regions = new ArrayList<TextureRegion>(regions);
	}
}