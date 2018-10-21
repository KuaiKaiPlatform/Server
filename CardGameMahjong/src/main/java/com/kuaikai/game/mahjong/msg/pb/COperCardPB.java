// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/COperCard.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class COperCardPB {
  private COperCardPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface COperCardOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .mahjong.OperDetail operDetail = 1;
    /**
     * <code>required .mahjong.OperDetail operDetail = 1;</code>
     *
     * <pre>
     * 操作细节
     * </pre>
     */
    boolean hasOperDetail();
    /**
     * <code>required .mahjong.OperDetail operDetail = 1;</code>
     *
     * <pre>
     * 操作细节
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail getOperDetail();
    /**
     * <code>required .mahjong.OperDetail operDetail = 1;</code>
     *
     * <pre>
     * 操作细节
     * </pre>
     */
    com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetailOrBuilder getOperDetailOrBuilder();
  }
  /**
   * Protobuf type {@code mahjong.COperCard}
   *
   * <pre>
   * 玩家请求操作
   * </pre>
   */
  public static final class COperCard extends
      com.google.protobuf.GeneratedMessage
      implements COperCardOrBuilder {
    // Use COperCard.newBuilder() to construct.
    private COperCard(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private COperCard(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final COperCard defaultInstance;
    public static COperCard getDefaultInstance() {
      return defaultInstance;
    }

    public COperCard getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private COperCard(
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
              com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = operDetail_.toBuilder();
              }
              operDetail_ = input.readMessage(com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(operDetail_);
                operDetail_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
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
      return com.kuaikai.game.mahjong.msg.pb.COperCardPB.internal_static_mahjong_COperCard_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.mahjong.msg.pb.COperCardPB.internal_static_mahjong_COperCard_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard.class, com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard.Builder.class);
    }

    public static com.google.protobuf.Parser<COperCard> PARSER =
        new com.google.protobuf.AbstractParser<COperCard>() {
      public COperCard parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new COperCard(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<COperCard> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .mahjong.OperDetail operDetail = 1;
    public static final int OPERDETAIL_FIELD_NUMBER = 1;
    private com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail operDetail_;
    /**
     * <code>required .mahjong.OperDetail operDetail = 1;</code>
     *
     * <pre>
     * 操作细节
     * </pre>
     */
    public boolean hasOperDetail() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .mahjong.OperDetail operDetail = 1;</code>
     *
     * <pre>
     * 操作细节
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail getOperDetail() {
      return operDetail_;
    }
    /**
     * <code>required .mahjong.OperDetail operDetail = 1;</code>
     *
     * <pre>
     * 操作细节
     * </pre>
     */
    public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetailOrBuilder getOperDetailOrBuilder() {
      return operDetail_;
    }

    private void initFields() {
      operDetail_ = com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasOperDetail()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getOperDetail().isInitialized()) {
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
        output.writeMessage(1, operDetail_);
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
          .computeMessageSize(1, operDetail_);
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

    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard prototype) {
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
     * Protobuf type {@code mahjong.COperCard}
     *
     * <pre>
     * 玩家请求操作
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCardOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.mahjong.msg.pb.COperCardPB.internal_static_mahjong_COperCard_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.mahjong.msg.pb.COperCardPB.internal_static_mahjong_COperCard_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard.class, com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard.Builder.class);
      }

      // Construct using com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard.newBuilder()
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
          getOperDetailFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (operDetailBuilder_ == null) {
          operDetail_ = com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.getDefaultInstance();
        } else {
          operDetailBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.mahjong.msg.pb.COperCardPB.internal_static_mahjong_COperCard_descriptor;
      }

      public com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard getDefaultInstanceForType() {
        return com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard.getDefaultInstance();
      }

      public com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard build() {
        com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard buildPartial() {
        com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard result = new com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (operDetailBuilder_ == null) {
          result.operDetail_ = operDetail_;
        } else {
          result.operDetail_ = operDetailBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard) {
          return mergeFrom((com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard other) {
        if (other == com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard.getDefaultInstance()) return this;
        if (other.hasOperDetail()) {
          mergeOperDetail(other.getOperDetail());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasOperDetail()) {
          
          return false;
        }
        if (!getOperDetail().isInitialized()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .mahjong.OperDetail operDetail = 1;
      private com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail operDetail_ = com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetailOrBuilder> operDetailBuilder_;
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public boolean hasOperDetail() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail getOperDetail() {
        if (operDetailBuilder_ == null) {
          return operDetail_;
        } else {
          return operDetailBuilder_.getMessage();
        }
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public Builder setOperDetail(com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail value) {
        if (operDetailBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          operDetail_ = value;
          onChanged();
        } else {
          operDetailBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public Builder setOperDetail(
          com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder builderForValue) {
        if (operDetailBuilder_ == null) {
          operDetail_ = builderForValue.build();
          onChanged();
        } else {
          operDetailBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public Builder mergeOperDetail(com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail value) {
        if (operDetailBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              operDetail_ != com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.getDefaultInstance()) {
            operDetail_ =
              com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.newBuilder(operDetail_).mergeFrom(value).buildPartial();
          } else {
            operDetail_ = value;
          }
          onChanged();
        } else {
          operDetailBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public Builder clearOperDetail() {
        if (operDetailBuilder_ == null) {
          operDetail_ = com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.getDefaultInstance();
          onChanged();
        } else {
          operDetailBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder getOperDetailBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getOperDetailFieldBuilder().getBuilder();
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      public com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetailOrBuilder getOperDetailOrBuilder() {
        if (operDetailBuilder_ != null) {
          return operDetailBuilder_.getMessageOrBuilder();
        } else {
          return operDetail_;
        }
      }
      /**
       * <code>required .mahjong.OperDetail operDetail = 1;</code>
       *
       * <pre>
       * 操作细节
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetailOrBuilder> 
          getOperDetailFieldBuilder() {
        if (operDetailBuilder_ == null) {
          operDetailBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetail.Builder, com.kuaikai.game.mahjong.msg.pb.OperDetailPB.OperDetailOrBuilder>(
                  operDetail_,
                  getParentForChildren(),
                  isClean());
          operDetail_ = null;
        }
        return operDetailBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:mahjong.COperCard)
    }

    static {
      defaultInstance = new COperCard(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:mahjong.COperCard)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_mahjong_COperCard_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_mahjong_COperCard_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027mahjong/COperCard.proto\022\007mahjong\032\030mahj" +
      "ong/OperDetail.proto\"4\n\tCOperCard\022\'\n\nope" +
      "rDetail\030\001 \002(\0132\023.mahjong.OperDetailB.\n\037co" +
      "m.kuaikai.game.mahjong.msg.pbB\013COperCard" +
      "PB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_mahjong_COperCard_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_mahjong_COperCard_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_mahjong_COperCard_descriptor,
              new java.lang.String[] { "OperDetail", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.mahjong.msg.pb.OperDetailPB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
