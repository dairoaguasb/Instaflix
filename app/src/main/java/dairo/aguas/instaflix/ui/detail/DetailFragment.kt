package dairo.aguas.instaflix.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.databinding.FragmentDetailBinding
import dairo.aguas.instaflix.ui.model.DetailViewData
import dairo.aguas.instaflix.ui.utils.gone
import dairo.aguas.instaflix.ui.utils.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgsBundle()
        subscribeToDetailState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getArgsBundle() {
        DetailFragmentArgs.fromBundle(requireArguments()).let {
            when (it.type) {
                TYPE_MOVIE -> detailViewModel.getMovieDetail(it.id)
                TYPE_SERIE -> detailViewModel.getSerieDetail(it.id)
            }
        }
    }

    private fun subscribeToDetailState() {
        detailViewModel.state.onEach(::handleDetailState).launchIn(lifecycleScope)
    }

    private fun handleDetailState(detailState: DetailState) {
        when (detailState) {
            is DetailState.Loading -> {
                binding.pbLoading.visible()
            }
            is DetailState.Success -> {
                binding.pbLoading.gone()
            }
            is DetailState.Error -> {
                binding.pbLoading.gone()
            }
        }
    }

    companion object {
        const val TYPE_MOVIE = 1
        const val TYPE_SERIE = 2
    }
}