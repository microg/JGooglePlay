package com.google.android;

import com.google.play.VendingClient;
import com.google.play.proto.Asset;
import com.google.play.proto.Requests;
import com.google.play.proto.Unsorted;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class AndroidVending extends VendingClient {
	public static final int SOFTWARE_VERSION = 8015017;

        private AndroidContext context;

        public AndroidVending(AndroidContext context)
        {
            this.context = context;
        }

	public RequestInfo getRequestInfo(String service, String authToken) {
		return new RequestInfo(authToken, service, context.getAndroidIdHex(),
							   context.getBuildDevice() + " " + context.getBuildId());
	}

	public Requests.RequestPropertiesProto getRequestProperties(String authToken) {
		Requests.RequestPropertiesProto props = new Requests.RequestPropertiesProto();
		props.setAid(context.getAndroidIdHex());
		props.setUserAuthToken(authToken);
		props.setUserAuthTokenSecure(false);
		props.setClientId(context.getBuildClientId());
		props.setProductNameAndVersion(context.getBuildDevice() + ":" + context.getBuildSdkVersion());
		props.setOperatorName(context.getCellOperatorName());
		props.setOperatorNumericName(context.getCellOperator());
		props.setSimOperatorName(context.getSimOperatorName());
		props.setSimOperatorNumericName(context.getSimOperator());
		props.setLoggingId(context.getLoggingId() + "");
		props.setUserCountry(context.getCountry().toLowerCase());
		props.setUserLanguage(context.getLanguage().toLowerCase());
		props.setSoftwareVersion(SOFTWARE_VERSION);
		return props;
	}

	public Unsorted.CheckLicenseResponseProto checkLicenseRequest(String service, String authToken, String packageName, int versionCode, long nonce) {
		Unsorted.CheckLicenseRequestProto request = new Unsorted.CheckLicenseRequestProto();
		request.setPackageName(packageName);
		request.setVersionCode(versionCode);
		request.setNonce(nonce);
		return sendRequest(getRequestProperties(authToken), request, getRequestInfo(service, authToken));
	}

	public Unsorted.GetMarketMetadataResponseProto getMarketMetadata(String service,
																			String authToken) {
		Unsorted.GetMarketMetadataRequestProto request = new Unsorted.GetMarketMetadataRequestProto();
		request.setDeviceConfiguration(createDeviceConfig());
		request.setDeviceRoaming(context.getRoaming().contains("roaming") && !context.getRoaming().contains("noroaming"));
		request.setDeviceModelName(context.getBuildModel());
		request.setDeviceManufacturerName(context.getBuildManufacturer());
		return sendRequest(getRequestProperties(authToken), request, getRequestInfo(service, authToken));
	}

	public List<Asset.ExternalAssetProto> getAssetsByPackageNames(String service,
																		 String authToken, List<String> packageNames) {
		ArrayList<Asset.AssetsRequestProto> requests = new ArrayList<Asset.AssetsRequestProto>();
		for (String packageName : packageNames) {
			requests.add(createAssetsRequest("pname:" + packageName, 0, 1, false));
		}
		List<Asset.AssetsResponseProto> responses = sendAssetRequests(getRequestProperties(authToken), requests,
																	  getRequestInfo(service, authToken));
		ArrayList<Asset.ExternalAssetProto> result = new ArrayList<Asset.ExternalAssetProto>();
		for (Asset.AssetsResponseProto res : responses) {
			result.add(extractSingleAsset(res));
		}
		return result;
	}

	public Asset.ExternalAssetProto getAssetByPackageName(String service, String authToken,
																 String packageName) {
		Asset.AssetsResponseProto response =
				getAssetsResponseByQuery(service, authToken, "pname:" + packageName, 0, 1, true);
		return extractSingleAsset(response);
	}

	private Asset.ExternalAssetProto extractSingleAsset(Asset.AssetsResponseProto response) {
		if (response == null)
			return null;
		if (response.getNumTotalEntries() == 1 && response.getAssetCount() == 1) {
			return response.getAsset(0);
		} else if (response.getNumCorrectedEntries() == 1 && response.getAltAssetCount() == 1) {
			return response.getAltAsset(0);
		}
		return null;
	}

	public Asset.AssetsResponseProto getAssetsResponseByQuery(String service, String authToken,
																	 String query) {
		return getAssetsResponseByQuery(service, authToken, query, 0, 10, false);
	}

	public Asset.AssetsRequestProto createAssetsRequest(String query, int startIndex, int numEntries,
																	   boolean extended) {
		Asset.AssetsRequestProto request = new Asset.AssetsRequestProto();
		request.setQuery(query);
		request.setStartIndex(startIndex);
		request.setNumEntries(numEntries);
		request.setRetrieveExtendedInfo(extended);
		return request;
	}

	public Asset.AssetsResponseProto getAssetsResponseByQuery(String service, String authToken,
																	 String query, int startIndex, int numEntries,
																	 boolean extended) {
		return sendRequest(getRequestProperties(authToken),
						   createAssetsRequest(query, startIndex, numEntries, extended),
						   getRequestInfo(service, authToken));
	}

	public int syncAppsForUpdates(String service, String authToken,
										 List<Unsorted.ContentSyncRequestProto.AssetInstallState> installedAssets,
										 List<Unsorted.ContentSyncRequestProto.SystemApp> systemApps,
										 int sideloadedApps) {
		Unsorted.ContentSyncRequestProto request = new Unsorted.ContentSyncRequestProto();
		for (Unsorted.ContentSyncRequestProto.AssetInstallState asset : installedAssets) {
			request.addAssetInstallState(asset);
		}
		for (Unsorted.ContentSyncRequestProto.SystemApp app : systemApps) {
			request.addSystemApp(app);
		}
		request.setSideloadedAppCount(sideloadedApps); // TODO: maybe sent real number?
		Unsorted.ContentSyncResponseProto response =
				sendRequest(getRequestProperties(authToken), request, getRequestInfo(service, authToken));
		return (response != null) ? response.getNumUpdatesAvailable() : -1;
	}

	private int inRangeOr(int val, int min, int max, int def) {
		return (val >= min && val <= max) ? val : def;
	}

	private boolean isKnown(String s) {
		return (notEmpty(s) && !s.equalsIgnoreCase("unknown"));
	}

	private boolean notEmpty(String s) {
		return (s != null && !s.isEmpty());
	}

	public Unsorted.DeviceConfig createDeviceConfig() {
		final Unsorted.DeviceConfig deviceConfig = new Unsorted.DeviceConfig();
		deviceConfig.setTouchScreen(inRangeOr(context.getDeviceTouchScreen(), 0, 3, 0));
		deviceConfig.setKeyboardType(inRangeOr(context.getDeviceKeyboardType(), 0, 3, 0));
		deviceConfig.setNavigation(inRangeOr(context.getDeviceNavigation(), 0, 4, 0));
		deviceConfig.setWidthPixels(context.getDeviceWidthPixels());
		deviceConfig.setHeightPixels(context.getDeviceHeightPixels());
		deviceConfig.setScreenLayout(inRangeOr(context.getDeviceScreenLayout(), 0, 4, 0));
		deviceConfig.setHasHardKeyboard(context.getDeviceHasHardKeyboard());
		deviceConfig.setDensityDpi(context.getDeviceDensityDpi());
		deviceConfig.setGlEsVersion(context.getDeviceGlEsVersion());
		for (String s : context.getDeviceGlExtensions()) {
			deviceConfig.addGlExtension(s);
		}
		deviceConfig.addNativePlatform(context.getBuildCpuAbi());
		if (isKnown(context.getBuildCpuAbi2())) {
			deviceConfig.addNativePlatform(context.getBuildCpuAbi2());
		}
		for (String s : context.getDeviceFeatures()) {
			deviceConfig.addAvailableFeature(s);
		}
		for (String s : context.getDeviceSharedLibraries()) {
			deviceConfig.addSharedLibrary(s);
		}
		for (String s : context.getDeviceLocales()) {
			deviceConfig.addLocale(s);
		}
		deviceConfig.setHasFiveWayNavigation(context.getDeviceHasFiveWayNavigation());
		return deviceConfig;
	}
}
