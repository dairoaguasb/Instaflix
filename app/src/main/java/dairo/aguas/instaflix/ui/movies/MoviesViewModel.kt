package dairo.aguas.instaflix.ui.movies

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetMoviesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesTopRatedUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesUpcomingUseCase
import dairo.aguas.instaflix.ui.base.BaseViewModel
import dairo.aguas.instaflix.ui.model.MovieViewData
import dairo.aguas.instaflix.ui.utils.handleViewModelExceptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase,
    private val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<MoviesState>(MoviesState.Loading) {

    init {
        getMoviesPopular()
    }

    fun getMoviesPopular() {
        getMoviesPopularUseCase.invoke().map { moviesResult ->
            if (moviesResult is Result.Success) {
                mutableState.value = MoviesState.Success(
                    data = moviesResult.data.movies.map {
                        MovieViewData(it)
                    }
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = MoviesState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun getMoviesUpcoming() {
        getMoviesUpcomingUseCase.invoke().map { moviesResult ->
            if (moviesResult is Result.Success) {
                mutableState.value = MoviesState.Success(
                    data = moviesResult.data.movies.map {
                        MovieViewData(it)
                    }
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = MoviesState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun getMoviesTopRated() {
        getMoviesTopRatedUseCase.invoke().map { moviesResult ->
            if (moviesResult is Result.Success) {
                mutableState.value = MoviesState.Success(
                    data = moviesResult.data.movies.map {
                        MovieViewData(it)
                    }
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = MoviesState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun emptyState() {
        mutableState.value = MoviesState.Empty
    }
}