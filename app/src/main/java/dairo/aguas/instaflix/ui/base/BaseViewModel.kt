package dairo.aguas.instaflix.ui.base

import androidx.lifecycle.ViewModel
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.domain.exception.DomainException
import dairo.aguas.instaflix.domain.exception.InternalErrorException
import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.exception.ParseException
import dairo.aguas.instaflix.domain.exception.TimeOutException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
abstract class BaseViewModel<T>(initialState: T) : ViewModel() {

    protected val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<T> get() = mutableState.asStateFlow()

    fun manageException(domainException: DomainException): Int =
        when (domainException) {
            is TimeOutException -> R.string.error_time_out
            is InternalErrorException -> R.string.error_internal_error_exception
            is ParseException -> R.string.error_parsing_error
            is NoConnectivityDomainException -> R.string.error_internet_connection
            else -> R.string.error_some_wrong
        }
}
