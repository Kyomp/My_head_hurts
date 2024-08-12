import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.Pokemon
import com.example.myapplication.R

class PokemonAdapter(
    private val pokemons: MutableList<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private lateinit var context: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        context = parent
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pokemon_card,
                parent,
                false
            )
        )
    }

    fun addPokemon(pokemon: Pokemon) {
        pokemons.add(pokemon)
        notifyItemInserted(pokemons.size - 1)
    }

    fun deletePokemons() {
        pokemons.removeAll { true }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val curPokemon = pokemons[position]
        val name: TextView = holder.itemView.findViewById<TextView>(R.id.tvPokemonName)
        name.text = curPokemon.name

        val img: ImageView = holder.itemView.findViewById<ImageView>(R.id.ivPokemonImage)
//        img.setOnClickListener(open modal)
        Glide.with(context)
            .load(curPokemon.low_imageURL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(img)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
}