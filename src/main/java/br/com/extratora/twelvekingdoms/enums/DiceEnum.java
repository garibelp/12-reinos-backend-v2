package br.com.extratora.twelvekingdoms.enums;

public enum DiceEnum {
    D4(4), D6(6), D8(8), D12(12), D20(20);

    private final int value;

    DiceEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
