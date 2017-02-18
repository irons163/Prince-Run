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

import com.example.try_gameengine.action.EaseInDecorator;
import com.example.try_gameengine.action.EaseOutDecorator;
import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionOwnerGroup;
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
import com.example.try_gameengine.framework.Sprite.MoveRageType;
import com.example.try_gameengine.remotecontroller.RemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController.CommandType;
import com.example.try_gameengine.scene.DialogScene;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.scene.SceneManager;
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

public class ComicScene extends EasyScene{
	private int move = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	boolean isPressLeftMoveBtn, isPressRightMoveBtn;
	private int gameTime;
	
	private GameTimeUtil fireballTimeUtil;
	private GameTimeUtil gameTimeUtil;

	private static final int INVALID_POINTER_ID = -1;
	
//	private List<Sheep> sheeps = new ArrayList<Sheep>();
	
//	private EnemyManager enemyManager;
//	private Sprite crosshair;
	
	private float moveXOfSheep = 5;
	private float moveYOfSheep = 5;
	
	public ComicScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		
//		Sprite bg = new Sprite(BitmapUtil.bg, CommonUtil.screenWidth, CommonUtil.screenHeight, false);
//		addAutoDraw(bg);
		Sprite leftTop = new Sprite(BitmapUtil.Comic1.getBitmap().getWidth(), BitmapUtil.Comic1.getBitmap().getHeight(), false);
		leftTop.setLightImage(BitmapUtil.Comic1);
		addAutoDraw(leftTop);
		leftTop.setPosition(0, 0);
		final Sprite leftBottom = new Sprite(BitmapUtil.Comic2.getBitmap().getWidth(), BitmapUtil.Comic2.getBitmap().getHeight(), false);
		leftBottom.setLightImage(BitmapUtil.Comic2);
		addAutoDraw(leftBottom);
		leftBottom.setPosition(0, CommonUtil.screenHeight - leftBottom.getHeight());
		final Sprite rightTop = new Sprite(BitmapUtil.Comic2_1.getBitmap().getWidth(), BitmapUtil.Comic2_1.getBitmap().getHeight(), false);
		rightTop.setLightImage(BitmapUtil.Comic2_1);
		addAutoDraw(rightTop);
		rightTop.setPosition(CommonUtil.screenWidth - BitmapUtil.Comic2_1.getBitmap().getWidth(), 0);
		Sprite rightBottom = new Sprite(BitmapUtil.Comic2_2.getBitmap().getWidth(), BitmapUtil.Comic2_2.getBitmap().getHeight(), false);
		rightBottom.setLightImage(BitmapUtil.Comic2_2);
		addAutoDraw(rightBottom);
		rightBottom.setPosition(CommonUtil.screenWidth - BitmapUtil.Comic2_2.getBitmap().getWidth(), CommonUtil.screenHeight - rightBottom.getHeight());
		
		final MovementActionOwnerGroup actionOwnerGroup = new MovementActionOwnerGroup();
		actionOwnerGroup.addMovementAction(leftTop, MAction.alphaAction2(3000, 0), null, new MovementActionOwnerGroup.Block() {
			
			@Override
			public void runBlock() {
				// TODO Auto-generated method stub
			}
		});
		
		actionOwnerGroup.addMovementAction(leftBottom, new EaseOutDecorator(MAction.alphaAction2(3000, 0), 0.3f) , null, new MovementActionOwnerGroup.Block() {
			
			@Override
			public void runBlock() {
				// TODO Auto-generated method stub
			}
		});
		
		actionOwnerGroup.addMovementAction(rightTop, MAction.alphaAction2(3000, 50), null, new MovementActionOwnerGroup.Block() {
			
			@Override
			public void runBlock() {
				// TODO Auto-generated method stub
			}
		});
		
		actionOwnerGroup.addMovementAction(rightBottom, MAction.sequence(new MovementAction[]{MAction.alphaAction2(3000, 0), MAction.waitAction(120), MAction.runBlockNoDelay(new MAction.MActionBlock() {
			
			@Override
			public void runBlock() {
				// TODO Auto-generated method stub
				((GameActivity)context).runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						((GameActivity)context).sceneManager.nextWithExistedScenes();
					}
				});	
			}})})
			,
			null, new MovementActionOwnerGroup.Block() {
			
			@Override
			public void runBlock() {
				// TODO Auto-generated method stub
			}
		});

		actionOwnerGroup.setOnGroupListener(new MovementActionOwnerGroup.OnGroupListener() {
			
			@Override
			public void onStart(int startIndex) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLastStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLastFinish() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFirstStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFirstFinish() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinish(int finishIndex) {
				// TODO Auto-generated method stub
				
			}
		});
		
		actionOwnerGroup.run();
		
//		leftTop.runMovementAction(MAction.alphaAction2(3000, 0));
//		leftBottom.runMovementAction(MAction.alphaAction2(3000, 0));
//		rightTop.runMovementAction(MAction.alphaAction2(3000, 0));
//		rightBottom.runMovementAction(MAction.sequence(new MovementAction[]{MAction.alphaAction2(3000, 0), MAction.waitAction(120), MAction.runBlockNoDelay(new MAction.MActionBlock() {
//			
//			@Override
//			public void runBlock() {
//				// TODO Auto-generated method stub
//				((GameActivity)context).runOnUiThread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						((GameActivity)context).sceneManager.next();
//					}
//				});	
//			}
//		})}));
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
		
		tickTime();
		
		LayerManager.getInstance().processSceneLayers(sceneLayerLevel);
	}
	
	private void tickTime(){
		if(gameTimeUtil.isArriveExecuteTime()){
			gameTime++;
		}
	}
		
	boolean isMoveing = false;
	
	public void drawEnemis(Canvas canvas){
		
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
//		player.drawSelf(canvas, null);
		
//		for(Sheep sheep : sheeps){
//			sheep.drawSelf(canvas, null);
//		}
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

		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
			float dx = event.getX() - x;
			float dy = event.getY() - y;
			
			x = event.getX();
			y = event.getY();

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
		
//		getRemoteController().getRightKey().setHidden(true);
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
