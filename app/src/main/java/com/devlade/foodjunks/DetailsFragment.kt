package com.devlade.foodjunks

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar


class DetailsFragment : Fragment(), View.OnClickListener {
    private lateinit var addToCart: LinearLayout
    private lateinit var addToOrder: ImageButton
    private lateinit var minusFromOrder: ImageButton
    private lateinit var backBtn: ImageButton
    private lateinit var favoriteBtn: ImageButton
    private var amount: Int = 0
    private val args by navArgs<DetailsFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        val image: ImageView = view.findViewById(R.id.details_image)
        val title: TextView = view.findViewById(R.id.details_title)
        val rating: TextView = view.findViewById(R.id.details_rating)
        val price: TextView = view.findViewById(R.id.details_price)
        val orderAmount: TextView = view.findViewById(R.id.order_amount)

        image.setImageResource(args.currentItem.getImage())
        title.text = args.currentItem.getTitle()
        rating.text = args.currentItem.getRating()
        price.text = args.currentItem.getPrice()

        backBtn = view.findViewById(R.id.back_btn)
        favoriteBtn = view.findViewById(R.id.favorite_btn)
        addToCart = view.findViewById(R.id.add_to_cart_btn)
        addToOrder = view.findViewById(R.id.add_btn)
        minusFromOrder = view.findViewById(R.id.minus_btn)


        backBtn.setOnClickListener(this)
        addToCart.setOnClickListener(this)
        favoriteBtn.setOnClickListener(this)

        addToOrder.setOnClickListener {
            amount++
            orderAmount.text = (amount).toString()
        }
        minusFromOrder.setOnClickListener {
            if(amount != 0){
                amount--
                orderAmount.text = (amount).toString()
            }
            else
                orderAmount.text = amount.toString()
        }

        return view
    }

    override fun onClick(view: View?) {

        when (view) {
            backBtn -> {
                val action = DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
                backBtn.findNavController().navigate(action)
            }
            addToCart -> {
                Toast.makeText(
                    activity,
                    "$amount ${args.currentItem.getTitle()}"+ "(s) has been added to cart",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            favoriteBtn -> {
                favoriteBtn.setImageResource(R.drawable.ic_favorite_filled)
                Toast.makeText(activity, "Added to Favorites", Toast.LENGTH_SHORT).show()
            }

        }
    }
}