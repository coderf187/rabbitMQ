package com.bsx.mq.spring;

import com.bsx.mq.enums.ExchangeEnum;
import com.bsx.mq.enums.QueueEnum;
import com.bsx.mq.enums.RoutingKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq 交换机、队列、Routingkey绑定关系生成类
 * Created by zhangtengda on 2018/5/30.
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class RabbitMqExchangeConfig {
    //    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqExchangeConfig.class);


    /**
     * test
     */
    @Bean
    DirectExchange sjTestExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange contractDirectExchange = new DirectExchange(ExchangeEnum.SJ_TEST.getCode(), true, false);
        rabbitAdmin.declareExchange(contractDirectExchange);
        logger.debug("实例化test交换机完成");
        return contractDirectExchange;
    }

    /**
     * 小凡test队列
     */
    @Bean
    Queue sjTestQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(QueueEnum.SJ_TEST.getCode(), false);
        rabbitAdmin.declareQueue(queue);
        logger.debug("实例化test队列完成");
        return queue;
    }

    /**
     * 小凡test_two队列
     */
    @Bean
    Queue sjTestTwoQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(QueueEnum.SJ_TEST_TWO.getCode(), false);
        rabbitAdmin.declareQueue(queue);
        logger.debug("实例化test_two队列完成");
        return queue;
    }

    /**
     * test绑定
     */
    @Bean
    Binding sjTestQueueExchangeBind(Queue sjTestQueue, DirectExchange sjTestExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(sjTestQueue).to(sjTestExchange).with(RoutingKeyEnum.SJ_TEST.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("test绑定完成");
        return binding;
    }


}
