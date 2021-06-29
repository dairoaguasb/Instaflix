package dairo.aguas.instaflix.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getMoviesPopular() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }

    fun getMoviesLatest() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }

    fun getMoviesTopRated() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }
}