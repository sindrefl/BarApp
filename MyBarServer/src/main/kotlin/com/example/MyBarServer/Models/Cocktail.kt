package com.example.MyBarServer.Models

enum class Glass{
    HIGHBALL, COCKTAIL, ROCKS
}

data class Cocktail(var name : String,var glass : Glass, var category: Category, var ingredients : List<Ingredient>, var amounts : List<String>, var description : String){
    var alcoholic = true
    var image_link : String? = null
}