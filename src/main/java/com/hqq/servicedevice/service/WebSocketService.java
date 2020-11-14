package com.hqq.servicedevice.service;

import com.hqq.servicedevice.model.dto.DeviceDto;

/**
 * Created by huqiaoqian on 2020/11/5
 */
public interface WebSocketService {
    public void pushDataToWebClient(DeviceDto deviceDto);
}
