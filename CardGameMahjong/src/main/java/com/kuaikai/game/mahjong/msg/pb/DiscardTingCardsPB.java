// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/DiscardTingCards.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class DiscardTingCardsPB {
  private DiscardTingCardsPB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface DiscardTingCardsOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 discard = 1;
    /**
     * <code>required int32 discard = 1;</code>
     *
     * <pre>
     * 打出的牌
     * </pre>
     */
    boolean hasDiscard();
    /**
     * <code>required int32 discard = 1;</code>
     *
     * <pre>
     * 打出的牌
     * </pre>
     */
    int getDiscard();

    // repeated int32 tingCards = 2;
    /**
     * <code>repeated int32 tingCards = 2;</code>
     *
     * <pre>
     * 听牌列表
     * </pre>
     */
    java.util.List<java.lang.Integer> getTingCardsList();
    /**
     * <code>repeated int32 tingCards = 2;</code>
     *
     * <pre>
     * 听牌列表
     * </pre>
     */
    int getTingCardsCount();
    /**
     * <code>repeated int32 tingCards = 2;</code>
     *
     * <pre>
     * 听牌列表
     * </pre>
     */
    int getTingCards(int index);
  }
  /**
   * Protobuf type {@code mahjong.DiscardTingCards}
   *
   * <pre>
   * 打出一张牌后的听牌列表
   * </pre>
   */
  public static final class DiscardTingCards extends
      com.google.protobuf.GeneratedMessage
      implements DiscardTingCardsOrBuilder {
    // Use DiscardTingCards.newBuilder() to construct.
    private DiscardTingCards(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private DiscardTingCards(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final DiscardTingCards defaultInstance;
    public static DiscardTingCards getDefaultInstance() {
      return defaultInstance;
    }

    public DiscardTingCards getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private DiscardTingCards(
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
              discard_ = input.readInt32();
              break;
            }
            case 16: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                tingCards_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000002;
              }
              tingCards_.add(input.readInt32());
              break;
            }
            case 18: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
                tingCards_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000002;
              }
              while (input.getBytesUntilLimit() > 0) {
                tingCards_.add(input.readInt32());
              }
              input.popLimit(limit);
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
          tingCards_ = java.util.Collections.unmodifiableList(tingCards_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.internal_static_mahjong_DiscardTingCards_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.internal_static_mahjong_DiscardTingCards_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards.class, com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards.Builder.class);
    }

    public static com.google.protobuf.Parser<DiscardTingCards> PARSER =
        new com.google.protobuf.AbstractParser<DiscardTingCards>() {
      public DiscardTingCards parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DiscardTingCards(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<DiscardTingCards> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 discard = 1;
    public static final int DISCARD_FIELD_NUMBER = 1;
    private int discard_;
    /**
     * <code>required int32 discard = 1;</code>
     *
     * <pre>
     * 打出的牌
     * </pre>
     */
    public boolean hasDiscard() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 discard = 1;</code>
     *
     * <pre>
     * 打出的牌
     * </pre>
     */
    public int getDiscard() {
      return discard_;
    }

    // repeated int32 tingCards = 2;
    public static final int TINGCARDS_FIELD_NUMBER = 2;
    private java.util.List<java.lang.Integer> tingCards_;
    /**
     * <code>repeated int32 tingCards = 2;</code>
     *
     * <pre>
     * 听牌列表
     * </pre>
     */
    public java.util.List<java.lang.Integer>
        getTingCardsList() {
      return tingCards_;
    }
    /**
     * <code>repeated int32 tingCards = 2;</code>
     *
     * <pre>
     * 听牌列表
     * </pre>
     */
    public int getTingCardsCount() {
      return tingCards_.size();
    }
    /**
     * <code>repeated int32 tingCards = 2;</code>
     *
     * <pre>
     * 听牌列表
     * </pre>
     */
    public int getTingCards(int index) {
      return tingCards_.get(index);
    }

    private void initFields() {
      discard_ = 0;
      tingCards_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasDiscard()) {
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
        output.writeInt32(1, discard_);
      }
      for (int i = 0; i < tingCards_.size(); i++) {
        output.writeInt32(2, tingCards_.get(i));
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
          .computeInt32Size(1, discard_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < tingCards_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(tingCards_.get(i));
        }
        size += dataSize;
        size += 1 * getTingCardsList().size();
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

    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards prototype) {
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
     * Protobuf type {@code mahjong.DiscardTingCards}
     *
     * <pre>
     * 打出一张牌后的听牌列表
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCardsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.internal_static_mahjong_DiscardTingCards_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.internal_static_mahjong_DiscardTingCards_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards.class, com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards.Builder.class);
      }

      // Construct using com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards.newBuilder()
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
        discard_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        tingCards_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.internal_static_mahjong_DiscardTingCards_descriptor;
      }

      public com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards getDefaultInstanceForType() {
        return com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards.getDefaultInstance();
      }

      public com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards build() {
        com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards buildPartial() {
        com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards result = new com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.discard_ = discard_;
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          tingCards_ = java.util.Collections.unmodifiableList(tingCards_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.tingCards_ = tingCards_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards) {
          return mergeFrom((com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards other) {
        if (other == com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards.getDefaultInstance()) return this;
        if (other.hasDiscard()) {
          setDiscard(other.getDiscard());
        }
        if (!other.tingCards_.isEmpty()) {
          if (tingCards_.isEmpty()) {
            tingCards_ = other.tingCards_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureTingCardsIsMutable();
            tingCards_.addAll(other.tingCards_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasDiscard()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.kuaikai.game.mahjong.msg.pb.DiscardTingCardsPB.DiscardTingCards) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 discard = 1;
      private int discard_ ;
      /**
       * <code>required int32 discard = 1;</code>
       *
       * <pre>
       * 打出的牌
       * </pre>
       */
      public boolean hasDiscard() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 discard = 1;</code>
       *
       * <pre>
       * 打出的牌
       * </pre>
       */
      public int getDiscard() {
        return discard_;
      }
      /**
       * <code>required int32 discard = 1;</code>
       *
       * <pre>
       * 打出的牌
       * </pre>
       */
      public Builder setDiscard(int value) {
        bitField0_ |= 0x00000001;
        discard_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 discard = 1;</code>
       *
       * <pre>
       * 打出的牌
       * </pre>
       */
      public Builder clearDiscard() {
        bitField0_ = (bitField0_ & ~0x00000001);
        discard_ = 0;
        onChanged();
        return this;
      }

      // repeated int32 tingCards = 2;
      private java.util.List<java.lang.Integer> tingCards_ = java.util.Collections.emptyList();
      private void ensureTingCardsIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          tingCards_ = new java.util.ArrayList<java.lang.Integer>(tingCards_);
          bitField0_ |= 0x00000002;
         }
      }
      /**
       * <code>repeated int32 tingCards = 2;</code>
       *
       * <pre>
       * 听牌列表
       * </pre>
       */
      public java.util.List<java.lang.Integer>
          getTingCardsList() {
        return java.util.Collections.unmodifiableList(tingCards_);
      }
      /**
       * <code>repeated int32 tingCards = 2;</code>
       *
       * <pre>
       * 听牌列表
       * </pre>
       */
      public int getTingCardsCount() {
        return tingCards_.size();
      }
      /**
       * <code>repeated int32 tingCards = 2;</code>
       *
       * <pre>
       * 听牌列表
       * </pre>
       */
      public int getTingCards(int index) {
        return tingCards_.get(index);
      }
      /**
       * <code>repeated int32 tingCards = 2;</code>
       *
       * <pre>
       * 听牌列表
       * </pre>
       */
      public Builder setTingCards(
          int index, int value) {
        ensureTingCardsIsMutable();
        tingCards_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 tingCards = 2;</code>
       *
       * <pre>
       * 听牌列表
       * </pre>
       */
      public Builder addTingCards(int value) {
        ensureTingCardsIsMutable();
        tingCards_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 tingCards = 2;</code>
       *
       * <pre>
       * 听牌列表
       * </pre>
       */
      public Builder addAllTingCards(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureTingCardsIsMutable();
        super.addAll(values, tingCards_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 tingCards = 2;</code>
       *
       * <pre>
       * 听牌列表
       * </pre>
       */
      public Builder clearTingCards() {
        tingCards_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:mahjong.DiscardTingCards)
    }

    static {
      defaultInstance = new DiscardTingCards(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:mahjong.DiscardTingCards)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_mahjong_DiscardTingCards_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_mahjong_DiscardTingCards_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036mahjong/DiscardTingCards.proto\022\007mahjon" +
      "g\"6\n\020DiscardTingCards\022\017\n\007discard\030\001 \002(\005\022\021" +
      "\n\ttingCards\030\002 \003(\005B5\n\037com.kuaikai.game.ma" +
      "hjong.msg.pbB\022DiscardTingCardsPB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_mahjong_DiscardTingCards_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_mahjong_DiscardTingCards_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_mahjong_DiscardTingCards_descriptor,
              new java.lang.String[] { "Discard", "TingCards", });
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
