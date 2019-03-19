// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hall/SPlayerJoin.proto

package com.kuaikai.game.hall.msg.pb;

public final class SPlayerJoinPB {
  private SPlayerJoinPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SPlayerJoinOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .common.PlayerInfo player = 1;
    /**
     * <code>required .common.PlayerInfo player = 1;</code>
     *
     * <pre>
     * 玩家信息
     * </pre>
     */
    boolean hasPlayer();
    /**
     * <code>required .common.PlayerInfo player = 1;</code>
     *
     * <pre>
     * 玩家信息
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo getPlayer();
    /**
     * <code>required .common.PlayerInfo player = 1;</code>
     *
     * <pre>
     * 玩家信息
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfoOrBuilder getPlayerOrBuilder();

    // required .common.DeskUniq uniq = 2;
    /**
     * <code>required .common.DeskUniq uniq = 2;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    boolean hasUniq();
    /**
     * <code>required .common.DeskUniq uniq = 2;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq getUniq();
    /**
     * <code>required .common.DeskUniq uniq = 2;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder getUniqOrBuilder();
  }
  /**
   * Protobuf type {@code hall.SPlayerJoin}
   *
   * <pre>
   * 有玩家加入牌桌
   * </pre>
   */
  public static final class SPlayerJoin extends
      com.google.protobuf.GeneratedMessage
      implements SPlayerJoinOrBuilder {
    // Use SPlayerJoin.newBuilder() to construct.
    private SPlayerJoin(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SPlayerJoin(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SPlayerJoin defaultInstance;
    public static SPlayerJoin getDefaultInstance() {
      return defaultInstance;
    }

    public SPlayerJoin getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SPlayerJoin(
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
              com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = player_.toBuilder();
              }
              player_ = input.readMessage(com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(player_);
                player_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 18: {
              com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder subBuilder = null;
              if (((bitField0_ & 0x00000002) == 0x00000002)) {
                subBuilder = uniq_.toBuilder();
              }
              uniq_ = input.readMessage(com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(uniq_);
                uniq_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000002;
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.internal_static_hall_SPlayerJoin_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.internal_static_hall_SPlayerJoin_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin.class, com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin.Builder.class);
    }

    public static com.google.protobuf.Parser<SPlayerJoin> PARSER =
        new com.google.protobuf.AbstractParser<SPlayerJoin>() {
      public SPlayerJoin parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SPlayerJoin(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SPlayerJoin> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .common.PlayerInfo player = 1;
    public static final int PLAYER_FIELD_NUMBER = 1;
    private com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo player_;
    /**
     * <code>required .common.PlayerInfo player = 1;</code>
     *
     * <pre>
     * 玩家信息
     * </pre>
     */
    public boolean hasPlayer() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .common.PlayerInfo player = 1;</code>
     *
     * <pre>
     * 玩家信息
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo getPlayer() {
      return player_;
    }
    /**
     * <code>required .common.PlayerInfo player = 1;</code>
     *
     * <pre>
     * 玩家信息
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfoOrBuilder getPlayerOrBuilder() {
      return player_;
    }

    // required .common.DeskUniq uniq = 2;
    public static final int UNIQ_FIELD_NUMBER = 2;
    private com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq uniq_;
    /**
     * <code>required .common.DeskUniq uniq = 2;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    public boolean hasUniq() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .common.DeskUniq uniq = 2;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq getUniq() {
      return uniq_;
    }
    /**
     * <code>required .common.DeskUniq uniq = 2;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder getUniqOrBuilder() {
      return uniq_;
    }

    private void initFields() {
      player_ = com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.getDefaultInstance();
      uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasPlayer()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUniq()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getPlayer().isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getUniq().isInitialized()) {
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
        output.writeMessage(1, player_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeMessage(2, uniq_);
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
          .computeMessageSize(1, player_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, uniq_);
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

    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin prototype) {
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
     * Protobuf type {@code hall.SPlayerJoin}
     *
     * <pre>
     * 有玩家加入牌桌
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoinOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.internal_static_hall_SPlayerJoin_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.internal_static_hall_SPlayerJoin_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin.class, com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin.Builder.class);
      }

      // Construct using com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin.newBuilder()
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
          getPlayerFieldBuilder();
          getUniqFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (playerBuilder_ == null) {
          player_ = com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.getDefaultInstance();
        } else {
          playerBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        if (uniqBuilder_ == null) {
          uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
        } else {
          uniqBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.internal_static_hall_SPlayerJoin_descriptor;
      }

      public com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin getDefaultInstanceForType() {
        return com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin.getDefaultInstance();
      }

      public com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin build() {
        com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin buildPartial() {
        com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin result = new com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (playerBuilder_ == null) {
          result.player_ = player_;
        } else {
          result.player_ = playerBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        if (uniqBuilder_ == null) {
          result.uniq_ = uniq_;
        } else {
          result.uniq_ = uniqBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin) {
          return mergeFrom((com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin other) {
        if (other == com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin.getDefaultInstance()) return this;
        if (other.hasPlayer()) {
          mergePlayer(other.getPlayer());
        }
        if (other.hasUniq()) {
          mergeUniq(other.getUniq());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasPlayer()) {
          
          return false;
        }
        if (!hasUniq()) {
          
          return false;
        }
        if (!getPlayer().isInitialized()) {
          
          return false;
        }
        if (!getUniq().isInitialized()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.hall.msg.pb.SPlayerJoinPB.SPlayerJoin) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .common.PlayerInfo player = 1;
      private com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo player_ = com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo, com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.Builder, com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfoOrBuilder> playerBuilder_;
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public boolean hasPlayer() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo getPlayer() {
        if (playerBuilder_ == null) {
          return player_;
        } else {
          return playerBuilder_.getMessage();
        }
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public Builder setPlayer(com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo value) {
        if (playerBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          player_ = value;
          onChanged();
        } else {
          playerBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public Builder setPlayer(
          com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.Builder builderForValue) {
        if (playerBuilder_ == null) {
          player_ = builderForValue.build();
          onChanged();
        } else {
          playerBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public Builder mergePlayer(com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo value) {
        if (playerBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              player_ != com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.getDefaultInstance()) {
            player_ =
              com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.newBuilder(player_).mergeFrom(value).buildPartial();
          } else {
            player_ = value;
          }
          onChanged();
        } else {
          playerBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public Builder clearPlayer() {
        if (playerBuilder_ == null) {
          player_ = com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.getDefaultInstance();
          onChanged();
        } else {
          playerBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.Builder getPlayerBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getPlayerFieldBuilder().getBuilder();
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfoOrBuilder getPlayerOrBuilder() {
        if (playerBuilder_ != null) {
          return playerBuilder_.getMessageOrBuilder();
        } else {
          return player_;
        }
      }
      /**
       * <code>required .common.PlayerInfo player = 1;</code>
       *
       * <pre>
       * 玩家信息
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo, com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.Builder, com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfoOrBuilder> 
          getPlayerFieldBuilder() {
        if (playerBuilder_ == null) {
          playerBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo, com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfo.Builder, com.kuaikai.game.common.msg.pb.PlayerInfoPB.PlayerInfoOrBuilder>(
                  player_,
                  getParentForChildren(),
                  isClean());
          player_ = null;
        }
        return playerBuilder_;
      }

      // required .common.DeskUniq uniq = 2;
      private com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder> uniqBuilder_;
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public boolean hasUniq() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq getUniq() {
        if (uniqBuilder_ == null) {
          return uniq_;
        } else {
          return uniqBuilder_.getMessage();
        }
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public Builder setUniq(com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq value) {
        if (uniqBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          uniq_ = value;
          onChanged();
        } else {
          uniqBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public Builder setUniq(
          com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder builderForValue) {
        if (uniqBuilder_ == null) {
          uniq_ = builderForValue.build();
          onChanged();
        } else {
          uniqBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public Builder mergeUniq(com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq value) {
        if (uniqBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002) &&
              uniq_ != com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance()) {
            uniq_ =
              com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.newBuilder(uniq_).mergeFrom(value).buildPartial();
          } else {
            uniq_ = value;
          }
          onChanged();
        } else {
          uniqBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public Builder clearUniq() {
        if (uniqBuilder_ == null) {
          uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
          onChanged();
        } else {
          uniqBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder getUniqBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getUniqFieldBuilder().getBuilder();
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder getUniqOrBuilder() {
        if (uniqBuilder_ != null) {
          return uniqBuilder_.getMessageOrBuilder();
        } else {
          return uniq_;
        }
      }
      /**
       * <code>required .common.DeskUniq uniq = 2;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder> 
          getUniqFieldBuilder() {
        if (uniqBuilder_ == null) {
          uniqBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder>(
                  uniq_,
                  getParentForChildren(),
                  isClean());
          uniq_ = null;
        }
        return uniqBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:hall.SPlayerJoin)
    }

    static {
      defaultInstance = new SPlayerJoin(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:hall.SPlayerJoin)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_hall_SPlayerJoin_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_hall_SPlayerJoin_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026hall/SPlayerJoin.proto\022\004hall\032\027common/P" +
      "layerInfo.proto\032\025common/DeskUniq.proto\"Q" +
      "\n\013SPlayerJoin\022\"\n\006player\030\001 \002(\0132\022.common.P" +
      "layerInfo\022\036\n\004uniq\030\002 \002(\0132\020.common.DeskUni" +
      "qB-\n\034com.kuaikai.game.hall.msg.pbB\rSPlay" +
      "erJoinPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_hall_SPlayerJoin_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_hall_SPlayerJoin_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_hall_SPlayerJoin_descriptor,
              new java.lang.String[] { "Player", "Uniq", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.common.msg.pb.PlayerInfoPB.getDescriptor(),
          com.kuaikai.game.common.msg.pb.DeskUniqPB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}