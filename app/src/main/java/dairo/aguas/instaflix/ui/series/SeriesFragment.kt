package dairo.aguas.instaflix.ui.series

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
import dairo.aguas.instaflix.databinding.FragmentSeriesBinding
import dairo.aguas.instaflix.ui.adapter.OnListenerDetail
import dairo.aguas.instaflix.ui.adapter.SeriesAdapter
import dairo.aguas.instaflix.ui.detail.DetailFragment
import dairo.aguas.instaflix.ui.utils.gone
import dairo.aguas.instaflix.ui.utils.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SeriesFragment : Fragment(), OnListenerDetail {

    private val seriesViewModel: SeriesViewModel by viewModels()
    private val seriesAdapter by lazy { SeriesAdapter(this) }
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
                binding.pbLoading.visible()
            }
            is SeriesState.Success -> {
                binding.pbLoading.gone()
                seriesAdapter.submitList(seriesState.data)
            }
            is SeriesState.Error -> {
                binding.pbLoading.gone()
            }
        }
    }

    override fun onClickListener(id: Int) {
        this.findNavController().navigate(
            SeriesFragmentDirections.actionNavigationNotificationsToDetailFragment(
                id,
                DetailFragment.TYPE_SERIE
            )
        )
    }
}