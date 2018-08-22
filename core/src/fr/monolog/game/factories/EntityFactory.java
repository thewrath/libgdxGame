package fr.monolog.game.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.maps.MapObject;

import fr.monolog.game.components.*;
import fr.monolog.game.utils.Helpers;

public class  EntityFactory {
	
	private static ArrayList<Quaternion> pnjRegions = new ArrayList<Quaternion>( Arrays.asList(
			new Quaternion(96, 224, 16, 16),
			new Quaternion(112, 224, 16, 16),
			new Quaternion(128, 224, 16, 16),
			new Quaternion(144, 224, 16, 16),
			new Quaternion(160, 224, 16, 16),
			new Quaternion(176, 224, 16, 16)
	));
	
	private static Dictionary<String, Quaternion> structuresRegions = new Hashtable<String, Quaternion>();

	private static void initStructuresRegions() {
		if(structuresRegions.size() != 0)
			return;
		structuresRegions.put("firePit", new Quaternion(64, 240, 16, 16));
		structuresRegions.put("chess", new Quaternion(240, 208, 16, 16));
	}
	
	public static Entity createPlayer(AssetManager manager) {
		Entity player = new Entity();
		
		Texture texture = manager.get("sprites.png", Texture.class);
    	ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
    	regions.add(new TextureRegion(texture, 96, 240, 16, 16));
    	regions.add(new TextureRegion(texture, 80, 240, 16, 16));
    	texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		
		player.add(new PositionComponent(0.5f,0.5f, 16, 16));
		player.add(new VelocityComponent());
		player.add(new ControllableComponent());
		player.add(new VisualComponent(regions));
		player.add(new StateComponent());
		return player;
	}
	
	public static ArrayList<Entity> createPnjs(AssetManager manager, MapLayer layer){
		ArrayList<Entity> entities = new ArrayList<Entity>();
		MapObjects objects = layer.getObjects();
		for(MapObject object : objects) {
			Entity entity = EntityFactory.createPnj(manager, object);
			entities.add(entity);
		}
		return entities;
	}
	
	private static Entity createPnj(AssetManager manager, MapObject object){
		Entity entity = new Entity();
		Quaternion regionDatas = pnjRegions.get(0);
		pnjRegions.remove(0);
		
		
		Texture texture = manager.get("sprites.png", Texture.class);
		ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
		regions.add(new TextureRegion(texture, (int)regionDatas.x, (int)regionDatas.y, (int)regionDatas.z, (int)regionDatas.w));
		regions.add(new TextureRegion(texture, 80, 240, 16, 16));
		texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		
		float x = object.getProperties().get("x", float.class);
		float y = object.getProperties().get("y", float.class);
		
		entity.add(new PositionComponent(x/16,y/16, 16, 16));
		entity.add(new VelocityComponent());
		entity.add(new AIComponent());
		entity.add(new VisualComponent(regions));
		entity.add(new StateComponent());
		
		return entity;
	}
	
	public static ArrayList<Entity> createStructures(AssetManager manager, MapLayer layer){
		initStructuresRegions();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		MapObjects objects = layer.getObjects();
		for(MapObject object : objects) {
			Entity entity = EntityFactory.createStructure(manager, object);
			entities.add(entity);
		}
		return entities;
	}
	
	private static Entity createStructure(AssetManager manager, MapObject object) {
		Entity entity = new Entity();
		
		Quaternion regionDatas = structuresRegions.get(object.getProperties().get("type", String.class));
		
		Texture texture = manager.get("sprites.png", Texture.class);
		TextureRegion region = new TextureRegion(texture, (int)regionDatas.x, (int)regionDatas.y, (int)regionDatas.z, (int)regionDatas.w);
		texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		
		float x = object.getProperties().get("x", float.class);
		float y = object.getProperties().get("y", float.class);
		
		entity.add(new PositionComponent(x/16,y/16, 16, 16));
		entity.add(new VisualComponent(region));
		entity.add(new StateComponent());
		
		return entity;
	}
	
	
}
