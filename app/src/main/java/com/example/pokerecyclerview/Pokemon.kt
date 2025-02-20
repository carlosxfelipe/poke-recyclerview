package com.example.pokerecyclerview

data class Pokemon(
    val name: String,
    val url: String
) {
    fun getImageUrl(): String {
        val id = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }

    fun getBackImageUrl(): String {
        val id = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
    }
}

data class PokemonResponse(
    val results: List<Pokemon>
)