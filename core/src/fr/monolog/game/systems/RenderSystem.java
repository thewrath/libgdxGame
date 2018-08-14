package fr.monolog.game.systems;

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import fr.monolog.game.components.PositionComponent;
import fr.monolog.game.components.VisualComponent;
import fr.monolog.game.utils.Constants;
import fr.monolog.game.utils.DebugFont;
import fr.monolog.game.utils.FrameRate;
import fr.monolog.game.utils.TextCamera;

public class RenderSystem extends SortedIteratingSystem {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private OrthographicCamera textCamera;

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VisualComponent> vm = ComponentMapper.getFor(VisualComponent.class);
	
	private TiledMap map;
	private TiledMapRenderer mapRenderer;
	
	
	//DEBUG 
	private DebugFont debugFont;
	private FrameRate frameRate;

	@SuppressWarnings("unchecked")
	public RenderSystem (OrthographicCamera camera, TextCamera textCamera, TiledMap map) {
		super(Family.all(PositionComponent.class, VisualComponent.class).get(), new ZComparator());
		batch = new SpriteBatch();
		this.camera = camera;
		this.textCamera = textCamera;
		this.map = map;
		this.mapRenderer = new OrthogonalTiledMapRenderer(this.map, 1f/16f);
		this.debugFont = new DebugFont();
		this.frameRate = new FrameRate(batch, debugFont);
	}
	
	private static class ZComparator implements Comparator<Entity> {
		private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
		
		@Override
		public int compare(Entity e1, Entity e2) {
			int signum = 0-(int)Math.signum(pm.get(e1).getDepthCenter().y - pm.get(e2).getDepthCenter().y);
			return signum;
		}
	}

	@Override
	public void update (float deltaTime) {

		camera.update();
		textCamera.update();
		this.mapRenderer.setView(camera);
		this.mapRenderer.render();
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		this.forceSort();
		super.update(deltaTime);
		batch.setProjectionMatrix(textCamera.combined);
		
		if(Constants.DEBUG) {
			
			this.debugFont.draw(batch, "DEBUG ON", 20, 550);
			this.frameRate.update();
			this.frameRate.render();
			
		}
		batch.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PositionComponent position;
		VisualComponent visual;

		position = pm.get(entity); 
		visual = vm.get(entity);

		for(int j = visual.regions.size()-1; j>=0; j--)
			batch.draw(visual.regions.get(j), position.getX(), position.getY(), 1, 1);
		
	}
	
	@Override
	public void removedFromEngine(Engine engine) {
		super.removedFromEngine(engine);
		this.frameRate.dispose();
		this.batch.dispose();
	}

}