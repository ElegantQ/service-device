package com.hqq.servicedevice.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/16
 */
@Data
public class DeviceModelPropertyDto implements Serializable {
    private static final long serialVersionUID = -3384858985540044057L;

    private String name;

    private String description;

    private String accessModeForInt;

    private int valueForInt;

    private String accessModeForDouble;

    private double valueForDouble;

    private String accessModeForString;

    private String valueForString;
}
