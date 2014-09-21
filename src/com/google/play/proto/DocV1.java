// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Documents.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.STRING;

public final class DocV1 extends Message {

  public static final String DEFAULT_DOCID = "";
  public static final String DEFAULT_DETAILSURL = "";
  public static final String DEFAULT_REVIEWSURL = "";
  public static final String DEFAULT_RELATEDLISTURL = "";
  public static final String DEFAULT_MOREBYLISTURL = "";
  public static final String DEFAULT_SHAREURL = "";
  public static final String DEFAULT_CREATOR = "";
  public static final String DEFAULT_DESCRIPTIONHTML = "";
  public static final String DEFAULT_RELATEDBROWSEURL = "";
  public static final String DEFAULT_MOREBYBROWSEURL = "";
  public static final String DEFAULT_RELATEDHEADER = "";
  public static final String DEFAULT_MOREBYHEADER = "";
  public static final String DEFAULT_TITLE = "";
  public static final String DEFAULT_WARNINGMESSAGE = "";

  @ProtoField(tag = 1)
  public final Document finskyDoc;

  @ProtoField(tag = 2, type = STRING)
  public final String docid;

  @ProtoField(tag = 3, type = STRING)
  public final String detailsUrl;

  @ProtoField(tag = 4, type = STRING)
  public final String reviewsUrl;

  @ProtoField(tag = 5, type = STRING)
  public final String relatedListUrl;

  @ProtoField(tag = 6, type = STRING)
  public final String moreByListUrl;

  @ProtoField(tag = 7, type = STRING)
  public final String shareUrl;

  @ProtoField(tag = 8, type = STRING)
  public final String creator;

  @ProtoField(tag = 9)
  public final DocumentDetails details;

  @ProtoField(tag = 10, type = STRING)
  public final String descriptionHtml;

  @ProtoField(tag = 11, type = STRING)
  public final String relatedBrowseUrl;

  @ProtoField(tag = 12, type = STRING)
  public final String moreByBrowseUrl;

  @ProtoField(tag = 13, type = STRING)
  public final String relatedHeader;

  @ProtoField(tag = 14, type = STRING)
  public final String moreByHeader;

  @ProtoField(tag = 15, type = STRING)
  public final String title;

  @ProtoField(tag = 16)
  public final PlusOneData plusOneData;

  @ProtoField(tag = 17, type = STRING)
  public final String warningMessage;

  public DocV1(Document finskyDoc, String docid, String detailsUrl, String reviewsUrl, String relatedListUrl, String moreByListUrl, String shareUrl, String creator, DocumentDetails details, String descriptionHtml, String relatedBrowseUrl, String moreByBrowseUrl, String relatedHeader, String moreByHeader, String title, PlusOneData plusOneData, String warningMessage) {
    this.finskyDoc = finskyDoc;
    this.docid = docid;
    this.detailsUrl = detailsUrl;
    this.reviewsUrl = reviewsUrl;
    this.relatedListUrl = relatedListUrl;
    this.moreByListUrl = moreByListUrl;
    this.shareUrl = shareUrl;
    this.creator = creator;
    this.details = details;
    this.descriptionHtml = descriptionHtml;
    this.relatedBrowseUrl = relatedBrowseUrl;
    this.moreByBrowseUrl = moreByBrowseUrl;
    this.relatedHeader = relatedHeader;
    this.moreByHeader = moreByHeader;
    this.title = title;
    this.plusOneData = plusOneData;
    this.warningMessage = warningMessage;
  }

  private DocV1(Builder builder) {
    this(builder.finskyDoc, builder.docid, builder.detailsUrl, builder.reviewsUrl, builder.relatedListUrl, builder.moreByListUrl, builder.shareUrl, builder.creator, builder.details, builder.descriptionHtml, builder.relatedBrowseUrl, builder.moreByBrowseUrl, builder.relatedHeader, builder.moreByHeader, builder.title, builder.plusOneData, builder.warningMessage);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof DocV1)) return false;
    DocV1 o = (DocV1) other;
    return equals(finskyDoc, o.finskyDoc)
        && equals(docid, o.docid)
        && equals(detailsUrl, o.detailsUrl)
        && equals(reviewsUrl, o.reviewsUrl)
        && equals(relatedListUrl, o.relatedListUrl)
        && equals(moreByListUrl, o.moreByListUrl)
        && equals(shareUrl, o.shareUrl)
        && equals(creator, o.creator)
        && equals(details, o.details)
        && equals(descriptionHtml, o.descriptionHtml)
        && equals(relatedBrowseUrl, o.relatedBrowseUrl)
        && equals(moreByBrowseUrl, o.moreByBrowseUrl)
        && equals(relatedHeader, o.relatedHeader)
        && equals(moreByHeader, o.moreByHeader)
        && equals(title, o.title)
        && equals(plusOneData, o.plusOneData)
        && equals(warningMessage, o.warningMessage);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = finskyDoc != null ? finskyDoc.hashCode() : 0;
      result = result * 37 + (docid != null ? docid.hashCode() : 0);
      result = result * 37 + (detailsUrl != null ? detailsUrl.hashCode() : 0);
      result = result * 37 + (reviewsUrl != null ? reviewsUrl.hashCode() : 0);
      result = result * 37 + (relatedListUrl != null ? relatedListUrl.hashCode() : 0);
      result = result * 37 + (moreByListUrl != null ? moreByListUrl.hashCode() : 0);
      result = result * 37 + (shareUrl != null ? shareUrl.hashCode() : 0);
      result = result * 37 + (creator != null ? creator.hashCode() : 0);
      result = result * 37 + (details != null ? details.hashCode() : 0);
      result = result * 37 + (descriptionHtml != null ? descriptionHtml.hashCode() : 0);
      result = result * 37 + (relatedBrowseUrl != null ? relatedBrowseUrl.hashCode() : 0);
      result = result * 37 + (moreByBrowseUrl != null ? moreByBrowseUrl.hashCode() : 0);
      result = result * 37 + (relatedHeader != null ? relatedHeader.hashCode() : 0);
      result = result * 37 + (moreByHeader != null ? moreByHeader.hashCode() : 0);
      result = result * 37 + (title != null ? title.hashCode() : 0);
      result = result * 37 + (plusOneData != null ? plusOneData.hashCode() : 0);
      result = result * 37 + (warningMessage != null ? warningMessage.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<DocV1> {

    public Document finskyDoc;
    public String docid;
    public String detailsUrl;
    public String reviewsUrl;
    public String relatedListUrl;
    public String moreByListUrl;
    public String shareUrl;
    public String creator;
    public DocumentDetails details;
    public String descriptionHtml;
    public String relatedBrowseUrl;
    public String moreByBrowseUrl;
    public String relatedHeader;
    public String moreByHeader;
    public String title;
    public PlusOneData plusOneData;
    public String warningMessage;

    public Builder() {
    }

    public Builder(DocV1 message) {
      super(message);
      if (message == null) return;
      this.finskyDoc = message.finskyDoc;
      this.docid = message.docid;
      this.detailsUrl = message.detailsUrl;
      this.reviewsUrl = message.reviewsUrl;
      this.relatedListUrl = message.relatedListUrl;
      this.moreByListUrl = message.moreByListUrl;
      this.shareUrl = message.shareUrl;
      this.creator = message.creator;
      this.details = message.details;
      this.descriptionHtml = message.descriptionHtml;
      this.relatedBrowseUrl = message.relatedBrowseUrl;
      this.moreByBrowseUrl = message.moreByBrowseUrl;
      this.relatedHeader = message.relatedHeader;
      this.moreByHeader = message.moreByHeader;
      this.title = message.title;
      this.plusOneData = message.plusOneData;
      this.warningMessage = message.warningMessage;
    }

    public Builder finskyDoc(Document finskyDoc) {
      this.finskyDoc = finskyDoc;
      return this;
    }

    public Builder docid(String docid) {
      this.docid = docid;
      return this;
    }

    public Builder detailsUrl(String detailsUrl) {
      this.detailsUrl = detailsUrl;
      return this;
    }

    public Builder reviewsUrl(String reviewsUrl) {
      this.reviewsUrl = reviewsUrl;
      return this;
    }

    public Builder relatedListUrl(String relatedListUrl) {
      this.relatedListUrl = relatedListUrl;
      return this;
    }

    public Builder moreByListUrl(String moreByListUrl) {
      this.moreByListUrl = moreByListUrl;
      return this;
    }

    public Builder shareUrl(String shareUrl) {
      this.shareUrl = shareUrl;
      return this;
    }

    public Builder creator(String creator) {
      this.creator = creator;
      return this;
    }

    public Builder details(DocumentDetails details) {
      this.details = details;
      return this;
    }

    public Builder descriptionHtml(String descriptionHtml) {
      this.descriptionHtml = descriptionHtml;
      return this;
    }

    public Builder relatedBrowseUrl(String relatedBrowseUrl) {
      this.relatedBrowseUrl = relatedBrowseUrl;
      return this;
    }

    public Builder moreByBrowseUrl(String moreByBrowseUrl) {
      this.moreByBrowseUrl = moreByBrowseUrl;
      return this;
    }

    public Builder relatedHeader(String relatedHeader) {
      this.relatedHeader = relatedHeader;
      return this;
    }

    public Builder moreByHeader(String moreByHeader) {
      this.moreByHeader = moreByHeader;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder plusOneData(PlusOneData plusOneData) {
      this.plusOneData = plusOneData;
      return this;
    }

    public Builder warningMessage(String warningMessage) {
      this.warningMessage = warningMessage;
      return this;
    }

    @Override
    public DocV1 build() {
      return new DocV1(this);
    }
  }
}