package cn.zjc.mq.consumer.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author zhangjinci
 * @version 2016/8/8 18:04
 * @function
 */

@Component
public class AnnotationTopicReceiver extends MessageListenerAdapter{

    private static final Logger log = LoggerFactory.getLogger(AnnotationTopicReceiver.class);

    @JmsListener(destination = "test.topic",concurrency = "5-10")
    public void onMessage(Message message, Session session) throws JMSException{
        String msg = (String)getMessageConverter().fromMessage(message);
        System.out.println("AnnotationTopicReceiver receive msg --> " + msg );
        System.out.println("session info --> " + session);
        message.acknowledge(); //确认接收到消息,否则会重发
        log.debug("AnnotationTopicReceiver确认接收到消息");
    }
}
