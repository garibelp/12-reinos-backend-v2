package br.com.extratora.twelvekingdoms.enums;

public enum Error {
    INVALID_CREATION_DICES("attributes", "values 2D4, 1D6 and 1d8 need to be used on character creation"),
    INVALID_CREATION_LINEAGE("lineage", "lineage couldn't be retrieved from database"),
    INVALID_CREATION_BACKGROUND("background", "background couldn't be retrieved on database"),
    INVALID_CREATION_JOB("job", "job couldn't be retrieved on database"),
    INVALID_CREATION_APTITUDE_LIST("aptitude", "must choose 3 distinct aptitudes"),
    INVALID_CREATION_APTITUDE_JOB("aptitude", "aptitudes not found on job aptitude list"),
    INVALID_CAMPAIGN_SHEET_LIST("sheetList", "sheet list couldn't be retrieved on database"),
    INVALID_SHEET_LEVEL_UP("sheet", "sheet already in the maximum level currently allowed"),
    INVALID_WOUND_ID("wound", "wound couldn't be retrieved from database"),
    SHEET_WITHOUT_WOUND("wound", "sheet doesn't contain wound"),
    SHEET_WITH_WOUND("wound", "sheet already contains wound");

    private final String name;
    private final String description;

    Error(String name, String description) {
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
