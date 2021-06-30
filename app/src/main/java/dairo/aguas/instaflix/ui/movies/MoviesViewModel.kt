package dairo.aguas.instaflix.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetMoviesLatestUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesTopRatedUseCase
import dairo.aguas.instaflix.ui.base.BaseViewModel
import dairo.aguas.instaflix.ui.model.MovieViewData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesLatestUseCase: GetMoviesLatestUseCase,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase,
    private val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<MoviesState>(MoviesState.Loading) {

    fun getMoviesPopular() {
        getMoviesPopularUseCase.invoke().map { moviesResult ->
            if (moviesResult is Result.Success) {
                mutableState.value = MoviesState.Success(
                    data = moviesResult.data.movies.map {
                        MovieViewData(it)
                    }
                )
            } else if (moviesResult is Result.Failure) {
                mutableState.value = MoviesState.Error(
                    moviesResult.exception.message ?: "ERROR DESCONICIDO"
                )
            }
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun getMoviesLatest() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }

    fun getMoviesTopRated() {
        // TODO: 29/06/2021 Implementar el caso de uso
    }
}