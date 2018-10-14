// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hall/CJoinDesk.proto

package com.kuaikai.game.hall.msg.pb;

public final class CJoinDeskPB {
  private CJoinDeskPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CJoinDeskOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .common.DeskUniq uniq = 1;
    /**
     * <code>required .common.DeskUniq uniq = 1;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    boolean hasUniq();
    /**
     * <code>required .common.DeskUniq uniq = 1;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq getUniq();
    /**
     * <code>required .common.DeskUniq uniq = 1;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder getUniqOrBuilder();
  }
  /**
   * Protobuf type {@code hall.CJoinDesk}
   *
   * <pre>
   * 玩家请求加入牌桌
   * </pre>
   */
  public static final class CJoinDesk extends
      com.google.protobuf.GeneratedMessage
      implements CJoinDeskOrBuilder {
    // Use CJoinDesk.newBuilder() to construct.
    private CJoinDesk(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CJoinDesk(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CJoinDesk defaultInstance;
    public static CJoinDesk getDefaultInstance() {
      return defaultInstance;
    }

    public CJoinDesk getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CJoinDesk(
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
              com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = uniq_.toBuilder();
              }
              uniq_ = input.readMessage(com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(uniq_);
                uniq_ = subBuilder.buildPartial();
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
      return com.kuaikai.game.hall.msg.pb.CJoinDeskPB.internal_static_hall_CJoinDesk_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.hall.msg.pb.CJoinDeskPB.internal_static_hall_CJoinDesk_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk.class, com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk.Builder.class);
    }

    public static com.google.protobuf.Parser<CJoinDesk> PARSER =
        new com.google.protobuf.AbstractParser<CJoinDesk>() {
      public CJoinDesk parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CJoinDesk(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CJoinDesk> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .common.DeskUniq uniq = 1;
    public static final int UNIQ_FIELD_NUMBER = 1;
    private com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq uniq_;
    /**
     * <code>required .common.DeskUniq uniq = 1;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    public boolean hasUniq() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .common.DeskUniq uniq = 1;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq getUniq() {
      return uniq_;
    }
    /**
     * <code>required .common.DeskUniq uniq = 1;</code>
     *
     * <pre>
     * 牌桌唯一标识
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder getUniqOrBuilder() {
      return uniq_;
    }

    private void initFields() {
      uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

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
        output.writeMessage(1, uniq_);
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
          .computeMessageSize(1, uniq_);
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

    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk prototype) {
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
     * Protobuf type {@code hall.CJoinDesk}
     *
     * <pre>
     * 玩家请求加入牌桌
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDeskOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.hall.msg.pb.CJoinDeskPB.internal_static_hall_CJoinDesk_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.hall.msg.pb.CJoinDeskPB.internal_static_hall_CJoinDesk_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk.class, com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk.Builder.class);
      }

      // Construct using com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk.newBuilder()
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
        if (uniqBuilder_ == null) {
          uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
        } else {
          uniqBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.hall.msg.pb.CJoinDeskPB.internal_static_hall_CJoinDesk_descriptor;
      }

      public com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk getDefaultInstanceForType() {
        return com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk.getDefaultInstance();
      }

      public com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk build() {
        com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk buildPartial() {
        com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk result = new com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
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
        if (other instanceof com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk) {
          return mergeFrom((com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk other) {
        if (other == com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk.getDefaultInstance()) return this;
        if (other.hasUniq()) {
          mergeUniq(other.getUniq());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
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
        com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.hall.msg.pb.CJoinDeskPB.CJoinDesk) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .common.DeskUniq uniq = 1;
      private com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq uniq_ = com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder, com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniqOrBuilder> uniqBuilder_;
      /**
       * <code>required .common.DeskUniq uniq = 1;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public boolean hasUniq() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .common.DeskUniq uniq = 1;</code>
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
       * <code>required .common.DeskUniq uniq = 1;</code>
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
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 1;</code>
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
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 1;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public Builder mergeUniq(com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq value) {
        if (uniqBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
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
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 1;</code>
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
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>required .common.DeskUniq uniq = 1;</code>
       *
       * <pre>
       * 牌桌唯一标识
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq.Builder getUniqBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getUniqFieldBuilder().getBuilder();
      }
      /**
       * <code>required .common.DeskUniq uniq = 1;</code>
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
       * <code>required .common.DeskUniq uniq = 1;</code>
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

      // @@protoc_insertion_point(builder_scope:hall.CJoinDesk)
    }

    static {
      defaultInstance = new CJoinDesk(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:hall.CJoinDesk)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_hall_CJoinDesk_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_hall_CJoinDesk_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024hall/CJoinDesk.proto\022\004hall\032\025common/Des" +
      "kUniq.proto\"+\n\tCJoinDesk\022\036\n\004uniq\030\001 \002(\0132\020" +
      ".common.DeskUniqB+\n\034com.kuaikai.game.hal" +
      "l.msg.pbB\013CJoinDeskPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_hall_CJoinDesk_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_hall_CJoinDesk_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_hall_CJoinDesk_descriptor,
              new java.lang.String[] { "Uniq", });
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
