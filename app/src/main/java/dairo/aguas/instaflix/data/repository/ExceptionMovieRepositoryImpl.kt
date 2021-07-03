package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.exception.HttpErrors.getHttpError
import dairo.aguas.instaflix.domain.exception.CommonErrors
import dairo.aguas.instaflix.domain.exception.DomainException
import dairo.aguas.instaflix.domain.repository.DomainExceptionRepository
import retrofit2.HttpException

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
class ExceptionMovieRepositoryImpl : CommonErrors(), DomainExceptionRepository {

    override fun manageError(error: Throwable): DomainException {
        return if (error is HttpException) {
            getHttpError(error)
        } else {
            manageException(error)
        }
    }
}