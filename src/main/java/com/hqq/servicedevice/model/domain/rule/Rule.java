package com.hqq.servicedevice.model.domain.rule;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Created by huqiaoqian on 2020/11/3
 */
@Data
public class Rule implements Serializable {
    private static final long serialVersionUID = -5661765038996680662L;
    private Long ruleId;

    private List<String> deviceList;

    private Long rentId;

    private String type;

    private String status;
}
