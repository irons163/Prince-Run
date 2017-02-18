package com.example.try_prince_run.sprite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.Log;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MAction2;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS.FrameTimesType;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.avg.GraphicsUtils;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.stage.StageManager;
import com.example.try_prince_run.R;

public class Panda extends Sprite{
	public enum Status{
		run, jump, jump2,roll
	}
	
	public Panda(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		init();
	}

    //定�?跑�?跳�?滚动等动作动??
//    Bitmap runAtlas = SKTextureAtlas(named: "run.atlas")
    List<Bitmap> runFrames = new ArrayList<Bitmap>();
//    List<Bitmap> jumpAtlas = SKTextureAtlas(named: "jump.atlas")
    		List<Bitmap> jumpFrames = new ArrayList<Bitmap>();
//    				List<Bitmap> rollAtlas = SKTextureAtlas(named: "roll.atlas")
    		List<Bitmap> rollFrames = new ArrayList<Bitmap>();

    List<Bitmap> jumpEffectFrames = new ArrayList<Bitmap>();
    List<Bitmap> downFrames = new ArrayList<Bitmap>();
    Sprite jumpEffect;
    
    List<Bitmap> knifeEffectFrames = new ArrayList<Bitmap>();
    List<Bitmap> spearEffectFrames = new ArrayList<Bitmap>();
    
    public Status status = Status.run;
    public float jumpStart = 1000.0f;
    public float jumpEnd = 0.0f;
    public boolean isDisableAutoForward;
    
    public void init(){
    	Context context = StageManager.getCurrentStage();
    	
    	Bitmap runTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Run/Hero01_Run_01.png");
    	runFrames.add(runTexture);
    	runTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Run/Hero01_Run_02.png");
    	runFrames.add(runTexture);
    	runTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Run/Hero01_Run_03.png");
    	runFrames.add(runTexture);
    	runTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Run/Hero01_Run_04.png");
    	runFrames.add(runTexture);
    
    	Bitmap jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_01.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_02.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_03.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_04.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_05.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_06.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_07.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_13.png");
    	jumpFrames.add(jumpTexture);
    	jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_14.png");
    	jumpFrames.add(jumpTexture);
    	
    	Bitmap rollTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Roll/Hero01_Roll_01.png");
    	rollFrames.add(rollTexture);
    	rollTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Roll/Hero01_Roll_02.png");
    	rollFrames.add(rollTexture);
    	rollTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Roll/Hero01_Roll_03.png");
    	rollFrames.add(rollTexture);
    	rollTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Roll/Hero01_Roll_04.png");
    	rollFrames.add(rollTexture);
    	rollTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Roll/Hero01_Roll_05.png");
    	rollFrames.add(rollTexture);
    	rollTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Roll/Hero01_Roll_06.png");
    	rollFrames.add(rollTexture);
    	rollTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Roll/Hero01_Roll_07.png");
    	rollFrames.add(rollTexture);
    
    	Bitmap jumpEffectTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/jump_effect_01.png");
    	jumpEffectFrames.add(jumpEffectTexture);
    	jumpEffectTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/jump_effect_02.png");
    	jumpEffectFrames.add(jumpEffectTexture);
    	jumpEffectTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/jump_effect_03.png");
    	jumpEffectFrames.add(jumpEffectTexture);
    	jumpEffectTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/jump_effect_04.png");
    	jumpEffectFrames.add(jumpEffectTexture);
    	//DOWN
    	Bitmap downTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_13.png");
    	downFrames.add(downTexture);
    	downTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_14.png");
    	downFrames.add(downTexture);
    	
//        Bitmap texture = runAtlas.textureNamed("panda_run_01")
    	 Bitmap texture = runFrames.get(0);
        PointF size = new PointF(texture.getWidth(), texture.getHeight());
        setCollisionRectFEnable(true);
        setBitmapAndAutoChangeWH(texture);
        setCollisionRectF(getCollisionRectF().left+5, getCollisionRectF().top, getCollisionRectF().right-5, getCollisionRectF().bottom);
        setCollisionOffsetX(5);
        setCollisionRectFWidth(getCollisionRectF().width());
        
        jumpEffect = new Sprite(-80,0,false);
        jumpEffect.setBitmapAndAutoChangeWH(jumpEffectFrames.get(0));
        jumpEffect.setHidden(true);
        this.addChild(jumpEffect);

        run();      
    }

    public void run(){
    	if(this.currentAction!=null && this.getActionName()!=null && this.getActionName().equals("run"))
    		return;

    	removeAllMovementActions();
    	
        this.status = Status.run;

        	this.addActionFPS("run", (Bitmap[]) runFrames.toArray(new Bitmap[runFrames.size()]), new int[]{2,2,2,2,2,2,2,2}, true);
            this.setAction("run");
    
        Log.e("Panda", "run.");
    }
    public void jump(final PlatformFactory platformFactory){
//        this.removeAllActions();
    	
        if (status != Status.jump2) {;
        	removeAllMovementActions();
            //Adds an action to the list of actions executed by the node.
            //Creates an action that animates changes to a texture.
            
//            self.runAction(SKAction.animateWithTextures(jumpFrames, timePerFrame: 0.05),withKey:"jump")
        	this.addActionFPS("jump", (Bitmap[]) jumpFrames.toArray(new Bitmap[jumpFrames.size()]), new int[]{2,2,2,2,2,2,2,2,2}, false);
            this.setAction("jump");

            MovementActionItemBaseReugularFPS jump = new MovementActionItemBaseReugularFPS(new MovementActionInfo(40, 2, 0, -60, "jump", true));
            jump.setMovementActionController(new MovementAtionController());
            jump.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
				
				@Override
				public void onTick(float dx, float dy) {
					// TODO Auto-generated method stub
					boolean isChanged = false;
					for(Platform platform : platformFactory.getPlatforms()){
						if(Panda.this.getFrame().left + Panda.this.getWidth() >= platform.getFrame().left && Panda.this.getFrame().left <= platform.getFrame().left + platform.getWidth()){
							if(dy>0){
								if(Panda.this.getFrame().top + Panda.this.getHeight() + dy > platform.getFrame().top && Panda.this.getFrame().top  + Panda.this.getHeight() <= platform.getFrame().top + platform.getHeight()){
									dy = platform.getFrame().top - (Panda.this.getFrame().top + Panda.this.getHeight()) + 1;
									isChanged = true;
								}
							}else if(dy<0){
								if(Panda.this.getFrame().top + dy <= platform.getFrame().top + platform.getHeight() && Panda.this.getFrame().top >= platform.getFrame().top){
									dy = platform.getFrame().top + platform.getHeight() - Panda.this.getFrame().top;
									isChanged = true;
								}

							}
						}	
						
						if(isChanged)
							break;
					}
				
					Panda.this.move(dx, dy);
					Log.e("Panda", "jump " + dy);
				}
			});
            jump.setName("jump");
            jump.setFrameTimesType(FrameTimesType.FrameTimesIntervalAfterAction);
            this.runMovementAction(jump);
            Log.e("Panda", "jump.");
            
            if (status == Status.jump) {
            	this.addActionFPS("roll", (Bitmap[]) rollFrames.toArray(new Bitmap[rollFrames.size()]), new int[]{2,2,2,2,2,2,2,2}, false);
                this.setAction("roll");
                status = Status.jump2;
                this.jumpStart = this.getY();
            }else {
                showJumpEffect();
                status = Status.jump;
            }
        }
    }
    public void roll(){

    	removeAllMovementActions();
    	
    	this.status = Status.roll;

    	MovementAction roll = MAction2.sequence(new MovementAction[]{MAction.animateAction((Bitmap[]) rollFrames.toArray(new Bitmap[rollFrames.size()]), 0.05f), MAction.runBlockNoDelay(new MAction.MActionBlock() {
			
			@Override
			public void runBlock() {
				// TODO Auto-generated method stub
				Log.e("Panda", "run after roll.");
				Panda.this.run();
				
			}
		})});
    	roll.setName("roll");
    	this.runMovementAction(roll);
    	Log.e("Panda", "roll.");
    }
    
    void showJumpEffect(){
        jumpEffect.setHidden(false);
        
        jumpEffect.runMovementAction(MAction.sequence(new MovementAction[]{MAction.animateAction((Bitmap[]) jumpEffectFrames.toArray(new Bitmap[jumpEffectFrames.size()]), 0.05f), MAction.runBlockNoDelay(new MAction.MActionBlock() {
			
			@Override
			public void runBlock() {
				// TODO Auto-generated method stub
				jumpEffect.setHidden(true);
			}
		})}));
    }
    
    public void down(){
    	if(this.action!=null && (this.action.getName().equals("jump") || this.action.getName().equals("down")) && !this.action.isFinish())
    		return;

    	this.addActionFPS("down", (Bitmap[]) downFrames.toArray(new Bitmap[downFrames.size()]), new int[]{2,2,2,2,2}, false);
        this.setAction("down");
        MovementAction down = new MovementActionItemBaseReugularFPS(new MovementActionInfo(40, 5, 0, 0, "down", true));
        down.setMovementActionController(new MovementAtionController());
        down.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				Panda.this.move(dx, dy);
			}
		});
        down.setName("down");
        this.runMovementAction(down);
        Log.e("Panda", "down.");
    }
    
    public void attack(){
    	
    }
    
    public void reset(){
    	if(currentAction!=null)
    		currentAction = null;
    	removeAllMovementActions();
    }
}
