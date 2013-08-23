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
	public static final String BUY_URL = BASE_URL + "buy";
	public static final String BROWSE_URL = BASE_URL + "browse";
	public static final String DELIVERY_URL = BASE_URL + "delivery";
	public static final String DETAILS_URL = BASE_URL + "details";
	public static final String SUGGEST_URL = BASE_URL + "suggest";
	public static final String LIST_URL = BASE_URL + "list";
	public static final String BULK_DETAILS_URL = BASE_URL + "bulkDetails";
	public static final String REPLICATE_LIBRARY_URL = BASE_URL + "replicateLibrary";
	public static final String UPLOAD_DEVICE_CONFIG_URL = BASE_URL + "uploadDeviceConfig";
	public static final String TOC_URL = BASE_URL + "toc";
	protected static final String REQUEST_CONTENT_TYPE = "application/x-protobuf";
	private static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
	private static final SimpleDateFormat PATTERN_RFC1123_FORMAT = new SimpleDateFormat(PATTERN_RFC1123);
	private DfeContext context;

	public DfeClient(DfeContext context) {
		this.context = context;
	}

	protected void prepareConnection(HttpURLConnection connection, boolean post) {
		if (post) {
			super.prepareConnection(connection, false);
			connection.setRequestProperty(REQUEST_CONTENT_TYPE_FIELD, REQUEST_CONTENT_TYPE);
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
	public DfeResponse<Purchase.BuyResponse> requestBuy(String docId, int versionCode) {
		return requestBuy(docId, 1, versionCode);
	}

	// untested
	public DfeResponse<Purchase.BuyResponse> requestBuy(String docId, int ot, int versionCode) {
		Requests.ResponseWrapper wrapper =
				simplePostRequest(BUY_URL, ("doc=" + docId + "&ot=" + ot + "&vc=" + versionCode).getBytes());
		return new DfeResponse<Purchase.BuyResponse>(wrapper, wrapper.getPayload().getBuyResponse());
	}

	public DfeResponse<Unsorted.DeliveryResponse> requestDeliver(String docId, int versionCode) {
		return requestDeliver(docId, 1, versionCode);
	}

	public DfeResponse<Unsorted.DeliveryResponse> requestDeliver(String docId, int ot, int versionCode) {
		Requests.ResponseWrapper wrapper =
				simpleGetRequest(DELIVERY_URL + "?doc=" + docId + "&ot=" + ot + "&vc=" + versionCode);
		return new DfeResponse<Unsorted.DeliveryResponse>(wrapper, wrapper.getPayload() != null ?
																   wrapper.getPayload().getDeliveryResponse() : null);
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
		Requests.ResponseWrapper wrapper = simpleGetRequest(prepareUrl(url, BROWSE_URL));
		return new DfeResponse<Browse.BrowseResponse>(wrapper, wrapper.getPayload().getBrowseResponse());
	}

	public DfeResponse<Documents.DetailsResponse> requestDetails(String url) {
		Requests.ResponseWrapper wrapper =
				simpleGetRequest(prepareUrl(url.contains("=") ? url : ("doc=" + url), DETAILS_URL));
		return new DfeResponse<Documents.DetailsResponse>(wrapper, wrapper.getPayload().getDetailsResponse());
	}

	public DfeResponse<Documents.BulkDetailsResponse> requestBulkDetails(Documents.BulkDetailsRequest request) {
		Requests.ResponseWrapper wrapper = simplePostRequest(BULK_DETAILS_URL, request.toByteArray());
		return new DfeResponse<Documents.BulkDetailsResponse>(wrapper, wrapper.getPayload().getBulkDetailsResponse());
	}

	public DfeResponse<Library.LibraryReplicationResponse> requestLibraryReplication(
			Library.LibraryReplicationRequest request) {
		Requests.ResponseWrapper wrapper = simplePostRequest(REPLICATE_LIBRARY_URL, request.toByteArray());
		return new DfeResponse<Library.LibraryReplicationResponse>(wrapper, wrapper.getPayload()
																				   .getLibraryReplicationResponse());
	}

	public DfeResponse<Documents.ListResponse> requestSuggest(String url) {
		Requests.ResponseWrapper wrapper = simpleGetRequest(prepareUrl(url, SUGGEST_URL));
		return new DfeResponse<Documents.ListResponse>(wrapper, wrapper.getPayload().getListResponse());
	}

	private Requests.ResponseWrapper simpleGetRequest(String url) {
		if (DEBUG)
			System.out.println("GET "+url);
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		prepareConnection(connection, false);
		beforeRequest(connection);
		byte[] bytes = readData(connection, false);
		try {
			return Requests.ResponseWrapper.parseFrom(bytes);
		} catch (InvalidProtocolBufferMicroException e) {
			throw new RuntimeException(e);
		}
	}

	private Requests.ResponseWrapper simplePostRequest(String url, byte[] data) {
		if (DEBUG)
			System.out.println("POST " + url);
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		prepareConnection(connection, true);
		writeData(connection, data, false);
		byte[] bytes = readData(connection, false);
		try {
			return Requests.ResponseWrapper.parseFrom(bytes);
		} catch (InvalidProtocolBufferMicroException e) {
			throw new RuntimeException(e);
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
		Requests.ResponseWrapper wrapper = simpleGetRequest(prepareUrl(url, LIST_URL));
		return new DfeResponse<Documents.ListResponse>(wrapper, wrapper.getPayload().getListResponse());
	}

}
