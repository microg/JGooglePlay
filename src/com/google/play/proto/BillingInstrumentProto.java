// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Purchase.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.BOOL;
import static com.squareup.wire.Message.Datatype.INT32;
import static com.squareup.wire.Message.Datatype.STRING;

public final class BillingInstrumentProto extends Message {

  public static final String DEFAULT_ID = "";
  public static final String DEFAULT_NAME = "";
  public static final Boolean DEFAULT_ISINVALID = false;
  public static final Integer DEFAULT_INSTRUMENTTYPE = 0;
  public static final Integer DEFAULT_INSTRUMENTSTATUS = 0;

  @ProtoField(tag = 5, type = STRING)
  public final String id;

  @ProtoField(tag = 6, type = STRING)
  public final String name;

  @ProtoField(tag = 7, type = BOOL)
  public final Boolean isInvalid;

  @ProtoField(tag = 11, type = INT32)
  public final Integer instrumentType;

  @ProtoField(tag = 14, type = INT32)
  public final Integer instrumentStatus;

  public BillingInstrumentProto(String id, String name, Boolean isInvalid, Integer instrumentType, Integer instrumentStatus) {
    this.id = id;
    this.name = name;
    this.isInvalid = isInvalid;
    this.instrumentType = instrumentType;
    this.instrumentStatus = instrumentStatus;
  }

  private BillingInstrumentProto(Builder builder) {
    this(builder.id, builder.name, builder.isInvalid, builder.instrumentType, builder.instrumentStatus);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof BillingInstrumentProto)) return false;
    BillingInstrumentProto o = (BillingInstrumentProto) other;
    return equals(id, o.id)
        && equals(name, o.name)
        && equals(isInvalid, o.isInvalid)
        && equals(instrumentType, o.instrumentType)
        && equals(instrumentStatus, o.instrumentStatus);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = id != null ? id.hashCode() : 0;
      result = result * 37 + (name != null ? name.hashCode() : 0);
      result = result * 37 + (isInvalid != null ? isInvalid.hashCode() : 0);
      result = result * 37 + (instrumentType != null ? instrumentType.hashCode() : 0);
      result = result * 37 + (instrumentStatus != null ? instrumentStatus.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<BillingInstrumentProto> {

    public String id;
    public String name;
    public Boolean isInvalid;
    public Integer instrumentType;
    public Integer instrumentStatus;

    public Builder() {
    }

    public Builder(BillingInstrumentProto message) {
      super(message);
      if (message == null) return;
      this.id = message.id;
      this.name = message.name;
      this.isInvalid = message.isInvalid;
      this.instrumentType = message.instrumentType;
      this.instrumentStatus = message.instrumentStatus;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder isInvalid(Boolean isInvalid) {
      this.isInvalid = isInvalid;
      return this;
    }

    public Builder instrumentType(Integer instrumentType) {
      this.instrumentType = instrumentType;
      return this;
    }

    public Builder instrumentStatus(Integer instrumentStatus) {
      this.instrumentStatus = instrumentStatus;
      return this;
    }

    @Override
    public BillingInstrumentProto build() {
      return new BillingInstrumentProto(this);
    }
  }
}