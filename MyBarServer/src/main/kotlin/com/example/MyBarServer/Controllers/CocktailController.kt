package com.example.MyBarServer.Controllers

import com.example.MyBarServer.Models.Category
import com.example.MyBarServer.Models.Cocktail
import com.example.MyBarServer.Models.Glass
import com.example.MyBarServer.Models.Ingredient
import com.example.MyBarServer.Services.CSVService
import com.example.MyBarServer.Services.CocktailService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.logging.Logger
import javax.servlet.http.HttpServletResponse

@RestController("/")
class CocktailController(@Autowired var cocktailService: CocktailService, @Autowired var csvService: CSVService) {

    val LOG = LoggerFactory.getLogger(CocktailController::class.java.name)

    @RequestMapping("/")
    fun home(): String{
        return "This is the home"
    }

    @PostMapping("/addDrink")
    @CrossOrigin("*")
    fun addDrink(@RequestBody cocktail: Cocktail){
        if(cocktail.name.contains('*')){
            cocktail.name = "Quick Fuck"
        }
        if(cocktail.name.contains("\"")){
            cocktail.name = cocktail.name.replace("\"", "")
        }
        downloadImage(cocktail.image_link, cocktail.name.replace(' ', '_'))
        cocktailService.addCocktail(cocktail)
    }


    fun downloadImage(url: String?, name : String){
        var fileName = name
        if(url.isNullOrBlank()){
            return
        }
        if(name.contains("/")){
            fileName = name.replace("/","_")
        }

        try {
            val image = URL(url).openStream().use({ input -> Files.copy(input, Paths.get("C:/Users/sindre.flood/Documents/MyBar/MyBarServer/target/classes/static/images/drinks/$fileName.jpg")) })
        } catch (e : IOException) {
            LOG.error("image failed: ${e.message}")
        }
    }


    @GetMapping("/random")
    @CrossOrigin("*")
    fun index(): Cocktail{


        return cocktailService.getCocktail(1)
    }

    @GetMapping("/allDrinks")
    @CrossOrigin("*")
    fun getDrinks() : List<Cocktail> = cocktailService.getCocktails()



    @GetMapping("/glassTypes")
    @CrossOrigin("*")
    fun getGlasses() : Array<Glass> {
        return Glass.values()
    }

    @GetMapping("/glassTypes/{length}")
    @CrossOrigin("*")
    fun getGlasses(@PathVariable length:Int) : List<Glass> {
        return Glass.values().take(length)
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