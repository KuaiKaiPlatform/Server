// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hall/SRuleDialects.proto

package com.kuaikai.game.hall.msg.pb;

public final class SRuleDialectsPB {
  private SRuleDialectsPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SRuleDialectsOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .common.GameRule rule = 1;
    /**
     * <code>required .common.GameRule rule = 1;</code>
     *
     * <pre>
     * 玩法编号
     * </pre>
     */
    boolean hasRule();
    /**
     * <code>required .common.GameRule rule = 1;</code>
     *
     * <pre>
     * 玩法编号
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.GameRulePB.GameRule getRule();

    // repeated .common.Dialect dialects = 2;
    /**
     * <code>repeated .common.Dialect dialects = 2;</code>
     *
     * <pre>
     * 方言列表
     * </pre>
     */
    java.util.List<com.kuaikai.game.common.msg.pb.DialectPB.Dialect> getDialectsList();
    /**
     * <code>repeated .common.Dialect dialects = 2;</code>
     *
     * <pre>
     * 方言列表
     * </pre>
     */
    int getDialectsCount();
    /**
     * <code>repeated .common.Dialect dialects = 2;</code>
     *
     * <pre>
     * 方言列表
     * </pre>
     */
    com.kuaikai.game.common.msg.pb.DialectPB.Dialect getDialects(int index);
  }
  /**
   * Protobuf type {@code hall.SRuleDialects}
   *
   * <pre>
   * 玩法使用的方言
   * </pre>
   */
  public static final class SRuleDialects extends
      com.google.protobuf.GeneratedMessage
      implements SRuleDialectsOrBuilder {
    // Use SRuleDialects.newBuilder() to construct.
    private SRuleDialects(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SRuleDialects(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SRuleDialects defaultInstance;
    public static SRuleDialects getDefaultInstance() {
      return defaultInstance;
    }

    public SRuleDialects getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SRuleDialects(
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
              com.kuaikai.game.common.msg.pb.GameRulePB.GameRule value = com.kuaikai.game.common.msg.pb.GameRulePB.GameRule.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                rule_ = value;
              }
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              com.kuaikai.game.common.msg.pb.DialectPB.Dialect value = com.kuaikai.game.common.msg.pb.DialectPB.Dialect.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                  dialects_ = new java.util.ArrayList<com.kuaikai.game.common.msg.pb.DialectPB.Dialect>();
                  mutable_bitField0_ |= 0x00000002;
                }
                dialects_.add(value);
              }
              break;
            }
            case 18: {
              int length = input.readRawVarint32();
              int oldLimit = input.pushLimit(length);
              while(input.getBytesUntilLimit() > 0) {
                int rawValue = input.readEnum();
                com.kuaikai.game.common.msg.pb.DialectPB.Dialect value = com.kuaikai.game.common.msg.pb.DialectPB.Dialect.valueOf(rawValue);
                if (value == null) {
                  unknownFields.mergeVarintField(2, rawValue);
                } else {
                  if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                    dialects_ = new java.util.ArrayList<com.kuaikai.game.common.msg.pb.DialectPB.Dialect>();
                    mutable_bitField0_ |= 0x00000002;
                  }
                  dialects_.add(value);
                }
              }
              input.popLimit(oldLimit);
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
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          dialects_ = java.util.Collections.unmodifiableList(dialects_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.internal_static_hall_SRuleDialects_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.internal_static_hall_SRuleDialects_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects.class, com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects.Builder.class);
    }

    public static com.google.protobuf.Parser<SRuleDialects> PARSER =
        new com.google.protobuf.AbstractParser<SRuleDialects>() {
      public SRuleDialects parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SRuleDialects(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SRuleDialects> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required .common.GameRule rule = 1;
    public static final int RULE_FIELD_NUMBER = 1;
    private com.kuaikai.game.common.msg.pb.GameRulePB.GameRule rule_;
    /**
     * <code>required .common.GameRule rule = 1;</code>
     *
     * <pre>
     * 玩法编号
     * </pre>
     */
    public boolean hasRule() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .common.GameRule rule = 1;</code>
     *
     * <pre>
     * 玩法编号
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.GameRulePB.GameRule getRule() {
      return rule_;
    }

    // repeated .common.Dialect dialects = 2;
    public static final int DIALECTS_FIELD_NUMBER = 2;
    private java.util.List<com.kuaikai.game.common.msg.pb.DialectPB.Dialect> dialects_;
    /**
     * <code>repeated .common.Dialect dialects = 2;</code>
     *
     * <pre>
     * 方言列表
     * </pre>
     */
    public java.util.List<com.kuaikai.game.common.msg.pb.DialectPB.Dialect> getDialectsList() {
      return dialects_;
    }
    /**
     * <code>repeated .common.Dialect dialects = 2;</code>
     *
     * <pre>
     * 方言列表
     * </pre>
     */
    public int getDialectsCount() {
      return dialects_.size();
    }
    /**
     * <code>repeated .common.Dialect dialects = 2;</code>
     *
     * <pre>
     * 方言列表
     * </pre>
     */
    public com.kuaikai.game.common.msg.pb.DialectPB.Dialect getDialects(int index) {
      return dialects_.get(index);
    }

    private void initFields() {
      rule_ = com.kuaikai.game.common.msg.pb.GameRulePB.GameRule.JIN_HUA;
      dialects_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasRule()) {
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
        output.writeEnum(1, rule_.getNumber());
      }
      for (int i = 0; i < dialects_.size(); i++) {
        output.writeEnum(2, dialects_.get(i).getNumber());
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
          .computeEnumSize(1, rule_.getNumber());
      }
      {
        int dataSize = 0;
        for (int i = 0; i < dialects_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeEnumSizeNoTag(dialects_.get(i).getNumber());
        }
        size += dataSize;
        size += 1 * dialects_.size();
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

    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects prototype) {
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
     * Protobuf type {@code hall.SRuleDialects}
     *
     * <pre>
     * 玩法使用的方言
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialectsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.internal_static_hall_SRuleDialects_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.internal_static_hall_SRuleDialects_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects.class, com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects.Builder.class);
      }

      // Construct using com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects.newBuilder()
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
        rule_ = com.kuaikai.game.common.msg.pb.GameRulePB.GameRule.JIN_HUA;
        bitField0_ = (bitField0_ & ~0x00000001);
        dialects_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.internal_static_hall_SRuleDialects_descriptor;
      }

      public com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects getDefaultInstanceForType() {
        return com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects.getDefaultInstance();
      }

      public com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects build() {
        com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects buildPartial() {
        com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects result = new com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.rule_ = rule_;
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          dialects_ = java.util.Collections.unmodifiableList(dialects_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.dialects_ = dialects_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects) {
          return mergeFrom((com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects other) {
        if (other == com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects.getDefaultInstance()) return this;
        if (other.hasRule()) {
          setRule(other.getRule());
        }
        if (!other.dialects_.isEmpty()) {
          if (dialects_.isEmpty()) {
            dialects_ = other.dialects_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureDialectsIsMutable();
            dialects_.addAll(other.dialects_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasRule()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.hall.msg.pb.SRuleDialectsPB.SRuleDialects) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required .common.GameRule rule = 1;
      private com.kuaikai.game.common.msg.pb.GameRulePB.GameRule rule_ = com.kuaikai.game.common.msg.pb.GameRulePB.GameRule.JIN_HUA;
      /**
       * <code>required .common.GameRule rule = 1;</code>
       *
       * <pre>
       * 玩法编号
       * </pre>
       */
      public boolean hasRule() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .common.GameRule rule = 1;</code>
       *
       * <pre>
       * 玩法编号
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.GameRulePB.GameRule getRule() {
        return rule_;
      }
      /**
       * <code>required .common.GameRule rule = 1;</code>
       *
       * <pre>
       * 玩法编号
       * </pre>
       */
      public Builder setRule(com.kuaikai.game.common.msg.pb.GameRulePB.GameRule value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        rule_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .common.GameRule rule = 1;</code>
       *
       * <pre>
       * 玩法编号
       * </pre>
       */
      public Builder clearRule() {
        bitField0_ = (bitField0_ & ~0x00000001);
        rule_ = com.kuaikai.game.common.msg.pb.GameRulePB.GameRule.JIN_HUA;
        onChanged();
        return this;
      }

      // repeated .common.Dialect dialects = 2;
      private java.util.List<com.kuaikai.game.common.msg.pb.DialectPB.Dialect> dialects_ =
        java.util.Collections.emptyList();
      private void ensureDialectsIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          dialects_ = new java.util.ArrayList<com.kuaikai.game.common.msg.pb.DialectPB.Dialect>(dialects_);
          bitField0_ |= 0x00000002;
        }
      }
      /**
       * <code>repeated .common.Dialect dialects = 2;</code>
       *
       * <pre>
       * 方言列表
       * </pre>
       */
      public java.util.List<com.kuaikai.game.common.msg.pb.DialectPB.Dialect> getDialectsList() {
        return java.util.Collections.unmodifiableList(dialects_);
      }
      /**
       * <code>repeated .common.Dialect dialects = 2;</code>
       *
       * <pre>
       * 方言列表
       * </pre>
       */
      public int getDialectsCount() {
        return dialects_.size();
      }
      /**
       * <code>repeated .common.Dialect dialects = 2;</code>
       *
       * <pre>
       * 方言列表
       * </pre>
       */
      public com.kuaikai.game.common.msg.pb.DialectPB.Dialect getDialects(int index) {
        return dialects_.get(index);
      }
      /**
       * <code>repeated .common.Dialect dialects = 2;</code>
       *
       * <pre>
       * 方言列表
       * </pre>
       */
      public Builder setDialects(
          int index, com.kuaikai.game.common.msg.pb.DialectPB.Dialect value) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDialectsIsMutable();
        dialects_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated .common.Dialect dialects = 2;</code>
       *
       * <pre>
       * 方言列表
       * </pre>
       */
      public Builder addDialects(com.kuaikai.game.common.msg.pb.DialectPB.Dialect value) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDialectsIsMutable();
        dialects_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated .common.Dialect dialects = 2;</code>
       *
       * <pre>
       * 方言列表
       * </pre>
       */
      public Builder addAllDialects(
          java.lang.Iterable<? extends com.kuaikai.game.common.msg.pb.DialectPB.Dialect> values) {
        ensureDialectsIsMutable();
        super.addAll(values, dialects_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated .common.Dialect dialects = 2;</code>
       *
       * <pre>
       * 方言列表
       * </pre>
       */
      public Builder clearDialects() {
        dialects_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:hall.SRuleDialects)
    }

    static {
      defaultInstance = new SRuleDialects(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:hall.SRuleDialects)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_hall_SRuleDialects_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_hall_SRuleDialects_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030hall/SRuleDialects.proto\022\004hall\032\025common" +
      "/GameRule.proto\032\024common/Dialect.proto\"R\n" +
      "\rSRuleDialects\022\036\n\004rule\030\001 \002(\0162\020.common.Ga" +
      "meRule\022!\n\010dialects\030\002 \003(\0162\017.common.Dialec" +
      "tB/\n\034com.kuaikai.game.hall.msg.pbB\017SRule" +
      "DialectsPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_hall_SRuleDialects_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_hall_SRuleDialects_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_hall_SRuleDialects_descriptor,
              new java.lang.String[] { "Rule", "Dialects", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.kuaikai.game.common.msg.pb.GameRulePB.getDescriptor(),
          com.kuaikai.game.common.msg.pb.DialectPB.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}