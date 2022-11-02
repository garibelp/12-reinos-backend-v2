package br.com.extratora.twelvekingdoms.enums;

public enum LineageEnum {
    ANAO("Anão"),
    ASHARIANO("Ashariano"),
    COGNI("Cogni"),
    ECLESEN("Eclésen"),
    ELFO("Elfo"),
    EREPE("Erepe"),
    GNOMO("Gnomo"),
    HUMANO("Humano"),
    IUVENTI("Iuventi"),
    KADIT("Kadit"),
    LAVI("Lavi"),
    ORC("Orc");

    private final String name;

    LineageEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
