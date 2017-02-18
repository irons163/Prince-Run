package com.example.try_prince_run.utils;

import com.example.try_gameengine.framework.Config;

import android.app.Activity;

public class CommonUtil {
	public static int screenWidth;
	public static int screenHeight;
	public static int statusBarHeight;
	
	public static int getStatusBarHeight(Activity activity) { 
	      int result = 0;
	      int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
	      if (resourceId > 0) {
	          result = activity.getResources().getDimensionPixelSize(resourceId);
	      } 
	      return result;
	}
	
	public static int getNewHeight(float oldWidth, float oldHeight, float newWidth){
		int newHeight = 0;
		float scale = newWidth/oldWidth;
		newHeight = (int) (oldHeight*scale);
		return newHeight;
	}
	
	public static float getValueInGGEngein(float value){
		float newValue = 0;
		float scale = Config.defaultScreenWidth/CommonUtil.screenWidth;
		newValue = (float) (value*scale);
		return newValue;
	}
	
//	public static int getNewHeight(int oldWidth, int oldHeight){
//		
//	}
}
