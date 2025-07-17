package org.dimasik.shame.utils;

import lombok.Getter;

public class Pair<F, S> {
    @Getter
    private F first;
    @Getter
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public Pair<F, S> setFirst(F first){
        this.first = first;
        return this;
    }

    public Pair<F, S> setSecond(S second){
        this.second = second;
        return this;
    }
}