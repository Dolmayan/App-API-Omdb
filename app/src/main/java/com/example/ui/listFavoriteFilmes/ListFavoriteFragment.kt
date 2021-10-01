package com.example.ui.listFavoriteFilmes

import androidx.navigation.findNavController
import com.example.data.response.SimpleFilm
import com.example.lista_de_filmes_oficial.R
import com.example.lista_de_filmes_oficial.databinding.FragmentFilmesFavoritosBinding
import com.example.ui.adapter.FilmesAdapter
import com.example.ui.listFavoriteFilmes.di.ListFavoriteFilmModule.LIST_FAVORITE_SCOPE
import com.example.ui.listFavoriteFilmes.di.ListFavoriteFilmModule.LIST_FAVORITE_SCOPE_ID
import com.example.ui.listFavoriteFilmes.viewModel.ListFavoriteViewModel
import com.example.utils.base.BaseFragment
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class ListFavoriteFragment: BaseFragment<FragmentFilmesFavoritosBinding>() {

    private val scope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        LIST_FAVORITE_SCOPE_ID,
        named(LIST_FAVORITE_SCOPE)
    )

    private var viewModel: ListFavoriteViewModel = scope.get()

    private lateinit var filmsAdapter: FilmesAdapter

    override fun getLayout() = R.layout.fragment_filmes_favoritos

    override fun getViewModel() = viewModel

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        val items = mutableListOf<SimpleFilm>()
        filmsAdapter = FilmesAdapter(items) { filmModel: SimpleFilm, i: Int ->
            navigationForDetails(filmModel, i)
        }.also {
            filmsAdapter = it
        }

        binding.sum.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_favorite_film_to_list_film)
        }
    }

    override fun observers() {
            viewModel.films.observe(viewLifecycleOwner) {
                val newList: MutableList<SimpleFilm> = mutableListOf()
                for(film in it){
                    newList.add(SimpleFilm(film.title!!,film.year!!, film.imdbID!!, film.poster!!))
                }
                filmsAdapter.updateList(newList)
            }
    }

    private fun navigationForDetails(
        model: SimpleFilm,
        position: Int
    ) {
        val action = ListFavoriteFragmentDirections.actionFavoriteFilmToDetailsFilm(model.id)
        view?.findNavController()?.navigate(action)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFilms()
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }
}