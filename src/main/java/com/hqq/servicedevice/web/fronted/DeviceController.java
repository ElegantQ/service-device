package com.hqq.servicedevice.web.fronted;

import com.hqq.servicedevice.model.dto.EdgeDeviceDto;
import com.hqq.servicedevice.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huqiaoqian on 2020/10/14
 */
@RestController
@RequestMapping("device")
@Api(value = "k8s",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("addDevice")
    @ApiOperation("创建设备")
    public void addEdgeDevice(@RequestBody EdgeDeviceDto deviceDto) {
        deviceService.createDevice(deviceDto);
    }
//
//    @PostMapping("addDeviceModel")
//    @ApiOperation("创建设备模型")
}
