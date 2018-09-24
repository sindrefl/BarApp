package com.example.MyBarServer

import com.example.MyBarServer.Models.Category
import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Glass
import com.example.MyBarServer.Models.Ingredient
import com.example.MyBarServer.Services.CocktailService
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths

@Component
class ApplicationStartupListener : ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    var cocktailService : CocktailService? = null

    @EventListener
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        LOG.info("Event caught")
        //trawlTop100()
    }

    val LOG = LoggerFactory.getLogger("com.example.BarServer")

    fun trawlTop100() {
        val most_viewed = Jsoup.connect("https://www.diffordsguide.com/cocktails/most-viewed").get()

        val drinkUrls = most_viewed.select(".box-link__content").eachAttr("href").map { "https://www.diffordsguide.com/" + it }


        LOG.info(drinkUrls.toString())

        //for(url in drinkUrls)
        val url = drinkUrls.get(0)
        val name = url.split("/").last().toUpperCase().replace("-", " ")

        val drinkDocument = Jsoup.connect(url).get()
        val drink = drinkDocument.select("meta[itemprop=recipeIngredient]").eachAttr("content")

        val glass = Glass.ROCKS

        LOG.info(drink.toString())

        val ingredients = emptyList<Ingredient>().toMutableList()
        val amounts = emptyList<String>().toMutableList()

        //val ingredientMap = hashMapOf<Ingredient, String>()

        for (string in drink) {
            var _ingredient = ""
            if (string.indexOf("(") > -1) _ingredient = string.substring(0, string.indexOf("("))
            else _ingredient = string

            val split = _ingredient.split("ml")
            if (split.size == 1) {
          //      ingredientMap.put(Ingredient(split[0].substring(split[0].indexOf(" ")).trim()), split[0].substring(0, split[0].indexOf(" ")))

                amounts.add(split[0].substring(0, split[0].indexOf(" ")))
                //ingredients.add(Ingredient(split[0].substring(split[0].indexOf(" ")).trim()))
            } else {
                //ingredientMap.put(Ingredient(split[1].trim()), split[0].trim())

                //ingredients.add(Ingredient(split[1].trim()))
                amounts.add(split[0].trim())
            }
        }

        //LOG.info(ingredients.toString())
        //LOG.info(amounts.toString())

        //LOG.info(ingredientMap.toString())

        //val cocktail = Cocktail(glass, name, ingredientMap, "")
        val cocktail = Cocktail(name,glass, Category("HARDCODED"),ingredients,amounts, "","")

        cocktailService!!.addCocktail(cocktail)
    }
}