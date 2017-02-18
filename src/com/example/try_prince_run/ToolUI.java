package com.example.try_prince_run;

import android.view.MotionEvent;

import com.example.try_gameengine.framework.ILayer;
import com.example.try_gameengine.framework.Sprite;

public class ToolUI extends Sprite{
	private ToolUICallback toolUICallback = new ToolUICallback() {
		
		@Override
		public void clickToolUI(ClickToolType Knife) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public interface ToolUICallback{
		public void clickToolUI(ClickToolType Knife);
	}
	
	public enum ClickToolType{
		Knife
	}
	
	public ToolUI(){
		initUI();
	}
	
	private void initUI(){
		Sprite knifeIcon = new Sprite();
		knifeIcon.setBitmap(getBitmap());
		knifeIcon.setPosition(0, 0);
		knifeIcon.setOnLayerClickListener(new OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				toolUICallback.clickToolUI(ClickToolType.Knife);
			}
		});
		addChild(knifeIcon);
		
		Sprite sword = new Sprite();
		sword.setBitmap(getBitmap());
		sword.setPosition(knifeIcon.getWidth(), 0);
		sword.setOnLayerClickListener(new OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				toolUICallback.clickToolUI(ClickToolType.Knife);
			}
		});
		addChild(sword);
		
		Sprite stick = new Sprite();
		stick.setBitmap(getBitmap());
		stick.setPosition(knifeIcon.getWidth(), 0);
		stick.setOnLayerClickListener(new OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				toolUICallback.clickToolUI(ClickToolType.Knife);
			}
		});
		addChild(stick);
		
		Sprite bow = new Sprite();
		bow.setBitmap(getBitmap());
		bow.setPosition(knifeIcon.getWidth(), 0);
		bow.setOnLayerClickListener(new OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				toolUICallback.clickToolUI(ClickToolType.Knife);
			}
		});
		addChild(bow);
	}
	
	public void setToolUICallback(ToolUICallback toolUICallback){
		this.toolUICallback = toolUICallback;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
}
