package dairo.aguas.instaflix.domain.model

import arrow.core.Either
import dairo.aguas.instaflix.domain.exception.DomainException

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */

typealias Result<T> = Either<DomainException, T>
