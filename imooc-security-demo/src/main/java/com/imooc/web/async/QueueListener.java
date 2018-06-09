package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;//spring初始化完毕的一个事件
import org.springframework.stereotype.Component;

/**
 *@date 2018年6月9日-下午4:36:06
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

	private Logger logger = LoggerFactory.getLogger(QueueListener.class);
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		/**
		 * 需要单独开启线程，防止阻塞系统启动
		 */
		new Thread(() ->  {
			//系统启动后执行的业务逻辑
			while(true) {
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNum = mockQueue.getCompleteOrder();
					logger.info("返回订单 处理结果，"+orderNum);
					deferredResultHolder.getMap().get(orderNum).setResult("place order success");
					mockQueue.setCompleteOrder(null);
				}else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	
}
