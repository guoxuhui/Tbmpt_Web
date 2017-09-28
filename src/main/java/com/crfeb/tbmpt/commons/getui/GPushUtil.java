package com.crfeb.tbmpt.commons.getui;

import java.util.ArrayList;
import java.util.List;

public class GPushUtil {
	private static GPushUtil gpush = null;
	private static List<Thread> threads = null;
	private static List<OptGPushService> optGpushs = null;
	private static Boolean IS_ALIVE = false;
	private int THREAD_NUM = 3;
	private GPushUtil() {
		if(threads == null || optGpushs == null){
			threads = new ArrayList<Thread>();
			optGpushs = new ArrayList<OptGPushService>();
		}else{
			for(OptGPushService t:optGpushs){
				t.stop();
			}
			for(Thread t:threads){
				t.interrupt();
			}
		}
		for (int i = 0; i < THREAD_NUM; i++){
			OptGPushService ogs = new OptGPushService();
			Thread t = new Thread(ogs);
			t.setName("OptGPushService-" + i);
			ogs.setBlinker(t);
			t.start();
			
			threads.add(t);
			optGpushs.add(ogs);
		}
	}
	
	public static GPushUtil init(){
		if(gpush == null){
			gpush = new GPushUtil();
		}
		return gpush;
	}
	
	public static void stop(){
		if(gpush != null){
			for(OptGPushService t:optGpushs){
				t.stop();
			}
			for(Thread t:threads){
				t.interrupt();
			}
			IS_ALIVE = false;
			gpush = null;
		}
	}
	
	public static Boolean isAlive(){
		if(threads != null && !threads.isEmpty()){
			IS_ALIVE = true;
		}
		
		return IS_ALIVE;
	}
	
	public static void sentMsg(){
		
	}

}
