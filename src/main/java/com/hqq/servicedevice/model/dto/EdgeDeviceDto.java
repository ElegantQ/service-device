package com.hqq.servicedevice.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class EdgeDeviceDto implements Serializable {

    private static final long serialVersionUID = 2619278405554102852L;
    private String deviceName;

    private String description;

    private String model;

    private String deviceModelRefName;

    private String nodeName;

    private List<EdgeDeviceTwinDto> deviceTwinDtoList;
}
