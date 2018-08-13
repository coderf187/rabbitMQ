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
    public void processTestOneMessage(String content) {
        System.out.println("test获取消息1：" + content);
    }

    @RabbitListener(queues = "queue.superloan.test")
    public void processTestTwoMessage(String content) {
        System.out.println("test获取消息2：" + content);
    }

    @RabbitListener(queues = "queue.superloan.dev")
    public void processDevOneMessage(String content){
        System.out.println("dev获取消息1："  + content);
    }
    @RabbitListener(queues = "queue.superloan.dev")
    public void processDevTwoMessage(String content){
        System.out.println("dev获取消息2："  + content);
    }
}
