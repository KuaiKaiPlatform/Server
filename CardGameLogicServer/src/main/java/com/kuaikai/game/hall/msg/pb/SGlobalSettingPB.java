// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hall/SGlobalSetting.proto

package com.kuaikai.game.hall.msg.pb;

public final class SGlobalSettingPB {
  private SGlobalSettingPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SGlobalSettingOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .common.GlobalSetting setting = 1;
    /**
     * <code>required .common.GlobalSetting setting = 1;</code>
     *
     * <pre>
     * 全局设置
     * </pre>
     */
    boolean hasSetting();
    /**
     * <code>required .common.GlobalSetting setting = 1;</code>
     *
     * <pre>
     * 全局设置
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting getSetting();
    /**
     * <code>required .common.GlobalSetting setting = 1;</code>
     *
     * <pre>
     * 全局设置
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSettingOrBuilder getSettingOrBuilder();
  }
  /**
   * Protobuf type {@code hall.SGlobalSetting}
   *
   * <pre>
   * 全局设置
   * </pre>
   */
  public static final class SGlobalSetting extends
      com.google.protobuf.GeneratedMessage
      implements SGlobalSettingOrBuilder {
    // Use SGlobalSetting.newBuilder() to construct.
    private SGlobalSetting(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SGlobalSetting(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SGlobalSetting defaultInstance;
    public static SGlobalSetting getDefaultInstance() {
      return defaultInstance;
    }

    public SGlobalSetting getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SGlobalSetting(
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
              com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = setting_.toBuilder();
              }
              setting_ = input.readMessage(com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(setting_);
                setting_ = subBuilder.buildPartial();
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
      return com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.internal_static_hall_SGlobalSetting_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.internal_static_hall_SGlobalSetting_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting.class, com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting.Builder.class);
    }

    public static com.google.protobuf.Parser<SGlobalSetting> PARSER =
        new com.google.protobuf.AbstractParser<SGlobalSetting>() {
      public SGlobalSetting parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SGlobalSetting(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SGlobalSetting> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .common.GlobalSetting setting = 1;
    public static final int SETTING_FIELD_NUMBER = 1;
    private com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting setting_;
    /**
     * <code>required .common.GlobalSetting setting = 1;</code>
     *
     * <pre>
     * 全局设置
     * </pre>
     */
    public boolean hasSetting() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .common.GlobalSetting setting = 1;</code>
     *
     * <pre>
     * 全局设置
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting getSetting() {
      return setting_;
    }
    /**
     * <code>required .common.GlobalSetting setting = 1;</code>
     *
     * <pre>
     * 全局设置
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSettingOrBuilder getSettingOrBuilder() {
      return setting_;
    }

    private void initFields() {
      setting_ = com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasSetting()) {
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
        output.writeMessage(1, setting_);
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
          .computeMessageSize(1, setting_);
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

    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting prototype) {
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
     * Protobuf type {@code hall.SGlobalSetting}
     *
     * <pre>
     * 全局设置
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSettingOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.internal_static_hall_SGlobalSetting_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.internal_static_hall_SGlobalSetting_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting.class, com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting.Builder.class);
      }

      // Construct using com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting.newBuilder()
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
          getSettingFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (settingBuilder_ == null) {
          setting_ = com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.getDefaultInstance();
        } else {
          settingBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.internal_static_hall_SGlobalSetting_descriptor;
      }

      public com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting getDefaultInstanceForType() {
        return com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting.getDefaultInstance();
      }

      public com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting build() {
        com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting buildPartial() {
        com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting result = new com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (settingBuilder_ == null) {
          result.setting_ = setting_;
        } else {
          result.setting_ = settingBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting) {
          return mergeFrom((com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting other) {
        if (other == com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting.getDefaultInstance()) return this;
        if (other.hasSetting()) {
          mergeSetting(other.getSetting());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasSetting()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .common.GlobalSetting setting = 1;
      private com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting setting_ = com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting, com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.Builder, com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSettingOrBuilder> settingBuilder_;
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public boolean hasSetting() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting getSetting() {
        if (settingBuilder_ == null) {
          return setting_;
        } else {
          return settingBuilder_.getMessage();
        }
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public Builder setSetting(com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting value) {
        if (settingBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          setting_ = value;
          onChanged();
        } else {
          settingBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public Builder setSetting(
          com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.Builder builderForValue) {
        if (settingBuilder_ == null) {
          setting_ = builderForValue.build();
          onChanged();
        } else {
          settingBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public Builder mergeSetting(com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting value) {
        if (settingBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              setting_ != com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.getDefaultInstance()) {
            setting_ =
              com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.newBuilder(setting_).mergeFrom(value).buildPartial();
          } else {
            setting_ = value;
          }
          onChanged();
        } else {
          settingBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public Builder clearSetting() {
        if (settingBuilder_ == null) {
          setting_ = com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.getDefaultInstance();
          onChanged();
        } else {
          settingBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.Builder getSettingBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getSettingFieldBuilder().getBuilder();
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSettingOrBuilder getSettingOrBuilder() {
        if (settingBuilder_ != null) {
          return settingBuilder_.getMessageOrBuilder();
        } else {
          return setting_;
        }
      }
      /**
       * <code>required .common.GlobalSetting setting = 1;</code>
       *
       * <pre>
       * 全局设置
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting, com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.Builder, com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSettingOrBuilder> 
          getSettingFieldBuilder() {
        if (settingBuilder_ == null) {
          settingBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting, com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting.Builder, com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSettingOrBuilder>(
                  setting_,
                  getParentForChildren(),
                  isClean());
          setting_ = null;
        }
        return settingBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:hall.SGlobalSetting)
    }

    static {
      defaultInstance = new SGlobalSetting(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:hall.SGlobalSetting)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_hall_SGlobalSetting_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_hall_SGlobalSetting_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031hall/SGlobalSetting.proto\022\004hall\032\032commo" +
      "n/GlobalSetting.proto\"8\n\016SGlobalSetting\022" +
      "&\n\007setting\030\001 \002(\0132\025.common.GlobalSettingB" +
      "0\n\034com.kuaikai.game.hall.msg.pbB\020SGlobal" +
      "SettingPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_hall_SGlobalSetting_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_hall_SGlobalSetting_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_hall_SGlobalSetting_descriptor,
              new java.lang.String[] { "Setting", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.common.msg.pb.GlobalSettingPB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
