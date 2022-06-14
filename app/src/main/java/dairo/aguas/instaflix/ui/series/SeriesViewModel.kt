package dairo.aguas.instaflix.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetSeriesPopularUseCase
import dairo.aguas.instaflix.ui.model.ItemViewData
import dairo.aguas.instaflix.ui.utils.handleViewModelExceptions
import dairo.aguas.instaflix.ui.utils.manageErrorsToPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getSeriesPopularUseCase: GetSeriesPopularUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(SeriesState())
    val state: StateFlow<SeriesState> = _state.asStateFlow()

    init {
        getSeriesPopular()
    }

    private fun getSeriesPopular() {
        getSeriesPopularUseCase.invoke().map { seriesResult ->
            seriesResult.fold(
                ifRight = {
                    _state.value = SeriesState(
                        items = it.series.map { serie ->
                            ItemViewData(serie)
                        }
                    )
                },
                ifLeft = {
                    _state.value = SeriesState(
                        error = manageErrorsToPresentation(it)
                    )
                }
            )
        }.handleViewModelExceptions {
            _state.value = SeriesState(
                error = manageErrorsToPresentation(it)
            )
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}
