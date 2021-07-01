package dairo.aguas.instaflix.domain.repository

import dairo.aguas.instaflix.domain.exception.DomainException

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
interface DomainExceptionRepository {
    fun manageError(error: Throwable): DomainException
}