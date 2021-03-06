package com.example.MyBarServer.Services

import com.example.MyBarServer.Models.Category
import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Ingredient
import com.opencsv.CSVWriter
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import com.opencsv.exceptions.CsvException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.PrintWriter
import kotlin.reflect.full.declaredMemberProperties


@Service
class CSVService {


    class beanToCSVWrapper(private val btcsv : StatefulBeanToCsv<Cocktail>){
        fun writeSingle(cocktail : Cocktail):Unit = btcsv.write(cocktail)
        fun writeMany(cocktails : List<Cocktail>):Unit = btcsv.write(cocktails)
    }


    fun writeCocktails(writer: PrintWriter, cocktails: List<Cocktail>) {
        val LOGGER = LoggerFactory.getLogger(CSVService::class.java)
        try {

            val ingredientVariableNames = Ingredient::class.java.declaredFields.map { "Ingredient_" + it.name }.toMutableList()
            val categoryVariableNames = Category::class.java.declaredFields.map { "Category_" + it.name }.toTypedArray()

            val mapStrategy = ColumnPositionMappingStrategy<Cocktail>()

            mapStrategy.setType(Cocktail::class.java)
            mapStrategy.generateHeader()

            val columns = arrayOf("name", "glass", "category", "ingredients", "amounts")
            mapStrategy.setColumnMapping(*columns)

            val btcsv = StatefulBeanToCsvBuilder<Cocktail>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build()

            beanToCSVWrapper(btcsv as StatefulBeanToCsv<Cocktail>).writeMany(cocktails)


        } catch (ex: CsvException) {
            LOGGER.error("Error mapping Bean to CSV", ex)
        }

    }
}
