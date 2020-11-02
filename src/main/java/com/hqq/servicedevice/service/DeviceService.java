package com.hqq.servicedevice.service;


import com.alibaba.fastjson.JSONObject;
import com.hqq.servicedevice.model.dto.DeviceDto;
import com.hqq.servicedevice.model.dto.EdgeDeviceDto;


import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/14
 */
public interface DeviceService {
    public void createDevice(EdgeDeviceDto devicedto);

    public List<EdgeDeviceDto> getAllDevice();

    public DeviceDto getDeviceDataByDeviceName(String deviceName);

}
