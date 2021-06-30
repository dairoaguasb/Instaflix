package dairo.aguas.instaflix.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.databinding.FragmentSeriesBinding

@AndroidEntryPoint
class SeriesFragment : Fragment() {

    private val seriesViewModel: SeriesViewModel by viewModels()
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
        setOptionListener()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}