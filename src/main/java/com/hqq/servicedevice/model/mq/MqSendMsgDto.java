package com.hqq.servicedevice.model.mq;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/28
 */
@Data
public class MqSendMsgDto<T> implements Serializable {
    private static final long serialVersionUID = 2535043478418791024L;
    /**
     * 需要推送的用户的Id
     */
    private Long userId;

    /**
     * 发送的消息体Dto
     */
    private T msgBodyDto;
}
