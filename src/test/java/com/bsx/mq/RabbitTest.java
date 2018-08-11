package com.bsx.mq;


import com.bsx.mq.enums.ExchangeEnum;
import com.bsx.mq.enums.QueueEnum;
import com.bsx.mq.enums.RoutingKeyEnum;
import com.bsx.mq.spring.RabbitMqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangtengda on 2018/5/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitTest {

    @Autowired
    RabbitMqSender rabbitMqSender;


    @Test
    public void testMsg() {
        rabbitMqSender.sendRabbitmqDirect(ExchangeEnum.SJ_TEST.getCode(), RoutingKeyEnum.SJ_TEST.getCode(), "test msg.");
    }

    @Test
    public void testQue(){
        rabbitMqSender.sendQueue(QueueEnum.SJ_TEST.getCode(),"test queue.");
        rabbitMqSender.sendQueue(QueueEnum.SJ_TEST_TWO.getCode(),"test two queue");
    }


}
