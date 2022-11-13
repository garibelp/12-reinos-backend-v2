package br.com.extratora.twelvekingdoms.enums;

public enum ErrorEnum {
    INVALID_CREATION_DICES("attributes", "values 2D4, 1D6 and 1d8 need to be used on character creation"),
    INVALID_CREATION_LINEAGE("lineage", "lineage couldn't be retrieved on database");

    private final String name;
    private final String description;

    ErrorEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
