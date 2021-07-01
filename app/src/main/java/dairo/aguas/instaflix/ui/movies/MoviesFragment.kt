package dairo.aguas.instaflix.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.databinding.FragmentMoviesBinding
import dairo.aguas.instaflix.ui.adapter.MoviesAdapter
import dairo.aguas.instaflix.ui.adapter.OnListenerDetail
import dairo.aguas.instaflix.ui.detail.DetailFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnListenerDetail {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private val moviesAdapter by lazy { MoviesAdapter(this) }
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.getMoviesPopular()
        subscribeToMoviesState()
        setupAdapter()
        setOptionListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToMoviesState() {
        moviesViewModel.state.onEach(::handleMoviesState).launchIn(lifecycleScope)
    }

    private fun setupAdapter() {
        binding.rvMovies.apply {
            adapter = moviesAdapter
        }
    }

    private fun setOptionListener() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chipPopular -> {
                    moviesViewModel.getMoviesPopular()
                }
                R.id.chipUpComing -> {
                    moviesViewModel.getMoviesUpcoming()
                }
                R.id.chipTopRated -> {
                    moviesViewModel.getMoviesTopRated()
                }
            }
        }
    }

    private fun handleMoviesState(moviesState: MoviesState) {
        when (moviesState) {
            is MoviesState.Loading -> {
                binding.pbLoading.visibility = View.VISIBLE
            }
            is MoviesState.Success -> {
                binding.pbLoading.visibility = View.INVISIBLE
                moviesAdapter.submitList(moviesState.data)
            }
            is MoviesState.Error -> {

            }
        }
    }

    override fun onClickListener(id: Int) {
        this.findNavController().navigate(
            MoviesFragmentDirections.actionNavigationDashboardToDetailFragment(
                id,
                DetailFragment.TYPE_MOVIE
            )
        )
    }
}