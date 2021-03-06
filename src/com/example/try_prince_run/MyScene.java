package com.example.try_prince_run;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.action.listener.IActionListener;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.remotecontroller.RemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController.CommandType;
import com.example.try_gameengine.scene.DialogScene;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaRequest;
import com.example.try_gameengine.utils.GameTimeUtil;
import com.example.try_gameengine.utils.IDetectAreaRequest;
import com.example.try_gameengine.utils.ISpriteDetectAreaListener;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;
import com.example.try_gameengine.utils.SpriteDetectAreaHelper;
import com.example.try_prince_run.sprite.Crosshair;
import com.example.try_prince_run.sprite.Sheep;
import com.example.try_prince_run.utils.BitmapUtil;
import com.example.try_prince_run.utils.CommonUtil;

public class MyScene extends EasyScene{
	private List<Sprite> fireballs = new CopyOnWriteArrayList<Sprite>();
	Sprite player;
	private int move = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	boolean isPressLeftMoveBtn, isPressRightMoveBtn;
	private int gameTime;
	
	private GameTimeUtil fireballTimeUtil;
	private GameTimeUtil gameTimeUtil;

	private static final int INVALID_POINTER_ID = -1;
	
	private List<Sheep> sheeps = new ArrayList<Sheep>();
	
//	private EnemyManager enemyManager;
	private Sprite crosshair;
	
	private float moveXOfSheep = 5;
	private float moveYOfSheep = 5;
	
	public MyScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		setRemoteController(RemoteController.createRemoteController());
		((RemoteController) getRemoteController()).setUpKyPosition(CommonUtil.screenWidth - ((RemoteController) getRemoteController()).getUpKey().getWidth(), CommonUtil.screenHeight - ((RemoteController) getRemoteController()).getUpKey().getHeight());
		((RemoteController) getRemoteController()).setLeftKyPosition(0, CommonUtil.screenHeight - ((RemoteController) getRemoteController()).getUpKey().getHeight());
		((RemoteController) getRemoteController()).setRemoteContollerListener(new RemoteController.RemoteContollerListener() {
			
			@Override
			public void pressDown(
					List<com.example.try_gameengine.remotecontroller.RemoteController.CommandType> commandTypes) {
				// TODO Auto-generated method stub
				for(CommandType commandType : commandTypes){
					switch (commandType) {
					case UPKeyUpCommand:
						isPressRightMoveBtn = false;
						move = LEFT;
						
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn){
							player.setXscale(-1.0f);
							player.runActionFPSFrame(null, new int[]{11,10,9}, new int[]{8,8,8}, false, new com.example.try_gameengine.framework.IActionListener() {
								
								@Override
								public void beforeChangeFrame(int nextFrameId) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void afterChangeFrame(int periousFrameId) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void actionFinish() {
									// TODO Auto-generated method stub
									isMoveing = false;
								}
							});
							
							move = 0;
							player.currentAction = null;
							isMoveing = false;
						}
						

						break;
					case UPKeyDownCommand:
						isPressRightMoveBtn = true;
						move = RIGHT;
						
						final Layer bgLayer = new Layer(BitmapUtil.gameover, BitmapUtil.gameover.getWidth(), BitmapUtil.gameover.getHeight(), false);
						bgLayer.setPosition(0, bgLayer.getHeight());
//						final Sprite restartButton = new Sprite(BitmapUtil.restartBtn01, 350, 200, false);
						final ButtonLayer restartButton = new ButtonLayer(0, 0, false);
						restartButton.setBitmapAndAutoChangeWH(BitmapUtil.restartBtn01);
						restartButton.setButtonBitmap(BitmapUtil.restartBtn01, BitmapUtil.restartBtn02, BitmapUtil.restartBtn01);
						restartButton.setPosition(CommonUtil.screenWidth/2.0f - restartButton.getWidth()/2.0f, CommonUtil.screenHeight/4.0f*3);
						restartButton.setOnClickListener(new ButtonLayer.OnClickListener() {
							
							@Override
							public void onClick(ButtonLayer buttonLayer) {
								// TODO Auto-generated method stub
								((GameActivity)context).sceneManager.previousWithExistedScenes();
							}
						});
						final LabelLayer labelLayer = new LabelLayer("hello", 0, 0, false);
						labelLayer.setTextSize(100);
						labelLayer.setPosition(500, 500);
						final DialogScene dialogScene = new DialogScene(context, "c");
						dialogScene.setDialogSceneDraw(new DialogScene.DialogSceneDrawListener() {
							
							@Override
							public void draw(Canvas canvas) {
								// TODO Auto-generated method stub
				//				canvas.drawColor(Color.RED);
								canvas.drawColor(Color.TRANSPARENT,Mode.CLEAR);
								Paint paint = new Paint();
								paint.setColor(Color.RED);
								canvas.drawRect(new Rect(100, 100, 300, 300), paint);
								paint.setColor(Color.BLACK);
								paint.setAlpha(150);
								canvas.drawRect(new RectF(0, 0, CommonUtil.screenWidth, CommonUtil.screenHeight ), paint);
								
								bgLayer.drawSelf(canvas, null);
								
								restartButton.drawSelf(canvas, null);
								
								labelLayer.drawSelf(canvas, null);
							}
						});
						dialogScene.setDialogSceneTouchListener(new DialogScene.DialogSceneTouchListener() {
							
							@Override
							public void onTouchEvent(MotionEvent event) {
								// TODO Auto-generated method stub
								float x = event.getX();
								float y = event.getY();
								
								LayerManager.getInstance().onTouchSceneLayers(event, dialogScene.getLayerLevel());
								

							}
						});
						dialogScene.setIsNeedToStopTheActiveScene(false);
						((GameActivity)context).sceneManager.addScene(dialogScene);
						((GameActivity)context).sceneManager.startLastScene();
						
						dialogScene.addAutoDraw(restartButton);
				//		dialogScene.start();
						
						
						
						break;
					case LeftKeyDownCommand:
						isPressLeftMoveBtn = true;
						move = LEFT;
						
						
						
						break;
					case LeftKeyUpCommand:
						isPressLeftMoveBtn = false;
						move = RIGHT;
				
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn){
							player.setXscale(1.0f);
							player.runActionFPSFrame(null, new int[]{11,10,9}, new int[]{8,8,8}, false, new com.example.try_gameengine.framework.IActionListener() {
								
								@Override
								public void beforeChangeFrame(int nextFrameId) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void afterChangeFrame(int periousFrameId) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void actionFinish() {
									// TODO Auto-generated method stub
									isMoveing = false;
								}
							});
							
							move = 0;
							player.currentAction = null;
							isMoveing = false;
						}
						break;
					default:
						break;
					}
				}
			}
		});
		
//		Sprite bg = new Sprite(BitmapUtil.bg, CommonUtil.screenWidth, CommonUtil.screenHeight, false);
//		addAutoDraw(bg);
//		Sprite flower = new Sprite(BitmapUtil.flower, BitmapUtil.flower.getWidth(), BitmapUtil.flower.getHeight(), false);
//		addAutoDraw(flower);
//		flower.setPosition(0, CommonUtil.screenHeight - flower.h*1.5f);
		Sprite cloud1 = new Sprite(BitmapUtil.cloud1, BitmapUtil.cloud1.getWidth(), BitmapUtil.cloud1.getHeight(), false);
		addAutoDraw(cloud1);
		cloud1.setPosition(CommonUtil.screenWidth/2.0f - cloud1.getWidth()/2.0f, 0);
		Sprite cloud2 = new Sprite(BitmapUtil.cloud2, BitmapUtil.cloud2.getWidth(), BitmapUtil.cloud2.getHeight(), false);
		addAutoDraw(cloud2);
		cloud2.setPosition(0, 0);
		Sprite cloud3 = new Sprite(BitmapUtil.cloud3, BitmapUtil.cloud3.getWidth(), BitmapUtil.cloud3.getHeight(), false);
		addAutoDraw(cloud3);
		cloud3.setPosition(CommonUtil.screenWidth - cloud1.getWidth(), 0);
		
		player = new Sprite(BitmapUtil.yellowPoint, 100, 1000, false);
		player.setBitmapAndFrameWH(BitmapUtil.hamster, 150, 150);
		player.setCollisionRectFEnable(true);
		player.setPosition(CommonUtil.screenWidth/2.0f - player.getWidth()/2.0f, CommonUtil.screenHeight - player.getHeight());
		player.setCollisionOffsetXY(50, 100);
		player.setCollisionRectFWH(100, 100);
		
		Sheep sheep = new Sheep(500, 500, false);
		sheep.setMoveRage(0,0,CommonUtil.screenHeight,CommonUtil.screenWidth);
		sheep.setMoveRageType(MoveRageType.Reflect);
		sheeps.add(sheep);
		
		SpriteDetectAreaHelper spriteDetectAreaHelper = new SpriteDetectAreaHelper();
		DetectArea a = spriteDetectAreaHelper.createDetectAreaRound(new PointF(sheep.getCenterX(), sheep.getCenterY()), 200);
		DetectArea b = spriteDetectAreaHelper.createDetectAreaRound(new PointF(sheep.getCenterX(), sheep.getCenterY()), 300);
		
		SpriteDetectAreaHandler spriteDetectAreaHandler = new SpriteDetectAreaHandler();
		spriteDetectAreaHandler.addSuccessorDetectAreaByRound(new PointF(sheep.getCenterX(), sheep.getCenterY()), 30);
		spriteDetectAreaHandler.addSuccessorDetectArea(a, new ISpriteDetectAreaListener() {
			
			@Override
			public void didDetected(DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea) {
				// TODO Auto-generated method stub
//				Sheep sheep = (Sheep) handlerDetectArea.getObjectTag();
				PointF center = handlerDetectArea.getCenter();
				PointF requestCenter = requestDetectArea.getDetectArea().getCenter();
				if(requestCenter.x <= center.x && requestCenter.y <= center.y ){ //LeftTop
					moveXOfSheep = Math.abs(moveXOfSheep);
					moveYOfSheep = Math.abs(moveYOfSheep);
				}else if(requestCenter.x <= center.x && requestCenter.y > center.y){ //LeftBottom
					moveXOfSheep = Math.abs(moveXOfSheep);
					moveYOfSheep = -1*Math.abs(moveYOfSheep);
				}else if(requestCenter.x > center.x && requestCenter.y <= center.y){ //RightTop
					moveXOfSheep = -1*Math.abs(moveXOfSheep);
					moveYOfSheep = Math.abs(moveYOfSheep);
				}else if(requestCenter.x > center.x && requestCenter.y > center.y){ //RightBottom
					moveXOfSheep = -1*Math.abs(moveXOfSheep);
					moveYOfSheep = -1*Math.abs(moveYOfSheep);
				}
			}

			@Override
			public boolean stopDoSuccessorDetected(
					DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea, boolean isDetected) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		spriteDetectAreaHandler.addSuccessorDetectArea(b, new ISpriteDetectAreaListener() {
			
			@Override
			public void didDetected(DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean stopDoSuccessorDetected(
					DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea, boolean isDetected) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		spriteDetectAreaHandler.apply();
		sheep.setSpriteDetectAreaHandler(spriteDetectAreaHandler);
		
//		enemyManager = new EnemyManager();
//		enemyManager.setRedEnemyBitmap(BitmapUtil.redPoint);
//		enemyManager.setBlueEnemyBitmap(BitmapUtil.bluePoint);
//		enemyManager.createEnemy();
		crosshair = new Crosshair(CommonUtil.screenWidth/2, CommonUtil.screenHeight/2, false);
		
//		sprite.setAction(actionName);
	}

	GameView gameview;
	
	@Override
	public GameView initGameView(Activity activity, IGameController gameController,
			IGameModel gameModel) {
		// TODO Auto-generated method stub
		class MyGameView extends GameView{
			public MyGameView(Context context, IGameController gameController,
					IGameModel gameModel) {
				super(context, gameController, gameModel);
				// TODO Auto-generated constructor stub
			}			
		}		
		gameview = new MyGameView(activity, gameController, gameModel); return gameview;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		checkCollision();
		
		for(Sprite fireball : fireballs){
			fireball.frameTrig();
		}
		
		if(fireballTimeUtil.isArriveExecuteTime()){
			createFireball();
		}
		
		checkPlayerMoved();
		
		for(Sheep sheep : sheeps){
			sheep.move(moveXOfSheep, moveYOfSheep);
		}
		
		tickTime();
	}
	
	private void tickTime(){
		if(gameTimeUtil.isArriveExecuteTime()){
			gameTime++;
		}
	}
	
	private void checkCollision(){
		for(Sprite fireball : fireballs){
			if(fireball.getDst().intersect(player.getCollisionRectF())){
				fireball.getMovementAction().controller.cancelAllMove();
				fireballs.remove(fireball);
			}
		}
	}
	
	private void createFireball(){
		final Sprite fireball = new Sprite(BitmapUtil.fireball, 150, 200, false);
		addAutoDraw(fireball);
		MovementAction fireballDownAciton = MAction.moveTo(100, CommonUtil.screenHeight, 2500);
		fireballDownAciton.setMovementActionController(new MovementAtionController());
		fireballDownAciton.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				fireball.move(dx, dy);
			}
		});
		fireballDownAciton.setActionListener(new IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void actionStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
				fireball.getMovementAction().controller.cancelAllMove();
				fireballs.remove(fireball);
			}
			
			@Override
			public void actionCycleFinish() {
				// TODO Auto-generated method stub
				
			}
		});
		fireball.runMovementAction(fireballDownAciton);
		fireballs.add(fireball);
	}
	
	boolean isMoveing = false;
	private void checkPlayerMoved(){
		
		if(move == LEFT){
			player.move(-5, 0);
			player.setXscale(1.0f);
			if(!isMoveing){
				isMoveing = true;
				player.runActionFPSFrame(null, new int[]{12,12,13}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
					
					@Override
					public void beforeChangeFrame(int nextFrameId) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterChangeFrame(int periousFrameId) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void actionFinish() {
						// TODO Auto-generated method stub
						isMoveing = false;
					}
				});
			}
		}else if(move == RIGHT){
			player.move(5, 0);
			player.setXscale(-1.0f);
			if(!isMoveing){
				isMoveing = true;
				player.runActionFPSFrame(null, new int[]{12,12,13}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
					
					@Override
					public void beforeChangeFrame(int nextFrameId) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterChangeFrame(int periousFrameId) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void actionFinish() {
						// TODO Auto-generated method stub
						isMoveing = false;
					}
				});
			}
		}
		player.frameTrig();
//		MovementAction playerMoveAciton = MAction.moveTo(100, CommonUtil.screenHeight, 1000);
//		player.runMovementAction(fireballDownAciton);
	}

	private void showGameOverDialog(){
		final Layer bgLayer = new Layer(BitmapUtil.gameover, BitmapUtil.gameover.getWidth(), BitmapUtil.gameover.getHeight(), false);
		bgLayer.setPosition(0, bgLayer.getHeight());
//		final Sprite restartButton = new Sprite(BitmapUtil.restartBtn01, 350, 200, false);
		final ButtonLayer restartButton = new ButtonLayer(0, 0, false);
		restartButton.setBitmapAndAutoChangeWH(BitmapUtil.restartBtn01);
		restartButton.setButtonBitmap(BitmapUtil.restartBtn01, BitmapUtil.restartBtn02, BitmapUtil.restartBtn01);
		restartButton.setPosition(CommonUtil.screenWidth/2.0f - restartButton.getWidth()/2.0f, CommonUtil.screenHeight/4.0f*3);
		restartButton.setOnClickListener(new ButtonLayer.OnClickListener() {
			
			@Override
			public void onClick(ButtonLayer buttonLayer) {
				// TODO Auto-generated method stub
				((GameActivity)context).sceneManager.previousWithExistedScenes();
			}
		});
		final LabelLayer labelLayer = new LabelLayer("hello", 0, 0, false);
		labelLayer.setTextSize(100);
		labelLayer.setPosition(500, 500);
		final DialogScene dialogScene = new DialogScene(context, "c");
		dialogScene.setDialogSceneDraw(new DialogScene.DialogSceneDrawListener() {
			
			@Override
			public void draw(Canvas canvas) {
				canvas.drawColor(Color.TRANSPARENT,Mode.CLEAR);
				Paint paint = new Paint();
				paint.setColor(Color.RED);
				canvas.drawRect(new Rect(100, 100, 300, 300), paint);
				paint.setColor(Color.BLACK);
				paint.setAlpha(150);
				canvas.drawRect(new RectF(0, 0, CommonUtil.screenWidth, CommonUtil.screenHeight ), paint);
				
				bgLayer.drawSelf(canvas, null);
				
				restartButton.drawSelf(canvas, null);
				
				labelLayer.drawSelf(canvas, null);
			}
		});
		dialogScene.setDialogSceneTouchListener(new DialogScene.DialogSceneTouchListener() {
			
			@Override
			public void onTouchEvent(MotionEvent event) {
				// TODO Auto-generated method stub
				float x = event.getX();
				float y = event.getY();
				
				LayerManager.getInstance().onTouchSceneLayers(event, dialogScene.getLayerLevel());
			}
		});
		dialogScene.setIsNeedToStopTheActiveScene(false);
		((GameActivity)context).sceneManager.addScene(dialogScene);
		((GameActivity)context).sceneManager.startLastScene();
		
		dialogScene.addAutoDraw(restartButton);
//		dialogScene.start();
	}
	
	private void moveBG(){
//		for(){
//			
//		}
	}
	
	private void jump(){
		
	}
	
	private void skill(){
		
	}
	
	public void drawEnemis(Canvas canvas){
//		enemyManager.drawEnemies(canvas);
	}
	
	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
//		sprite.drawSelf(canvas, null);
//		LayerManager.drawLayers(canvas, null);
		LayerManager.getInstance().drawSceneLayers(canvas, null, sceneLayerLevel);
		
		Paint paint = new Paint();
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		canvas.drawText(gameTime+"", 100, 100, paint);
		player.drawSelf(canvas, null);
		
		for(Sheep sheep : sheeps){
			sheep.drawSelf(canvas, null);
		}
	}

	int count =0;
	float x = 0;
	float y = 0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			x = event.getX();
			y = event.getY();
			
			for(int i = 0; i < sheeps.size(); i++){
				Sheep sheep = sheeps.get(i);
				DetectArea detectArea = SpriteDetectAreaHelper.createDetectAreaPoint(new PointF(x, y));
				IDetectAreaRequest request = new DetectAreaRequest(detectArea);
				request.setTag("request");
				if(sheep.getSpriteDetectAreaHandler().detectByDetectAreaRequest(request)){
//					this.stop();
				}	
			}
		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
			float dx = event.getX() - x;
			float dy = event.getY() - y;
			
			x = event.getX();
			y = event.getY();
			
//			enemyManager.moveEnemies((int)dx, (int)dy);
			crosshair.move(dx, dy);
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public void beforeGameStart() {
		// TODO Auto-generated method stub
		fireballTimeUtil = new GameTimeUtil(1000);
		gameTimeUtil = new GameTimeUtil(1000);
	}

	@Override
	public void arrangeView(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivityContentView(Activity activity) {
		// TODO Auto-generated method stub
		activity.setContentView(gameview);
	}

	@Override
	public void afterGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

}
