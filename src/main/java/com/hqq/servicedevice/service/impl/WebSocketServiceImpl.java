package com.hqq.servicedevice.service.impl;

import com.hqq.servicedevice.model.dto.DeviceDto;
import com.hqq.servicedevice.service.WebSocketService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by huqiaoqian on 2020/11/5
 */
@Service
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Override
    public void pushDataToWebClient(DeviceDto deviceDto) {
        try {
            messagingTemplate.convertAndSend("/queue/chat",deviceDto);
        }catch (Exception e){
            log.info("{}",e);
        }
    }
}
