package com.example.try_prince_run.sprite;

import com.example.try_gameengine.framework.Sprite;

public class Tool extends Sprite{
	public enum ToolType{
		Knife
	}
	
	private ToolType toolType;

	public ToolType getToolType() {
		return toolType;
	}

	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}
	
	
}
