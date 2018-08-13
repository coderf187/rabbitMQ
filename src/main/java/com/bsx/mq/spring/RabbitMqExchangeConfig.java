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
     * 实例化exchange--test
     * @param rabbitAdmin
     * @return
     */
    @Bean
    DirectExchange sjTestExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange contractDirectExchange = new DirectExchange(ExchangeEnum.SJ_TEST.getCode(), true, false);
        rabbitAdmin.declareExchange(contractDirectExchange);
        logger.debug("实例化test交换机完成");
        return contractDirectExchange;
    }

    /**
     * 实例化exchange--dev
     * @param rabbitAdmin
     * @return
     */
    @Bean
    DirectExchange sjDevExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange contractDirectExchange = new DirectExchange(ExchangeEnum.SJ_DEV.getCode(), true, false);
        rabbitAdmin.declareExchange(contractDirectExchange);
        logger.debug("实例化dev交换机完成");
        return contractDirectExchange;
    }

    /**
     * 实例化queue--test
     * @param rabbitAdmin
     * @return
     */
    @Bean
    Queue sjTestQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(QueueEnum.SJ_TEST.getCode(), false);
        rabbitAdmin.declareQueue(queue);
        logger.debug("实例化test队列完成");
        return queue;
    }

    /**
     * 实例化queue--dev
     * @param rabbitAdmin
     * @return
     */
    @Bean
    Queue sjDevQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(QueueEnum.SJ_DEV.getCode(), false);
        rabbitAdmin.declareQueue(queue);
        logger.debug("实例化dev队列完成");
        return queue;
    }

    /**
     * 绑定test相关
     * @param sjTestQueue
     * @param sjTestExchange
     * @param rabbitAdmin
     * @return
     */
    @Bean
    Binding sjTestQueueExchangeBind(Queue sjTestQueue, DirectExchange sjTestExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(sjTestQueue).to(sjTestExchange).with(RoutingKeyEnum.SJ_TEST.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("test绑定完成");
        return binding;
    }

    /**
     * 绑定dev
     * @param sjDevQueue
     * @param sjDevExchange
     * @param rabbitAdmin
     * @return
     */
    @Bean
    Binding sjDevQueueExchangeBind(Queue sjDevQueue, DirectExchange sjDevExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(sjDevQueue).to(sjDevExchange).with(RoutingKeyEnum.SJ_DEV.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("dev绑定完成");
        return binding;
    }

    /**
     * 绑定test queue绑定dev routing
     * @param sjTestQueue
     * @param sjTestExchange
     * @param rabbitAdmin
     * @return
     */
    @Bean
    Binding sjTestQueueDevRoutExchangeBind(Queue sjTestQueue, DirectExchange sjTestExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(sjTestQueue).to(sjTestExchange).with(RoutingKeyEnum.SJ_DEV.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("test_queueAndExchange_dev_routing绑定完成");
        return binding;
    }

}
