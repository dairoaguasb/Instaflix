package dairo.aguas.instaflix.ui.movies

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetMoviesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesTopRatedUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesUpcomingUseCase
import dairo.aguas.instaflix.ui.base.BaseViewModel
import dairo.aguas.instaflix.ui.model.ItemViewData
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

    fun getMoviesPopular() {
        getMoviesPopularUseCase.invoke().map { moviesResult ->
            if (moviesResult is Result.Success) {
                mutableState.value = MoviesState.Success(
                    data = moviesResult.data.movies.map {
                        ItemViewData(it)
                    }
                )
            } else if (moviesResult is Result.Failure) {
                mutableState.value = MoviesState.Error(
                    resource = manageException(moviesResult.domainException)
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
                        ItemViewData(it)
                    }
                )
            } else if (moviesResult is Result.Failure) {
                mutableState.value = MoviesState.Error(
                    resource = manageException(moviesResult.domainException)
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
                        ItemViewData(it)
                    }
                )
            } else if (moviesResult is Result.Failure) {
                mutableState.value = MoviesState.Error(
                    resource = manageException(moviesResult.domainException)
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = MoviesState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}