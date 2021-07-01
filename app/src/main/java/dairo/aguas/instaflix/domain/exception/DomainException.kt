package dairo.aguas.instaflix.domain.exception

import dairo.aguas.instaflix.utils.Constants
import java.io.IOException

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
open class DomainException(override val message: String = Constants.Values.EMPTY) : Throwable(message)
object NotFoundException : DomainException()
object BadRequestException : DomainException()
object InternalErrorException : DomainException()
object UnknownError : DomainException()
object NoConnectivityException : IOException()
object NoConnectivityDomainException : DomainException()
object TimeOutException : DomainException()
object ParseException : DomainException()
data class HttpError(override val message: String) : DomainException()