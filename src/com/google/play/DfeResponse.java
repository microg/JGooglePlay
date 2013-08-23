package com.google.play;

import com.google.play.proto.Notifications;
import com.google.play.proto.Requests;
import com.google.play.proto.Unsorted;

import java.util.List;

public class DfeResponse<ResponseType> {
	private final ResponseType response;
	private final Requests.ResponseWrapper wrapper;

	public DfeResponse(Requests.ResponseWrapper wrapper, ResponseType response) {
		this.wrapper = wrapper;
		this.response = response;
	}

	public ResponseType getResponse() {
		return response;
	}

	public Unsorted.ServerCommands getCommands() {
		return wrapper.getCommands();
	}

	public List<Notifications.Notification> getNotifications() {
		return wrapper.getNotificationList();
	}

	public List<Requests.PreFetch> getPreFetches() {
		return wrapper.getPreFetchList();
	}
}
