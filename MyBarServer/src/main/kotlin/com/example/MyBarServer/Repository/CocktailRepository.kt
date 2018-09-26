package com.example.MyBarServer.Repository

import com.example.MyBarServer.Models.Category
import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Glass
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.support.GeneratedKeyHolder


@Repository
class CocktailRepository(@Autowired var namedParameterJdbcTemplate: NamedParameterJdbcTemplate) {

    final val INSERT_COCKTAIL_SQL = "INSERT INTO COCKTAIL_DB.COCKTAIL (name,description,glass,category,alcoholic,img_link, recipe) VALUES (:name,:description,:glass,:category, :alcoholic, :image_link,:recipe) "

    fun addCocktail(cocktail: Cocktail): Int {

        //holds IDs generated by mysql:
        val keyHolder: GeneratedKeyHolder = GeneratedKeyHolder()

        val namedParameters = MapSqlParameterSource(hashMapOf(
                "recipe" to cocktail.recipe,
                "image_link" to cocktail.image_link,
                "alcoholic" to cocktail.alcoholic,
                "category" to cocktail.category.name,
                "name" to cocktail.name,
                "description" to cocktail.description,
                "glass" to cocktail.glass.toString())
        )

        namedParameterJdbcTemplate.update(INSERT_COCKTAIL_SQL, namedParameters, keyHolder)

        return keyHolder.key!!.toInt()
    }

    val GET_COCKTAIL_SQL = "SELECT COCKTAIL.NAME  AS COCKTAILNAME,DESCRIPTION,GLASS,CATEGORY,INGREDIENT.ID AS INGREDIENTID, AMOUNT,IMG_LINK,RECIPE FROM COCKTAIL JOIN COCKTAILHASINGREDIENT ON COCKTAIL.ID=COCKTAILHASINGREDIENT.COCKTAIL_ID JOIN INGREDIENT ON COCKTAILHASINGREDIENT.INGREDIENT_ID=INGREDIENT.ID WHERE COCKTAIL.ID=:cocktailid"


    fun getCocktail(id: Int): Pair<Cocktail,List<Int>> {
        val namedParameters = MapSqlParameterSource(hashMapOf(
                "cocktailid" to id
        ))
        val res = namedParameterJdbcTemplate.queryForList(GET_COCKTAIL_SQL, namedParameters)

        /*val ingredients = hashMapOf<Ingredient,String>()
        for(row in res){
            ingredients.put(Ingredient(row.get("ingredientname").toString()), row.get("amount").toString())
        }
        */

        val ingredients = res.map { it.get("ingredientid").toString().toInt() }.toList()
        val amounts = res.map { it.get("amount").toString() }.toList()

        val row = res.get(0)
        val cocktail = Cocktail(row.get("cocktailname").toString(),Glass.valueOf(row.get("glass").toString()), Category( row.get("category").toString()), emptyList(), amounts, row.get("description").toString(), row.get("recipe").toString())
        cocktail.image_link = row.get("img_link").toString()
        return Pair(cocktail,ingredients)
    }

    val LOG = LoggerFactory.getLogger(CocktailRepository::class.java)

    //Todo optimize:
    fun getCocktails(ids: List<Int>) : List<Pair<Cocktail,List<Int>>> {
        val res = emptyList<Pair<Cocktail,List<Int>>>().toMutableList()
        for(id in ids){
            res.add(getCocktail(id))
        }
        return res
    }

    //extremely shitty:

    val shittysql = "SELECT ID FROM COCKTAIL_DB.COCKTAIL"
    fun getAll() : List<Pair<Cocktail,List<Int>>>{

        val ids = namedParameterJdbcTemplate.queryForList(shittysql, MapSqlParameterSource()).map { it.get("id").toString().toInt() }

        return getCocktails(ids)
    }

    fun getMinMax() : Pair<Int,Int> {
        val ids = namedParameterJdbcTemplate.queryForList(shittysql, MapSqlParameterSource()).map { it.get("id").toString().toInt() }

        return Pair(ids[0], ids.size)
    }

    val Categorysql = "SELECT DISTINCT CATEGORY FROM COCKTAIL_DB.COCKTAIL"
    fun getCategories() : List<Category>  {
        return namedParameterJdbcTemplate.queryForList(Categorysql, MapSqlParameterSource()).map { Category(name=it.get("category").toString()) }

    }


    fun getIdsFromFilter(params : List<Pair<String,String>>) : List<Int>{
        val sql = "SELECT ID FROM COCKTAIL_DB.COCKTAIL WHERE " + params.map { it.first + " LIKE ('%" + it.second + "%')" }.joinToString(" AND ")
        LOG.info(sql)
        val ids = namedParameterJdbcTemplate.queryForList(sql, MapSqlParameterSource()).map { it.get("id").toString().toInt()}
        return ids
    }

}