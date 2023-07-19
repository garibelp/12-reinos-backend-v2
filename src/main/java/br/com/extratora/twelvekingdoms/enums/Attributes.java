package br.com.extratora.twelvekingdoms.enums;

public enum Attributes {
    INTELLIGENCE("Intelecto"), CUNNING("Astúcia"), CELERITY("Celeridade"), TENACITY("Tenacidade");

    private final String translation;

    Attributes(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
