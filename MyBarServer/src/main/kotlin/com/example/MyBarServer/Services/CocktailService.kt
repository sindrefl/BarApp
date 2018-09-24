package com.example.MyBarServer.Services

import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Ingredient
import com.example.MyBarServer.Repository.CocktailRepository
import com.example.MyBarServer.Repository.IngredientsRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class CocktailService(@Autowired var cocktailRepository: CocktailRepository, @Autowired var ingredientsRepository: IngredientsRepository) {

    fun addCocktail(cocktail: Cocktail){
        val cocktailIds = cocktailRepository.addCocktail(cocktail)
        val ingredientIds = ingredientsRepository.addIngredients(cocktail.ingredients)
        ingredientsRepository.addCocktailIngredients(cocktail.amounts, cocktailIds, ingredientIds)
    }

    fun getCocktail(id :Int) : Cocktail {
        val cocktailPair = cocktailRepository.getCocktail(id)
        cocktailPair.first.ingredients = getIngredients(cocktailPair.second)
        return cocktailPair.first
    }
    fun getCocktails() : List<Cocktail> {
        val cocktailPairs = cocktailRepository.getAll()

        val res = emptyList<Cocktail>().toMutableList()

        for (pair in cocktailPairs) {
            pair.first.ingredients = getIngredients(pair.second)
            res.add(pair.first)
        }
        return res
    }

    fun getCocktails(ids : List<Int>) : List<Cocktail> {
        val cocktailPairs = cocktailRepository.getCocktails(ids)
        val res = emptyList<Cocktail>().toMutableList()

        for (pair in cocktailPairs) {
            pair.first.ingredients = getIngredients(pair.second)
            res.add(pair.first)
        }
        return res
    }

    fun getIngredient(id : Int) : Ingredient{
        return ingredientsRepository.getIngredient(id)
    }

    fun getIngredients(ids :List<Int>) : List<Ingredient> {
        return ingredientsRepository.getIngredients(ids)
    }
    fun getIngredients() : List<Ingredient> {
        return ingredientsRepository.getAll()
    }

    private val log = LoggerFactory.getLogger(Application::class.java)

}