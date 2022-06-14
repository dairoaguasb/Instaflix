package dairo.aguas.instaflix.ui.utils

import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.domain.exception.DomainException
import dairo.aguas.instaflix.domain.exception.InternalErrorException
import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.exception.ParseException
import dairo.aguas.instaflix.domain.exception.TimeOutException

fun manageErrorsToPresentation(domainException: DomainException): Int =
    when (domainException) {
        is TimeOutException -> R.string.error_time_out
        is InternalErrorException -> R.string.error_internal_error_exception
        is ParseException -> R.string.error_parsing_error
        is NoConnectivityDomainException -> R.string.error_internet_connection
        else -> R.string.error_some_wrong
    }
