package com.bsx.mq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangtengda on 2018/5/30.
 */
@Component
public class RabbitMqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 指定queue发送
     * @param queue
     * @param obj
     */
    public void sendQueue(String queue,Object obj){
        this.rabbitTemplate.convertAndSend(queue,obj);
    }

    /**
     * 发送到 指定routekey的指定queue
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqDirect(String exchange, String routeKey,Object obj) {
        this.rabbitTemplate.convertAndSend(exchange, routeKey , obj);
    }

}
