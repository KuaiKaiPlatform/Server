// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/PlayerBasicInfo.proto

package com.kuaikai.game.common.msg.pb;

public final class PlayerBasicInfoPB {
  private PlayerBasicInfoPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PlayerBasicInfoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 uid = 1;
    /**
     * <code>required int32 uid = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    boolean hasUid();
    /**
     * <code>required int32 uid = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    int getUid();

    // required string nkn = 2;
    /**
     * <code>required string nkn = 2;</code>
     *
     * <pre>
     * 玩家昵称
     * </pre>
     */
    boolean hasNkn();
    /**
     * <code>required string nkn = 2;</code>
     *
     * <pre>
     * 玩家昵称
     * </pre>
     */
    java.lang.String getNkn();
    /**
     * <code>required string nkn = 2;</code>
     *
     * <pre>
     * 玩家昵称
     * </pre>
     */
    com.google.protobuf.ByteString
        getNknBytes();

    // optional string head = 3;
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 玩家头像
     * </pre>
     */
    boolean hasHead();
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 玩家头像
     * </pre>
     */
    java.lang.String getHead();
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 玩家头像
     * </pre>
     */
    com.google.protobuf.ByteString
        getHeadBytes();

    // optional int32 sex = 4;
    /**
     * <code>optional int32 sex = 4;</code>
     *
     * <pre>
     * 玩家性别
     * </pre>
     */
    boolean hasSex();
    /**
     * <code>optional int32 sex = 4;</code>
     *
     * <pre>
     * 玩家性别
     * </pre>
     */
    int getSex();

    // optional string ip = 5;
    /**
     * <code>optional string ip = 5;</code>
     *
     * <pre>
     * 玩家IP地址
     * </pre>
     */
    boolean hasIp();
    /**
     * <code>optional string ip = 5;</code>
     *
     * <pre>
     * 玩家IP地址
     * </pre>
     */
    java.lang.String getIp();
    /**
     * <code>optional string ip = 5;</code>
     *
     * <pre>
     * 玩家IP地址
     * </pre>
     */
    com.google.protobuf.ByteString
        getIpBytes();
  }
  /**
   * Protobuf type {@code PlayerBasicInfo}
   */
  public static final class PlayerBasicInfo extends
      com.google.protobuf.GeneratedMessage
      implements PlayerBasicInfoOrBuilder {
    // Use PlayerBasicInfo.newBuilder() to construct.
    private PlayerBasicInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerBasicInfo(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerBasicInfo defaultInstance;
    public static PlayerBasicInfo getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerBasicInfo getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerBasicInfo(
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
              bitField0_ |= 0x00000001;
              uid_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              nkn_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              head_ = input.readBytes();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              sex_ = input.readInt32();
              break;
            }
            case 42: {
              bitField0_ |= 0x00000010;
              ip_ = input.readBytes();
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
      return com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.internal_static_PlayerBasicInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.internal_static_PlayerBasicInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo.class, com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerBasicInfo> PARSER =
        new com.google.protobuf.AbstractParser<PlayerBasicInfo>() {
      public PlayerBasicInfo parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerBasicInfo(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerBasicInfo> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 uid = 1;
    public static final int UID_FIELD_NUMBER = 1;
    private int uid_;
    /**
     * <code>required int32 uid = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public boolean hasUid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 uid = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public int getUid() {
      return uid_;
    }

    // required string nkn = 2;
    public static final int NKN_FIELD_NUMBER = 2;
    private java.lang.Object nkn_;
    /**
     * <code>required string nkn = 2;</code>
     *
     * <pre>
     * 玩家昵称
     * </pre>
     */
    public boolean hasNkn() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string nkn = 2;</code>
     *
     * <pre>
     * 玩家昵称
     * </pre>
     */
    public java.lang.String getNkn() {
      java.lang.Object ref = nkn_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          nkn_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string nkn = 2;</code>
     *
     * <pre>
     * 玩家昵称
     * </pre>
     */
    public com.google.protobuf.ByteString
        getNknBytes() {
      java.lang.Object ref = nkn_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nkn_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string head = 3;
    public static final int HEAD_FIELD_NUMBER = 3;
    private java.lang.Object head_;
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 玩家头像
     * </pre>
     */
    public boolean hasHead() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 玩家头像
     * </pre>
     */
    public java.lang.String getHead() {
      java.lang.Object ref = head_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          head_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 玩家头像
     * </pre>
     */
    public com.google.protobuf.ByteString
        getHeadBytes() {
      java.lang.Object ref = head_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        head_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional int32 sex = 4;
    public static final int SEX_FIELD_NUMBER = 4;
    private int sex_;
    /**
     * <code>optional int32 sex = 4;</code>
     *
     * <pre>
     * 玩家性别
     * </pre>
     */
    public boolean hasSex() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 sex = 4;</code>
     *
     * <pre>
     * 玩家性别
     * </pre>
     */
    public int getSex() {
      return sex_;
    }

    // optional string ip = 5;
    public static final int IP_FIELD_NUMBER = 5;
    private java.lang.Object ip_;
    /**
     * <code>optional string ip = 5;</code>
     *
     * <pre>
     * 玩家IP地址
     * </pre>
     */
    public boolean hasIp() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional string ip = 5;</code>
     *
     * <pre>
     * 玩家IP地址
     * </pre>
     */
    public java.lang.String getIp() {
      java.lang.Object ref = ip_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          ip_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string ip = 5;</code>
     *
     * <pre>
     * 玩家IP地址
     * </pre>
     */
    public com.google.protobuf.ByteString
        getIpBytes() {
      java.lang.Object ref = ip_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        ip_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      uid_ = 0;
      nkn_ = "";
      head_ = "";
      sex_ = 0;
      ip_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasUid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasNkn()) {
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
        output.writeInt32(1, uid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getNknBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getHeadBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, sex_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getIpBytes());
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
          .computeInt32Size(1, uid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getNknBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getHeadBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, sex_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getIpBytes());
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

    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo prototype) {
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
     * Protobuf type {@code PlayerBasicInfo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.internal_static_PlayerBasicInfo_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.internal_static_PlayerBasicInfo_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo.class, com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo.Builder.class);
      }

      // Construct using com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo.newBuilder()
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
        uid_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        nkn_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        head_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        sex_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        ip_ = "";
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.internal_static_PlayerBasicInfo_descriptor;
      }

      public com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo getDefaultInstanceForType() {
        return com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo.getDefaultInstance();
      }

      public com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo build() {
        com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo buildPartial() {
        com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo result = new com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.uid_ = uid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.nkn_ = nkn_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.head_ = head_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.sex_ = sex_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.ip_ = ip_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo) {
          return mergeFrom((com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo other) {
        if (other == com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo.getDefaultInstance()) return this;
        if (other.hasUid()) {
          setUid(other.getUid());
        }
        if (other.hasNkn()) {
          bitField0_ |= 0x00000002;
          nkn_ = other.nkn_;
          onChanged();
        }
        if (other.hasHead()) {
          bitField0_ |= 0x00000004;
          head_ = other.head_;
          onChanged();
        }
        if (other.hasSex()) {
          setSex(other.getSex());
        }
        if (other.hasIp()) {
          bitField0_ |= 0x00000010;
          ip_ = other.ip_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUid()) {
          
          return false;
        }
        if (!hasNkn()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.common.msg.pb.PlayerBasicInfoPB.PlayerBasicInfo) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 uid = 1;
      private int uid_ ;
      /**
       * <code>required int32 uid = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public boolean hasUid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 uid = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public int getUid() {
        return uid_;
      }
      /**
       * <code>required int32 uid = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder setUid(int value) {
        bitField0_ |= 0x00000001;
        uid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 uid = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder clearUid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        uid_ = 0;
        onChanged();
        return this;
      }

      // required string nkn = 2;
      private java.lang.Object nkn_ = "";
      /**
       * <code>required string nkn = 2;</code>
       *
       * <pre>
       * 玩家昵称
       * </pre>
       */
      public boolean hasNkn() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string nkn = 2;</code>
       *
       * <pre>
       * 玩家昵称
       * </pre>
       */
      public java.lang.String getNkn() {
        java.lang.Object ref = nkn_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          nkn_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string nkn = 2;</code>
       *
       * <pre>
       * 玩家昵称
       * </pre>
       */
      public com.google.protobuf.ByteString
          getNknBytes() {
        java.lang.Object ref = nkn_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          nkn_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string nkn = 2;</code>
       *
       * <pre>
       * 玩家昵称
       * </pre>
       */
      public Builder setNkn(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        nkn_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string nkn = 2;</code>
       *
       * <pre>
       * 玩家昵称
       * </pre>
       */
      public Builder clearNkn() {
        bitField0_ = (bitField0_ & ~0x00000002);
        nkn_ = getDefaultInstance().getNkn();
        onChanged();
        return this;
      }
      /**
       * <code>required string nkn = 2;</code>
       *
       * <pre>
       * 玩家昵称
       * </pre>
       */
      public Builder setNknBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        nkn_ = value;
        onChanged();
        return this;
      }

      // optional string head = 3;
      private java.lang.Object head_ = "";
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 玩家头像
       * </pre>
       */
      public boolean hasHead() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 玩家头像
       * </pre>
       */
      public java.lang.String getHead() {
        java.lang.Object ref = head_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          head_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 玩家头像
       * </pre>
       */
      public com.google.protobuf.ByteString
          getHeadBytes() {
        java.lang.Object ref = head_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          head_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 玩家头像
       * </pre>
       */
      public Builder setHead(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        head_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 玩家头像
       * </pre>
       */
      public Builder clearHead() {
        bitField0_ = (bitField0_ & ~0x00000004);
        head_ = getDefaultInstance().getHead();
        onChanged();
        return this;
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 玩家头像
       * </pre>
       */
      public Builder setHeadBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        head_ = value;
        onChanged();
        return this;
      }

      // optional int32 sex = 4;
      private int sex_ ;
      /**
       * <code>optional int32 sex = 4;</code>
       *
       * <pre>
       * 玩家性别
       * </pre>
       */
      public boolean hasSex() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 sex = 4;</code>
       *
       * <pre>
       * 玩家性别
       * </pre>
       */
      public int getSex() {
        return sex_;
      }
      /**
       * <code>optional int32 sex = 4;</code>
       *
       * <pre>
       * 玩家性别
       * </pre>
       */
      public Builder setSex(int value) {
        bitField0_ |= 0x00000008;
        sex_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 sex = 4;</code>
       *
       * <pre>
       * 玩家性别
       * </pre>
       */
      public Builder clearSex() {
        bitField0_ = (bitField0_ & ~0x00000008);
        sex_ = 0;
        onChanged();
        return this;
      }

      // optional string ip = 5;
      private java.lang.Object ip_ = "";
      /**
       * <code>optional string ip = 5;</code>
       *
       * <pre>
       * 玩家IP地址
       * </pre>
       */
      public boolean hasIp() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional string ip = 5;</code>
       *
       * <pre>
       * 玩家IP地址
       * </pre>
       */
      public java.lang.String getIp() {
        java.lang.Object ref = ip_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          ip_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string ip = 5;</code>
       *
       * <pre>
       * 玩家IP地址
       * </pre>
       */
      public com.google.protobuf.ByteString
          getIpBytes() {
        java.lang.Object ref = ip_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          ip_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string ip = 5;</code>
       *
       * <pre>
       * 玩家IP地址
       * </pre>
       */
      public Builder setIp(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        ip_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string ip = 5;</code>
       *
       * <pre>
       * 玩家IP地址
       * </pre>
       */
      public Builder clearIp() {
        bitField0_ = (bitField0_ & ~0x00000010);
        ip_ = getDefaultInstance().getIp();
        onChanged();
        return this;
      }
      /**
       * <code>optional string ip = 5;</code>
       *
       * <pre>
       * 玩家IP地址
       * </pre>
       */
      public Builder setIpBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        ip_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PlayerBasicInfo)
    }

    static {
      defaultInstance = new PlayerBasicInfo(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PlayerBasicInfo)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_PlayerBasicInfo_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PlayerBasicInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035account/PlayerBasicInfo.proto\"R\n\017Playe" +
      "rBasicInfo\022\013\n\003uid\030\001 \002(\005\022\013\n\003nkn\030\002 \002(\t\022\014\n\004" +
      "head\030\003 \001(\t\022\013\n\003sex\030\004 \001(\005\022\n\n\002ip\030\005 \001(\tB3\n\036c" +
      "om.kuaikai.game.common.msg.pbB\021PlayerBas" +
      "icInfoPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_PlayerBasicInfo_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_PlayerBasicInfo_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_PlayerBasicInfo_descriptor,
              new java.lang.String[] { "Uid", "Nkn", "Head", "Sex", "Ip", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
