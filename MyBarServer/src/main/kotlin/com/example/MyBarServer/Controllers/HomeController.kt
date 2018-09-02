package com.example.MyBarServer.Controllers

import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Glass
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController("/")
@EnableAutoConfiguration
class HomeController {

    @RequestMapping("/")
    fun home(): String{
        return "This is the home"
    }

    @GetMapping("/random")
    @CrossOrigin("*")
    fun index(): Cocktail{

        val cocktail = Cocktail(Glass.HIGHBALL, "Whisky Sour", listOf(Ingredient("4CL", "Whisky"), Ingredient("2Cl", "Sugar")) ,"This is a whiskey sour")

        return cocktail;
    }
}