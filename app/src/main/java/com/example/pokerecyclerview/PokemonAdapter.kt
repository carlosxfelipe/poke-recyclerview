package com.example.pokerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(private val pokemonList: MutableList<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val showingBackMap = mutableMapOf<String, Boolean>()

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.pokemonNameTextView)
        val imageView: ImageView = view.findViewById(R.id.pokemonImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.nameTextView.text = pokemon.name.replaceFirstChar { it.uppercase() }

        // Obtém o ID do Pokémon para ser a chave do estado no mapa
        val id = pokemon.url.split("/".toRegex()).dropLast(1).last()

        // Verifica se o Pokémon está virado, padrão é falso (frente)
        val showingBack = showingBackMap[id] ?: false
        val imageUrl = if (showingBack) pokemon.getBackImageUrl() else pokemon.getImageUrl()

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val newShowingBack = !(showingBackMap[id] ?: false)
            showingBackMap[id] = newShowingBack

            val newImageUrl = if (newShowingBack) pokemon.getBackImageUrl() else pokemon.getImageUrl()
            Glide.with(holder.itemView.context)
                .load(newImageUrl)
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int = pokemonList.size

    fun addPokemon(newPokemon: List<Pokemon>) {
        pokemonList.addAll(newPokemon)
        notifyDataSetChanged()
    }
}
