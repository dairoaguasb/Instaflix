package dairo.aguas.instaflix.ui.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetMovieDetailUseCase
import dairo.aguas.instaflix.domain.usecase.GetSerieDetailUseCase
import dairo.aguas.instaflix.ui.base.BaseViewModel
import dairo.aguas.instaflix.ui.model.DetailViewData
import dairo.aguas.instaflix.ui.utils.handleViewModelExceptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getSerieDetailUseCase: GetSerieDetailUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<DetailState>(DetailState.Loading) {

    fun getMovieDetail(id: Int) {
        getMovieDetailUseCase.invoke(id).map { movieResult ->
            if (movieResult is Result.Success) {
                mutableState.value = DetailState.Success(
                    data = DetailViewData(movieResult.data)
                )
            } else if (movieResult is Result.Failure) {
                mutableState.value = DetailState.Error(
                    resource = manageException(movieResult.domainException)
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = DetailState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }


    fun getSerieDetail(id: Int) {
        getSerieDetailUseCase.invoke(id).map { serieResult ->
            if (serieResult is Result.Success) {
                mutableState.value = DetailState.Success(
                    data = DetailViewData(serieResult.data)
                )
            } else if (serieResult is Result.Failure) {
                mutableState.value = DetailState.Error(
                    resource = manageException(serieResult.domainException)
                )
            }
        }.handleViewModelExceptions {
            mutableState.value = DetailState.Error(manageException(it))
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}