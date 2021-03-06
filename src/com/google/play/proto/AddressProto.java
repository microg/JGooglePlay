// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/BillingAddress.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.STRING;

public final class AddressProto extends Message {

  public static final String DEFAULT_ADDRESS1 = "";
  public static final String DEFAULT_ADDRESS2 = "";
  public static final String DEFAULT_CITY = "";
  public static final String DEFAULT_STATE = "";
  public static final String DEFAULT_POSTALCODE = "";
  public static final String DEFAULT_COUNTRY = "";
  public static final String DEFAULT_NAME = "";
  public static final String DEFAULT_TYPE = "";
  public static final String DEFAULT_PHONE = "";

  @ProtoField(tag = 1, type = STRING)
  public final String address1;

  @ProtoField(tag = 2, type = STRING)
  public final String address2;

  @ProtoField(tag = 3, type = STRING)
  public final String city;

  @ProtoField(tag = 4, type = STRING)
  public final String state;

  @ProtoField(tag = 5, type = STRING)
  public final String postalCode;

  @ProtoField(tag = 6, type = STRING)
  public final String country;

  @ProtoField(tag = 7, type = STRING)
  public final String name;

  @ProtoField(tag = 8, type = STRING)
  public final String type;

  @ProtoField(tag = 9, type = STRING)
  public final String phone;

  public AddressProto(String address1, String address2, String city, String state, String postalCode, String country, String name, String type, String phone) {
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
    this.name = name;
    this.type = type;
    this.phone = phone;
  }

  private AddressProto(Builder builder) {
    this(builder.address1, builder.address2, builder.city, builder.state, builder.postalCode, builder.country, builder.name, builder.type, builder.phone);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof AddressProto)) return false;
    AddressProto o = (AddressProto) other;
    return equals(address1, o.address1)
        && equals(address2, o.address2)
        && equals(city, o.city)
        && equals(state, o.state)
        && equals(postalCode, o.postalCode)
        && equals(country, o.country)
        && equals(name, o.name)
        && equals(type, o.type)
        && equals(phone, o.phone);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = address1 != null ? address1.hashCode() : 0;
      result = result * 37 + (address2 != null ? address2.hashCode() : 0);
      result = result * 37 + (city != null ? city.hashCode() : 0);
      result = result * 37 + (state != null ? state.hashCode() : 0);
      result = result * 37 + (postalCode != null ? postalCode.hashCode() : 0);
      result = result * 37 + (country != null ? country.hashCode() : 0);
      result = result * 37 + (name != null ? name.hashCode() : 0);
      result = result * 37 + (type != null ? type.hashCode() : 0);
      result = result * 37 + (phone != null ? phone.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<AddressProto> {

    public String address1;
    public String address2;
    public String city;
    public String state;
    public String postalCode;
    public String country;
    public String name;
    public String type;
    public String phone;

    public Builder() {
    }

    public Builder(AddressProto message) {
      super(message);
      if (message == null) return;
      this.address1 = message.address1;
      this.address2 = message.address2;
      this.city = message.city;
      this.state = message.state;
      this.postalCode = message.postalCode;
      this.country = message.country;
      this.name = message.name;
      this.type = message.type;
      this.phone = message.phone;
    }

    public Builder address1(String address1) {
      this.address1 = address1;
      return this;
    }

    public Builder address2(String address2) {
      this.address2 = address2;
      return this;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder state(String state) {
      this.state = state;
      return this;
    }

    public Builder postalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public Builder country(String country) {
      this.country = country;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    @Override
    public AddressProto build() {
      return new AddressProto(this);
    }
  }
}
