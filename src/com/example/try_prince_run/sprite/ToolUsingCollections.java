package com.example.try_prince_run.sprite;

import java.util.ArrayList;
import java.util.List;

public class ToolUsingCollections {
	
	private List<Tool> tools = new ArrayList<Tool>();
	
	public void setToolUsing(Tool tool){
		tools.add(tool);
	}
	
	public List<Tool> getToolUsings(){
		return tools;
	}
	

}
