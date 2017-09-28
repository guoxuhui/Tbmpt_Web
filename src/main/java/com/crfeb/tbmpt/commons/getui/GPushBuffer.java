
package com.crfeb.tbmpt.commons.getui;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crfeb.tbmpt.commons.getui.model.PushMessage;

/**
 * @author xhgui
 * @version 1.0
 * @since 1.0
 */

public class GPushBuffer {
	
	private final Logger logger = LoggerFactory.getLogger(GPushBuffer.class);
	//记录链表的长度
	public static int queueLength = 0;
	//记录舍弃的长度
	public static int abortLength = 0;
	//缓存数据
	private Queue<PushMessage> queue = new LinkedList<PushMessage>();
	
	private static GPushBuffer instance = new GPushBuffer();
	private GPushBuffer() {}
	public static GPushBuffer getInstance(){
		return instance;
	}
	
	//写入出栈
	public PushMessage poll() {
		
		PushMessage msg = null;
		synchronized (this.queue) {
			msg = this.queue.poll();
			if (msg != null)
				queueLength -= 1;
		}
		return msg;
	}
	
	//写入入栈
	public void offer(PushMessage msg){
		try {
			//写入栈时检测服务是否开启
			if(!GPushUtil.isAlive()){
				GPushUtil.init();
			}
			if (msg != null) {
				synchronized (this.queue) {
					if(queueLength > 100){
						this.queue.clear();
						abortLength = abortLength + queueLength;
						queueLength = 0;
					}
					if(this.queue.offer(msg))
					queueLength += 1;
				}
			}
		} catch (Exception e) {
			logger.error("Add GPushBuffer Error.", e);
		}
	}
	
}

