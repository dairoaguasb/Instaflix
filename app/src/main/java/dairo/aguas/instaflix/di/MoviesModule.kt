package dairo.aguas.instaflix.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.instaflix.data.endpoints.MovieAPI
import dairo.aguas.instaflix.data.repository.MovieRepositoryImpl
import dairo.aguas.instaflix.domain.repository.MovieRepository
import dairo.aguas.instaflix.domain.usecase.GetMoviesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesTopRatedUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesUpcomingUseCase
import dairo.aguas.instaflix.ui.movies.MoviesViewModel
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
object MoviesModule {

    @Provides
    fun moviesViewModelProvider(
        getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase,
        getMoviesPopularUseCase: GetMoviesPopularUseCase,
        getMoviesTopRatedUseCase: GetMoviesTopRatedUseCase,
        coroutineDispatcher: CoroutineDispatcher
    ) = MoviesViewModel(
        getMoviesUpcomingUseCase,
        getMoviesPopularUseCase,
        getMoviesTopRatedUseCase,
        coroutineDispatcher
    )

    @Provides
    @ViewModelScoped
    fun getMoviesLatestUseCaseProvider(movieRepository: MovieRepository) =
        GetMoviesUpcomingUseCase(movieRepository)

    @Provides
    @ViewModelScoped
    fun getMoviesPopularUseCaseProvider(movieRepository: MovieRepository) =
        GetMoviesPopularUseCase(movieRepository)

    @Provides
    @ViewModelScoped
    fun getMoviesTopRatedUseCaseProvider(movieRepository: MovieRepository) =
        GetMoviesTopRatedUseCase(movieRepository)

    @Provides
    @ViewModelScoped
    fun moviesRepositoryProvider(movieAPI: MovieAPI, apiKey: String): MovieRepository =
        MovieRepositoryImpl(movieAPI, apiKey)

    @Provides
    @ViewModelScoped
    fun movieAPIProvide(retrofit: Retrofit): MovieAPI =
        retrofit.create(MovieAPI::class.java)
}