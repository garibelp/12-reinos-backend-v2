package br.com.extratora.twelvekingdoms.enums;

public enum AttributesEnum {
    INTELLIGENCE("Intelecto"), CUNNING("Astúcia"), CELERITY("Celeridade"), TENACITY("Tenacidade");

    private final String translation;

    AttributesEnum(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
