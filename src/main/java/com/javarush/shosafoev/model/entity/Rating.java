package com.javarush.shosafoev.model.entity;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");
    private final String value;
    
    Rating(String value) {
        this.value = value;
    }
    
    public static Rating fromCode(String code) {
        for (Rating rating : Rating.values()) {
            if (rating.value.equals(code)) {
                return rating;
            }
        }
        throw new UnsupportedOperationException("The code " + code + "is not supported!");
    }
    
    public String getCode() {
        return value;
    }
}