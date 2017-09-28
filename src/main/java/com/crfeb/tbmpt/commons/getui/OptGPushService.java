package com.crfeb.tbmpt.commons.getui;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crfeb.tbmpt.commons.getui.model.PushMessage;


/**
 * 处理数据
 * @author xhguo
 *
 */
public class OptGPushService implements Runnable{
	private final Logger logger = LoggerFactory.getLogger(OptGPushService.class);
	private volatile Thread blinker; 
	public OptGPushService() {
	}
    public void stop() { 
        blinker = null; 
    }
	
    public void setBlinker(Thread thisThread){
    	blinker = thisThread;
    }
	public void run() {
		PushMessage pm = null;
		Thread thisThread = Thread.currentThread(); 
		System.out.println("当前线程="+thisThread.toString());
        while (blinker == thisThread) { 
			try {

				if((pm = GPushBuffer.getInstance().poll()) != null){
					if(PushMessage.COMM.equals(pm.getMsgType())){
						List<String> uids = (List<String>)pm.getTargets();
						String content = (String)pm.getContent();
						GPushService.getInstace().sendCommMultiMsgToPush(uids, content);
					}
				}

				Thread.sleep(2000L);
			} catch (Throwable e) {
				logger.error("线程运行失败-"+e.getMessage());
				logger.error(e.getLocalizedMessage());
				e.printStackTrace();
			}

		}
	}


}
