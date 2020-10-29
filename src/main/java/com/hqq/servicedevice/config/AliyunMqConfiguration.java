package com.hqq.servicedevice.config;

import com.hqq.servicedevice.model.constant.AliyunMqTopicConstants;
import com.hqq.servicedevice.mq.consumer.listener.MsgListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

/**
 * Created by huqiaoqian on 2020/10/28
 */
@Slf4j
@Configuration
public class AliyunMqConfiguration {
    @Autowired
    private RocketMqProperties rocketMqProperties;

    @Autowired
    private MsgListener msgListener;

    @Autowired
    private TaskExecutor taskExecutor;
    /**
     * Default mq push consumer default mq push consumer.
     *
     * @return the default mq push consumer
     *
     * @throws MQClientException the mq client exception
     */
    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMqProperties.getConsumerGroup());
        consumer.setNamesrvAddr(rocketMqProperties.getNamesrvAddr());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        String[] strArray = AliyunMqTopicConstants.ConsumerTopics.DEVICE.split(",");
        for (String aStrArray : strArray) {
            String[] topicArray = aStrArray.split("@");
            String topic = topicArray[0];
            String tags = topicArray[1];
            if (tags==null||tags.length()==0) {
                tags = "*";
            }
            consumer.subscribe(topic, tags);
            log.info("RocketMq DeviceDataConsumer topic = {}, tags={}", topic, tags);
        }

        consumer.registerMessageListener(msgListener);
        consumer.setConsumeThreadMax(2);
        consumer.setConsumeThreadMin(2);

        taskExecutor.execute(() -> {
            try {
                Thread.sleep(5000);
                consumer.start();
                log.info("RocketMq DeviceDataConsumer OK.");
            } catch (InterruptedException | MQClientException e) {
                log.error("RocketMq DeviceDataConsumer, 出现异常={}", e.getMessage(), e);
            }
        });
        return consumer;
    }
}
