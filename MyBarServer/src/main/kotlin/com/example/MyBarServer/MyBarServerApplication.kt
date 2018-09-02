package com.example.MyBarServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyBarServerApplication

fun main(args: Array<String>) {
    runApplication<MyBarServerApplication>(*args)
}
