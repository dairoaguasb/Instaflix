package dairo.aguas.instaflix.domain.usecase

import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.model.Series
import dairo.aguas.instaflix.domain.repository.SerieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class GetSeriesOnAirUseCase(private val serieRepository: SerieRepository) {

    operator fun invoke(): Flow<Result<Series>> =
        serieRepository.getSeriesOnAir()
}