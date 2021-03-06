package com.example.MyBarServer.Services

import com.example.MyBarServer.Models.Category
import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Ingredient
import com.example.MyBarServer.Repository.CocktailRepository
import com.example.MyBarServer.Repository.IngredientsRepository
import javafx.application.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class CocktailService(@Autowired var cocktailRepository: CocktailRepository, @Autowired var ingredientsRepository: IngredientsRepository) {

    private val LOG = LoggerFactory.getLogger(CocktailService::class.java)

    var randomCocktail : Cocktail = getCocktail(1)

    fun updateRandomCocktail() : Unit {
        val range = cocktailRepository.getMinMax()
        val rand : Random = Random()
        randomCocktail = getCocktail(rand.nextInt(range.second - range.first + 1) + range.first)
    }

    //prevent sql injection
    private fun filterSqlQueries(string: String):Boolean {
        val illegals = listOf<String>("join","select","drop","insert")
        for(ill in illegals){
            if(string.contains(ill)) return true
        }
        return false
    }

    //to filter out potential unwanted queries
    private fun getParamsFromString(searchString: String):List<Pair<String,String>>{
        LOG.info("THIS IS THE SEARCHSTRING" + searchString)
        val split = searchString.split('&')
        LOG.info("THIS IS THE SPLIT $split")
        val params = emptyList<Pair<String,String>>().toMutableList()
        for(string in split){
            val sub = string.split("=")
            LOG.info("THIS IS THE SUB $sub")
            when(sub[0]){
                "category" -> params.add(Pair("cocktail.category",sub[1].replace(regex,"")))
                "glass" -> params.add(Pair("cocktail.glass",sub[1].replace(regex,"")))
            }
        }
        return params
    }

    fun getFilteredDrinkList(searchString: String) : List<Cocktail>{
        if (filterSqlQueries(searchString)) return emptyList()
        return getCocktails(cocktailRepository.getIdsFromFilter(getParamsFromString(searchString)))
    }

    val regex = Regex("[^a-zA-Z /-_'èéÈÉ0-9]")
    fun addCocktail(cocktail: Cocktail){

        cocktail.name = cocktail.name.replace(regex,"")
        cocktail.ingredients = cocktail.ingredients.map { Ingredient(it.name.replace(regex,""), it.description.replace(regex,""), it.type.replace(regex,""), it.isBattery) }.toList()
        cocktail.category = Category(cocktail.category.name.replace(regex,""))
        cocktail.recipe = cocktail.recipe.replace(regex,"")
        cocktail.description = cocktail.description.replace(regex,"")
        cocktail.amounts = cocktail.amounts.map { it.replace(regex,"") }

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

    fun getCategories() : List<Category>{
        return cocktailRepository.getCategories()
    }

    private val log = LoggerFactory.getLogger(Application::class.java)

}