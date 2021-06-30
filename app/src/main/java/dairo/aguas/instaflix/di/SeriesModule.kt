package dairo.aguas.instaflix.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.instaflix.data.endpoints.SerieAPI
import dairo.aguas.instaflix.data.repository.SeriesRepositoryImpl
import dairo.aguas.instaflix.domain.repository.SerieRepository
import dairo.aguas.instaflix.domain.usecase.GetSeriesOnAirUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesPopularUseCase
import dairo.aguas.instaflix.domain.usecase.GetSeriesTopRatedUseCase
import retrofit2.Retrofit

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
object SeriesModule {

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
    fun serieRepositoryProvider(serieAPI: SerieAPI): SerieRepository =
        SeriesRepositoryImpl(serieAPI)

    @Provides
    @ViewModelScoped
    fun serieAPIProvide(retrofit: Retrofit): SerieAPI =
        retrofit.create(SerieAPI::class.java)
}