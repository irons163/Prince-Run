package com.example.try_prince_run.sprite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.stage.StageManager;
import com.example.try_prince_run.R;
import com.example.try_prince_run.utils.BitmapUtil;
import com.example.try_prince_run.utils.CommonUtil;

public class Background extends Layer{
	//近�??��???
	List<Sprite> arrBG = new ArrayList<Sprite>();
    //远�??��???
	List<Sprite> arrFar = new ArrayList<Sprite>();
    
//    public Background {
//        super.init()
    public Background(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		
//        Bitmap farTexture = SKTexture(imageNamed: "background_f1")
		Context context = StageManager.getCurrentStage();
		Bitmap farTexture = BitmapUtil.bg1;
    	
		Sprite farBg0 = new Sprite(0, 0, false);
//		farBg0.setBitmapAndAutoChangeWH(farTexture);
		farBg0.setBitmap(farTexture);
		farBg0.setWidth((int) (CommonUtil.screenWidth*1.1f));
		farBg0.setHeight(CommonUtil.getNewHeight(farTexture.getWidth(), farTexture.getHeight(), CommonUtil.screenWidth*1.1f));
//        farBg0.position.y = 150
//        farBg0.zPosition = 9
//        farBg0.anchorPoint = CGPointMake(0, 0)
        farBg0.setY(CommonUtil.screenHeight - farBg0.getHeight() + CommonUtil.getValueInGGEngein(450));

//        var farBg1 = SKSpriteNode(texture: farTexture)
//        farBg1.position.y = 150
//        farBg1.zPosition = 9
//        farBg1.anchorPoint = CGPointMake(0, 0)
//        farBg1.position.x = farBg1.frame.width
        Sprite farBg1 = new Sprite(0, 0, false);
//        farBg1.setBitmapAndAutoChangeWH(farTexture);
        farBg1.setBitmap(farTexture);
        farBg1.setWidth((int) (CommonUtil.screenWidth*1.1f));
        farBg1.setHeight(CommonUtil.getNewHeight(farTexture.getWidth(), farTexture.getHeight(), CommonUtil.screenWidth*1.1f));
        farBg1.setY(CommonUtil.screenHeight - farBg1.getHeight() + CommonUtil.getValueInGGEngein(450));
        farBg1.setX(farBg1.getFrame().width());
        
//        var farBg2 = SKSpriteNode(texture: farTexture)
//        farBg2.position.y = 150
//        farBg2.zPosition = 9
//        farBg2.anchorPoint = CGPointMake(0, 0)
//        farBg2.position.x = farBg2.frame.width*2
        Sprite farBg2 = new Sprite(0, 0, false);
//        farBg2.setBitmapAndAutoChangeWH(farTexture);
        farBg2.setBitmap(farTexture);
        farBg2.setWidth((int) (CommonUtil.screenWidth*1.1f));
        farBg2.setHeight(CommonUtil.getNewHeight(farTexture.getWidth(), farTexture.getHeight(), CommonUtil.screenWidth*1.1f));
        farBg2.setY(CommonUtil.screenHeight - farBg2.getHeight() + CommonUtil.getValueInGGEngein(450));
        farBg2.setX(farBg2.getFrame().width()*2);
        
        this.addChild(farBg0);
        this.addChild(farBg1);
        this.addChild(farBg2);
        arrFar.add(farBg0);
         arrFar.add(farBg1);
         arrFar.add(farBg2);
        
         Bitmap texture = BitmapUtil.bg2;
//         Bitmap texture = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.background_f0), CommonUtil.screenWidth, CommonUtil.screenHeight);
//        Sprite bg0 = new Sprite(texture, CommonUtil.screenWidth, CommonUtil.screenHeight, false);
         Sprite bg0 = new Sprite(0, 0, false);
//         bg0.setBitmapAndAutoChangeWH(texture);
         bg0.setBitmap(texture);
         bg0.setWidth((int) (CommonUtil.screenWidth*1.1f));
         bg0.setHeight(CommonUtil.getNewHeight(texture.getWidth(), texture.getHeight(), CommonUtil.screenWidth*1.1f));
        bg0.setY(CommonUtil.screenHeight - bg0.getHeight()+ CommonUtil.getValueInGGEngein(600));
        
//        var bg1 = SKSpriteNode(texture: texture)
//        bg1.anchorPoint = CGPointMake(0, 0)
//        bg1.position.y = 70
//        bg1.zPosition = 10
//        bg1.position.x = bg0.frame.size.width
//        self.addChild(bg0)
//        self.addChild(bg1)
//        arrBG.append(bg0)
//        arrBG.append(bg1)
//        Sprite bg1 = new Sprite(texture, CommonUtil.screenWidth, CommonUtil.screenHeight, false);
        Sprite bg1 = new Sprite(0, 0, false);
//        bg1.setBitmapAndAutoChangeWH(texture);
        bg1.setBitmap(texture);
        bg1.setWidth((int) (CommonUtil.screenWidth*1.1f));
        bg1.setHeight(CommonUtil.getNewHeight(texture.getWidth(), texture.getHeight(), CommonUtil.screenWidth*1.1f));
        bg1.setY(CommonUtil.screenHeight - bg1.getHeight() + CommonUtil.getValueInGGEngein(600));
        bg1.setX(bg1.getFrame().width());
        
        Sprite bg2 = new Sprite(0, 0, false);
        bg2.setBitmap(texture);
        bg2.setWidth((int) (CommonUtil.screenWidth*1.1f));
        bg2.setHeight(CommonUtil.getNewHeight(texture.getWidth(), texture.getHeight(), CommonUtil.screenWidth*1.1f));
        bg2.setY(CommonUtil.screenHeight - bg2.getHeight()+ CommonUtil.getValueInGGEngein(600));
        bg2.setX(bg2.getFrame().width()*2);
        
        this.addChild(bg0);
        this.addChild(bg1);
        this.addChild(bg2);
        arrBG.add(bg0);
        arrBG.add(bg1);
        arrBG.add(bg2);
    }

    public void move(float speed){
        for(Sprite bg : arrBG){
        	bg.setX(bg.getX() - speed);
        }
        if(arrBG.get(0).getX() + arrBG.get(0).getFrame().width() < speed){
        	arrBG.get(0).setX(0);
        	arrBG.get(1).setX(arrBG.get(0).getFrame().width());
        	arrBG.get(2).setX(arrBG.get(0).getFrame().width()*2);
        }
        
        for(Sprite far : arrFar){
        	far.setX(far.getX() - speed/4);
        }
        if(arrFar.get(0).getX() + arrFar.get(0).getFrame().width() < speed/4){
        	arrFar.get(0).setX(0);
        	arrFar.get(1).setX(arrFar.get(0).getFrame().width());
        	arrFar.get(2).setX(arrFar.get(0).getFrame().width()*2);
        }
    }
}
