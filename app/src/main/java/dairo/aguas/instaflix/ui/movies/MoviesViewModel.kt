package dairo.aguas.instaflix.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.usecase.GetMoviesPopularUseCase
import dairo.aguas.instaflix.ui.model.ItemViewData
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
class MoviesViewModel @Inject constructor(
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state.asStateFlow()

    init {
        getMoviesPopular()
    }

    fun getMoviesPopular() {
        getMoviesPopularUseCase.invoke().map { moviesResult ->
            moviesResult.fold(
                ifRight = {
                    _state.value = MoviesState(
                        items = it.movies.map { movie ->
                            ItemViewData(movie)
                        }
                    )
                },
                ifLeft = {
                    _state.value = MoviesState(error = manageErrorsToPresentation(it))
                }
            )
        }.onStart {
            _state.value = MoviesState(loading = true)
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}
