package com.javarush.shosafoev.model.entity;

import static java.util.Objects.isNull;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");
    private final String value;
    
    Feature(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static Feature fromCode(String value) {
        if (isNull(value) || value.isEmpty()) {
            return null;
        }
        Feature[] values = Feature.values();
        for (Feature feature : values) {
            if (feature.value.equals(value)) {
                return feature;
            }
        }
        return null;
    }
}