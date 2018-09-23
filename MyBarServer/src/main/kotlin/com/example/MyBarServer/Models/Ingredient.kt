package com.example.MyBarServer.Models

import com.fasterxml.jackson.core.JsonParser
import java.io.Serializable
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import java.io.IOException
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.node.TextNode
import org.springframework.boot.jackson.JsonComponent
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode
import org.slf4j.LoggerFactory

@JsonDeserialize(using = IngredientDeserializer::class)
data class Ingredient(var name : String){
    var description : String? = null
    var type : String? = null
}

val LOG = LoggerFactory.getLogger("ingredient")

class IngredientDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<Ingredient>(vc) {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Ingredient {
        val node = jp.codec.readTree<TreeNode>(jp)
        return Ingredient(node.toString())
    }
}