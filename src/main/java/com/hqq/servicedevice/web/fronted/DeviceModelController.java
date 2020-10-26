package com.hqq.servicedevice.web.fronted;

import com.hqq.servicedevice.model.dto.EdgeDeviceModelDto;
import com.hqq.servicedevice.service.DeviceModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huqiaoqian on 2020/10/16
 */
//@RestController
//@RequestMapping("model")
//@Api(value = "k8s-deviceModel",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//public class DeviceModelController {
//    @Autowired
//    private DeviceModelService deviceModelService;
//
//    @PostMapping("addDeviceModel")
//    @ApiOperation("创建设备模型")
//    public void addDeviceModel(@RequestBody EdgeDeviceModelDto deviceModelDto){
//        deviceModelService.createDeviceModel(deviceModelDto);
//    }
//}
