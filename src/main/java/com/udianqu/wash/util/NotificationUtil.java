package com.udianqu.wash.util;

import java.util.Map;

import com.udianqu.wash.core.Constants;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 消息推送
 * @author xml777
 *
 */
public class NotificationUtil {
	static String masterSecret = "1128d17aaa70b7b33fb7f390";
	static String appKey = "f7ac64807880e77cdbc6612e";
	public static PushResult SendPush(Map<String, Object> map,Integer pushType) throws APIConnectionException, APIRequestException{
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		PushPayload payload = buildPushObject_all_alias_alert(map, pushType);
		return jpushClient.sendPush(payload);
		
	}
	public static PushPayload buildPushObject_all_alias_alert(Map<String, Object> map, Integer pushType) {
		String orgId = (String) map.get("orgId");
		String customerId = (String) map.get("customerId");
		String MESSAGE = (String) map.get("MESSAGE");
		if(pushType == Constants.PUSH_TYPE_WASHER){
			return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.tag(orgId))
	                .setNotification(Notification.alert(MESSAGE))
	                .build();
		}else if(pushType == Constants.PUSH_TYPE_CUSTOMER){
			return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(Constants.CUSTOMER_TYPE+customerId))
	                .setNotification(Notification.alert(MESSAGE))
	                .build();
		}else{
			return null;
		}
		
                
	}
}
