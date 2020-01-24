package com.company.delivery.utils;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SortType {

    ID("id"),
    VALUE("value"),
    WEIGHT("weight");

    private final String name;

    SortType(final String name) {
        this.name = name;
    }

    public static SortType getSortType(final String sortBy) {
        return Arrays.stream(SortType.values())
                .filter(x -> x.getName().equals(sortBy))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

}
