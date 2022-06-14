package dairo.aguas.instaflix.domain.usecase

import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.model.Serie
import dairo.aguas.instaflix.domain.repository.SerieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
class GetSerieDetailUseCase(private val serieRepository: SerieRepository) {

    operator fun invoke(id: Int): Flow<Result<Serie>> =
        serieRepository.getSerieDetail(id)
}
