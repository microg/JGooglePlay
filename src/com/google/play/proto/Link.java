// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Documents.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.STRING;

public final class Link extends Message {

  public static final String DEFAULT_URI = "";

  @ProtoField(tag = 1, type = STRING)
  public final String uri;

  public Link(String uri) {
    this.uri = uri;
  }

  private Link(Builder builder) {
    this(builder.uri);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof Link)) return false;
    return equals(uri, ((Link) other).uri);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    return result != 0 ? result : (hashCode = uri != null ? uri.hashCode() : 0);
  }

  public static final class Builder extends Message.Builder<Link> {

    public String uri;

    public Builder() {
    }

    public Builder(Link message) {
      super(message);
      if (message == null) return;
      this.uri = message.uri;
    }

    public Builder uri(String uri) {
      this.uri = uri;
      return this;
    }

    @Override
    public Link build() {
      return new Link(this);
    }
  }
}
