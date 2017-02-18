package com.example.try_prince_run.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_prince_run.utils.BitmapUtil;

public class Sheep extends Sprite{

	public Sheep(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		setBitmapAndAutoChangeWH(BitmapUtil.sheep);
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
	}
}
