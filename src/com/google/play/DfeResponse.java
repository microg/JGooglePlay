package com.google.play;

import com.google.play.proto.Notifications;
import com.google.play.proto.Requests;
import com.google.play.proto.Unsorted;

import java.util.Collections;
import java.util.List;

public class DfeResponse<ResponseType> {
	private ResponseType response;
	private Requests.ResponseWrapper wrapper;
	private int statusCode;
	private String statusString;
	private Throwable throwable;

	public DfeResponse(Throwable throwable) {
		this.throwable = throwable;
	}

	public DfeResponse(int statusCode, String statusString, Throwable throwable) {
		this.statusCode = statusCode;
		this.statusString = statusString;
		this.throwable = throwable;
	}

	public DfeResponse(ResponseType response, Requests.ResponseWrapper wrapper, int statusCode, String statusString) {
		this.response = response;
		this.wrapper = wrapper;
		this.statusCode = statusCode;
		this.statusString = statusString;
	}

	public DfeResponse(int statusCode, String statusString) {
		this.statusCode = statusCode;
		this.statusString = statusString;
	}

	public DfeResponse(Requests.ResponseWrapper wrapper, ResponseType response) {
		this.wrapper = wrapper;
		this.response = response;
	}

	public DfeResponse(Requests.ResponseWrapper wrapper, int statusCode, String statusString) {
		this.wrapper = wrapper;
		this.statusCode = statusCode;
		this.statusString = statusString;
	}

	public boolean hasError() {
		return throwable != null || statusCode > 200;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusString() {
		return statusString;
	}

	public ResponseType getResponse() {
		return response;
	}

	void setResponse(ResponseType response) {
		this.response = response;
	}

	public Unsorted.ServerCommands getCommands() {
		return wrapper != null ? wrapper.getCommands() : null;
	}

	public List<Notifications.Notification> getNotifications() {
		return wrapper != null ? wrapper.getNotificationList() : Collections.<Notifications.Notification>emptyList();
	}

	public List<Requests.PreFetch> getPreFetches() {
		return wrapper != null ? wrapper.getPreFetchList() : Collections.<Requests.PreFetch>emptyList();
	}

	Requests.ResponseWrapper getWrapper() {
		return wrapper;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	boolean hasWrapperPayload() {
		return wrapper != null && wrapper.getPayload() != null;
	}
}
