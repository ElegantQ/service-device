package com.hqq.servicedevice.mq.consumer;

import com.hqq.servicedevice.model.dto.DeviceDto;
import com.hqq.servicedevice.service.WebSocketService;
import com.hqq.servicedevice.util.JacksonUtil;
import com.hqq.servicedevice.model.mq.MqMessage;
import com.hqq.servicedevice.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by huqiaoqian on 2020/10/28
 */
@Slf4j
@Service
public class TopicConsumer {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private WebSocketService websocketService;

    public void handlerSendMqMsg(String body, String topicName, String tags, String keys){
        log.info("handlerSendMqMsg:body={},topicName={},tags={},keys={}",body,topicName,tags,keys);
        MqMessage.checkMessage(body, keys, topicName);
        DeviceDto deviceDto=new DeviceDto();
        try {
            deviceDto = JacksonUtil.parseJson(body, DeviceDto.class);
        } catch (IOException e) {
            log.error("发送短信MQ出现异常={}", e.getMessage(), e);
            throw new IllegalArgumentException("JSON转换异常", e);
        }
        if(deviceDto==null){
            log.error("消息体为空");
        }
        redisUtil.set(deviceDto.getDeviceName(),deviceDto);
        websocketService.pushDataToWebClient(deviceDto);
        //TODO:将设备数据传给规则，进行处理
    }
}
