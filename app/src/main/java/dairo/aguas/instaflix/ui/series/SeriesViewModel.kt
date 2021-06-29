package dairo.aguas.instaflix.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeriesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getSeriesPopular() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }

    fun getSeriesOnAir() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }

    fun getSeriesTopRated() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }
}