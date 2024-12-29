package com.xuhao.salary.domain.model.employee;

public enum Gender {
    M, F;
    
    public static Gender fromString(String value) {
        if (value == null) return null;
        return Gender.valueOf(value.toUpperCase());
    }

    public static Gender fromChar(Character value) {
        if (value == null) return null;
        return Gender.valueOf(String.valueOf(value));
    }
}
