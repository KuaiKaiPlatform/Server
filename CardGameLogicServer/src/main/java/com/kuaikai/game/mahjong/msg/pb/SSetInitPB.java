// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/SSetInit.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class SSetInitPB {
  private SSetInitPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SSetInitOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // repeated .mahjong.PlayerSetInfo playerSetInfos = 1;
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    java.util.List<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo> 
        getPlayerSetInfosList();
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo getPlayerSetInfos(int index);
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    int getPlayerSetInfosCount();
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    java.util.List<? extends com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder> 
        getPlayerSetInfosOrBuilderList();
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder getPlayerSetInfosOrBuilder(
        int index);

    // required int32 curSet = 2;
    /**
     * <code>required int32 curSet = 2;</code>
     *
     * <pre>
     * 当前局数
     * </pre>
     */
    boolean hasCurSet();
    /**
     * <code>required int32 curSet = 2;</code>
     *
     * <pre>
     * 当前局数
     * </pre>
     */
    int getCurSet();

    // required int32 remainCards = 3;
    /**
     * <code>required int32 remainCards = 3;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    boolean hasRemainCards();
    /**
     * <code>required int32 remainCards = 3;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    int getRemainCards();

    // required int32 bankerId = 4;
    /**
     * <code>required int32 bankerId = 4;</code>
     *
     * <pre>
     * 庄家ID
     * </pre>
     */
    boolean hasBankerId();
    /**
     * <code>required int32 bankerId = 4;</code>
     *
     * <pre>
     * 庄家ID
     * </pre>
     */
    int getBankerId();
  }
  /**
   * Protobuf type {@code mahjong.SSetInit}
   *
   * <pre>
   * 开局或重连后返回牌局初始状态
   * </pre>
   */
  public static final class SSetInit extends
      com.google.protobuf.GeneratedMessage
      implements SSetInitOrBuilder {
    // Use SSetInit.newBuilder() to construct.
    private SSetInit(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SSetInit(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SSetInit defaultInstance;
    public static SSetInit getDefaultInstance() {
      return defaultInstance;
    }

    public SSetInit getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SSetInit(
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
            case 10: {
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                playerSetInfos_ = new java.util.ArrayList<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo>();
                mutable_bitField0_ |= 0x00000001;
              }
              playerSetInfos_.add(input.readMessage(com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.PARSER, extensionRegistry));
              break;
            }
            case 16: {
              bitField0_ |= 0x00000001;
              curSet_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000002;
              remainCards_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000004;
              bankerId_ = input.readInt32();
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
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          playerSetInfos_ = java.util.Collections.unmodifiableList(playerSetInfos_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.kuaikai.game.mahjong.msg.pb.SSetInitPB.internal_static_mahjong_SSetInit_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.mahjong.msg.pb.SSetInitPB.internal_static_mahjong_SSetInit_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit.class, com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit.Builder.class);
    }

    public static com.google.protobuf.Parser<SSetInit> PARSER =
        new com.google.protobuf.AbstractParser<SSetInit>() {
      public SSetInit parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SSetInit(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SSetInit> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // repeated .mahjong.PlayerSetInfo playerSetInfos = 1;
    public static final int PLAYERSETINFOS_FIELD_NUMBER = 1;
    private java.util.List<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo> playerSetInfos_;
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    public java.util.List<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo> getPlayerSetInfosList() {
      return playerSetInfos_;
    }
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    public java.util.List<? extends com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder> 
        getPlayerSetInfosOrBuilderList() {
      return playerSetInfos_;
    }
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    public int getPlayerSetInfosCount() {
      return playerSetInfos_.size();
    }
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo getPlayerSetInfos(int index) {
      return playerSetInfos_.get(index);
    }
    /**
     * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
     *
     * <pre>
     * 所有玩家牌局初始状态
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder getPlayerSetInfosOrBuilder(
        int index) {
      return playerSetInfos_.get(index);
    }

    // required int32 curSet = 2;
    public static final int CURSET_FIELD_NUMBER = 2;
    private int curSet_;
    /**
     * <code>required int32 curSet = 2;</code>
     *
     * <pre>
     * 当前局数
     * </pre>
     */
    public boolean hasCurSet() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 curSet = 2;</code>
     *
     * <pre>
     * 当前局数
     * </pre>
     */
    public int getCurSet() {
      return curSet_;
    }

    // required int32 remainCards = 3;
    public static final int REMAINCARDS_FIELD_NUMBER = 3;
    private int remainCards_;
    /**
     * <code>required int32 remainCards = 3;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    public boolean hasRemainCards() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 remainCards = 3;</code>
     *
     * <pre>
     * 剩余牌数
     * </pre>
     */
    public int getRemainCards() {
      return remainCards_;
    }

    // required int32 bankerId = 4;
    public static final int BANKERID_FIELD_NUMBER = 4;
    private int bankerId_;
    /**
     * <code>required int32 bankerId = 4;</code>
     *
     * <pre>
     * 庄家ID
     * </pre>
     */
    public boolean hasBankerId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 bankerId = 4;</code>
     *
     * <pre>
     * 庄家ID
     * </pre>
     */
    public int getBankerId() {
      return bankerId_;
    }

    private void initFields() {
      playerSetInfos_ = java.util.Collections.emptyList();
      curSet_ = 0;
      remainCards_ = 0;
      bankerId_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasCurSet()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRemainCards()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasBankerId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      for (int i = 0; i < getPlayerSetInfosCount(); i++) {
        if (!getPlayerSetInfos(i).isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (int i = 0; i < playerSetInfos_.size(); i++) {
        output.writeMessage(1, playerSetInfos_.get(i));
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(2, curSet_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(3, remainCards_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(4, bankerId_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < playerSetInfos_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, playerSetInfos_.get(i));
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, curSet_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, remainCards_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, bankerId_);
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

    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit prototype) {
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
     * Protobuf type {@code mahjong.SSetInit}
     *
     * <pre>
     * 开局或重连后返回牌局初始状态
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInitOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.mahjong.msg.pb.SSetInitPB.internal_static_mahjong_SSetInit_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.mahjong.msg.pb.SSetInitPB.internal_static_mahjong_SSetInit_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit.class, com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit.Builder.class);
      }

      // Construct using com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit.newBuilder()
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
          getPlayerSetInfosFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (playerSetInfosBuilder_ == null) {
          playerSetInfos_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          playerSetInfosBuilder_.clear();
        }
        curSet_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        remainCards_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        bankerId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.mahjong.msg.pb.SSetInitPB.internal_static_mahjong_SSetInit_descriptor;
      }

      public com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit getDefaultInstanceForType() {
        return com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit.getDefaultInstance();
      }

      public com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit build() {
        com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit buildPartial() {
        com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit result = new com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (playerSetInfosBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            playerSetInfos_ = java.util.Collections.unmodifiableList(playerSetInfos_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.playerSetInfos_ = playerSetInfos_;
        } else {
          result.playerSetInfos_ = playerSetInfosBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000001;
        }
        result.curSet_ = curSet_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000002;
        }
        result.remainCards_ = remainCards_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000004;
        }
        result.bankerId_ = bankerId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit) {
          return mergeFrom((com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit other) {
        if (other == com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit.getDefaultInstance()) return this;
        if (playerSetInfosBuilder_ == null) {
          if (!other.playerSetInfos_.isEmpty()) {
            if (playerSetInfos_.isEmpty()) {
              playerSetInfos_ = other.playerSetInfos_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensurePlayerSetInfosIsMutable();
              playerSetInfos_.addAll(other.playerSetInfos_);
            }
            onChanged();
          }
        } else {
          if (!other.playerSetInfos_.isEmpty()) {
            if (playerSetInfosBuilder_.isEmpty()) {
              playerSetInfosBuilder_.dispose();
              playerSetInfosBuilder_ = null;
              playerSetInfos_ = other.playerSetInfos_;
              bitField0_ = (bitField0_ & ~0x00000001);
              playerSetInfosBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getPlayerSetInfosFieldBuilder() : null;
            } else {
              playerSetInfosBuilder_.addAllMessages(other.playerSetInfos_);
            }
          }
        }
        if (other.hasCurSet()) {
          setCurSet(other.getCurSet());
        }
        if (other.hasRemainCards()) {
          setRemainCards(other.getRemainCards());
        }
        if (other.hasBankerId()) {
          setBankerId(other.getBankerId());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCurSet()) {
          
          return false;
        }
        if (!hasRemainCards()) {
          
          return false;
        }
        if (!hasBankerId()) {
          
          return false;
        }
        for (int i = 0; i < getPlayerSetInfosCount(); i++) {
          if (!getPlayerSetInfos(i).isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // repeated .mahjong.PlayerSetInfo playerSetInfos = 1;
      private java.util.List<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo> playerSetInfos_ =
        java.util.Collections.emptyList();
      private void ensurePlayerSetInfosIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          playerSetInfos_ = new java.util.ArrayList<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo>(playerSetInfos_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder> playerSetInfosBuilder_;

      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public java.util.List<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo> getPlayerSetInfosList() {
        if (playerSetInfosBuilder_ == null) {
          return java.util.Collections.unmodifiableList(playerSetInfos_);
        } else {
          return playerSetInfosBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public int getPlayerSetInfosCount() {
        if (playerSetInfosBuilder_ == null) {
          return playerSetInfos_.size();
        } else {
          return playerSetInfosBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo getPlayerSetInfos(int index) {
        if (playerSetInfosBuilder_ == null) {
          return playerSetInfos_.get(index);
        } else {
          return playerSetInfosBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder setPlayerSetInfos(
          int index, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo value) {
        if (playerSetInfosBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensurePlayerSetInfosIsMutable();
          playerSetInfos_.set(index, value);
          onChanged();
        } else {
          playerSetInfosBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder setPlayerSetInfos(
          int index, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder builderForValue) {
        if (playerSetInfosBuilder_ == null) {
          ensurePlayerSetInfosIsMutable();
          playerSetInfos_.set(index, builderForValue.build());
          onChanged();
        } else {
          playerSetInfosBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder addPlayerSetInfos(com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo value) {
        if (playerSetInfosBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensurePlayerSetInfosIsMutable();
          playerSetInfos_.add(value);
          onChanged();
        } else {
          playerSetInfosBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder addPlayerSetInfos(
          int index, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo value) {
        if (playerSetInfosBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensurePlayerSetInfosIsMutable();
          playerSetInfos_.add(index, value);
          onChanged();
        } else {
          playerSetInfosBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder addPlayerSetInfos(
          com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder builderForValue) {
        if (playerSetInfosBuilder_ == null) {
          ensurePlayerSetInfosIsMutable();
          playerSetInfos_.add(builderForValue.build());
          onChanged();
        } else {
          playerSetInfosBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder addPlayerSetInfos(
          int index, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder builderForValue) {
        if (playerSetInfosBuilder_ == null) {
          ensurePlayerSetInfosIsMutable();
          playerSetInfos_.add(index, builderForValue.build());
          onChanged();
        } else {
          playerSetInfosBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder addAllPlayerSetInfos(
          java.lang.Iterable<? extends com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo> values) {
        if (playerSetInfosBuilder_ == null) {
          ensurePlayerSetInfosIsMutable();
          super.addAll(values, playerSetInfos_);
          onChanged();
        } else {
          playerSetInfosBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder clearPlayerSetInfos() {
        if (playerSetInfosBuilder_ == null) {
          playerSetInfos_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          playerSetInfosBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public Builder removePlayerSetInfos(int index) {
        if (playerSetInfosBuilder_ == null) {
          ensurePlayerSetInfosIsMutable();
          playerSetInfos_.remove(index);
          onChanged();
        } else {
          playerSetInfosBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder getPlayerSetInfosBuilder(
          int index) {
        return getPlayerSetInfosFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder getPlayerSetInfosOrBuilder(
          int index) {
        if (playerSetInfosBuilder_ == null) {
          return playerSetInfos_.get(index);  } else {
          return playerSetInfosBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public java.util.List<? extends com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder> 
           getPlayerSetInfosOrBuilderList() {
        if (playerSetInfosBuilder_ != null) {
          return playerSetInfosBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(playerSetInfos_);
        }
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder addPlayerSetInfosBuilder() {
        return getPlayerSetInfosFieldBuilder().addBuilder(
            com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.getDefaultInstance());
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder addPlayerSetInfosBuilder(
          int index) {
        return getPlayerSetInfosFieldBuilder().addBuilder(
            index, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.getDefaultInstance());
      }
      /**
       * <code>repeated .mahjong.PlayerSetInfo playerSetInfos = 1;</code>
       *
       * <pre>
       * 所有玩家牌局初始状态
       * </pre>
       */
      public java.util.List<com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder> 
           getPlayerSetInfosBuilderList() {
        return getPlayerSetInfosFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder> 
          getPlayerSetInfosFieldBuilder() {
        if (playerSetInfosBuilder_ == null) {
          playerSetInfosBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfo.Builder, com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.PlayerSetInfoOrBuilder>(
                  playerSetInfos_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          playerSetInfos_ = null;
        }
        return playerSetInfosBuilder_;
      }

      // required int32 curSet = 2;
      private int curSet_ ;
      /**
       * <code>required int32 curSet = 2;</code>
       *
       * <pre>
       * 当前局数
       * </pre>
       */
      public boolean hasCurSet() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 curSet = 2;</code>
       *
       * <pre>
       * 当前局数
       * </pre>
       */
      public int getCurSet() {
        return curSet_;
      }
      /**
       * <code>required int32 curSet = 2;</code>
       *
       * <pre>
       * 当前局数
       * </pre>
       */
      public Builder setCurSet(int value) {
        bitField0_ |= 0x00000002;
        curSet_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 curSet = 2;</code>
       *
       * <pre>
       * 当前局数
       * </pre>
       */
      public Builder clearCurSet() {
        bitField0_ = (bitField0_ & ~0x00000002);
        curSet_ = 0;
        onChanged();
        return this;
      }

      // required int32 remainCards = 3;
      private int remainCards_ ;
      /**
       * <code>required int32 remainCards = 3;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public boolean hasRemainCards() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 remainCards = 3;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public int getRemainCards() {
        return remainCards_;
      }
      /**
       * <code>required int32 remainCards = 3;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public Builder setRemainCards(int value) {
        bitField0_ |= 0x00000004;
        remainCards_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 remainCards = 3;</code>
       *
       * <pre>
       * 剩余牌数
       * </pre>
       */
      public Builder clearRemainCards() {
        bitField0_ = (bitField0_ & ~0x00000004);
        remainCards_ = 0;
        onChanged();
        return this;
      }

      // required int32 bankerId = 4;
      private int bankerId_ ;
      /**
       * <code>required int32 bankerId = 4;</code>
       *
       * <pre>
       * 庄家ID
       * </pre>
       */
      public boolean hasBankerId() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required int32 bankerId = 4;</code>
       *
       * <pre>
       * 庄家ID
       * </pre>
       */
      public int getBankerId() {
        return bankerId_;
      }
      /**
       * <code>required int32 bankerId = 4;</code>
       *
       * <pre>
       * 庄家ID
       * </pre>
       */
      public Builder setBankerId(int value) {
        bitField0_ |= 0x00000008;
        bankerId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 bankerId = 4;</code>
       *
       * <pre>
       * 庄家ID
       * </pre>
       */
      public Builder clearBankerId() {
        bitField0_ = (bitField0_ & ~0x00000008);
        bankerId_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:mahjong.SSetInit)
    }

    static {
      defaultInstance = new SSetInit(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:mahjong.SSetInit)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_mahjong_SSetInit_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_mahjong_SSetInit_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026mahjong/SSetInit.proto\022\007mahjong\032\033mahjo" +
      "ng/PlayerSetInfo.proto\"q\n\010SSetInit\022.\n\016pl" +
      "ayerSetInfos\030\001 \003(\0132\026.mahjong.PlayerSetIn" +
      "fo\022\016\n\006curSet\030\002 \002(\005\022\023\n\013remainCards\030\003 \002(\005\022" +
      "\020\n\010bankerId\030\004 \002(\005B-\n\037com.kuaikai.game.ma" +
      "hjong.msg.pbB\nSSetInitPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_mahjong_SSetInit_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_mahjong_SSetInit_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_mahjong_SSetInit_descriptor,
              new java.lang.String[] { "PlayerSetInfos", "CurSet", "RemainCards", "BankerId", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.mahjong.msg.pb.PlayerSetInfoPB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
