package dairo.aguas.instaflix.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dairo.aguas.instaflix.databinding.DialogInformativeBinding

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
class BottomInformativeSheetDialog(private val description: String) : BottomSheetDialogFragment() {

    private var _binding: DialogInformativeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogInformativeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        initButtonsListeners()
        setAlert()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initButtonsListeners() {
        binding.btnInformativeDialog.setOnClickListener {
            dismiss()
        }
    }

    private fun setAlert() {
        binding.txvDescriptionInformativeDialog.text = description
    }
}