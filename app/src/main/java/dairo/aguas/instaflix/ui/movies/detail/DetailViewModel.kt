package dairo.aguas.instaflix.ui.movies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.usecase.GetMovieDetailUseCase
import dairo.aguas.instaflix.ui.model.DetailViewData
import dairo.aguas.instaflix.ui.utils.manageErrorsToPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun getMovieDetail(id: Int) {
        getMovieDetailUseCase.invoke(id).map { movieResult ->
            movieResult.fold(
                ifRight = { movie ->
                    _state.value = DetailState(detailViewData = DetailViewData(movie))
                },
                ifLeft = {
                    _state.value = DetailState(error = manageErrorsToPresentation(it))
                }
            )
        }.onStart {
            _state.value = DetailState(loading = true)
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}
