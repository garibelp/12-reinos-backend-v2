package br.com.extratora.twelvekingdoms.enums;

public enum ErrorEnum {
    INVALID_CREATION_DICES("attributes", "values 2D4, 1D6 and 1d8 need to be used on character creation"),
    INVALID_CREATION_LINEAGE("lineage", "lineage couldn't be retrieved on database"),
    INVALID_CREATION_BACKGROUND("background", "background couldn't be retrieved on database"),
    INVALID_CREATION_JOB("job", "job couldn't be retrieved on database"),
    INVALID_CREATION_APTITUDE_LIST("aptitude", "must choose 3 distinct aptitudes"),
    INVALID_CREATION_APTITUDE_JOB("aptitude", "aptitudes not found on job aptitude list"),
    INVALID_CAMPAIGN_SHEET_LIST("sheetList", "sheet list couldn't be retrieved on database");

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
