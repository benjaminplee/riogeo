package yardspoon.riogeo

inline val (() -> Any).asTheTruth: Boolean
    get() {
        this()
        return true
    }
