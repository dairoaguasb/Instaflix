package dairo.aguas.instaflix.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.instaflix.data.endpoints.SerieAPI
import dairo.aguas.instaflix.data.repository.ExceptionSeriesRepositoryImpl
import dairo.aguas.instaflix.data.repository.SeriesRepositoryImpl
import dairo.aguas.instaflix.domain.repository.DomainExceptionRepository
import dairo.aguas.instaflix.domain.repository.SerieRepository
import dairo.aguas.instaflix.domain.usecase.GetSeriesOnAirUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesTopRatedUseCase
import dairo.aguas.instaflix.ui.series.SeriesViewModel
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
object SeriesModule {

    @Provides
    fun seriesViewModelProvider(
        getSeriesOnAirUseCase: GetSeriesOnAirUseCase,
        getSeriesPopularUseCase: GetSeriesPopularUseCase,
        getSeriesTopRatedUseCase: GetSeriesTopRatedUseCase,
        coroutineDispatcher: CoroutineDispatcher
    ) = SeriesViewModel(
        getSeriesOnAirUseCase,
        getSeriesPopularUseCase,
        getSeriesTopRatedUseCase,
        coroutineDispatcher
    )

    @Provides
    @ViewModelScoped
    fun getSeriesOnAirUseCaseProvider(serieRepository: SerieRepository) =
        GetSeriesOnAirUseCase(serieRepository)

    @Provides
    @ViewModelScoped
    fun getSeriesPopularUseCaseProvider(serieRepository: SerieRepository) =
        GetSeriesPopularUseCase(serieRepository)

    @Provides
    @ViewModelScoped
    fun getSeriesTopRatedProvider(serieRepository: SerieRepository) =
        GetSeriesTopRatedUseCase(serieRepository)

    @Provides
    @ViewModelScoped
    fun serieRepositoryProvider(
        serieAPI: SerieAPI,
        apiKey: String,
        @Named(EXCEPTION_SERIES_REPOSITORY) exceptionMovie: DomainExceptionRepository
    ): SerieRepository =
        SeriesRepositoryImpl(serieAPI, apiKey, exceptionMovie)

    @Provides
    @ViewModelScoped
    fun serieAPIProvide(retrofit: Retrofit): SerieAPI =
        retrofit.create(SerieAPI::class.java)


    @Named(EXCEPTION_SERIES_REPOSITORY)
    @Provides
    @ViewModelScoped
    fun exceptionSeriesRepositoryProvide(): DomainExceptionRepository =
        ExceptionSeriesRepositoryImpl()
}

const val EXCEPTION_SERIES_REPOSITORY = "exceptionSeriesRepositoryProvide"