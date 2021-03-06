// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/ScoreDetail.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class ScoreDetailPB {
  private ScoreDetailPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ScoreDetailOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .mahjong.JieSuan mainType = 1;
    /**
     * <code>required .mahjong.JieSuan mainType = 1;</code>
     *
     * <pre>
     * 结算主类
     * </pre>
     */
    boolean hasMainType();
    /**
     * <code>required .mahjong.JieSuan mainType = 1;</code>
     *
     * <pre>
     * 结算主类
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan getMainType();

    // repeated .mahjong.JieSuan subTypes = 2;
    /**
     * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
     *
     * <pre>
     * 结算子类列表
     * </pre>
     */
    java.util.List<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan> getSubTypesList();
    /**
     * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
     *
     * <pre>
     * 结算子类列表
     * </pre>
     */
    int getSubTypesCount();
    /**
     * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
     *
     * <pre>
     * 结算子类列表
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan getSubTypes(int index);

    // optional int32 score = 3;
    /**
     * <code>optional int32 score = 3;</code>
     *
     * <pre>
     * 得失分
     * </pre>
     */
    boolean hasScore();
    /**
     * <code>optional int32 score = 3;</code>
     *
     * <pre>
     * 得失分
     * </pre>
     */
    int getScore();
  }
  /**
   * Protobuf type {@code mahjong.ScoreDetail}
   *
   * <pre>
   * 得失分明细
   * </pre>
   */
  public static final class ScoreDetail extends
      com.google.protobuf.GeneratedMessage
      implements ScoreDetailOrBuilder {
    // Use ScoreDetail.newBuilder() to construct.
    private ScoreDetail(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ScoreDetail(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ScoreDetail defaultInstance;
    public static ScoreDetail getDefaultInstance() {
      return defaultInstance;
    }

    public ScoreDetail getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ScoreDetail(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              int rawValue = input.readEnum();
              com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan value = com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                mainType_ = value;
              }
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan value = com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                  subTypes_ = new java.util.ArrayList<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan>();
                  mutable_bitField0_ |= 0x00000002;
                }
                subTypes_.add(value);
              }
              break;
            }
            case 18: {
              int length = input.readRawVarint32();
              int oldLimit = input.pushLimit(length);
              while(input.getBytesUntilLimit() > 0) {
                int rawValue = input.readEnum();
                com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan value = com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan.valueOf(rawValue);
                if (value == null) {
                  unknownFields.mergeVarintField(2, rawValue);
                } else {
                  if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                    subTypes_ = new java.util.ArrayList<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan>();
                    mutable_bitField0_ |= 0x00000002;
                  }
                  subTypes_.add(value);
                }
              }
              input.popLimit(oldLimit);
              break;
            }
            case 24: {
              bitField0_ |= 0x00000002;
              score_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          subTypes_ = java.util.Collections.unmodifiableList(subTypes_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.internal_static_mahjong_ScoreDetail_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.internal_static_mahjong_ScoreDetail_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail.class, com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail.Builder.class);
    }

    public static com.google.protobuf.Parser<ScoreDetail> PARSER =
        new com.google.protobuf.AbstractParser<ScoreDetail>() {
      public ScoreDetail parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ScoreDetail(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ScoreDetail> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .mahjong.JieSuan mainType = 1;
    public static final int MAINTYPE_FIELD_NUMBER = 1;
    private com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan mainType_;
    /**
     * <code>required .mahjong.JieSuan mainType = 1;</code>
     *
     * <pre>
     * 结算主类
     * </pre>
     */
    public boolean hasMainType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .mahjong.JieSuan mainType = 1;</code>
     *
     * <pre>
     * 结算主类
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan getMainType() {
      return mainType_;
    }

    // repeated .mahjong.JieSuan subTypes = 2;
    public static final int SUBTYPES_FIELD_NUMBER = 2;
    private java.util.List<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan> subTypes_;
    /**
     * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
     *
     * <pre>
     * 结算子类列表
     * </pre>
     */
    public java.util.List<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan> getSubTypesList() {
      return subTypes_;
    }
    /**
     * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
     *
     * <pre>
     * 结算子类列表
     * </pre>
     */
    public int getSubTypesCount() {
      return subTypes_.size();
    }
    /**
     * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
     *
     * <pre>
     * 结算子类列表
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan getSubTypes(int index) {
      return subTypes_.get(index);
    }

    // optional int32 score = 3;
    public static final int SCORE_FIELD_NUMBER = 3;
    private int score_;
    /**
     * <code>optional int32 score = 3;</code>
     *
     * <pre>
     * 得失分
     * </pre>
     */
    public boolean hasScore() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 score = 3;</code>
     *
     * <pre>
     * 得失分
     * </pre>
     */
    public int getScore() {
      return score_;
    }

    private void initFields() {
      mainType_ = com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan.HUN_YI_SE;
      subTypes_ = java.util.Collections.emptyList();
      score_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasMainType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeEnum(1, mainType_.getNumber());
      }
      for (int i = 0; i < subTypes_.size(); i++) {
        output.writeEnum(2, subTypes_.get(i).getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(3, score_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, mainType_.getNumber());
      }
      {
        int dataSize = 0;
        for (int i = 0; i < subTypes_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeEnumSizeNoTag(subTypes_.get(i).getNumber());
        }
        size += dataSize;
        size += 1 * subTypes_.size();
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, score_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code mahjong.ScoreDetail}
     *
     * <pre>
     * 得失分明细
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetailOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.internal_static_mahjong_ScoreDetail_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.internal_static_mahjong_ScoreDetail_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail.class, com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail.Builder.class);
      }

      // Construct using com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        mainType_ = com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan.HUN_YI_SE;
        bitField0_ = (bitField0_ & ~0x00000001);
        subTypes_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        score_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.internal_static_mahjong_ScoreDetail_descriptor;
      }

      public com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail getDefaultInstanceForType() {
        return com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail.getDefaultInstance();
      }

      public com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail build() {
        com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail buildPartial() {
        com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail result = new com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.mainType_ = mainType_;
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          subTypes_ = java.util.Collections.unmodifiableList(subTypes_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.subTypes_ = subTypes_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000002;
        }
        result.score_ = score_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail) {
          return mergeFrom((com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail other) {
        if (other == com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail.getDefaultInstance()) return this;
        if (other.hasMainType()) {
          setMainType(other.getMainType());
        }
        if (!other.subTypes_.isEmpty()) {
          if (subTypes_.isEmpty()) {
            subTypes_ = other.subTypes_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureSubTypesIsMutable();
            subTypes_.addAll(other.subTypes_);
          }
          onChanged();
        }
        if (other.hasScore()) {
          setScore(other.getScore());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasMainType()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.mahjong.msg.pb.ScoreDetailPB.ScoreDetail) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .mahjong.JieSuan mainType = 1;
      private com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan mainType_ = com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan.HUN_YI_SE;
      /**
       * <code>required .mahjong.JieSuan mainType = 1;</code>
       *
       * <pre>
       * 结算主类
       * </pre>
       */
      public boolean hasMainType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .mahjong.JieSuan mainType = 1;</code>
       *
       * <pre>
       * 结算主类
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan getMainType() {
        return mainType_;
      }
      /**
       * <code>required .mahjong.JieSuan mainType = 1;</code>
       *
       * <pre>
       * 结算主类
       * </pre>
       */
      public Builder setMainType(com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        mainType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .mahjong.JieSuan mainType = 1;</code>
       *
       * <pre>
       * 结算主类
       * </pre>
       */
      public Builder clearMainType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        mainType_ = com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan.HUN_YI_SE;
        onChanged();
        return this;
      }

      // repeated .mahjong.JieSuan subTypes = 2;
      private java.util.List<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan> subTypes_ =
        java.util.Collections.emptyList();
      private void ensureSubTypesIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          subTypes_ = new java.util.ArrayList<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan>(subTypes_);
          bitField0_ |= 0x00000002;
        }
      }
      /**
       * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
       *
       * <pre>
       * 结算子类列表
       * </pre>
       */
      public java.util.List<com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan> getSubTypesList() {
        return java.util.Collections.unmodifiableList(subTypes_);
      }
      /**
       * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
       *
       * <pre>
       * 结算子类列表
       * </pre>
       */
      public int getSubTypesCount() {
        return subTypes_.size();
      }
      /**
       * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
       *
       * <pre>
       * 结算子类列表
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan getSubTypes(int index) {
        return subTypes_.get(index);
      }
      /**
       * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
       *
       * <pre>
       * 结算子类列表
       * </pre>
       */
      public Builder setSubTypes(
          int index, com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan value) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSubTypesIsMutable();
        subTypes_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
       *
       * <pre>
       * 结算子类列表
       * </pre>
       */
      public Builder addSubTypes(com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan value) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSubTypesIsMutable();
        subTypes_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
       *
       * <pre>
       * 结算子类列表
       * </pre>
       */
      public Builder addAllSubTypes(
          java.lang.Iterable<? extends com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan> values) {
        ensureSubTypesIsMutable();
        super.addAll(values, subTypes_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated .mahjong.JieSuan subTypes = 2;</code>
       *
       * <pre>
       * 结算子类列表
       * </pre>
       */
      public Builder clearSubTypes() {
        subTypes_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }

      // optional int32 score = 3;
      private int score_ ;
      /**
       * <code>optional int32 score = 3;</code>
       *
       * <pre>
       * 得失分
       * </pre>
       */
      public boolean hasScore() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 score = 3;</code>
       *
       * <pre>
       * 得失分
       * </pre>
       */
      public int getScore() {
        return score_;
      }
      /**
       * <code>optional int32 score = 3;</code>
       *
       * <pre>
       * 得失分
       * </pre>
       */
      public Builder setScore(int value) {
        bitField0_ |= 0x00000004;
        score_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 score = 3;</code>
       *
       * <pre>
       * 得失分
       * </pre>
       */
      public Builder clearScore() {
        bitField0_ = (bitField0_ & ~0x00000004);
        score_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:mahjong.ScoreDetail)
    }

    static {
      defaultInstance = new ScoreDetail(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:mahjong.ScoreDetail)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_mahjong_ScoreDetail_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_mahjong_ScoreDetail_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031mahjong/ScoreDetail.proto\022\007mahjong\032\025ma" +
      "hjong/JieSuan.proto\"d\n\013ScoreDetail\022\"\n\010ma" +
      "inType\030\001 \002(\0162\020.mahjong.JieSuan\022\"\n\010subTyp" +
      "es\030\002 \003(\0162\020.mahjong.JieSuan\022\r\n\005score\030\003 \001(" +
      "\005B0\n\037com.kuaikai.game.mahjong.msg.pbB\rSc" +
      "oreDetailPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_mahjong_ScoreDetail_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_mahjong_ScoreDetail_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_mahjong_ScoreDetail_descriptor,
              new java.lang.String[] { "MainType", "SubTypes", "Score", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.mahjong.msg.pb.JieSuanPB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
