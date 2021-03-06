// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Challenges.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.INT32;
import static com.squareup.wire.Message.Datatype.STRING;

public final class AuthenticationChallenge extends Message {

  public static final Integer DEFAULT_AUTHENTICATIONTYPE = 0;
  public static final String DEFAULT_RESPONSEAUTHENTICATIONTYPEPARAM = "";
  public static final String DEFAULT_RESPONSERETRYCOUNTPARAM = "";
  public static final String DEFAULT_PINHEADERTEXT = "";
  public static final String DEFAULT_PINDESCRIPTIONTEXTHTML = "";
  public static final String DEFAULT_GAIAHEADERTEXT = "";
  public static final String DEFAULT_GAIADESCRIPTIONTEXTHTML = "";

  @ProtoField(tag = 1, type = INT32)
  public final Integer authenticationType;

  @ProtoField(tag = 2, type = STRING)
  public final String responseAuthenticationTypeParam;

  @ProtoField(tag = 3, type = STRING)
  public final String responseRetryCountParam;

  @ProtoField(tag = 4, type = STRING)
  public final String pinHeaderText;

  @ProtoField(tag = 5, type = STRING)
  public final String pinDescriptionTextHtml;

  @ProtoField(tag = 6, type = STRING)
  public final String gaiaHeaderText;

  @ProtoField(tag = 7, type = STRING)
  public final String gaiaDescriptionTextHtml;

  public AuthenticationChallenge(Integer authenticationType, String responseAuthenticationTypeParam, String responseRetryCountParam, String pinHeaderText, String pinDescriptionTextHtml, String gaiaHeaderText, String gaiaDescriptionTextHtml) {
    this.authenticationType = authenticationType;
    this.responseAuthenticationTypeParam = responseAuthenticationTypeParam;
    this.responseRetryCountParam = responseRetryCountParam;
    this.pinHeaderText = pinHeaderText;
    this.pinDescriptionTextHtml = pinDescriptionTextHtml;
    this.gaiaHeaderText = gaiaHeaderText;
    this.gaiaDescriptionTextHtml = gaiaDescriptionTextHtml;
  }

  private AuthenticationChallenge(Builder builder) {
    this(builder.authenticationType, builder.responseAuthenticationTypeParam, builder.responseRetryCountParam, builder.pinHeaderText, builder.pinDescriptionTextHtml, builder.gaiaHeaderText, builder.gaiaDescriptionTextHtml);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof AuthenticationChallenge)) return false;
    AuthenticationChallenge o = (AuthenticationChallenge) other;
    return equals(authenticationType, o.authenticationType)
        && equals(responseAuthenticationTypeParam, o.responseAuthenticationTypeParam)
        && equals(responseRetryCountParam, o.responseRetryCountParam)
        && equals(pinHeaderText, o.pinHeaderText)
        && equals(pinDescriptionTextHtml, o.pinDescriptionTextHtml)
        && equals(gaiaHeaderText, o.gaiaHeaderText)
        && equals(gaiaDescriptionTextHtml, o.gaiaDescriptionTextHtml);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = authenticationType != null ? authenticationType.hashCode() : 0;
      result = result * 37 + (responseAuthenticationTypeParam != null ? responseAuthenticationTypeParam.hashCode() : 0);
      result = result * 37 + (responseRetryCountParam != null ? responseRetryCountParam.hashCode() : 0);
      result = result * 37 + (pinHeaderText != null ? pinHeaderText.hashCode() : 0);
      result = result * 37 + (pinDescriptionTextHtml != null ? pinDescriptionTextHtml.hashCode() : 0);
      result = result * 37 + (gaiaHeaderText != null ? gaiaHeaderText.hashCode() : 0);
      result = result * 37 + (gaiaDescriptionTextHtml != null ? gaiaDescriptionTextHtml.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<AuthenticationChallenge> {

    public Integer authenticationType;
    public String responseAuthenticationTypeParam;
    public String responseRetryCountParam;
    public String pinHeaderText;
    public String pinDescriptionTextHtml;
    public String gaiaHeaderText;
    public String gaiaDescriptionTextHtml;

    public Builder() {
    }

    public Builder(AuthenticationChallenge message) {
      super(message);
      if (message == null) return;
      this.authenticationType = message.authenticationType;
      this.responseAuthenticationTypeParam = message.responseAuthenticationTypeParam;
      this.responseRetryCountParam = message.responseRetryCountParam;
      this.pinHeaderText = message.pinHeaderText;
      this.pinDescriptionTextHtml = message.pinDescriptionTextHtml;
      this.gaiaHeaderText = message.gaiaHeaderText;
      this.gaiaDescriptionTextHtml = message.gaiaDescriptionTextHtml;
    }

    public Builder authenticationType(Integer authenticationType) {
      this.authenticationType = authenticationType;
      return this;
    }

    public Builder responseAuthenticationTypeParam(String responseAuthenticationTypeParam) {
      this.responseAuthenticationTypeParam = responseAuthenticationTypeParam;
      return this;
    }

    public Builder responseRetryCountParam(String responseRetryCountParam) {
      this.responseRetryCountParam = responseRetryCountParam;
      return this;
    }

    public Builder pinHeaderText(String pinHeaderText) {
      this.pinHeaderText = pinHeaderText;
      return this;
    }

    public Builder pinDescriptionTextHtml(String pinDescriptionTextHtml) {
      this.pinDescriptionTextHtml = pinDescriptionTextHtml;
      return this;
    }

    public Builder gaiaHeaderText(String gaiaHeaderText) {
      this.gaiaHeaderText = gaiaHeaderText;
      return this;
    }

    public Builder gaiaDescriptionTextHtml(String gaiaDescriptionTextHtml) {
      this.gaiaDescriptionTextHtml = gaiaDescriptionTextHtml;
      return this;
    }

    @Override
    public AuthenticationChallenge build() {
      return new AuthenticationChallenge(this);
    }
  }
}
