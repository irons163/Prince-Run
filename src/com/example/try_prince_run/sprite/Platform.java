package com.example.try_prince_run.sprite;

import java.util.List;
import java.util.Random;

import com.example.try_gameengine.framework.Sprite;

public class Platform extends Sprite{

	public Platform(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}

	public float width;
	public float height;
	public boolean isDown = false;
	public boolean isShock = false;
	
	public void onCreate(List<Sprite> arrSprite){
		for(Sprite platform : arrSprite){
			platform.setX(this.width);
			this.addChild(platform);
			this.width += platform.getWidth();
		}
		
		//?�到?��?三�??��?平台会�???
        if (arrSprite.size() <= 3) {
            isDown = true;
        }else {
        	Random r = new Random();
            //?�机?�动
            int random = r.nextInt(10) % 10;
            if (random > 8) {
                isShock = true;
            }
        }
         this.height = 10.0f;

//         self.physicsBody = SKPhysicsBody(rectangleOfSize: CGSizeMake(self.width, self.height),center:CGPointMake(self.width/2, 0))
//        	        self.physicsBody?.categoryBitMask = BitMaskType.platform
//        	        self.physicsBody?.dynamic = false
//        	        self.physicsBody?.allowsRotation = false
//        	        self.physicsBody?.restitution = 0
//        	        self.zPosition = 20
	}
}
