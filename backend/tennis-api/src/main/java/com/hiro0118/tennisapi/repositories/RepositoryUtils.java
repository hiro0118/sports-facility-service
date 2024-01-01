package com.hiro0118.tennisapi.repositories;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoryUtils {

    private RepositoryUtils() {}

    public static boolean listIsEmpty(List<String> list) {
        return list == null || list.isEmpty();
    }

    public static String in(final String name, final List<String> conditions) {
        var ors = conditions
            .stream()
            .map(cond -> name + "='" + cond + "'")
            .collect(Collectors.joining(" OR "));
        return "(" + ors + ")";
    }
}
