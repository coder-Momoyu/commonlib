package com.momoyu.lytest

/**
 * Created by ${momoyu} on 2018/10/15.
 */
fun str(content: String) {
    var strContent = "我叫${content}${content.length}"
    println(strContent)
}


fun prentln() {
    str("刘宇")
}


fun test1(a: String): Int? {
    return a.toInt()
}

fun test2(a: Int, b: Int) {
    var ia = test1("2")
    var ib = test1("3")

    if (ia != null && ib != null)
        println(ia * ib)
}

fun main(args: Array<String>) {
    println("123")
}