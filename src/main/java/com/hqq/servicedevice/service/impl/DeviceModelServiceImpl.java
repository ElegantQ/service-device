package com.hqq.servicedevice.service.impl;

import com.hqq.servicedevice.model.deviceModel.*;
import com.hqq.servicedevice.model.dto.DeviceModelPropertyDto;
import com.hqq.servicedevice.model.dto.EdgeDeviceModelDto;
import com.hqq.servicedevice.model.modelType.DoubleType;
import com.hqq.servicedevice.model.modelType.IntType;
import com.hqq.servicedevice.model.modelType.ModelType;
import com.hqq.servicedevice.model.modelType.StringType;
import com.hqq.servicedevice.service.DeviceModelService;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/16
 */
//@Service
//public class DeviceModelServiceImpl implements DeviceModelService {
//    @Autowired
//    private NonNamespaceOperation<EdgeDeviceModel, DeviceModelList, DoneableDeviceModel, Resource<EdgeDeviceModel, DoneableDeviceModel>> deviceModelClient;
//
//    @Override
//    public void createDeviceModel(EdgeDeviceModelDto deviceModelDto) {
//        try {
//            EdgeDeviceModel deviceModel=new EdgeDeviceModel();
//            DeviceModelSpec modelSpec=new DeviceModelSpec();
//            List<DeviceModelProperty> modelProperties=new ArrayList<>();
//            List<DeviceModelPropertyDto> modelPropertyDtos=deviceModelDto.getPropertyDtos();
//            for(DeviceModelPropertyDto propertyDto:modelPropertyDtos) {
//                DeviceModelProperty modelProperty = new DeviceModelProperty();
//                modelProperty.setDescription(propertyDto.getDescription());
//                modelProperty.setName(propertyDto.getName());
//                ModelType type=new ModelType();
//                StringType stringType=new StringType();
//                stringType.setAccessMode(propertyDto.getAccessModeForString());
//                stringType.setDefaultValue(propertyDto.getValueForString());
//                type.setStringType(stringType);
//                DoubleType doubleType=new DoubleType();
//                doubleType.setAccessMode(propertyDto.getAccessModeForDouble());
//                doubleType.setDefaultValue(propertyDto.getValueForDouble());
//                type.setDoubleType(doubleType);
//                IntType intType=new IntType();
//
//            }
//            modelSpec.setProperties();
//            deviceModel.setSpec();
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("创建设备模型失败");
//        }
//    }
//}
