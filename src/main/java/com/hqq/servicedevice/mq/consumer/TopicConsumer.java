package com.hqq.servicedevice.mq.consumer;

import com.hqq.servicedevice.config.JacksonUtil;
import com.hqq.servicedevice.model.dto.MsgDto;
import com.hqq.servicedevice.model.mq.MqMessage;
import com.hqq.servicedevice.model.mq.MqSendMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by huqiaoqian on 2020/10/28
 */
@Slf4j
@Service
public class TopicConsumer {
    public void handlerSendMqMsg(String body, String topicName, String tags, String keys){
        log.info("handlerSendMqMsg:body={},topicName={},tags={},keys={}",body,topicName,tags,keys);
        MqMessage.checkMessage(body, keys, topicName);
        MqSendMsgDto mqSendMsgDto = new MqSendMsgDto();
        try {
            mqSendMsgDto = JacksonUtil.parseJson(body, MqSendMsgDto.class);
        } catch (IOException e) {
            log.error("发送短信MQ出现异常={}", e.getMessage(), e);
            throw new IllegalArgumentException("JSON转换异常", e);
        }
        if(mqSendMsgDto==null){
            log.error("消息体为空");
        }
        String userId = String.valueOf(mqSendMsgDto.getUserId());
        MsgDto<MqSendMsgDto> msgDto = new MsgDto<>();
        msgDto.setTopic(topicName);
        msgDto.setTag(tags);
        msgDto.setContent(mqSendMsgDto);
        log.info("webSocketMsgDto = {}",msgDto);
        log.info("userId = {}",userId);
        //TODO:将设备数据传给规则，进行处理
    }
}
