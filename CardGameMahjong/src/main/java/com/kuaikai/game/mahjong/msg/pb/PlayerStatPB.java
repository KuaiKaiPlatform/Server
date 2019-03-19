// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/PlayerStat.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class PlayerStatPB {
  private PlayerStatPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PlayerStatOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .mahjong.PlayerStatType statType = 1;
    /**
     * <code>required .mahjong.PlayerStatType statType = 1;</code>
     *
     * <pre>
     * 统计项
     * </pre>
     */
    boolean hasStatType();
    /**
     * <code>required .mahjong.PlayerStatType statType = 1;</code>
     *
     * <pre>
     * 统计项
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType getStatType();

    // optional int32 val = 2;
    /**
     * <code>optional int32 val = 2;</code>
     *
     * <pre>
     * 统计值
     * </pre>
     */
    boolean hasVal();
    /**
     * <code>optional int32 val = 2;</code>
     *
     * <pre>
     * 统计值
     * </pre>
     */
    int getVal();
  }
  /**
   * Protobuf type {@code mahjong.PlayerStat}
   *
   * <pre>
   * 玩家统计
   * </pre>
   */
  public static final class PlayerStat extends
      com.google.protobuf.GeneratedMessage
      implements PlayerStatOrBuilder {
    // Use PlayerStat.newBuilder() to construct.
    private PlayerStat(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerStat(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerStat defaultInstance;
    public static PlayerStat getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerStat getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerStat(
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
              com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType value = com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                statType_ = value;
              }
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              val_ = input.readInt32();
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
      return com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.internal_static_mahjong_PlayerStat_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.internal_static_mahjong_PlayerStat_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat.class, com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerStat> PARSER =
        new com.google.protobuf.AbstractParser<PlayerStat>() {
      public PlayerStat parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerStat(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerStat> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .mahjong.PlayerStatType statType = 1;
    public static final int STATTYPE_FIELD_NUMBER = 1;
    private com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType statType_;
    /**
     * <code>required .mahjong.PlayerStatType statType = 1;</code>
     *
     * <pre>
     * 统计项
     * </pre>
     */
    public boolean hasStatType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .mahjong.PlayerStatType statType = 1;</code>
     *
     * <pre>
     * 统计项
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType getStatType() {
      return statType_;
    }

    // optional int32 val = 2;
    public static final int VAL_FIELD_NUMBER = 2;
    private int val_;
    /**
     * <code>optional int32 val = 2;</code>
     *
     * <pre>
     * 统计值
     * </pre>
     */
    public boolean hasVal() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 val = 2;</code>
     *
     * <pre>
     * 统计值
     * </pre>
     */
    public int getVal() {
      return val_;
    }

    private void initFields() {
      statType_ = com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType.ZI_MO;
      val_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasStatType()) {
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
        output.writeEnum(1, statType_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, val_);
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
          .computeEnumSize(1, statType_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, val_);
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

    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat prototype) {
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
     * Protobuf type {@code mahjong.PlayerStat}
     *
     * <pre>
     * 玩家统计
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStatOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.internal_static_mahjong_PlayerStat_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.internal_static_mahjong_PlayerStat_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat.class, com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat.Builder.class);
      }

      // Construct using com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat.newBuilder()
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
        statType_ = com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType.ZI_MO;
        bitField0_ = (bitField0_ & ~0x00000001);
        val_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.internal_static_mahjong_PlayerStat_descriptor;
      }

      public com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat getDefaultInstanceForType() {
        return com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat.getDefaultInstance();
      }

      public com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat build() {
        com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat buildPartial() {
        com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat result = new com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.statType_ = statType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.val_ = val_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat) {
          return mergeFrom((com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat other) {
        if (other == com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat.getDefaultInstance()) return this;
        if (other.hasStatType()) {
          setStatType(other.getStatType());
        }
        if (other.hasVal()) {
          setVal(other.getVal());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasStatType()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.mahjong.msg.pb.PlayerStatPB.PlayerStat) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .mahjong.PlayerStatType statType = 1;
      private com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType statType_ = com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType.ZI_MO;
      /**
       * <code>required .mahjong.PlayerStatType statType = 1;</code>
       *
       * <pre>
       * 统计项
       * </pre>
       */
      public boolean hasStatType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .mahjong.PlayerStatType statType = 1;</code>
       *
       * <pre>
       * 统计项
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType getStatType() {
        return statType_;
      }
      /**
       * <code>required .mahjong.PlayerStatType statType = 1;</code>
       *
       * <pre>
       * 统计项
       * </pre>
       */
      public Builder setStatType(com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        statType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .mahjong.PlayerStatType statType = 1;</code>
       *
       * <pre>
       * 统计项
       * </pre>
       */
      public Builder clearStatType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        statType_ = com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType.ZI_MO;
        onChanged();
        return this;
      }

      // optional int32 val = 2;
      private int val_ ;
      /**
       * <code>optional int32 val = 2;</code>
       *
       * <pre>
       * 统计值
       * </pre>
       */
      public boolean hasVal() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 val = 2;</code>
       *
       * <pre>
       * 统计值
       * </pre>
       */
      public int getVal() {
        return val_;
      }
      /**
       * <code>optional int32 val = 2;</code>
       *
       * <pre>
       * 统计值
       * </pre>
       */
      public Builder setVal(int value) {
        bitField0_ |= 0x00000002;
        val_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 val = 2;</code>
       *
       * <pre>
       * 统计值
       * </pre>
       */
      public Builder clearVal() {
        bitField0_ = (bitField0_ & ~0x00000002);
        val_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:mahjong.PlayerStat)
    }

    static {
      defaultInstance = new PlayerStat(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:mahjong.PlayerStat)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_mahjong_PlayerStat_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_mahjong_PlayerStat_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030mahjong/PlayerStat.proto\022\007mahjong\032\034mah" +
      "jong/PlayerStatType.proto\"D\n\nPlayerStat\022" +
      ")\n\010statType\030\001 \002(\0162\027.mahjong.PlayerStatTy" +
      "pe\022\013\n\003val\030\002 \001(\005B/\n\037com.kuaikai.game.mahj" +
      "ong.msg.pbB\014PlayerStatPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_mahjong_PlayerStat_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_mahjong_PlayerStat_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_mahjong_PlayerStat_descriptor,
              new java.lang.String[] { "StatType", "Val", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
