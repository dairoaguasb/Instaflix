package dairo.aguas.instaflix.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
//        binding.chipPopular.setOnCheckedChangeListener { compoundButton, checked ->
//            if (compoundButton.isChecked && checked)
//                Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
//        }

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chipPopular -> {
                    Toast.makeText(requireContext(), "chipPopular", Toast.LENGTH_SHORT).show()
                }
                R.id.chipLatest -> {
                    Toast.makeText(requireContext(), "chipLatest", Toast.LENGTH_SHORT).show()
                }
                R.id.chipTopRated -> {
                    Toast.makeText(requireContext(), "chipTopRated", Toast.LENGTH_SHORT).show()
                }
            }
        }
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        moviesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}