package com.devlade.foodjunks

import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(
    private val categoriesList: ArrayList<CategoriesHelper>,
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var rating: TextView
        var duration: TextView
        var price: TextView
        var categoriesLayout: CardView

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            rating = itemView.findViewById(R.id.rating)
            duration = itemView.findViewById(R.id.duration)
            price = itemView.findViewById(R.id.price)
            categoriesLayout = itemView.findViewById(R.id.categories_layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item_layout,
            parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val data = categoriesList[position]
        holder.image.setImageResource(data.getImage())
        holder.title.text = data.getTitle()
        holder.rating.text = data.getRating()
        holder.duration.text = data.getDuration()
        holder.price.text = data.getPrice()

        holder.categoriesLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(data)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = categoriesList.size
}