package com.hqq.servicedevice.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/16
 */
@Data
public class EdgeDeviceModelDto implements Serializable {
    private static final long serialVersionUID = 3389016370326455681L;
    private String name;
    private List<DeviceModelPropertyDto> propertyDtos;
}
