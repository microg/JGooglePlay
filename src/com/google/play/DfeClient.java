package com.google.play;

import com.google.play.proto.*;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.tools.Client;
import com.google.tools.RequestContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DfeClient extends Client {

	public static final String BASE_URL = "https://android.clients.google.com/fdfe/";
	public static final String PURCHASE_URL = BASE_URL + "purchase";
	public static final String BROWSE_URL = BASE_URL + "browse";
	public static final String DELIVERY_URL = BASE_URL + "delivery";
	public static final String DETAILS_URL = BASE_URL + "details";
	public static final String SUGGEST_URL = BASE_URL + "suggest";
	public static final String LIST_URL = BASE_URL + "list";
	public static final String BULK_DETAILS_URL = BASE_URL + "bulkDetails";
	public static final String REPLICATE_LIBRARY_URL = BASE_URL + "replicateLibrary";
	public static final String UPLOAD_DEVICE_CONFIG_URL = BASE_URL + "uploadDeviceConfig";
	public static final String TOC_URL = BASE_URL + "toc";
	protected static final String REQUEST_CONTENT_TYPE_PROTOBUF = "application/x-protobuf";
	protected static final String REQUEST_CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
	private static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
	private static final SimpleDateFormat PATTERN_RFC1123_FORMAT = new SimpleDateFormat(PATTERN_RFC1123);
	private DfeContext context;

	public DfeClient(DfeContext context) {
		this.context = context;
	}

	protected void prepareConnection(HttpURLConnection connection, String postType) {
		if (postType != null) {
			super.prepareConnection(connection, false);
			connection.setRequestProperty(REQUEST_CONTENT_TYPE_FIELD, postType);
			/*
			byte[] nonce = new byte[100];
			new Random().nextBytes(nonce);
			try {
				connection.setRequestProperty("X-DFE-Signature-Request", "nonce="+Base64.encodeBytes(nonce, Base64.URL_SAFE));
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		} else {
			connection.setUseCaches(false);
			connection.setDoInput(true);
		}
		connection.setRequestProperty("X-DFE-MCCMNC", context.get(RequestContext.KEY_CELL_OPERATOR_NUMERIC));
		connection.setRequestProperty("Authorization",
									  "GoogleLogin auth=" + context.get(RequestContext.KEY_AUTHORIZATION_TOKEN));
		connection.setRequestProperty("X-DFE-Device-Id", context.get(RequestContext.KEY_ANDROID_ID_HEX));
		connection.setRequestProperty("X-DFE-Client-Id", context.get(RequestContext.KEY_CLIENT_ID));
		connection.setRequestProperty("X-DFE-Logging-Id", context.get(RequestContext.KEY_LOGGING_ID));
		connection.setRequestProperty("X-DFE-SmallestScreenWidthDp",
									  context.get(RequestContext.KEY_SMALEST_SCREEN_WIDTH_DP));
		connection.setRequestProperty("X-DFE-Filter-Level", context.get(RequestContext.KEY_FILTER_LEVEL));
		connection.setRequestProperty("Accept-Language", "en-GB");
		setUserAgent(connection, context);
	}

	// untested
	public DfeResponse<Purchase.PurchaseStatusResponse> requestPurchase(String docId, int versionCode) {
		return requestPurchase(docId, 1, versionCode);
	}

	// untested
	public DfeResponse<Purchase.PurchaseStatusResponse> requestPurchase(String docId, int ot, int versionCode) {
		DfeResponse<Purchase.PurchaseStatusResponse> response = simplePostRequest(PURCHASE_URL, REQUEST_CONTENT_TYPE_FORM,
															 ("ot=" + ot + "&doc=" + docId + "&vc=" + versionCode + "&")
																	 .getBytes());
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getPurchaseStatusResponse());
		return response;
	}

	public DfeResponse<Unsorted.DeliveryResponse> requestDeliver(String docId, int versionCode) {
		return requestDeliver(docId, 1, versionCode);
	}

	public DfeResponse<Unsorted.DeliveryResponse> requestDeliver(String docId, int ot, int versionCode) {
		DfeResponse<Unsorted.DeliveryResponse> response =
				simpleGetRequest(DELIVERY_URL + "?doc=" + docId + "&ot=" + ot + "&vc=" + versionCode);
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getDeliveryResponse());
		return response;
	}

	public DfeResponse<Unsorted.TocResponse> requestToc(String shh, String deviceConfigToken) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(TOC_URL + "?shh=" + shh).openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		prepareConnection(connection, false);
		if (deviceConfigToken != null) {
			connection.setRequestProperty("X-DFE-Device-Config-Token", deviceConfigToken);
			try {
				Date date = new Date(Long.parseLong(deviceConfigToken));
				connection.setRequestProperty("If-Modified-Since", PATTERN_RFC1123_FORMAT.format(date));
				connection.setRequestProperty("If-None-Match", "3690"); // TODO: What's this?!
			} catch (Exception e) {
				// Ignore
			}
		}
		beforeRequest(connection);
		byte[] bytes = readData(connection, false);
		try {
			Requests.ResponseWrapper wrapper = Requests.ResponseWrapper.parseFrom(bytes);
			return new DfeResponse<Unsorted.TocResponse>(wrapper, wrapper.getPayload().getTocResponse());
		} catch (InvalidProtocolBufferMicroException e) {
			throw new RuntimeException(e);
		}
	}

	public DfeResponse<Browse.BrowseResponse> requestBrowse(String url) {
		DfeResponse<Browse.BrowseResponse> response = simpleGetRequest(prepareUrl(url, BROWSE_URL));
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getBrowseResponse());
		return response;
	}

	public DfeResponse<Documents.DetailsResponse> requestDetails(String url) {
		DfeResponse<Documents.DetailsResponse> response =
				simpleGetRequest(prepareUrl(url.contains("=") ? url : ("doc=" + url), DETAILS_URL));
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getDetailsResponse());
		return response;
	}

	public DfeResponse<Documents.BulkDetailsResponse> requestBulkDetails(Documents.BulkDetailsRequest request) {
		DfeResponse<Documents.BulkDetailsResponse> response =
				simplePostRequest(BULK_DETAILS_URL, REQUEST_CONTENT_TYPE_PROTOBUF, request.toByteArray());
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getBulkDetailsResponse());
		return response;
	}

	public DfeResponse<Library.LibraryReplicationResponse> requestLibraryReplication(
			Library.LibraryReplicationRequest request) {
		DfeResponse<Library.LibraryReplicationResponse> response =
				simplePostRequest(REPLICATE_LIBRARY_URL, REQUEST_CONTENT_TYPE_PROTOBUF, request.toByteArray());
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getLibraryReplicationResponse());
		return response;
	}

	public DfeResponse<Documents.ListResponse> requestSuggest(String url) {
		DfeResponse<Documents.ListResponse> response = simpleGetRequest(prepareUrl(url, SUGGEST_URL));
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getListResponse());
		return response;
	}

	private <T> DfeResponse<T> simpleGetRequest(String url) {
		if (DEBUG)
			System.out.println("GET " + url);
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			prepareConnection(connection, null);
			byte[] bytes = readData(connection, false);
			Requests.ResponseWrapper wrapper = Requests.ResponseWrapper.parseFrom(bytes);
			return new DfeResponse<T>(wrapper, connection.getResponseCode(), connection.getResponseMessage());
		} catch (Throwable e) {
			return new DfeResponse<T>(e);
		}
	}

	private <T> DfeResponse<T> simplePostRequest(String url, String postType, byte[] data) {
		if (DEBUG)
			System.out.println("POST " + url);
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			prepareConnection(connection, postType);
			writeData(connection, data, false);
			byte[] bytes = readData(connection, false);
			Requests.ResponseWrapper wrapper = Requests.ResponseWrapper.parseFrom(bytes);
			return new DfeResponse<T>(wrapper, connection.getResponseCode(), connection.getResponseMessage());
		} catch (Throwable e) {
			return new DfeResponse<T>(e);
		}
	}

	private String prepareUrl(String url, String requestBase) {
		if (url.startsWith(requestBase))
			return url;
		if (url.startsWith(requestBase.substring(BASE_URL.length())))
			return BASE_URL + (url.startsWith("/") ? url.substring(1) : url);
		if (url.startsWith("?"))
			return requestBase + url;
		return requestBase + "?" + url;
	}

	public DfeResponse<Documents.ListResponse> requestList(String url) {
		DfeResponse<Documents.ListResponse> response = simpleGetRequest(prepareUrl(url, LIST_URL));
		if (response.hasWrapperPayload())
			response.setResponse(response.getWrapper().getPayload().getListResponse());
		return response;
	}

}
