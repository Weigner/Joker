package com.weigner.joker.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Transformations.map
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weigner.joker.R
import com.weigner.joker.data.CategoryRemote
import com.weigner.joker.model.Category
import com.weigner.joker.presetation.HomePresenter
import com.weigner.joker.view.CategoryItem
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment() {

    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: HomePresenter

    private val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_home)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (adapter.itemCount == 0) {
            presenter.findAllCategories()
        }

        recyclerView.adapter = adapter

        adapter.setOnItemClickListener{ item, view ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(JokeFragment.CATEGORY_KEY, categoryName)

            findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showCategories(response: List<Category>) {
        val categories = response.map { CategoryItem(it) }
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}