package com.example.try_prince_run.sprite;

import java.util.List;

public class ToolManager {
	private ToolFactory toolFactory;
	private ToolUsingCollections toolUsingCollections = new ToolUsingCollections();
	
	public List<Tool> getToolUsings(){
		return toolUsingCollections.getToolUsings();
	}
	
	public void checkToolUsingCollisionWithMonsters(List<Monster> monsters){
		for(Tool tool : toolUsingCollections.getToolUsings()){
//			...
		}
	}
	
	public void createToolFactory(float y, float sceneWidth){
		toolFactory = new ToolFactory(y, sceneWidth);
	}
	
	public void checkAndCreateToolsPerProcess(){
		toolFactory.process();
	}
	
	public void removeToolsInScreen(){
		toolFactory.reSet();
	}
}
