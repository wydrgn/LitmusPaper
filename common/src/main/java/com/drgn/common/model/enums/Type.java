package com.drgn.common.model.enums;

/**
 * @description: 属性
 * @author: wydrgn
 * @createDate: 2021-06-22 1:05
 */
public enum Type {
    Normal("一般", "0"),
    Fighting("格斗", "1"),
    Flying("飞行", "2"),
    Poison("毒", "3"),
    Ground("地面", "4"),
    Rock("岩石", "5"),
    Bug("虫", "6"),
    Ghost("幽灵", "7"),
    Steel("钢", "8"),
    Fire("火", "9"),
    Water("水", "10"),
    Grass("草", "11"),
    Electric("电", "12"),
    Psychic("超能力", "13"),
    Ice("冰", "14"),
    Dragon("龙", "15"),
    Dark("恶", "16"),
    Fairy("妖精", "17");


    public final String name;
    public final String value;

    Type(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static Type initFromValue(String value) {
        for (Type t : Type.values()) {
            if (value.equals(t.value)) {
                return t;
            }
        }
        return null;
    }
}
