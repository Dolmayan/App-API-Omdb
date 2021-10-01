package com.example.ui.listFilmes

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.navigation.findNavController
import com.example.data.response.SimpleFilm
import com.example.lista_de_filmes_oficial.R
import com.example.lista_de_filmes_oficial.databinding.FragmentFilmesBinding
import com.example.ui.listFilmes.di.ListaDeFIlmesModule.LISTA_DE_FILMES_SCOPE
import com.example.ui.listFilmes.di.ListaDeFIlmesModule.LISTA_DE_FILMES_SCOPE_ID
import com.example.ui.adapter.FilmesAdapter
import com.example.ui.listFilmes.viewModel.ListFilmViewModel
import com.example.utils.base.BaseFragment
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class ListFilmFragment : BaseFragment<FragmentFilmesBinding>() {

    private val scope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        LISTA_DE_FILMES_SCOPE_ID,
        named(LISTA_DE_FILMES_SCOPE)
    )

    override fun getLayout() = R.layout.fragment_filmes

    private lateinit var filmesAdapter: FilmesAdapter

    private var viewModel: ListFilmViewModel = scope.get()

    override fun getViewModel() = viewModel

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

    override fun initBinding() {
        binding.viewModel = viewModel
        this.lifecycle.addObserver(viewModel)

        val items = mutableListOf<SimpleFilm>()
        filmesAdapter = FilmesAdapter(items) { filmModel: SimpleFilm, i: Int ->
            navigationForDetails(filmModel, i)
        }.also {
            filmesAdapter = it
        }

        binding.rvListaFilmes.adapter = filmesAdapter

        binding.btBack.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }

        binding.scrollId.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
                viewModel.nextPage()
            }
        })
    }

    private fun navigationForDetails(
        model: SimpleFilm,
        position: Int
    ) {
        val action = ListFilmFragmentDirections.actionListFilmToDetailsFilm(model.id)
        view?.findNavController()?.navigate(action)
    }

    override fun observers() {
        viewModel.filmesList.observe(this, { filmesAdapter.updateList(it.search) })

        viewModel.closeKeyboard.observe(this) {
            activity?.let {
                val imm: InputMethodManager =
                    it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                var view: View? = it.currentFocus
                if (view == null) {
                    view = View(activity)
                }
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        viewModel.msgError.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        filmesAdapter.notifyDataSetChanged()
    }
}