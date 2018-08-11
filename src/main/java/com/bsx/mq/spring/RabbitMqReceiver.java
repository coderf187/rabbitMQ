package com.bsx.mq.spring;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMq 消息接受类
 * Created by zhangtengda on 2018/5/30.
 */
@Component
public class RabbitMqReceiver {
    @RabbitListener(queues = "queue.superloan.test")
    public void processMessage(String content) {
        System.out.println("test获取消息：" + content);
    }

    @RabbitListener(queues = "queue.superloan.test.two")
    public void processMessageTwo(String content){
        System.out.println("test two获取消息："  + content);
    }
}
