package com.example.MyBarServer.Controllers

import com.example.MyBarServer.Models.Category
import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Glass
import com.example.MyBarServer.Models.Ingredient
import com.example.MyBarServer.Services.CSVService
import com.example.MyBarServer.Services.CocktailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger
import javax.servlet.http.HttpServletResponse

@RestController("/")
class CocktailController(@Autowired var cocktailService: CocktailService, @Autowired var csvService: CSVService) {

    val LOG = Logger.getLogger(this.javaClass.name)

    @RequestMapping("/")
    fun home(): String{
        return "This is the home"
    }

    @PostMapping("/addDrink")
    @CrossOrigin("*")
    fun addDrink(@RequestBody cocktail: Cocktail){
        cocktailService.addCocktail(cocktail)
    }


    @GetMapping("/random")
    @CrossOrigin("*")
    fun index(): Cocktail{

        val cocktail = Cocktail("Hardcoded Whisky Sour",Glass.HIGHBALL, Category("This is a category"), listOf(Ingredient("Whisky"), Ingredient("Sugar")), listOf("4cl", "4cl") ,"This is a whiskey sour")

        return cocktail;
    }

    @GetMapping("/allDrinks")
    @CrossOrigin("*")
    fun getDrinks() : List<Cocktail>{


        val all = cocktailService.getCocktails()



        LOG.info(all.toString())
        return all
    }


    @GetMapping("/glassTypes")
    @CrossOrigin("*")
    fun getGlasses() : Array<Glass> {
        return Glass.values()
    }

    @GetMapping("/categories")
    @CrossOrigin("*")
    fun getCategories() : List<Category> {
        return emptyList()
    }

    @RequestMapping("/cocktails.csv")
    fun getCocktailsAsCsv(response:HttpServletResponse){
        response.contentType = ("text/csv")
        response.setHeader("Content-Disposition", "attachment; filename=\"cocktails.csv\"")
        csvService.writeCocktails(response.writer, cocktailService.getCocktails());
        response.writer.flush()
        response.writer.close()
    }

}