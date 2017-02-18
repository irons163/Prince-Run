package com.example.try_prince_run.sprite;

import android.graphics.Bitmap;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MAction2;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.listener.IActionListener;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaSpriteRect;
import com.example.try_gameengine.utils.IDetectAreaRequest;
import com.example.try_gameengine.utils.ISpriteDetectAreaListener;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;
import com.example.try_prince_run.utils.BitmapUtil;

public class Monster extends Sprite{
	private Bitmap[] attack = new Bitmap[]{BitmapUtil.Monster1_Attack1, BitmapUtil.Monster1_Attack2, BitmapUtil.Monster1_Attack3, BitmapUtil.Monster1_Attack4};
	private Bitmap[] hurt = new Bitmap[]{BitmapUtil.Monster1_Hurt};
	private Bitmap[] run = new Bitmap[]{BitmapUtil.Monster1_Run1, BitmapUtil.Monster1_Run2, BitmapUtil.Monster1_Run3, BitmapUtil.Monster1_Run4};
	private Bitmap[] catchHero = new Bitmap[]{BitmapUtil.Monster1_Catch1, BitmapUtil.Monster1_Catch2, BitmapUtil.Monster1_Catch3, BitmapUtil.Monster1_Catch4};
	
	private MovementAction runAction, attackAnimateAction, catchHeroAnimateAction, hurtAnimateAction; 
	
	public Monster() {
		initMovementAction();
		setDectecAreas();
	}
	
	private void initMovementAction(){
		MovementAction runAnimateAction = MAction.animateAction(run, 0.1f);
		MovementAction runMoveAction = MAction.moveByX(3, 10);
		runAction = MAction.group(new MovementAction[]{runAnimateAction, runMoveAction});
		runAction.setIsLoop(true);
		attackAnimateAction = MAction.animateAction(attack, 0.1f);
		catchHeroAnimateAction = MAction.animateAction(catchHero, 0.1f);
		hurtAnimateAction = MAction.animateAction(hurt, 0.1f);
	}
	
	public void run(){
		runMovementAction(runAction);
	}
	
	public void attack(){
		runMovementAction(attackAnimateAction);
	}
	
	public void catchHero(){
		runMovementAction(runAction);
	}
	
	public void hurt(){
		hurtAnimateAction.setActionListener(new IActionListener() {
			
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
				Monster.this.run();
			}
			
			@Override
			public void actionCycleFinish() {
				// TODO Auto-generated method stub
				
			}
		});
		runMovementAction(hurtAnimateAction);
	}
	
	private void setDectecAreas(){
//		DetectAreaRect userRectDetectArea = new DetectAreaRect(userRectF);
//		DetectAreaRect rectDetectArea = new DetectAreaRect(rectF);
//		circleDetectArea = new DetectAreaRound(circleCenter, circleRadius);
//		pointDetectArea = new DetectAreaPoint(pointF);
		
		SpriteDetectAreaHandler spriteDetectAreaHandler = new SpriteDetectAreaHandler();
		DetectArea a = new DetectAreaSpriteRect(this);
		a.setSpriteDetectAreaListener(new ISpriteDetectAreaListener() {
			
			@Override
			public boolean stopDoSuccessorDetected(DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea, boolean isDetected) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void didDetected(DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea) {
				// TODO Auto-generated method stub
				hurt();
			}
		});
		
		setSpriteDetectAreaHandler(spriteDetectAreaHandler);
		spriteDetectAreaHandler.addSuccessorDetectArea(a);
	}
}
