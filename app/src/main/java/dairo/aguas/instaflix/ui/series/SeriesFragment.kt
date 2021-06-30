package dairo.aguas.instaflix.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.databinding.FragmentSeriesBinding
import dairo.aguas.instaflix.ui.adapter.SeriesAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SeriesFragment : Fragment() {

    private val seriesViewModel: SeriesViewModel by viewModels()
    private val seriesAdapter by lazy { SeriesAdapter() }
    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seriesViewModel.getSeriesPopular()
        subscribeToSeriesState()
        setupAdapter()
        setOptionListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToSeriesState() {
        seriesViewModel.state.onEach(::handleSeriesState).launchIn(lifecycleScope)
    }

    private fun setupAdapter() {
        binding.rvMovies.apply {
            adapter = seriesAdapter
        }
    }

    private fun setOptionListener() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chipPopular -> {
                    seriesViewModel.getSeriesPopular()
                }
                R.id.chipOnAir -> {
                    seriesViewModel.getSeriesOnAir()
                }
                R.id.chipTopRated -> {
                    seriesViewModel.getSeriesTopRated()
                }
            }
        }
    }

    private fun handleSeriesState(seriesState: SeriesState) {
        when (seriesState) {
            is SeriesState.Loading -> {
                binding.pbLoading.visibility = View.INVISIBLE
            }
            is SeriesState.Success -> {
                binding.pbLoading.visibility = View.INVISIBLE
                seriesAdapter.submitList(seriesState.data)
            }
            is SeriesState.Error -> {

            }
        }
    }
}