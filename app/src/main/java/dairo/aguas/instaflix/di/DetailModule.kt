package dairo.aguas.instaflix.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.instaflix.domain.repository.MovieRepository
import dairo.aguas.instaflix.domain.repository.SerieRepository
import dairo.aguas.instaflix.domain.usecase.GetMovieDetailUseCase
import dairo.aguas.instaflix.domain.usecase.GetSerieDetailUseCase

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
object DetailModule {

//    @Provides
//    fun detailViewModelProvider(
//        getMovieDetailUseCase: GetMovieDetailUseCase,
//        getSerieDetailUseCase: GetSerieDetailUseCase,
//        coroutineDispatcher: CoroutineDispatcher
//    ) = DetailViewModel(
//        getMovieDetailUseCase,
//        getSerieDetailUseCase,
//        coroutineDispatcher
//    )

    @Provides
    @ViewModelScoped
    fun getMovieDetailUseCaseProvider(movieRepository: MovieRepository) =
        GetMovieDetailUseCase(movieRepository)

    @Provides
    @ViewModelScoped
    fun getSerieDetailUseCaseProvider(serieRepository: SerieRepository) =
        GetSerieDetailUseCase(serieRepository)
}
