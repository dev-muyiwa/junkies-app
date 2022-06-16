package com.devlade.foodjunks

import android.os.*
import android.text.Html
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.*
import androidx.viewpager2.widget.ViewPager2.*
import kotlin.math.abs


class HomeFragment : Fragment() {

    private lateinit var handler: Handler
    private lateinit var recommendedViewPager: ViewPager2
    private lateinit var recommendedAdapter: RecommendedAdapter
    private lateinit var indicators: Array<TextView?>
    private lateinit var indicatorLayout: LinearLayout
    private var recommendedList: ArrayList<RecommendedHelper> = ArrayList()
    private var mRecommendedListPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        indicatorLayout = view.findViewById(R.id.indicator_layout)
        loadCategoriesView(view)
        loadRecommendedView(view)
        setUpTransformer()
//        addViewPagerIndicator(0)
        recommendedViewPager.registerOnPageChangeCallback(object :
        OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
//                addViewPagerIndicator(position)
//                mRecommendedListPosition = position
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
        return view
    }

    private fun addViewPagerIndicator(position: Int) {
        indicators = arrayOfNulls(recommendedList.size)
        indicatorLayout.removeAllViews()
        for (i in indicators.indices){
            indicators[i] = TextView(context)
            indicators[i]!!.text = Html.fromHtml("&#8226;")
            indicators[i]!!.textSize = 35f
            indicatorLayout.addView(indicators[i])

            if (indicators.isNotEmpty()){
                indicators[position]!!.setTextColor(
                    resources.getColor(R.color.yellow))
            }
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }

    private fun loadRecommendedView(view: View) {
        recommendedViewPager = view.findViewById(R.id.recommended_view_pager)
        handler = Handler(Looper.myLooper()!!)

        recommendedList.add(RecommendedHelper(R.drawable.wine_v1,
            "Cabernet Sauvignon", "Elite French Wine", "#8,400"))
        recommendedList.add(RecommendedHelper(R.drawable.pizza_v1,
            "Chicken Pizza Combo", "Pizza with a 1 litre of soda", "#6,400"))
        recommendedList.add(RecommendedHelper(R.drawable.burger_v2,
            "Krabby Combo Burger", "Burger, Chips & Milkshake", "#6,700"))
        recommendedList.add(RecommendedHelper(R.drawable.wine_v1,
            "Cabernet Sauvignon", "Elite French Wine", "#8,400"))
        recommendedList.add(RecommendedHelper(R.drawable.pizza_v1,
            "Chicken Pizza Combo", "Pizza with a 1 litre of soda", "#6,400"))
        recommendedList.add(RecommendedHelper(R.drawable.burger_v3,
            "Krabby Combo Burger Deluxe", "Burger, Chips, Milkshake & a lil bit of magic",
            "#7,700"))

        recommendedAdapter = RecommendedAdapter(recommendedList, recommendedViewPager)
        recommendedViewPager.adapter = recommendedAdapter

        recommendedViewPager.offscreenPageLimit = 2
        recommendedViewPager.clipChildren = false
        recommendedViewPager.clipToPadding = false
        recommendedViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

    private fun loadCategoriesView(view: View) {
        val recyclerView: RecyclerView =
            view.findViewById(R.id.categories_recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        val categories: ArrayList<CategoriesHelper> = ArrayList()
        categories.add(CategoriesHelper(R.drawable.burger_v2,
            "Zinger Burger", "4.6", "8 min", "#3,500"))
        categories.add(CategoriesHelper(R.drawable.pizza_v1,
            "Pepperoni Pizza", "4.8", "4 min", "#3,900"))
        categories.add(CategoriesHelper(R.drawable.plate_of_burger,
            "Mac & Cheese", "4.4", "12 min", "#4,700"))
        categories.add(CategoriesHelper(R.drawable.wine_v1,
            "Cabernet Sauvignon", "4.9", "15 min", "#8,400"))
        categories.add(CategoriesHelper(R.drawable.burger_v2,
            "Zinger Burger", "4.6", "8 min", "#3,500"))
        categories.add(CategoriesHelper(R.drawable.pizza_v1,
            "Pepperoni Pizza", "4.8", "4 min", "#3,900"))
        categories.add(CategoriesHelper(R.drawable.plate_of_burger,
            "Mac & Cheese", "4.4", "12 min", "#4,700"))
        categories.add(CategoriesHelper(R.drawable.wine_v1,
            "Cabernet Sauvignon", "4.9", "15 min", "#8,400"))

        recyclerView.adapter = CategoriesAdapter(categories)

    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(20))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        recommendedViewPager.setPageTransformer(transformer)

    }

    private val runnable = Runnable {
        recommendedViewPager.currentItem = recommendedViewPager.currentItem + 1
    }

}