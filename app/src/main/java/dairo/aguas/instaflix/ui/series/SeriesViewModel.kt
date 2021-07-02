package dairo.aguas.instaflix.ui.series

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetSeriesOnAirUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesTopRatedUseCase
import dairo.aguas.instaflix.ui.base.BaseViewModel
import dairo.aguas.instaflix.ui.model.SerieViewData
import dairo.aguas.instaflix.ui.movies.MoviesState
import dairo.aguas.instaflix.ui.utils.handleViewModelExceptions
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
) : BaseViewModel<SeriesState>(SeriesState.Loading) {

    fun getSeriesPopular() {
        getSeriesPopularUseCase.invoke().map { seriesResult ->
            if (seriesResult is Result.Success) {
                mutableState.value = SeriesState.Success(
                    data = seriesResult.data.series.map {
                        SerieViewData(it)
                    }
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = SeriesState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun getSeriesOnAir() {
        getSeriesOnAirUseCase.invoke().map { seriesResult ->
            if (seriesResult is Result.Success) {
                mutableState.value = SeriesState.Success(
                    data = seriesResult.data.series.map {
                        SerieViewData(it)
                    }
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = SeriesState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun getSeriesTopRated() {
        getSeriesTopRatedUseCase.invoke().map { seriesResult ->
            if (seriesResult is Result.Success) {
                mutableState.value = SeriesState.Success(
                    data = seriesResult.data.series.map {
                        SerieViewData(it)
                    }
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = SeriesState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}