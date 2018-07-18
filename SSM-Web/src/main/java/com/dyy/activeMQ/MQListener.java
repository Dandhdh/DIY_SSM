package com.dyy.activeMQ;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * MQ监听
 */
public class MQListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            String text = ((TextMessage) message).getText();
            System.out.println("收到的信息是"+text);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
