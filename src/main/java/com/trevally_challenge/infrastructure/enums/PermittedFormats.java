package com.trevally_challenge.infrastructure.enums;

public enum PermittedFormats {
    CSV(".csv");

    private final String extension;

    PermittedFormats(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
