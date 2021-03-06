// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Download.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.BOOL;
import static com.squareup.wire.Message.Datatype.INT32;
import static com.squareup.wire.Message.Datatype.INT64;
import static com.squareup.wire.Message.Datatype.STRING;

public final class InstallAssetProto extends Message {

  public static final String DEFAULT_ASSETID = "";
  public static final String DEFAULT_ASSETNAME = "";
  public static final Integer DEFAULT_ASSETTYPE = 0;
  public static final String DEFAULT_ASSETPACKAGE = "";
  public static final String DEFAULT_BLOBURL = "";
  public static final String DEFAULT_ASSETSIGNATURE = "";
  public static final Long DEFAULT_ASSETSIZE = 0L;
  public static final Long DEFAULT_REFUNDTIMEOUTMILLIS = 0L;
  public static final Boolean DEFAULT_FORWARDLOCKED = false;
  public static final Boolean DEFAULT_SECURED = false;
  public static final Integer DEFAULT_VERSIONCODE = 0;
  public static final String DEFAULT_DOWNLOADAUTHCOOKIENAME = "";
  public static final String DEFAULT_DOWNLOADAUTHCOOKIEVALUE = "";
  public static final Long DEFAULT_POSTINSTALLREFUNDWINDOWMILLIS = 0L;

  @ProtoField(tag = 2, type = STRING)
  public final String assetId;

  @ProtoField(tag = 3, type = STRING)
  public final String assetName;

  @ProtoField(tag = 4, type = INT32)
  public final Integer assetType;

  @ProtoField(tag = 5, type = STRING)
  public final String assetPackage;

  @ProtoField(tag = 6, type = STRING)
  public final String blobUrl;

  @ProtoField(tag = 7, type = STRING)
  public final String assetSignature;

  @ProtoField(tag = 8, type = INT64)
  public final Long assetSize;

  @ProtoField(tag = 9, type = INT64)
  public final Long refundTimeoutMillis;

  @ProtoField(tag = 10, type = BOOL)
  public final Boolean forwardLocked;

  @ProtoField(tag = 11, type = BOOL)
  public final Boolean secured;

  @ProtoField(tag = 12, type = INT32)
  public final Integer versionCode;

  @ProtoField(tag = 13, type = STRING)
  public final String downloadAuthCookieName;

  @ProtoField(tag = 14, type = STRING)
  public final String downloadAuthCookieValue;

  @ProtoField(tag = 16, type = INT64)
  public final Long postInstallRefundWindowMillis;

  public InstallAssetProto(String assetId, String assetName, Integer assetType, String assetPackage, String blobUrl, String assetSignature, Long assetSize, Long refundTimeoutMillis, Boolean forwardLocked, Boolean secured, Integer versionCode, String downloadAuthCookieName, String downloadAuthCookieValue, Long postInstallRefundWindowMillis) {
    this.assetId = assetId;
    this.assetName = assetName;
    this.assetType = assetType;
    this.assetPackage = assetPackage;
    this.blobUrl = blobUrl;
    this.assetSignature = assetSignature;
    this.assetSize = assetSize;
    this.refundTimeoutMillis = refundTimeoutMillis;
    this.forwardLocked = forwardLocked;
    this.secured = secured;
    this.versionCode = versionCode;
    this.downloadAuthCookieName = downloadAuthCookieName;
    this.downloadAuthCookieValue = downloadAuthCookieValue;
    this.postInstallRefundWindowMillis = postInstallRefundWindowMillis;
  }

  private InstallAssetProto(Builder builder) {
    this(builder.assetId, builder.assetName, builder.assetType, builder.assetPackage, builder.blobUrl, builder.assetSignature, builder.assetSize, builder.refundTimeoutMillis, builder.forwardLocked, builder.secured, builder.versionCode, builder.downloadAuthCookieName, builder.downloadAuthCookieValue, builder.postInstallRefundWindowMillis);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof InstallAssetProto)) return false;
    InstallAssetProto o = (InstallAssetProto) other;
    return equals(assetId, o.assetId)
        && equals(assetName, o.assetName)
        && equals(assetType, o.assetType)
        && equals(assetPackage, o.assetPackage)
        && equals(blobUrl, o.blobUrl)
        && equals(assetSignature, o.assetSignature)
        && equals(assetSize, o.assetSize)
        && equals(refundTimeoutMillis, o.refundTimeoutMillis)
        && equals(forwardLocked, o.forwardLocked)
        && equals(secured, o.secured)
        && equals(versionCode, o.versionCode)
        && equals(downloadAuthCookieName, o.downloadAuthCookieName)
        && equals(downloadAuthCookieValue, o.downloadAuthCookieValue)
        && equals(postInstallRefundWindowMillis, o.postInstallRefundWindowMillis);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = assetId != null ? assetId.hashCode() : 0;
      result = result * 37 + (assetName != null ? assetName.hashCode() : 0);
      result = result * 37 + (assetType != null ? assetType.hashCode() : 0);
      result = result * 37 + (assetPackage != null ? assetPackage.hashCode() : 0);
      result = result * 37 + (blobUrl != null ? blobUrl.hashCode() : 0);
      result = result * 37 + (assetSignature != null ? assetSignature.hashCode() : 0);
      result = result * 37 + (assetSize != null ? assetSize.hashCode() : 0);
      result = result * 37 + (refundTimeoutMillis != null ? refundTimeoutMillis.hashCode() : 0);
      result = result * 37 + (forwardLocked != null ? forwardLocked.hashCode() : 0);
      result = result * 37 + (secured != null ? secured.hashCode() : 0);
      result = result * 37 + (versionCode != null ? versionCode.hashCode() : 0);
      result = result * 37 + (downloadAuthCookieName != null ? downloadAuthCookieName.hashCode() : 0);
      result = result * 37 + (downloadAuthCookieValue != null ? downloadAuthCookieValue.hashCode() : 0);
      result = result * 37 + (postInstallRefundWindowMillis != null ? postInstallRefundWindowMillis.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<InstallAssetProto> {

    public String assetId;
    public String assetName;
    public Integer assetType;
    public String assetPackage;
    public String blobUrl;
    public String assetSignature;
    public Long assetSize;
    public Long refundTimeoutMillis;
    public Boolean forwardLocked;
    public Boolean secured;
    public Integer versionCode;
    public String downloadAuthCookieName;
    public String downloadAuthCookieValue;
    public Long postInstallRefundWindowMillis;

    public Builder() {
    }

    public Builder(InstallAssetProto message) {
      super(message);
      if (message == null) return;
      this.assetId = message.assetId;
      this.assetName = message.assetName;
      this.assetType = message.assetType;
      this.assetPackage = message.assetPackage;
      this.blobUrl = message.blobUrl;
      this.assetSignature = message.assetSignature;
      this.assetSize = message.assetSize;
      this.refundTimeoutMillis = message.refundTimeoutMillis;
      this.forwardLocked = message.forwardLocked;
      this.secured = message.secured;
      this.versionCode = message.versionCode;
      this.downloadAuthCookieName = message.downloadAuthCookieName;
      this.downloadAuthCookieValue = message.downloadAuthCookieValue;
      this.postInstallRefundWindowMillis = message.postInstallRefundWindowMillis;
    }

    public Builder assetId(String assetId) {
      this.assetId = assetId;
      return this;
    }

    public Builder assetName(String assetName) {
      this.assetName = assetName;
      return this;
    }

    public Builder assetType(Integer assetType) {
      this.assetType = assetType;
      return this;
    }

    public Builder assetPackage(String assetPackage) {
      this.assetPackage = assetPackage;
      return this;
    }

    public Builder blobUrl(String blobUrl) {
      this.blobUrl = blobUrl;
      return this;
    }

    public Builder assetSignature(String assetSignature) {
      this.assetSignature = assetSignature;
      return this;
    }

    public Builder assetSize(Long assetSize) {
      this.assetSize = assetSize;
      return this;
    }

    public Builder refundTimeoutMillis(Long refundTimeoutMillis) {
      this.refundTimeoutMillis = refundTimeoutMillis;
      return this;
    }

    public Builder forwardLocked(Boolean forwardLocked) {
      this.forwardLocked = forwardLocked;
      return this;
    }

    public Builder secured(Boolean secured) {
      this.secured = secured;
      return this;
    }

    public Builder versionCode(Integer versionCode) {
      this.versionCode = versionCode;
      return this;
    }

    public Builder downloadAuthCookieName(String downloadAuthCookieName) {
      this.downloadAuthCookieName = downloadAuthCookieName;
      return this;
    }

    public Builder downloadAuthCookieValue(String downloadAuthCookieValue) {
      this.downloadAuthCookieValue = downloadAuthCookieValue;
      return this;
    }

    public Builder postInstallRefundWindowMillis(Long postInstallRefundWindowMillis) {
      this.postInstallRefundWindowMillis = postInstallRefundWindowMillis;
      return this;
    }

    @Override
    public InstallAssetProto build() {
      return new InstallAssetProto(this);
    }
  }
}
