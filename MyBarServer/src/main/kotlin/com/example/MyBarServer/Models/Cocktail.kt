package com.example.MyBarServer.Models

import com.example.MyBarServer.Controllers.Ingredient

enum class Glass{
    HIGHBALL, COCKTAIL, LONGDRINK
}

data class Cocktail( var glass : Glass, var name : String,var ingredients : List<Ingredient> , var description :String){


}