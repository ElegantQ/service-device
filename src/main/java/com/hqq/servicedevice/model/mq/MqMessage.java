/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqMessage.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */

package com.hqq.servicedevice.model.mq;

import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * The class Mq message.
 *
 * @author ananops.net@gmail.com
 */
@Slf4j
@Data
@ApiModel("消息队列")
public class MqMessage implements Serializable {
	private static final long serialVersionUID = 9215900048842983997L;
	@ApiModelProperty("主题")
	private String topic;
	@ApiModelProperty("标签")
	private String tag;
	@ApiModelProperty("唯一键")
	private String key;
	@ApiModelProperty("消息体")
	private String body;

	/**
	 * Check message message.
	 *
	 * @param mqMessage the mq message
	 *
	 * @return the message
	 */
	public static Message checkMessage(MqMessage mqMessage) {

		String topic = mqMessage.getTopic();
		String key = mqMessage.getKey();
		String body = mqMessage.getBody();
		String tag = mqMessage.getTag();
		printCheckMessageLog(topic, key, body, tag);
		checkMessage(topic, key, body);
		return buildMessage(body, topic, tag, key);

	}

	private static void printCheckMessageLog(final String topic, final String key, final String body, final String tag) {
		log.info("checkMessage - 校验MQ body={}, topic={}, tag={}, key={}", body, topic, tag, key);
	}

	/**
	 * Check message message.
	 *
	 * @param body  the body
	 * @param topic the topic
	 * @param tag   the tag
	 * @param key   the key
	 *
	 * @return the message
	 */
	public static Message checkMessage(String body, String topic, String tag, String key) {
		printCheckMessageLog(topic, key, body, tag);
		checkMessage(topic, key, body);
		return buildMessage(body, topic, tag, key);

	}

	/**
	 * Check message message.
	 *
	 * @param message the message
	 *
	 * @return the message
	 */
	public static Message checkMessage(Message message) {

		String body = new String(message.getBody());
		String topic = message.getTopic();
		String key = message.getKeys();
		String tag = message.getTags();
		printCheckMessageLog(topic, key, body, tag);
		checkMessage(topic, key, body);
		return buildMessage(body, topic, tag, key);

	}

	/**
	 * Instantiates a new Mq message.
	 *
	 * @param message the message
	 */
	public MqMessage(Message message) {
		this.body = new String(message.getBody());
		this.topic = message.getTopic();
		this.key = message.getKeys();
		this.tag = message.getTags();

	}

	private static Message buildMessage(String body, String topic, String tag, String key) {
		Message message = new Message();
		try {
			message.setBody(body.getBytes(RemotingHelper.DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			log.error("编码转换,出现异常={}", e.getMessage(), e);
		}
		message.setKeys(key);
		message.setTopic(topic);
		message.setTags(tag);
		return message;
	}

	/**
	 * Instantiates a new Mq message.
	 *
	 * @param topic the topic
	 * @param tag   the tag
	 * @param key   the key
	 * @param body  the body
	 */
	public MqMessage(String topic, String tag, String key, String body) {
		this.topic = topic;
		this.tag = tag;
		this.key = key;
		this.body = body;
	}

	/**
	 * Check message.
	 *
	 * @param topic the topic
	 * @param key   the key
	 * @param body  the body
	 */
	public static void checkMessage(String topic, String key, String body) {

		Preconditions.checkArgument(!StringUtils.isEmpty(topic), "发送消息失败, 消息主题不能为空");
		Preconditions.checkArgument(!StringUtils.isEmpty(key), "发送消息失败, 消息关键字不能为空");
		Preconditions.checkArgument(!StringUtils.isEmpty(body), "发送消息失败, 消息体不能为空");
	}

	/**
	 * Print producer result.
	 *
	 * @param sendResult the send result
	 * @param logger     the logger
	 */
	public static void printProducerResult(SendResult sendResult, Logger logger) {
		if (sendResult != null) {
			logger.info("sendSimpleMessage - 发送MQ [OK]sendResult={}", sendResult);
		} else {
			logger.info("sendSimpleMessage - 发送MQ [FAIL]");
		}
	}

	/**
	 * Print producer exception.
	 *
	 * @param topic  the topic
	 * @param tag    the tag
	 * @param key    the key
	 * @param logger the logger
	 * @param e      the e
	 */
	public static void printProducerException(String topic, String tag, String key, Logger logger, Exception e) {
		logger.error("sendSimpleMessage - 发送MQ [FAIL] topic={}, tag={}, key={}", topic, tag, key, e);
	}
}
