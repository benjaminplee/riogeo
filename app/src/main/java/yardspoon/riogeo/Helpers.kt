package yardspoon.riogeo

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}
