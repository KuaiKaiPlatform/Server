// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hall/SPlayerQuit.proto

package com.kuaikai.game.hall.msg.pb;

public final class SPlayerQuitPB {
  private SPlayerQuitPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SPlayerQuitOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 uid = 1;
    /**
     * <code>required int32 uid = 1;</code>
     *
     * <pre>
     * 玩家ID
     * </pre>
     */
    boolean hasUid();
    /**
     * <code>required int32 uid = 1;</code>
     *
     * <pre>
     * 玩家ID
     * </pre>
     */
    int getUid();

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
   * Protobuf type {@code hall.SPlayerQuit}
   *
   * <pre>
   * 有玩家退出牌桌
   * </pre>
   */
  public static final class SPlayerQuit extends
      com.google.protobuf.GeneratedMessage
      implements SPlayerQuitOrBuilder {
    // Use SPlayerQuit.newBuilder() to construct.
    private SPlayerQuit(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SPlayerQuit(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SPlayerQuit defaultInstance;
    public static SPlayerQuit getDefaultInstance() {
      return defaultInstance;
    }

    public SPlayerQuit getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SPlayerQuit(
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
      return com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.internal_static_hall_SPlayerQuit_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.internal_static_hall_SPlayerQuit_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit.class, com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit.Builder.class);
    }

    public static com.google.protobuf.Parser<SPlayerQuit> PARSER =
        new com.google.protobuf.AbstractParser<SPlayerQuit>() {
      public SPlayerQuit parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SPlayerQuit(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SPlayerQuit> getParserForType() {
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
     * 玩家ID
     * </pre>
     */
    public boolean hasUid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 uid = 1;</code>
     *
     * <pre>
     * 玩家ID
     * </pre>
     */
    public int getUid() {
      return uid_;
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
      uid_ = 0;
      uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasUid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUniq()) {
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
        output.writeInt32(1, uid_);
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
          .computeInt32Size(1, uid_);
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

    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit prototype) {
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
     * Protobuf type {@code hall.SPlayerQuit}
     *
     * <pre>
     * 有玩家退出牌桌
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuitOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.internal_static_hall_SPlayerQuit_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.internal_static_hall_SPlayerQuit_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit.class, com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit.Builder.class);
      }

      // Construct using com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit.newBuilder()
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
          getUniqFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        uid_ = 0;
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
        return com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.internal_static_hall_SPlayerQuit_descriptor;
      }

      public com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit getDefaultInstanceForType() {
        return com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit.getDefaultInstance();
      }

      public com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit build() {
        com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit buildPartial() {
        com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit result = new com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.uid_ = uid_;
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
        if (other instanceof com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit) {
          return mergeFrom((com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit other) {
        if (other == com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit.getDefaultInstance()) return this;
        if (other.hasUid()) {
          setUid(other.getUid());
        }
        if (other.hasUniq()) {
          mergeUniq(other.getUniq());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUid()) {
          
          return false;
        }
        if (!hasUniq()) {
          
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
        com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.hall.msg.pb.SPlayerQuitPB.SPlayerQuit) e.getUnfinishedMessage();
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
       * 玩家ID
       * </pre>
       */
      public boolean hasUid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 uid = 1;</code>
       *
       * <pre>
       * 玩家ID
       * </pre>
       */
      public int getUid() {
        return uid_;
      }
      /**
       * <code>required int32 uid = 1;</code>
       *
       * <pre>
       * 玩家ID
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
       * 玩家ID
       * </pre>
       */
      public Builder clearUid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        uid_ = 0;
        onChanged();
        return this;
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

      // @@protoc_insertion_point(builder_scope:hall.SPlayerQuit)
    }

    static {
      defaultInstance = new SPlayerQuit(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:hall.SPlayerQuit)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_hall_SPlayerQuit_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_hall_SPlayerQuit_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026hall/SPlayerQuit.proto\022\004hall\032\025common/D" +
      "eskUniq.proto\":\n\013SPlayerQuit\022\013\n\003uid\030\001 \002(" +
      "\005\022\036\n\004uniq\030\002 \002(\0132\020.common.DeskUniqB-\n\034com" +
      ".kuaikai.game.hall.msg.pbB\rSPlayerQuitPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_hall_SPlayerQuit_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_hall_SPlayerQuit_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_hall_SPlayerQuit_descriptor,
              new java.lang.String[] { "Uid", "Uniq", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.common.msg.pb.DeskUniqPB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}