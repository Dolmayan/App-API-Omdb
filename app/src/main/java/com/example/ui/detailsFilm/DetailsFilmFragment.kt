package com.example.ui.detailsFilm

import android.graphics.Color
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lista_de_filmes_oficial.R
import com.example.lista_de_filmes_oficial.databinding.FragmentDetailsFilmBinding
import com.example.ui.detailsFilm.di.DetailsFilmModule.DETAILS_FILM_SCOPE
import com.example.ui.detailsFilm.di.DetailsFilmModule.DETAILS_FILM_SCOPE_ID
import com.example.ui.detailsFilm.viewModel.DetailsFilmViewModel
import com.example.utils.base.BaseFragment
import com.squareup.picasso.Picasso
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class DetailsFilmFragment : BaseFragment<FragmentDetailsFilmBinding>() {

    private val scope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        DETAILS_FILM_SCOPE_ID,
        named(DETAILS_FILM_SCOPE)
    )

    private var viewModel: DetailsFilmViewModel = scope.get()

    override fun getLayout() = R.layout.fragment_details_film

    override fun getViewModel() = viewModel

    private val args: DetailsFilmFragmentArgs by navArgs()

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        viewModel.getDetailsFilm(args.id)
//        viewModel.getFilm(args.id)
//
//        binding.btnStar.setOnClickListener {
//            if (viewModel.favorite.value!!) {
//                viewModel.delete(args.id)
//            } else {
//                viewModel.add(args.id)
//            }
//        }

        binding.buttonBack.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }
    }

    override fun observers() {
        viewModel.response.observe(viewLifecycleOwner) {
            Picasso.get()
                .load(it.poster)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imgId);

            binding.title.text = it.title
            binding.year.text = it.year
            binding.released.text = it.released
            binding.genre.text = it.genre
            binding.rating.text = if (it.ratings.isNotEmpty()) it.ratings[0].value else "Not Found"
            binding.plot.text = it.plot
        }

        viewModel.favorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnStar.setBackgroundColor(Color.parseColor("#F9FF13"))
            } else {
                binding.btnStar.setBackgroundColor(Color.parseColor("#AAAAAA"))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.closed
    }
}