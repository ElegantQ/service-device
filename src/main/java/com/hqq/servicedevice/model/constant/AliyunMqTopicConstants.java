/*
 * Copyright (c) 2019. ananops.net All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：AliyunMqTopicConstants.java
 * 创建人：ananops
 * 联系方式：ananops.net@gmail.com


 *  * 平台官网: http://ananops.com
 */
package com.hqq.servicedevice.model.constant;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class Aliyun mq topic constants.
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AliyunMqTopicConstants {

	/**
	 * The enum  mq topic enum.
	 *
	 * @author ananops.net @gmail.com
	 */
	public enum MqTopicEnum {
		/**
		 * 设备数据
		 */
		DEVICE_DATA("DEVICE", "边缘设备数据"),

		;

		MqTopicEnum(String topic, String topicName) {
			this.topic = topic;
			this.topicName = topicName;
		}

		/**
		 * The Topic.
		 */
		String topic;
		/**
		 * The Topic name.
		 */
		String topicName;

		/**
		 * Gets topic.
		 *
		 * @return the topic
		 */
		public String getTopic() {
			return topic;
		}

	}


	/**
	 * The enum mq tag enum.
	 *
	 */
	public enum MqTagEnum {

		/**
		 * 设备数据上传.
		 */
		UPLOAD_DEVICE_DATA("UPLOAD_DEVICE_DATA", MqTopicEnum.DEVICE_DATA.getTopic(), "设备数据上传"),
		;
		/**
		 * The Tag.
		 */
		String tag;
		/**
		 * The Topic.
		 */
		String topic;
		/**
		 * The Tag name.
		 */
		String tagName;

		MqTagEnum(String tag, String topic, String tagName) {
			this.tag = tag;
			this.topic = topic;
			this.tagName = tagName;
		}

		/**
		 * Gets tag.
		 *
		 * @return the tag
		 */
		public String getTag() {
			return tag;
		}

		/**
		 * Gets topic.
		 *
		 * @return the topic
		 */
		public String getTopic() {
			return topic;
		}
	}

	/**
	 * The class Consumer topics.
	 *
	 * @author ananops.net @gmail.com
	 */
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class ConsumerTopics {


		public static final String DEVICE = buildConsumerTopics();

	}
	private static String buildConsumerTopics(){
		List<TopicObj> topicObjList = new ArrayList<>();
		//消费者消费者话题
		Set<String> tagList=new HashSet<>();
		tagList.add(MqTagEnum.UPLOAD_DEVICE_DATA.getTag());

		topicObjList.add(new TopicObj(MqTopicEnum.DEVICE_DATA.getTopic(),tagList));
		return buildConsumerTopics(topicObjList);
	}

	private static String buildConsumerTopics(List<TopicObj> topicList) {

		StringBuilder result = new StringBuilder();

		if (!CollectionUtils.isEmpty(topicList)) {
			for (TopicObj topicObj : topicList) {
				String topic = topicObj.getTopic();
				Set<String> tagList = topicObj.getTagList();

				if (StringUtils.isEmpty(topic) || CollectionUtils.isEmpty(topicList)) {
					continue;
				}

				StringBuilder tagInfo = new StringBuilder();
				for (String tag : tagList) {
					tagInfo.append(tag).append("||");
				}
				trimEnd(tagInfo, "||");
				result.append(topic).append("@").append(tagInfo).append(",");
			}
		}
		trimEnd(result, ",");
		return result.toString();

	}

	/**
	 * The class Topic obj.
	 *
	 * @author ananops.net @gmail.com
	 */
	static class TopicObj {

		private String topic;
		private Set<String> tagList;

		/**
		 * Instantiates a new Topic obj.
		 *
		 * @param topic   the topic
		 * @param tagList the tag list
		 */
		TopicObj(String topic, Set<String> tagList) {
			this.topic = topic;
			this.tagList = tagList;
		}

		/**
		 * Gets topic.
		 *
		 * @return the topic
		 */
		String getTopic() {
			return topic;
		}

		/**
		 * Gets tag list.
		 *
		 * @return the tag list
		 */
		Set<String> getTagList() {
			return tagList;
		}
	}

	private static void trimEnd(StringBuilder stringBuilder, String suffix) {
		if (null == stringBuilder) {
			return;
		}
		String str = stringBuilder.toString();
		if (!StringUtils.isEmpty(suffix) && !str.endsWith(suffix)) {
			return;
		}
		stringBuilder.delete(str.length() - suffix.length(), str.length());
	}
}
