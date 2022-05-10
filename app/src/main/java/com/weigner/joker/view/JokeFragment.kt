package com.weigner.joker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.weigner.joker.R
import com.weigner.joker.model.Joke
import com.weigner.joker.presetation.JokePresenter

class JokeFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var textJoke: TextView
    private lateinit var imageJoke: ImageView

    private lateinit var presenter: JokePresenter

    companion object {
        const val CATEGORY_KEY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY_KEY)!!

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName

        progressBar = view.findViewById(R.id.progress_bar)
        textJoke = view.findViewById(R.id.text_joke)
        //imageJoke = view.findViewById(R.id.image_joke)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            presenter.findByCategory(categoryName)
        }

        presenter.findByCategory(categoryName)
    }

    fun showJoke(joke: Joke) {
        textJoke.text = joke.textJoke
        //TODO()IMAGEM
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
    }
}