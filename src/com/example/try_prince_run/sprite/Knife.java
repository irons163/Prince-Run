package com.example.try_prince_run.sprite;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.try_gameengine.avg.GraphicsUtils;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaRect;
import com.example.try_gameengine.utils.DetectAreaSpriteRect;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;

public class Knife extends Tool{
	Bitmap jumpTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_01.png");
	
	public Knife() {
		// TODO Auto-generated constructor stub
		setToolType(ToolType.Knife);
	}
	
	public void attack(float x, float y){
		Sprite effect = new Sprite();
		effect.setBitmap(getBitmap());
		
		effect.removeFromAuto();
	}
	
	public void checkAttacked(){
		
	}
	
	class KnifeAttackSprite extends Sprite{
		Bitmap knifeAttackTexture = GraphicsUtils.loadImage("assets/prince_run/animation/Hero01/Jump/Hero01_Jump_01.png");
		
		public KnifeAttackSprite() {
			// TODO Auto-generated constructor stub
			setBitmapAndAutoChangeWH(knifeAttackTexture);
			
			setDectecAreas();
		}
		
		private void setDectecAreas(){
//			DetectAreaRect userRectDetectArea = new DetectAreaRect(userRectF);
//			DetectAreaRect rectDetectArea = new DetectAreaRect(rectF);
//			circleDetectArea = new DetectAreaRound(circleCenter, circleRadius);
//			pointDetectArea = new DetectAreaPoint(pointF);
			
			SpriteDetectAreaHandler spriteDetectAreaHandler = new SpriteDetectAreaHandler();
			DetectArea a = new DetectAreaSpriteRect(this);
		
			setSpriteDetectAreaHandler(spriteDetectAreaHandler);
			spriteDetectAreaHandler.addSuccessorDetectArea(a);
		}	
		
		private void checkDetectAreasCollision(){
//			if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, rectDetectArea)){
//				collisionMsgLayer.setText("Collision RECT");
//			}else if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, circleDetectArea)){
//				collisionMsgLayer.setText("Collision CIRCLE");
//			}else if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, pointDetectArea)){
//				collisionMsgLayer.setText("Collision POINT");
//			}else{
//				collisionMsgLayer.setText("");
//			}
		}
	}
}
