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
import com.squareup.picasso.Picasso
import com.weigner.joker.R
import com.weigner.joker.model.Joke
import com.weigner.joker.presetation.JokeDayPresenter

class JokeDayFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var textJoke: TextView
    private lateinit var imageJoke: ImageView

    private lateinit var presenter: JokeDayPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokeDayPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = getString(R.string.menu_joke_day)

        progressBar = view.findViewById(R.id.progress_bar)
        textJoke = view.findViewById(R.id.text_joke)
        imageJoke = view.findViewById(R.id.image_joke)

        presenter.findRandom()
    }

    fun showJoke(joke: Joke) {
        textJoke.text = joke.textJoke
        Picasso.get().load(joke.iconUrl).into(imageJoke)
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