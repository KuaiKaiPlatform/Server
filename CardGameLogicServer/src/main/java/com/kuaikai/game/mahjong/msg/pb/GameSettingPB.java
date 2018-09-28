// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/GameSetting.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class GameSettingPB {
  private GameSettingPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GameSettingOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional int32 totalSet = 1;
    /**
     * <code>optional int32 totalSet = 1;</code>
     *
     * <pre>
     * 总局数
     * </pre>
     */
    boolean hasTotalSet();
    /**
     * <code>optional int32 totalSet = 1;</code>
     *
     * <pre>
     * 总局数
     * </pre>
     */
    int getTotalSet();

    // optional int32 totalQuan = 2;
    /**
     * <code>optional int32 totalQuan = 2;</code>
     *
     * <pre>
     * 总圈数
     * </pre>
     */
    boolean hasTotalQuan();
    /**
     * <code>optional int32 totalQuan = 2;</code>
     *
     * <pre>
     * 总圈数
     * </pre>
     */
    int getTotalQuan();

    // optional int32 totalDi = 3;
    /**
     * <code>optional int32 totalDi = 3;</code>
     *
     * <pre>
     * 总底数
     * </pre>
     */
    boolean hasTotalDi();
    /**
     * <code>optional int32 totalDi = 3;</code>
     *
     * <pre>
     * 总底数
     * </pre>
     */
    int getTotalDi();

    // optional int32 totalPlayer = 4;
    /**
     * <code>optional int32 totalPlayer = 4;</code>
     *
     * <pre>
     * 玩家人数
     * </pre>
     */
    boolean hasTotalPlayer();
    /**
     * <code>optional int32 totalPlayer = 4;</code>
     *
     * <pre>
     * 玩家人数
     * </pre>
     */
    int getTotalPlayer();
  }
  /**
   * Protobuf type {@code GameSetting}
   */
  public static final class GameSetting extends
      com.google.protobuf.GeneratedMessage
      implements GameSettingOrBuilder {
    // Use GameSetting.newBuilder() to construct.
    private GameSetting(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private GameSetting(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final GameSetting defaultInstance;
    public static GameSetting getDefaultInstance() {
      return defaultInstance;
    }

    public GameSetting getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private GameSetting(
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
              totalSet_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              totalQuan_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              totalDi_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              totalPlayer_ = input.readInt32();
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
      return com.kuaikai.game.mahjong.msg.pb.GameSettingPB.internal_static_GameSetting_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.mahjong.msg.pb.GameSettingPB.internal_static_GameSetting_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting.class, com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting.Builder.class);
    }

    public static com.google.protobuf.Parser<GameSetting> PARSER =
        new com.google.protobuf.AbstractParser<GameSetting>() {
      public GameSetting parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GameSetting(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<GameSetting> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional int32 totalSet = 1;
    public static final int TOTALSET_FIELD_NUMBER = 1;
    private int totalSet_;
    /**
     * <code>optional int32 totalSet = 1;</code>
     *
     * <pre>
     * 总局数
     * </pre>
     */
    public boolean hasTotalSet() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 totalSet = 1;</code>
     *
     * <pre>
     * 总局数
     * </pre>
     */
    public int getTotalSet() {
      return totalSet_;
    }

    // optional int32 totalQuan = 2;
    public static final int TOTALQUAN_FIELD_NUMBER = 2;
    private int totalQuan_;
    /**
     * <code>optional int32 totalQuan = 2;</code>
     *
     * <pre>
     * 总圈数
     * </pre>
     */
    public boolean hasTotalQuan() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 totalQuan = 2;</code>
     *
     * <pre>
     * 总圈数
     * </pre>
     */
    public int getTotalQuan() {
      return totalQuan_;
    }

    // optional int32 totalDi = 3;
    public static final int TOTALDI_FIELD_NUMBER = 3;
    private int totalDi_;
    /**
     * <code>optional int32 totalDi = 3;</code>
     *
     * <pre>
     * 总底数
     * </pre>
     */
    public boolean hasTotalDi() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 totalDi = 3;</code>
     *
     * <pre>
     * 总底数
     * </pre>
     */
    public int getTotalDi() {
      return totalDi_;
    }

    // optional int32 totalPlayer = 4;
    public static final int TOTALPLAYER_FIELD_NUMBER = 4;
    private int totalPlayer_;
    /**
     * <code>optional int32 totalPlayer = 4;</code>
     *
     * <pre>
     * 玩家人数
     * </pre>
     */
    public boolean hasTotalPlayer() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 totalPlayer = 4;</code>
     *
     * <pre>
     * 玩家人数
     * </pre>
     */
    public int getTotalPlayer() {
      return totalPlayer_;
    }

    private void initFields() {
      totalSet_ = 0;
      totalQuan_ = 0;
      totalDi_ = 0;
      totalPlayer_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, totalSet_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, totalQuan_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, totalDi_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, totalPlayer_);
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
          .computeInt32Size(1, totalSet_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, totalQuan_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, totalDi_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, totalPlayer_);
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

    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting prototype) {
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
     * Protobuf type {@code GameSetting}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSettingOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.mahjong.msg.pb.GameSettingPB.internal_static_GameSetting_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.mahjong.msg.pb.GameSettingPB.internal_static_GameSetting_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting.class, com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting.Builder.class);
      }

      // Construct using com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting.newBuilder()
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
        totalSet_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        totalQuan_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        totalDi_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        totalPlayer_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.mahjong.msg.pb.GameSettingPB.internal_static_GameSetting_descriptor;
      }

      public com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting getDefaultInstanceForType() {
        return com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting.getDefaultInstance();
      }

      public com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting build() {
        com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting buildPartial() {
        com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting result = new com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.totalSet_ = totalSet_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.totalQuan_ = totalQuan_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.totalDi_ = totalDi_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.totalPlayer_ = totalPlayer_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting) {
          return mergeFrom((com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting other) {
        if (other == com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting.getDefaultInstance()) return this;
        if (other.hasTotalSet()) {
          setTotalSet(other.getTotalSet());
        }
        if (other.hasTotalQuan()) {
          setTotalQuan(other.getTotalQuan());
        }
        if (other.hasTotalDi()) {
          setTotalDi(other.getTotalDi());
        }
        if (other.hasTotalPlayer()) {
          setTotalPlayer(other.getTotalPlayer());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.mahjong.msg.pb.GameSettingPB.GameSetting) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional int32 totalSet = 1;
      private int totalSet_ ;
      /**
       * <code>optional int32 totalSet = 1;</code>
       *
       * <pre>
       * 总局数
       * </pre>
       */
      public boolean hasTotalSet() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 totalSet = 1;</code>
       *
       * <pre>
       * 总局数
       * </pre>
       */
      public int getTotalSet() {
        return totalSet_;
      }
      /**
       * <code>optional int32 totalSet = 1;</code>
       *
       * <pre>
       * 总局数
       * </pre>
       */
      public Builder setTotalSet(int value) {
        bitField0_ |= 0x00000001;
        totalSet_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 totalSet = 1;</code>
       *
       * <pre>
       * 总局数
       * </pre>
       */
      public Builder clearTotalSet() {
        bitField0_ = (bitField0_ & ~0x00000001);
        totalSet_ = 0;
        onChanged();
        return this;
      }

      // optional int32 totalQuan = 2;
      private int totalQuan_ ;
      /**
       * <code>optional int32 totalQuan = 2;</code>
       *
       * <pre>
       * 总圈数
       * </pre>
       */
      public boolean hasTotalQuan() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 totalQuan = 2;</code>
       *
       * <pre>
       * 总圈数
       * </pre>
       */
      public int getTotalQuan() {
        return totalQuan_;
      }
      /**
       * <code>optional int32 totalQuan = 2;</code>
       *
       * <pre>
       * 总圈数
       * </pre>
       */
      public Builder setTotalQuan(int value) {
        bitField0_ |= 0x00000002;
        totalQuan_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 totalQuan = 2;</code>
       *
       * <pre>
       * 总圈数
       * </pre>
       */
      public Builder clearTotalQuan() {
        bitField0_ = (bitField0_ & ~0x00000002);
        totalQuan_ = 0;
        onChanged();
        return this;
      }

      // optional int32 totalDi = 3;
      private int totalDi_ ;
      /**
       * <code>optional int32 totalDi = 3;</code>
       *
       * <pre>
       * 总底数
       * </pre>
       */
      public boolean hasTotalDi() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 totalDi = 3;</code>
       *
       * <pre>
       * 总底数
       * </pre>
       */
      public int getTotalDi() {
        return totalDi_;
      }
      /**
       * <code>optional int32 totalDi = 3;</code>
       *
       * <pre>
       * 总底数
       * </pre>
       */
      public Builder setTotalDi(int value) {
        bitField0_ |= 0x00000004;
        totalDi_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 totalDi = 3;</code>
       *
       * <pre>
       * 总底数
       * </pre>
       */
      public Builder clearTotalDi() {
        bitField0_ = (bitField0_ & ~0x00000004);
        totalDi_ = 0;
        onChanged();
        return this;
      }

      // optional int32 totalPlayer = 4;
      private int totalPlayer_ ;
      /**
       * <code>optional int32 totalPlayer = 4;</code>
       *
       * <pre>
       * 玩家人数
       * </pre>
       */
      public boolean hasTotalPlayer() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 totalPlayer = 4;</code>
       *
       * <pre>
       * 玩家人数
       * </pre>
       */
      public int getTotalPlayer() {
        return totalPlayer_;
      }
      /**
       * <code>optional int32 totalPlayer = 4;</code>
       *
       * <pre>
       * 玩家人数
       * </pre>
       */
      public Builder setTotalPlayer(int value) {
        bitField0_ |= 0x00000008;
        totalPlayer_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 totalPlayer = 4;</code>
       *
       * <pre>
       * 玩家人数
       * </pre>
       */
      public Builder clearTotalPlayer() {
        bitField0_ = (bitField0_ & ~0x00000008);
        totalPlayer_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:GameSetting)
    }

    static {
      defaultInstance = new GameSetting(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:GameSetting)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_GameSetting_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_GameSetting_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031mahjong/GameSetting.proto\"X\n\013GameSetti" +
      "ng\022\020\n\010totalSet\030\001 \001(\005\022\021\n\ttotalQuan\030\002 \001(\005\022" +
      "\017\n\007totalDi\030\003 \001(\005\022\023\n\013totalPlayer\030\004 \001(\005B0\n" +
      "\037com.kuaikai.game.mahjong.msg.pbB\rGameSe" +
      "ttingPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_GameSetting_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_GameSetting_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_GameSetting_descriptor,
              new java.lang.String[] { "TotalSet", "TotalQuan", "TotalDi", "TotalPlayer", });
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
