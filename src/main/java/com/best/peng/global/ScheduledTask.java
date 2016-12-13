package com.best.peng.global;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器配置
 * @author zhoupeng
 *
 */
@Component
public class ScheduledTask {
	
	private static final SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 执行方法
	 * fixedRate=30000 ->30s执行一次
	 * @throws IOException
	 */
	@Scheduled(fixedRate=30000)
	public void reportCurrentTime() throws IOException{
		boolean flag=false;
		if(flag){
			System.out.println("1");
		}else{
			//java执行cmd 命令
			//Runtime.getRuntime().exec("shutdown -r -t 10");//10秒后重启电脑
			System.out.println("准备就绪!");
		}
		
		System.out.println("现在时间"+dateFormate.format(Calendar.getInstance().getTime()));
	}
}
