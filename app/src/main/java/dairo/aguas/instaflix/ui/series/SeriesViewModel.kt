package dairo.aguas.instaflix.ui.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetSeriesOnAirUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesTopRatedUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getSeriesOnAirUseCase: GetSeriesOnAirUseCase,
    private val getSeriesPopularUseCase: GetSeriesPopularUseCase,
    private val getSeriesTopRatedUseCase: GetSeriesTopRatedUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getSeriesPopular() {
        getSeriesPopularUseCase.invoke("").map { seriesResult ->
            if (seriesResult is Result.Success) {
                Log.d("getSeriesPopular", "${seriesResult.data.series}")
            }
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun getSeriesOnAir() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }

    fun getSeriesTopRated() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }
}