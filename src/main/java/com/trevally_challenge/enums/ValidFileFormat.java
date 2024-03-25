package com.trevally_challenge.enums;

public enum ValidFileFormat {
    VALID_CSV(".csv");

    private final String extension;

    ValidFileFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
