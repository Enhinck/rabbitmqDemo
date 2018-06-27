package com.example.demo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 外部调用接口
 * 
 * @author hueb
 *
 */
@RestController
@RequestMapping("/test")
public class DemoController {
	@Autowired
	private AmqpTemplate amqpTemplate;

	@GetMapping("/req")
	public String showUser() {
		for (int i = 1; i <= 10; i++) {
			amqpTemplate.convertAndSend("callback_exchange", "", "消息"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCESS";
	}

	
	

}
