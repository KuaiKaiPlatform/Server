// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mahjong/CardType.proto

package com.kuaikai.game.mahjong.msg.pb;

public final class CardTypePB {
  private CardTypePB() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code mahjong.CardType}
   *
   * <pre>
   * 麻将牌类型
   * </pre>
   */
  public enum CardType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>NA = 0;</code>
     */
    NA(0, 0),
    /**
     * <code>WAN = 10;</code>
     */
    WAN(1, 10),
    /**
     * <code>TIAO = 20;</code>
     */
    TIAO(2, 20),
    /**
     * <code>TONG = 30;</code>
     */
    TONG(3, 30),
    /**
     * <code>ZI = 40;</code>
     */
    ZI(4, 40),
    /**
     * <code>HUA = 50;</code>
     */
    HUA(5, 50),
    /**
     * <code>ALMIGHTY = 100;</code>
     */
    ALMIGHTY(6, 100),
    ;

    /**
     * <code>NA = 0;</code>
     */
    public static final int NA_VALUE = 0;
    /**
     * <code>WAN = 10;</code>
     */
    public static final int WAN_VALUE = 10;
    /**
     * <code>TIAO = 20;</code>
     */
    public static final int TIAO_VALUE = 20;
    /**
     * <code>TONG = 30;</code>
     */
    public static final int TONG_VALUE = 30;
    /**
     * <code>ZI = 40;</code>
     */
    public static final int ZI_VALUE = 40;
    /**
     * <code>HUA = 50;</code>
     */
    public static final int HUA_VALUE = 50;
    /**
     * <code>ALMIGHTY = 100;</code>
     */
    public static final int ALMIGHTY_VALUE = 100;


    public final int getNumber() { return value; }

    public static CardType valueOf(int value) {
      switch (value) {
        case 0: return NA;
        case 10: return WAN;
        case 20: return TIAO;
        case 30: return TONG;
        case 40: return ZI;
        case 50: return HUA;
        case 100: return ALMIGHTY;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<CardType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<CardType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<CardType>() {
            public CardType findValueByNumber(int number) {
              return CardType.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.kuaikai.game.mahjong.msg.pb.CardTypePB.getDescriptor().getEnumTypes().get(0);
    }

    private static final CardType[] VALUES = values();

    public static CardType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private CardType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:mahjong.CardType)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026mahjong/CardType.proto\022\007mahjong*N\n\010Car" +
      "dType\022\006\n\002NA\020\000\022\007\n\003WAN\020\n\022\010\n\004TIAO\020\024\022\010\n\004TONG" +
      "\020\036\022\006\n\002ZI\020(\022\007\n\003HUA\0202\022\014\n\010ALMIGHTY\020dB-\n\037com" +
      ".kuaikai.game.mahjong.msg.pbB\nCardTypePB"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
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
