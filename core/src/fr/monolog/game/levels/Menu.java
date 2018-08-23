package fr.monolog.game.levels;

import java.util.ArrayList;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;

import fr.monolog.game.Main;
import fr.monolog.game.factories.EntityFactory;
import fr.monolog.game.systems.AISystem;
import fr.monolog.game.systems.ControllableSystem;
import fr.monolog.game.systems.MovementSystem;
import fr.monolog.game.systems.RenderSystem;


public class Menu extends Level{

	private PooledEngine engine;
	private TiledMap map;


	public Menu(final Main game) {
		//récupération des données présente dans un JSON (définie les entités à créer, la map etc )
		//Création d'une factorie à l'aide des données récupéré dans le JSON ) 
		// Recupération des entités créées par la factorie et ajout dans l'engine
		super(game);
		engine = new PooledEngine();
		map = game.manager.get("map.tmx", TiledMap.class);
		
		Entity player = EntityFactory.createPlayer(game.manager);
		engine.addEntity(player);
	
		ArrayList<Entity> dynamicsEntities = EntityFactory.createPnjs(game.manager, map.getLayers().get("pnjs"));
		dynamicsEntities.addAll(EntityFactory.createStructures(game.manager, map.getLayers().get("structures")));
		for(Entity entity : dynamicsEntities) {
			engine.addEntity(entity);
		}
				
		MovementSystem movementSystem = new MovementSystem(game.camera);
		ControllableSystem controllableSystem = new ControllableSystem(game.camera);
		AISystem aiSystem = new AISystem();
		RenderSystem renderSystem = new RenderSystem(game.camera, game.textCamera, this.map);
		
		engine.addSystem(movementSystem);
		engine.addSystem(controllableSystem);
		engine.addSystem(aiSystem);
		engine.addSystem(renderSystem);

		
	}

	@Override
	public void render(float delta) {
		engine.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
	
}

