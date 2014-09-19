package com.google.play;

import com.google.play.proto.Asset;
import com.google.play.proto.Requests;
import com.google.play.proto.Unsorted;
import com.google.tools.Base64;
import com.google.tools.Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class VendingClient extends Client {

	private final static int PROTOCOL_VERSION = 2;
	private final static String REQUEST_COOKIE_FIELD = "Cookie";
	private final static String REQUEST_USER_AGENT_FIELD = "User-Agent";
	private final static String REQUEST_ACCEPT_CHARSET_FIELD = "Accept-Charset";
	private final static String REQUEST_ACCEPT_CHARSET = "utf-8;q=0.7,*;q=0.7";
	private final static String REQUEST_X_PUBLIC_ANDROID_ID_FIELD = "X-Public-Android-Id";

	protected void prepareConnection(final HttpURLConnection connection, final RequestInfo info) {
		prepareConnection(connection, true);
		connection.setRequestProperty(REQUEST_COOKIE_FIELD, info.getCookie());
		connection.setRequestProperty(REQUEST_X_PUBLIC_ANDROID_ID_FIELD, info.getAndroidId());

		connection
				.setRequestProperty(REQUEST_USER_AGENT_FIELD, "Android-Market/2 (" + info.getDeviceIdent() + "); gzip");

		connection.setRequestProperty(REQUEST_ACCEPT_CHARSET_FIELD, REQUEST_ACCEPT_CHARSET);
	}

	public Requests.RequestProto createRequestProto(Requests.RequestPropertiesProto props) {
		return new Requests.RequestProto().setRequestProperties(props);
	}

	public Requests.RequestProto createRequestProto(Requests.RequestPropertiesProto props,
														   List<Requests.RequestProto.Request> requests) {
		Requests.RequestProto request = createRequestProto(props);
		for (Requests.RequestProto.Request req : requests) {
			addRequestToProto(request, req);
		}
		return request;
	}

	public Requests.RequestProto createRequestProto(Requests.RequestPropertiesProto props,
														   Requests.RequestProto.Request request) {
		return addRequestToProto(createRequestProto(props), request);
	}

        public Requests.RequestProto.Request createRequest(Unsorted.CheckLicenseRequestProto request) {
                return new Requests.RequestProto.Request().setCheckLicenseRequest(request);
        }

	public Requests.RequestProto.Request createRequest(Asset.AssetsRequestProto request) {
		return new Requests.RequestProto.Request().setAssetRequest(request);
	}

	public Requests.RequestProto.Request createRequest(Unsorted.GetMarketMetadataRequestProto request) {
		return new Requests.RequestProto.Request().setGetMarketMetadataRequest(request);
	}

	public Requests.RequestProto.Request createRequest(Unsorted.ContentSyncRequestProto request) {
		return new Requests.RequestProto.Request().setContentSyncRequest(request);
	}

	public Requests.RequestProto addRequestToProto(Requests.RequestProto builder,
														  Requests.RequestProto.Request request) {
		return builder.addRequest(request);
	}

	public Unsorted.ContentSyncResponseProto sendRequest(Requests.RequestPropertiesProto props,
																Unsorted.ContentSyncRequestProto request,
																RequestInfo info) {
		Requests.ResponseProto.Response r = sendRequest(props, createRequest(request), info);
		if (r != null && r.hasContentSyncResponse()) {
			return r.getContentSyncResponse();
		} else {
			return null;
		}
	}

        public Unsorted.CheckLicenseResponseProto sendRequest(Requests.RequestPropertiesProto props,
																Unsorted.CheckLicenseRequestProto request,
																RequestInfo info) {
                Requests.ResponseProto.Response r = sendRequest(props, createRequest(request), info);
                if (r != null && r.hasCheckLicenseResponse()) {
                        return r.getCheckLicenseResponse();
                } else {
                        return null;
                }
	}

	public Unsorted.GetMarketMetadataResponseProto sendRequest(Requests.RequestPropertiesProto props,
																	  Unsorted.GetMarketMetadataRequestProto request,
																	  RequestInfo info) {
		Requests.ResponseProto.Response r = sendRequest(props, createRequest(request), info);
		if (r != null && r.hasGetMarketMetadataResponse()) {
			return r.getGetMarketMetadataResponse();
		} else {
			return null;
		}
	}

	public Asset.AssetsResponseProto sendRequest(Requests.RequestPropertiesProto props,
														final Asset.AssetsRequestProto request,
														final RequestInfo info) {
		Requests.ResponseProto.Response r = sendRequest(props, createRequest(request), info);
		if (r != null && r.hasAssetsResponse()) {
			return r.getAssetsResponse();
		} else {
			return null;
		}
	}

	public List<Asset.AssetsResponseProto> sendAssetRequests(Requests.RequestPropertiesProto props,
																	final List<Asset.AssetsRequestProto> requests,
																	final RequestInfo info) {
		ArrayList<Requests.RequestProto.Request> reqs =
				new ArrayList<Requests.RequestProto.Request>();
		for (Asset.AssetsRequestProto req : requests) {
			reqs.add(createRequest(req));
		}
		ArrayList<Asset.AssetsResponseProto> resp = new ArrayList<Asset.AssetsResponseProto>();
		for (Requests.ResponseProto.Response r : sendRequests(props, reqs, info)) {
			if (r != null && r.hasAssetsResponse()) {
				resp.add(r.getAssetsResponse());
			}
		}
		return resp;
	}

	public List<Requests.ResponseProto.Response> sendRequests(Requests.RequestPropertiesProto props,
																	 final List<Requests.RequestProto.Request> requests,
																	 final RequestInfo info) {
		return sendRequest(createRequestProto(props, requests), info).getResponseList();
	}

	public Requests.ResponseProto.Response sendRequest(Requests.RequestPropertiesProto props,
															  final Requests.RequestProto.Request request,
															  final RequestInfo info) {
		Requests.ResponseProto r = sendRequest(createRequestProto(props, request), info);
		if (r.getResponseCount() > 0) {
			return r.getResponse(0);
		} else {
			return null;
		}
	}

	public Requests.ResponseProto sendRequest(final Requests.RequestProto request, final RequestInfo info) {
		byte[] bytes = null;
		try {
			bytes = sendString("version=" + PROTOCOL_VERSION + "&request=" +
							   Base64.encodeBytes(request.toByteArray(), Base64.URL_SAFE), info);
			return Requests.ResponseProto.parseFrom(bytes);
		} catch (final Exception e) {
			if (DEBUG) {
				e.printStackTrace(System.err);
				if (bytes != null) {
					try {
						FileOutputStream stream = new FileOutputStream("/data/local/tmp/vending.debug");
						stream.write(bytes);
						stream.flush();
						stream.close();
					} catch (Exception ex) {
					}
					System.err.println(new String(bytes));
				}
			}
			return null;
		}
	}

	private byte[] sendString(final HttpURLConnection connection, final String string, final RequestInfo info) {
		prepareConnection(connection, info);
		writeData(connection, string, false);
		return readData(connection, isError(connection), true);
	}

	private byte[] sendString(final String string, final RequestInfo info) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) info.getRequestUrl().openConnection();
			return sendString(connection, string, info);
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not open Connection!");
			}
			throw new RuntimeException("Could not open Connection!", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static class RequestInfo {

		private static final String DEFAULT_URL = "https://android.clients.google.com/vending/api/ApiRequest";
		private final String authToken;
		private URL requestUrl;
		private String deviceIdent; // like "maguro JRO03L"
		private String service; // android, androidmarket or androidsecure
		private String androidId;

		public RequestInfo(final String authToken, final String service, final String androidId,
						   final String deviceIdent) {
			this.authToken = authToken;
			this.service = service;
			this.androidId = androidId;
			this.deviceIdent = deviceIdent;
			setRequestUrl(DEFAULT_URL);
		}

		public RequestInfo(final String authToken, final String requestUrl, final String service,
						   final String androidId, final String deviceIdent) {
			this.authToken = authToken;
			this.service = service;
			this.androidId = androidId;
			this.deviceIdent = deviceIdent;
			setRequestUrl(requestUrl);
		}

		public String getDeviceIdent() {
			return deviceIdent;
		}

		public String getAndroidId() {
			return androidId;
		}

		public String getCookie() {
			return service.toUpperCase() + "=" + authToken;
		}

		public URL getRequestUrl() {
			return requestUrl;
		}

		public void setRequestUrl(final URL requestUrl) {
			if (requestUrl == null) {
				throw new RuntimeException("RequestUrl should not be null!");
			}
			this.requestUrl = requestUrl;
		}

		public void setRequestUrl(final String requestUrl) {
			if (requestUrl == null || requestUrl.isEmpty()) {
				throw new RuntimeException("RequestUrl should not be empty!");
			}
			try {
				setRequestUrl(new URL(requestUrl));
			} catch (final Exception e) {
				throw new RuntimeException("RequestUrl should be a valid URL!", e);
			}
		}

		public String getService() {
			return service;
		}

	}
}
