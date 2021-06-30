package dairo.aguas.instaflix.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.instaflix.data.endpoints.MovieAPI
import dairo.aguas.instaflix.data.repository.MovieRepositoryImpl
import dairo.aguas.instaflix.domain.repository.MovieRepository
import dairo.aguas.instaflix.domain.usecase.GetMoviesLatestUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetMoviesTopRatedUseCase
import retrofit2.Retrofit

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
object MoviesModule {

    @Provides
    @ViewModelScoped
    fun getMoviesLatestUseCaseProvider(movieRepository: MovieRepository) =
        GetMoviesLatestUseCase(movieRepository)

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
    fun moviesRepositoryProvider(movieAPI: MovieAPI): MovieRepository =
        MovieRepositoryImpl(movieAPI)

    @Provides
    @ViewModelScoped
    fun movieAPIProvide(retrofit: Retrofit): MovieAPI =
        retrofit.create(MovieAPI::class.java)
}