package de.Syranda.RPG.CustomClasses;

import java.util.HashMap;

public class Runnables {

	private static HashMap<Integer, Runnable> runs = new HashMap<Integer, Runnable>();
	
	public static void registerRun(int id, Runnable run) {
		
		runs.put(id, run);
		
	}
	
	public static Runnable getRunnable(int id) {
		
		Runnable run = runs.get(id);
		
		if(run != null) return run;
		else return null;
		
	}
	
}
