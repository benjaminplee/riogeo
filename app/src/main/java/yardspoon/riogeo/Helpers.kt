package yardspoon.riogeo

inline val (() -> Unit?).asTheTruth: Boolean
    get() {
        this()
        return true
    }
