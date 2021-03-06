// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hall/COffline.proto

package com.kuaikai.game.hall.msg.pb;

public final class COfflinePB {
  private COfflinePB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface COfflineOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required bool offline = 1;
    /**
     * <code>required bool offline = 1;</code>
     *
     * <pre>
     * 是否离线
     * </pre>
     */
    boolean hasOffline();
    /**
     * <code>required bool offline = 1;</code>
     *
     * <pre>
     * 是否离线
     * </pre>
     */
    boolean getOffline();
  }
  /**
   * Protobuf type {@code hall.COffline}
   *
   * <pre>
   * 玩家离线状态切换
   * </pre>
   */
  public static final class COffline extends
      com.google.protobuf.GeneratedMessage
      implements COfflineOrBuilder {
    // Use COffline.newBuilder() to construct.
    private COffline(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private COffline(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final COffline defaultInstance;
    public static COffline getDefaultInstance() {
      return defaultInstance;
    }

    public COffline getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private COffline(
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
              offline_ = input.readBool();
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
      return com.kuaikai.game.hall.msg.pb.COfflinePB.internal_static_hall_COffline_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.hall.msg.pb.COfflinePB.internal_static_hall_COffline_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.hall.msg.pb.COfflinePB.COffline.class, com.kuaikai.game.hall.msg.pb.COfflinePB.COffline.Builder.class);
    }

    public static com.google.protobuf.Parser<COffline> PARSER =
        new com.google.protobuf.AbstractParser<COffline>() {
      public COffline parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new COffline(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<COffline> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required bool offline = 1;
    public static final int OFFLINE_FIELD_NUMBER = 1;
    private boolean offline_;
    /**
     * <code>required bool offline = 1;</code>
     *
     * <pre>
     * 是否离线
     * </pre>
     */
    public boolean hasOffline() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required bool offline = 1;</code>
     *
     * <pre>
     * 是否离线
     * </pre>
     */
    public boolean getOffline() {
      return offline_;
    }

    private void initFields() {
      offline_ = false;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasOffline()) {
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
        output.writeBool(1, offline_);
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
          .computeBoolSize(1, offline_);
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

    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.hall.msg.pb.COfflinePB.COffline prototype) {
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
     * Protobuf type {@code hall.COffline}
     *
     * <pre>
     * 玩家离线状态切换
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.hall.msg.pb.COfflinePB.COfflineOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.hall.msg.pb.COfflinePB.internal_static_hall_COffline_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.hall.msg.pb.COfflinePB.internal_static_hall_COffline_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.hall.msg.pb.COfflinePB.COffline.class, com.kuaikai.game.hall.msg.pb.COfflinePB.COffline.Builder.class);
      }

      // Construct using com.kuaikai.game.hall.msg.pb.COfflinePB.COffline.newBuilder()
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
        offline_ = false;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.hall.msg.pb.COfflinePB.internal_static_hall_COffline_descriptor;
      }

      public com.kuaikai.game.hall.msg.pb.COfflinePB.COffline getDefaultInstanceForType() {
        return com.kuaikai.game.hall.msg.pb.COfflinePB.COffline.getDefaultInstance();
      }

      public com.kuaikai.game.hall.msg.pb.COfflinePB.COffline build() {
        com.kuaikai.game.hall.msg.pb.COfflinePB.COffline result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.hall.msg.pb.COfflinePB.COffline buildPartial() {
        com.kuaikai.game.hall.msg.pb.COfflinePB.COffline result = new com.kuaikai.game.hall.msg.pb.COfflinePB.COffline(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.offline_ = offline_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.hall.msg.pb.COfflinePB.COffline) {
          return mergeFrom((com.kuaikai.game.hall.msg.pb.COfflinePB.COffline)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.hall.msg.pb.COfflinePB.COffline other) {
        if (other == com.kuaikai.game.hall.msg.pb.COfflinePB.COffline.getDefaultInstance()) return this;
        if (other.hasOffline()) {
          setOffline(other.getOffline());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasOffline()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.hall.msg.pb.COfflinePB.COffline parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.hall.msg.pb.COfflinePB.COffline) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required bool offline = 1;
      private boolean offline_ ;
      /**
       * <code>required bool offline = 1;</code>
       *
       * <pre>
       * 是否离线
       * </pre>
       */
      public boolean hasOffline() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required bool offline = 1;</code>
       *
       * <pre>
       * 是否离线
       * </pre>
       */
      public boolean getOffline() {
        return offline_;
      }
      /**
       * <code>required bool offline = 1;</code>
       *
       * <pre>
       * 是否离线
       * </pre>
       */
      public Builder setOffline(boolean value) {
        bitField0_ |= 0x00000001;
        offline_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bool offline = 1;</code>
       *
       * <pre>
       * 是否离线
       * </pre>
       */
      public Builder clearOffline() {
        bitField0_ = (bitField0_ & ~0x00000001);
        offline_ = false;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:hall.COffline)
    }

    static {
      defaultInstance = new COffline(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:hall.COffline)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_hall_COffline_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_hall_COffline_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023hall/COffline.proto\022\004hall\"\033\n\010COffline\022" +
      "\017\n\007offline\030\001 \002(\010B*\n\034com.kuaikai.game.hal" +
      "l.msg.pbB\nCOfflinePB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_hall_COffline_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_hall_COffline_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_hall_COffline_descriptor,
              new java.lang.String[] { "Offline", });
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
