// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Purchase.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;
import okio.ByteString;

import static com.squareup.wire.Message.Datatype.BYTES;
import static com.squareup.wire.Message.Datatype.INT32;
import static com.squareup.wire.Message.Datatype.STRING;

public final class SignatureHashProto extends Message {

  public static final String DEFAULT_PACKAGENAME = "";
  public static final Integer DEFAULT_VERSIONCODE = 0;
  public static final ByteString DEFAULT_HASH = ByteString.EMPTY;

  @ProtoField(tag = 1, type = STRING)
  public final String packageName;

  @ProtoField(tag = 2, type = INT32)
  public final Integer versionCode;

  @ProtoField(tag = 3, type = BYTES)
  public final ByteString hash;

  public SignatureHashProto(String packageName, Integer versionCode, ByteString hash) {
    this.packageName = packageName;
    this.versionCode = versionCode;
    this.hash = hash;
  }

  private SignatureHashProto(Builder builder) {
    this(builder.packageName, builder.versionCode, builder.hash);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof SignatureHashProto)) return false;
    SignatureHashProto o = (SignatureHashProto) other;
    return equals(packageName, o.packageName)
        && equals(versionCode, o.versionCode)
        && equals(hash, o.hash);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = packageName != null ? packageName.hashCode() : 0;
      result = result * 37 + (versionCode != null ? versionCode.hashCode() : 0);
      result = result * 37 + (hash != null ? hash.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<SignatureHashProto> {

    public String packageName;
    public Integer versionCode;
    public ByteString hash;

    public Builder() {
    }

    public Builder(SignatureHashProto message) {
      super(message);
      if (message == null) return;
      this.packageName = message.packageName;
      this.versionCode = message.versionCode;
      this.hash = message.hash;
    }

    public Builder packageName(String packageName) {
      this.packageName = packageName;
      return this;
    }

    public Builder versionCode(Integer versionCode) {
      this.versionCode = versionCode;
      return this;
    }

    public Builder hash(ByteString hash) {
      this.hash = hash;
      return this;
    }

    @Override
    public SignatureHashProto build() {
      return new SignatureHashProto(this);
    }
  }
}
