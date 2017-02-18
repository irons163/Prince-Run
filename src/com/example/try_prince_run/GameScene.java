package com.example.try_prince_run;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MAction2;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.framework.ALayer.LayerParam;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.GameTimeUtil;
import com.example.try_prince_run.ToolUI.ClickToolType;
import com.example.try_prince_run.ToolUI.ToolUICallback;
import com.example.try_prince_run.sprite.AppleFactory;
import com.example.try_prince_run.sprite.Background;
import com.example.try_prince_run.sprite.Monster;
import com.example.try_prince_run.sprite.Panda;
import com.example.try_prince_run.sprite.Panda.Status;
import com.example.try_prince_run.sprite.Platform;
import com.example.try_prince_run.sprite.PlatformFactory;
import com.example.try_prince_run.sprite.ProtocolMainscreen;
import com.example.try_prince_run.utils.CommonUtil;


public class GameScene extends EasyScene implements ProtocolMainscreen, ToolUICallback{
	private int move = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	boolean isPressLeftMoveBtn, isPressRightMoveBtn;
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;

	private static final int INVALID_POINTER_ID = -1;
	
	private float moveXOfSheep = 5;
	private float moveYOfSheep = 5;
	
	 Panda panda;
	    PlatformFactory platformFactory = new PlatformFactory(0, 0, false);
//	    lazy var sound = SoundManager()
	    Background bg = new Background(0, 0, false);
	    AppleFactory appleFactory;
	    LabelLayer scoreLab = new LabelLayer(0, 0, false); 
	    LabelLayer appLab  = new LabelLayer(0, 0, false); 
	    LabelLayer myLabel  = new LabelLayer(0, 0, false); 
	    int appleNum = 0;
	    
	    float moveSpeed = 15.0f;
	    float maxSpeed = 50.0f;
	    float distance = 0.0f;
	    float lastDis = 0.0f;
	    float theY = 0.0f;
	    boolean isLose = false;
		boolean isReadyToJump = false;
		
		private List<Monster> monsters = new ArrayList<Monster>();
	
	public GameScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub

		
		this.addAutoDraw(bg);
		
        int skyColor = Color.argb(255, 113, 197, 207);
        this.setBackgroundColor(skyColor);
        scoreLab.getPaint().setTextAlign(Align.LEFT);
        scoreLab.setPosition(20, 150);
        scoreLab.setText("run: 0 km");
        this.addAutoDraw(scoreLab);
        
        appLab.getPaint().setTextAlign(Align.LEFT);
        appLab.setPosition(400, 150);
        appLab.setText("eat: apple");
        this.addAutoDraw(appLab);
        
        myLabel.setText("");
        myLabel.setTextSize(100);
        myLabel.setzPosition(100);
        myLabel.setAutoHWByText();
        LayerParam layerParam = new LayerParam();
        layerParam.setPercentageX(0.5f);
        layerParam.setEnabledPercentagePositionX(true);
        myLabel.setLayerParam(new LayerParam());
        myLabel.setPosition(CommonUtil.screenWidth/2, CommonUtil.screenHeight/2);
        myLabel.setAnchorPoint(0.5f, 0);
        this.addAutoDraw(myLabel);
        
//        self.physicsWorld.contactDelegate = self
//        self.physicsWorld.gravity = CGVectorMake(0, -5)
//        self.physicsBody = SKPhysicsBody(edgeLoopFromRect: self.frame)
//        self.physicsBody!.categoryBitMask = BitMaskType.scene
//        self.physicsBody!.dynamic = false
        
        panda = new Panda(210, 100, true);
        
        appleFactory = new AppleFactory(0, 0, CommonUtil.screenWidth, false);
        this.addAutoDraw(appleFactory);
        
//        self.addChild(panda)
        this.addAutoDraw(platformFactory);
        platformFactory.setScreenWdith(CommonUtil.screenWidth);
        platformFactory.setProtocolMainscreen(this);
        platformFactory.createPlatform(3, 0, CommonUtil.screenHeight - 400);
        
//        platformFactory.createPlatformRandom();
        
        
        
//        self.addChild(sound)
//        AudioUtil.playBackgroundMusic();
        
        isEnableRemoteController(false);
        
//        appleFactory.onInit(self.frame.width, y: theY)
//        self.addChild( appleFactory )
			

        
//		Sprite bg = new Sprite(BitmapUtil.bg, CommonUtil.screenWidth, CommonUtil.screenHeight, false);
//		addAutoDraw(bg);
//		Sprite flower = new Sprite(BitmapUtil.flower, BitmapUtil.flower.getWidth(), BitmapUtil.flower.getHeight(), false);
//		addAutoDraw(flower);
//		flower.runMovementAction(MAction.sequence(new MovementAction[]{MAction.repeatForever(MAction.alphaAction(3000, 0)), MAction.waitAction(120), MAction.runBlockNoDelay(new MAction.MActionBlock() {
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
//		
//		flower.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
//			
//			@Override
//			public void onClick(ILayer layer) {
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
//		});
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
		
		if (isLose) {
			//do nothing
        }else{
//        	LayerManager.processLayers();
        	LayerManager.getInstance().processSceneLayers(sceneLayerLevel);
        	
            if (panda.getX() < 200 && !panda.isDisableAutoForward) {
                float x = panda.getX() + 1;
                panda.setX(x);
            }
            panda.isDisableAutoForward = false;
            distance += moveSpeed;
            lastDis -= moveSpeed;
            float tempSpeed = 5+(int)(distance/2000);
            if (tempSpeed > maxSpeed) {
                tempSpeed = maxSpeed;
            }
            if (moveSpeed < tempSpeed) {
                moveSpeed = tempSpeed;
            }
            
            if (lastDis < 0) {
                platformFactory.createPlatformRandom();
            }
            distance += moveSpeed;
            int runKM = (int)((distance/1000*10)/10);
            scoreLab.setText("run: " + runKM + "km");
            appLab.setText("eat: " + appleNum + "apple");
            platformFactory.move(moveSpeed, panda);
            bg.move(moveSpeed/5);
            appleFactory.move(moveSpeed);
            appleFactory.process();
            
            checkCollistion();
            
            boolean isOnGround = false;
            for(Platform platform : platformFactory.getPlatforms()){
            	if(!platform.isHidden() && panda.getY()==platform.getY()-panda.getHeight() && platform.getX() <= panda.getCollisionRectF().left + panda.getCollisionRectF().width() && platform.getX() + platform.getWidth() >= panda.getCollisionRectF().left){
            		isOnGround = true;
            		break;
            	}
            }
            if(!isOnGround)
            	panda.down();
            
            if(isReadyToJump){
            	panda.jump(platformFactory);
            	isReadyToJump = false;
            }
            
            checkGameOver();
        }
		
	}
	
	private void checkCo(){
		
        boolean isDown = false;
        boolean canRun = false;
        
//        panda.setY(platform.getY()-panda.h);
        
                canRun=true;
            

        
	        panda.jumpEnd = panda.getY();
	        if (panda.jumpEnd-panda.jumpStart >= 70) {
	            panda.roll();
	        }else{
	            if (canRun) {
	                panda.run();
	            }
	            
	        }
	        
	      //落地后jumpstart数据要设为当前位置，防止自由落地计算出错
	        panda.jumpStart = panda.getY();

//			//熊猫和苹果碰撞
//			for(Sprite apple : appleFactory.arrApple){
//				if (apple.isEnable() && RectF.intersects(panda.getCollisionRectF(), apple.getFrame())){
////				    AudioUtil.playEat();
//				    this.appleNum++;
//				    apple.setHidden(true);
//				}
//			}
	}
	
	private void checkGameOver(){
	    if (panda.getX() + panda.getWidth() < 0 || panda.getY() > CommonUtil.screenHeight) {
		    System.out.println("game over");
		    myLabel.setText("game over");
//		    AudioUtil.playDead();
		    isLose = true;
//		    AudioUtil.stopBackgroundMusic();
	    }
	}
	
	void checkCollistion(){
		
		for(Platform platform : platformFactory.getPlatforms()){
			
//		if(!panda.getFrame().equals(panda.getCollisionRectF()))
//			Log.e("NOT Equel", panda.getFrame() + ":" + panda.getCollisionRectF());
		
//		Log.e("checkCollistion", panda.getCollisionRectF() + ":" + platform.getFrame());
		if(platform.isEnable() && RectF.intersects(panda.getCollisionRectF(), platform.getFrame())){
//	        if (contact.bodyA.categoryBitMask | contact.bodyB.categoryBitMask) == (BitMaskType.platform | BitMaskType.panda){
//			Log.e("Collistion", panda.getCollisionRectF() + ":" + platform.getFrame());
	        boolean isDown = false;
	        boolean canRun = false;
	        
	        panda.setY(platform.getY()-panda.getHeight());
	        
	            if (platform.isDown) {
	                isDown = true;
	                platform.setEnable(false);
	                platform.setHidden(true);
//	                platform.physicsBody!.dynamic = true
//	                platform.physicsBody!.collisionBitMask = 0
	            }else if (platform.isShock) {
	            	platform.isShock = false;
//	                downAndUp(platform, 50, 0.2f, -100, 1, true);
	            }
	            if (panda.getY() < platform.getY()) {
	                canRun=true;
	            }
	            

	        
		        panda.jumpEnd = panda.getY();
//		        if (panda.jumpEnd-panda.jumpStart <= -70) {
		        if (panda.jumpEnd-panda.jumpStart >= 70) {
		            panda.roll();
//		            AudioUtil.playRoll();
		            
		            if (!isDown) {
		            	downAndUp(panda,50,0.05f,-50,0.1f,false);
		                downAndUp(platform,50,0.05f,-50,0.1f,false);
		                Log.e("Run", "DownUp");
		            }
		            
		        }else{
		            if (canRun) {
		                panda.run();
		            }
		            
		        }
		        
		      //落地后jumpstart数据要设为当前位置，防止自由落地计算出错
		        panda.jumpStart = panda.getY();
		        		break;
			}
		}
		
		//熊猫和苹果碰撞
		for(Sprite apple : appleFactory.arrApple){
			if (apple.isEnable() && RectF.intersects(panda.getCollisionRectF(), apple.getFrame())){
//			    AudioUtil.playEat();
			    this.appleNum++;
			    apple.setHidden(true);
			}
		}

	}

	public void downAndUp(final Sprite sprite,float down, float downTime, float up, float upTime, boolean isRepeat){
	    MovementAction downAct = MAction.moveByY(down, (long)(downTime*1000));
//	    downAct.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
//			
//			@Override
//			public void onTick(float dx, float dy) {
//				// TODO Auto-generated method stub
//				sprite.move(dx, dy);
//			}
//		});
	    //moveByX(CGFloat(0), y: down, duration: downTime)
	    MovementAction upAct = MAction.moveByY(up, (long)(upTime*1000));
//	    upAct.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
//			
//			@Override
//			public void onTick(float dx, float dy) {
//				// TODO Auto-generated method stub
//				sprite.move(dx, dy);
//			}
//		});
	    
	    //MAction use threadPool it would delay during action by action.
	    MovementAction downUpAct = MAction2.sequence(new MovementAction[]{downAct,upAct});
	    downUpAct.setMovementActionController(new MovementAtionController());
	    if (isRepeat) {
	    	sprite.runMovementActionAndAppend(MAction.repeatForever(downUpAct));
	    }else {
	    	sprite.runMovementActionAndAppend(downUpAct);
	    }
	    
	    
	}
	
	private void tickTime(){
		if(gameTimeUtil.isArriveExecuteTime()){
			gameTime++;
		}
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
	
	@Override
	public void doDraw(Canvas canvas) {
		LayerManager.getInstance().drawSceneLayers(canvas, null, sceneLayerLevel);
	}

	int count =0;
	float x = 0;
	float y = 0;
	
	//重新开始游戏
		public void reSet(){
	        isLose = false;
	        panda.setPosition(200, 400);
	        panda.reset();
	        myLabel.setText("");
	        moveSpeed  = 15.0f;
	        distance = 0.0f;
	        lastDis = 0.0f;
	        appleNum = 0;
	        platformFactory.reset();
	        appleFactory.reSet();
	        platformFactory.createPlatform(3, 0, 400);
//	        AudioUtil.playBackgroundMusic();
	    }
		
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			if (isLose) {
	            reSet();
	        }else{
	            if (panda.status != Status.jump2) {
//	            	AudioUtil.playJump();
	            }
	            isReadyToJump = true;
	        }
		}
		LayerManager.getInstance().onTouchLayers(event);
		return true;
	}
	
	@Override
	public void beforeGameStart() {
		// TODO Auto-generated method stub
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

	@Override
	public void onGetData(float dist, float theY) {
		// TODO Auto-generated method stub
		this.lastDis = dist;
		this.theY = theY;
        appleFactory.theY = theY;
	}

	@Override
	public void clickToolUI(ClickToolType Knife) {
		// TODO Auto-generated method stub
		switch (Knife) {
		case Knife:
//			panda.setAtack();
			break;

		default:
			break;
		}
	}

}
