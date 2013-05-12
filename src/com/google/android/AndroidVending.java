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

	public static RequestInfo getRequestInfo(AndroidInfo info, String service, String authToken) {
		return new RequestInfo(authToken, service, info.getAndroidIdHex(),
							   info.getBuildDevice() + " " + info.getBuildId());
	}

	public static Requests.RequestPropertiesProto getRequestProperties(AndroidInfo info, String authToken) {
		Requests.RequestPropertiesProto props = new Requests.RequestPropertiesProto();
		props.setAid(info.getAndroidIdHex());
		props.setUserAuthToken(authToken);
		props.setUserAuthTokenSecure(false);
		props.setClientId(info.getBuildClientId());
		props.setProductNameAndVersion(info.getBuildDevice() + ":" + info.getBuildSdkVersion());
		props.setOperatorName(info.getCellOperatorName());
		props.setOperatorNumericName(info.getCellOperator());
		props.setSimOperatorName(info.getSimOperatorName());
		props.setSimOperatorNumericName(info.getSimOperator());
		props.setLoggingId(info.getLoggingId() + "");
		props.setUserCountry(info.getCountry().toLowerCase());
		props.setUserLanguage(info.getLanguage().toLowerCase());
		props.setSoftwareVersion(SOFTWARE_VERSION);
		return props;
	}

	public static Unsorted.GetMarketMetadataResponseProto getMarketMetadata(AndroidInfo info, String service,
																			String authToken) {
		Unsorted.GetMarketMetadataRequestProto request = new Unsorted.GetMarketMetadataRequestProto();
		request.setDeviceConfiguration(createDeviceConfig(info));
		request.setDeviceRoaming(info.getRoaming().contains("roaming") && !info.getRoaming().contains("noroaming"));
		request.setDeviceModelName(info.getBuildModel());
		request.setDeviceManufacturerName(info.getBuildManufacturer());
		return sendRequest(getRequestProperties(info, authToken), request, getRequestInfo(info, service, authToken));
	}

	public static List<Asset.ExternalAssetProto> getAssetsByPackageNames(AndroidInfo info, String service,
																		 String authToken, List<String> packageNames) {
		ArrayList<Asset.AssetsRequestProto> requests = new ArrayList<Asset.AssetsRequestProto>();
		for (String packageName : packageNames) {
			requests.add(createAssetsRequest("pname:" + packageName, 0, 1, false));
		}
		List<Asset.AssetsResponseProto> responses = sendAssetRequests(getRequestProperties(info, authToken), requests,
																	  getRequestInfo(info, service, authToken));
		ArrayList<Asset.ExternalAssetProto> result = new ArrayList<Asset.ExternalAssetProto>();
		for (Asset.AssetsResponseProto res : responses) {
			result.add(extractSingleAsset(res));
		}
		return result;
	}

	public static Asset.ExternalAssetProto getAssetByPackageName(AndroidInfo info, String service, String authToken,
																 String packageName) {
		Asset.AssetsResponseProto response =
				getAssetsResponseByQuery(info, service, authToken, "pname:" + packageName, 0, 1, true);
		return extractSingleAsset(response);
	}

	private static Asset.ExternalAssetProto extractSingleAsset(Asset.AssetsResponseProto response) {
		if (response == null)
			return null;
		if (response.getNumTotalEntries() == 1 && response.getAssetCount() == 1) {
			return response.getAsset(0);
		} else if (response.getNumCorrectedEntries() == 1 && response.getAltAssetCount() == 1) {
			return response.getAltAsset(0);
		}
		return null;
	}

	public static Asset.AssetsResponseProto getAssetsResponseByQuery(AndroidInfo info, String service, String authToken,
																	 String query) {
		return getAssetsResponseByQuery(info, service, authToken, query, 0, 10, false);
	}

	public static Asset.AssetsRequestProto createAssetsRequest(String query, int startIndex, int numEntries,
																	   boolean extended) {
		Asset.AssetsRequestProto request = new Asset.AssetsRequestProto();
		request.setQuery(query);
		request.setStartIndex(startIndex);
		request.setNumEntries(numEntries);
		request.setRetrieveExtendedInfo(extended);
		return request;
	}

	public static Asset.AssetsResponseProto getAssetsResponseByQuery(AndroidInfo info, String service, String authToken,
																	 String query, int startIndex, int numEntries,
																	 boolean extended) {
		return sendRequest(getRequestProperties(info, authToken),
						   createAssetsRequest(query, startIndex, numEntries, extended),
						   getRequestInfo(info, service, authToken));
	}

	public static int syncAppsForUpdates(AndroidInfo info, String service, String authToken,
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
				sendRequest(getRequestProperties(info, authToken), request, getRequestInfo(info, service, authToken));
		return (response != null) ? response.getNumUpdatesAvailable() : -1;
	}

	private static int inRangeOr(int val, int min, int max, int def) {
		return (val >= min && val <= max) ? val : def;
	}

	private static boolean isKnown(String s) {
		return (notEmpty(s) && !s.equalsIgnoreCase("unknown"));
	}

	private static boolean notEmpty(String s) {
		return (s != null && !s.isEmpty());
	}

	public static Unsorted.DeviceConfig createDeviceConfig(AndroidInfo info) {
		final Unsorted.DeviceConfig deviceConfig = new Unsorted.DeviceConfig();
		deviceConfig.setTouchScreen(inRangeOr(info.getDeviceTouchScreen(), 0, 3, 0));
		deviceConfig.setKeyboardType(inRangeOr(info.getDeviceKeyboardType(), 0, 3, 0));
		deviceConfig.setNavigation(inRangeOr(info.getDeviceNavigation(), 0, 4, 0));
		deviceConfig.setWidthPixels(info.getDeviceWidthPixels());
		deviceConfig.setHeightPixels(info.getDeviceHeightPixels());
		deviceConfig.setScreenLayout(inRangeOr(info.getDeviceScreenLayout(), 0, 4, 0));
		deviceConfig.setHasHardKeyboard(info.getDeviceHasHardKeyboard());
		deviceConfig.setDensityDpi(info.getDeviceDensityDpi());
		deviceConfig.setGlEsVersion(info.getDeviceGlEsVersion());
		for (String s : info.getDeviceGlExtensions()) {
			deviceConfig.addGlExtension(s);
		}
		deviceConfig.addNativePlatform(info.getBuildCpuAbi());
		if (isKnown(info.getBuildCpuAbi2())) {
			deviceConfig.addNativePlatform(info.getBuildCpuAbi2());
		}
		for (String s : info.getDeviceFeatures()) {
			deviceConfig.addAvailableFeature(s);
		}
		for (String s : info.getDeviceSharedLibraries()) {
			deviceConfig.addSharedLibrary(s);
		}
		for (String s : info.getDeviceLocales()) {
			deviceConfig.addLocale(s);
		}
		deviceConfig.setHasFiveWayNavigation(info.getDeviceHasFiveWayNavigation());
		return deviceConfig;
	}
}
