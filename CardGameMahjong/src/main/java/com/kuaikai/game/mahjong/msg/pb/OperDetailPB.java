// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/OperDetail.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class OperDetailPB {
  private OperDetailPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface OperDetailOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .mahjong.OperType operType = 1;
    /**
     * <code>required .mahjong.OperType operType = 1;</code>
     *
     * <pre>
     * 操作类型
     * </pre>
     */
    boolean hasOperType();
    /**
     * <code>required .mahjong.OperType operType = 1;</code>
     *
     * <pre>
     * 操作类型
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType getOperType();

    // optional int32 uid = 2;
    /**
     * <code>optional int32 uid = 2;</code>
     *
     * <pre>
     * 操作者
     * </pre>
     */
    boolean hasUid();
    /**
     * <code>optional int32 uid = 2;</code>
     *
     * <pre>
     * 操作者
     * </pre>
     */
    int getUid();

    // repeated int32 cards = 3;
    /**
     * <code>repeated int32 cards = 3;</code>
     *
     * <pre>
     * 用于操作的牌
     * </pre>
     */
    java.util.List<java.lang.Integer> getCardsList();
    /**
     * <code>repeated int32 cards = 3;</code>
     *
     * <pre>
     * 用于操作的牌
     * </pre>
     */
    int getCardsCount();
    /**
     * <code>repeated int32 cards = 3;</code>
     *
     * <pre>
     * 用于操作的牌
     * </pre>
     */
    int getCards(int index);

    // optional int32 target = 4;
    /**
     * <code>optional int32 target = 4;</code>
     *
     * <pre>
     * 目标牌
     * </pre>
     */
    boolean hasTarget();
    /**
     * <code>optional int32 target = 4;</code>
     *
     * <pre>
     * 目标牌
     * </pre>
     */
    int getTarget();

    // optional int32 remainCards = 5;
    /**
     * <code>optional int32 remainCards = 5;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    boolean hasRemainCards();
    /**
     * <code>optional int32 remainCards = 5;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    int getRemainCards();
  }
  /**
   * Protobuf type {@code mahjong.OperDetail}
   *
   * <pre>
   * 操作信息
   * </pre>
   */
  public static final class OperDetail extends
      com.google.protobuf.GeneratedMessage
      implements OperDetailOrBuilder {
    // Use OperDetail.newBuilder() to construct.
    private OperDetail(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private OperDetail(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final OperDetail defaultInstance;
    public static OperDetail getDefaultInstance() {
      return defaultInstance;
    }

    public OperDetail getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private OperDetail(
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
              com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType value = com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                operType_ = value;
              }
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              uid_ = input.readInt32();
              break;
            }
            case 24: {
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                cards_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000004;
              }
              cards_.add(input.readInt32());
              break;
            }
            case 26: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004) && input.getBytesUntilLimit() > 0) {
                cards_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000004;
              }
              while (input.getBytesUntilLimit() > 0) {
                cards_.add(input.readInt32());
              }
              input.popLimit(limit);
              break;
            }
            case 32: {
              bitField0_ |= 0x00000004;
              target_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000008;
              remainCards_ = input.readInt32();
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
        if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
          cards_ = java.util.Collections.unmodifiableList(cards_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.kuaikai.game.mahjong.msg.pb.OperDetailPB.internal_static_mahjong_OperDetail_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.mahjong.msg.pb.OperDetailPB.internal_static_mahjong_OperDetail_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.class, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder.class);
    }

    public static com.google.protobuf.Parser<OperDetail> PARSER =
        new com.google.protobuf.AbstractParser<OperDetail>() {
      public OperDetail parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new OperDetail(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<OperDetail> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .mahjong.OperType operType = 1;
    public static final int OPERTYPE_FIELD_NUMBER = 1;
    private com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType operType_;
    /**
     * <code>required .mahjong.OperType operType = 1;</code>
     *
     * <pre>
     * 操作类型
     * </pre>
     */
    public boolean hasOperType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .mahjong.OperType operType = 1;</code>
     *
     * <pre>
     * 操作类型
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType getOperType() {
      return operType_;
    }

    // optional int32 uid = 2;
    public static final int UID_FIELD_NUMBER = 2;
    private int uid_;
    /**
     * <code>optional int32 uid = 2;</code>
     *
     * <pre>
     * 操作者
     * </pre>
     */
    public boolean hasUid() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 uid = 2;</code>
     *
     * <pre>
     * 操作者
     * </pre>
     */
    public int getUid() {
      return uid_;
    }

    // repeated int32 cards = 3;
    public static final int CARDS_FIELD_NUMBER = 3;
    private java.util.List<java.lang.Integer> cards_;
    /**
     * <code>repeated int32 cards = 3;</code>
     *
     * <pre>
     * 用于操作的牌
     * </pre>
     */
    public java.util.List<java.lang.Integer>
        getCardsList() {
      return cards_;
    }
    /**
     * <code>repeated int32 cards = 3;</code>
     *
     * <pre>
     * 用于操作的牌
     * </pre>
     */
    public int getCardsCount() {
      return cards_.size();
    }
    /**
     * <code>repeated int32 cards = 3;</code>
     *
     * <pre>
     * 用于操作的牌
     * </pre>
     */
    public int getCards(int index) {
      return cards_.get(index);
    }

    // optional int32 target = 4;
    public static final int TARGET_FIELD_NUMBER = 4;
    private int target_;
    /**
     * <code>optional int32 target = 4;</code>
     *
     * <pre>
     * 目标牌
     * </pre>
     */
    public boolean hasTarget() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 target = 4;</code>
     *
     * <pre>
     * 目标牌
     * </pre>
     */
    public int getTarget() {
      return target_;
    }

    // optional int32 remainCards = 5;
    public static final int REMAINCARDS_FIELD_NUMBER = 5;
    private int remainCards_;
    /**
     * <code>optional int32 remainCards = 5;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    public boolean hasRemainCards() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 remainCards = 5;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    public int getRemainCards() {
      return remainCards_;
    }

    private void initFields() {
      operType_ = com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType.MO;
      uid_ = 0;
      cards_ = java.util.Collections.emptyList();
      target_ = 0;
      remainCards_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasOperType()) {
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
        output.writeEnum(1, operType_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, uid_);
      }
      for (int i = 0; i < cards_.size(); i++) {
        output.writeInt32(3, cards_.get(i));
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(4, target_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(5, remainCards_);
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
          .computeEnumSize(1, operType_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, uid_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < cards_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(cards_.get(i));
        }
        size += dataSize;
        size += 1 * getCardsList().size();
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, target_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, remainCards_);
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

    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail prototype) {
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
     * Protobuf type {@code mahjong.OperDetail}
     *
     * <pre>
     * 操作信息
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetailOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.mahjong.msg.pb.OperDetailPB.internal_static_mahjong_OperDetail_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.mahjong.msg.pb.OperDetailPB.internal_static_mahjong_OperDetail_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.class, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder.class);
      }

      // Construct using com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.newBuilder()
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
        operType_ = com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType.MO;
        bitField0_ = (bitField0_ & ~0x00000001);
        uid_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        cards_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        target_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        remainCards_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.mahjong.msg.pb.OperDetailPB.internal_static_mahjong_OperDetail_descriptor;
      }

      public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail getDefaultInstanceForType() {
        return com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.getDefaultInstance();
      }

      public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail build() {
        com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail buildPartial() {
        com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail result = new com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.operType_ = operType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.uid_ = uid_;
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          cards_ = java.util.Collections.unmodifiableList(cards_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.cards_ = cards_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000004;
        }
        result.target_ = target_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000008;
        }
        result.remainCards_ = remainCards_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail) {
          return mergeFrom((com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail other) {
        if (other == com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.getDefaultInstance()) return this;
        if (other.hasOperType()) {
          setOperType(other.getOperType());
        }
        if (other.hasUid()) {
          setUid(other.getUid());
        }
        if (!other.cards_.isEmpty()) {
          if (cards_.isEmpty()) {
            cards_ = other.cards_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureCardsIsMutable();
            cards_.addAll(other.cards_);
          }
          onChanged();
        }
        if (other.hasTarget()) {
          setTarget(other.getTarget());
        }
        if (other.hasRemainCards()) {
          setRemainCards(other.getRemainCards());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasOperType()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .mahjong.OperType operType = 1;
      private com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType operType_ = com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType.MO;
      /**
       * <code>required .mahjong.OperType operType = 1;</code>
       *
       * <pre>
       * 操作类型
       * </pre>
       */
      public boolean hasOperType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .mahjong.OperType operType = 1;</code>
       *
       * <pre>
       * 操作类型
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType getOperType() {
        return operType_;
      }
      /**
       * <code>required .mahjong.OperType operType = 1;</code>
       *
       * <pre>
       * 操作类型
       * </pre>
       */
      public Builder setOperType(com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        operType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .mahjong.OperType operType = 1;</code>
       *
       * <pre>
       * 操作类型
       * </pre>
       */
      public Builder clearOperType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        operType_ = com.kuaikai.game.mahjong.msg.pb.OperTypePB.OperType.MO;
        onChanged();
        return this;
      }

      // optional int32 uid = 2;
      private int uid_ ;
      /**
       * <code>optional int32 uid = 2;</code>
       *
       * <pre>
       * 操作者
       * </pre>
       */
      public boolean hasUid() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 uid = 2;</code>
       *
       * <pre>
       * 操作者
       * </pre>
       */
      public int getUid() {
        return uid_;
      }
      /**
       * <code>optional int32 uid = 2;</code>
       *
       * <pre>
       * 操作者
       * </pre>
       */
      public Builder setUid(int value) {
        bitField0_ |= 0x00000002;
        uid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 uid = 2;</code>
       *
       * <pre>
       * 操作者
       * </pre>
       */
      public Builder clearUid() {
        bitField0_ = (bitField0_ & ~0x00000002);
        uid_ = 0;
        onChanged();
        return this;
      }

      // repeated int32 cards = 3;
      private java.util.List<java.lang.Integer> cards_ = java.util.Collections.emptyList();
      private void ensureCardsIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          cards_ = new java.util.ArrayList<java.lang.Integer>(cards_);
          bitField0_ |= 0x00000004;
         }
      }
      /**
       * <code>repeated int32 cards = 3;</code>
       *
       * <pre>
       * 用于操作的牌
       * </pre>
       */
      public java.util.List<java.lang.Integer>
          getCardsList() {
        return java.util.Collections.unmodifiableList(cards_);
      }
      /**
       * <code>repeated int32 cards = 3;</code>
       *
       * <pre>
       * 用于操作的牌
       * </pre>
       */
      public int getCardsCount() {
        return cards_.size();
      }
      /**
       * <code>repeated int32 cards = 3;</code>
       *
       * <pre>
       * 用于操作的牌
       * </pre>
       */
      public int getCards(int index) {
        return cards_.get(index);
      }
      /**
       * <code>repeated int32 cards = 3;</code>
       *
       * <pre>
       * 用于操作的牌
       * </pre>
       */
      public Builder setCards(
          int index, int value) {
        ensureCardsIsMutable();
        cards_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 cards = 3;</code>
       *
       * <pre>
       * 用于操作的牌
       * </pre>
       */
      public Builder addCards(int value) {
        ensureCardsIsMutable();
        cards_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 cards = 3;</code>
       *
       * <pre>
       * 用于操作的牌
       * </pre>
       */
      public Builder addAllCards(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureCardsIsMutable();
        super.addAll(values, cards_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 cards = 3;</code>
       *
       * <pre>
       * 用于操作的牌
       * </pre>
       */
      public Builder clearCards() {
        cards_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }

      // optional int32 target = 4;
      private int target_ ;
      /**
       * <code>optional int32 target = 4;</code>
       *
       * <pre>
       * 目标牌
       * </pre>
       */
      public boolean hasTarget() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 target = 4;</code>
       *
       * <pre>
       * 目标牌
       * </pre>
       */
      public int getTarget() {
        return target_;
      }
      /**
       * <code>optional int32 target = 4;</code>
       *
       * <pre>
       * 目标牌
       * </pre>
       */
      public Builder setTarget(int value) {
        bitField0_ |= 0x00000008;
        target_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 target = 4;</code>
       *
       * <pre>
       * 目标牌
       * </pre>
       */
      public Builder clearTarget() {
        bitField0_ = (bitField0_ & ~0x00000008);
        target_ = 0;
        onChanged();
        return this;
      }

      // optional int32 remainCards = 5;
      private int remainCards_ ;
      /**
       * <code>optional int32 remainCards = 5;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public boolean hasRemainCards() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 remainCards = 5;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public int getRemainCards() {
        return remainCards_;
      }
      /**
       * <code>optional int32 remainCards = 5;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public Builder setRemainCards(int value) {
        bitField0_ |= 0x00000010;
        remainCards_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 remainCards = 5;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public Builder clearRemainCards() {
        bitField0_ = (bitField0_ & ~0x00000010);
        remainCards_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:mahjong.OperDetail)
    }

    static {
      defaultInstance = new OperDetail(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:mahjong.OperDetail)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_mahjong_OperDetail_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_mahjong_OperDetail_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030mahjong/OperDetail.proto\022\007mahjong\032\026mah" +
      "jong/OperType.proto\"r\n\nOperDetail\022#\n\010ope" +
      "rType\030\001 \002(\0162\021.mahjong.OperType\022\013\n\003uid\030\002 " +
      "\001(\005\022\r\n\005cards\030\003 \003(\005\022\016\n\006target\030\004 \001(\005\022\023\n\013re" +
      "mainCards\030\005 \001(\005B/\n\037com.kuaikai.game.mahj" +
      "ong.msg.pbB\014OperDetailPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_mahjong_OperDetail_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_mahjong_OperDetail_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_mahjong_OperDetail_descriptor,
              new java.lang.String[] { "OperType", "Uid", "Cards", "Target", "RemainCards", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.mahjong.msg.pb.OperTypePB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
