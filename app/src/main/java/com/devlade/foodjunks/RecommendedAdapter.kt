package com.devlade.foodjunks

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class RecommendedAdapter(
    private val recommendedList: ArrayList<RecommendedHelper>,
    private val viewPager2: ViewPager2,
) : RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>() {

    private val runnable: Runnable = Runnable {
        recommendedList.addAll(recommendedList)
        notifyDataSetChanged()
    }

    class RecommendedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val name: TextView
        val description: TextView
        val price: TextView

        init {
            image = itemView.findViewById(R.id.meal_image)
            name = itemView.findViewById(R.id.meal_name)
            description = itemView.findViewById(R.id.meal_description)
            price = itemView.findViewById(R.id.meal_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recommended_item_layout,
            parent, false)
        return RecommendedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        val data = recommendedList[position]
        holder.image.setImageResource(data.getImage())
        holder.name.text = data.getName()
        holder.description.text = data.getDescription()
        holder.price.text = data.getPrice()

        if (position == recommendedList.size - 1) {
            viewPager2.post(runnable)
        }

    }

    override fun getItemCount(): Int = recommendedList.size
}