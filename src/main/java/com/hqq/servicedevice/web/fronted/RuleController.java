package com.hqq.servicedevice.web.fronted;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huqiaoqian on 2020/11/3
 */
@RestController
@RequestMapping("rule")
@Api(value = "rule",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RuleController {
}
