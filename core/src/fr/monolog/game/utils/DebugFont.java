package fr.monolog.game.utils;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class DebugFont extends BitmapFont{
	public DebugFont() {
		super();
	    this.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    this.getData().setScale(1.5f);
	    this.setColor(Constants.DEBUG_COLOR);
	}
}
