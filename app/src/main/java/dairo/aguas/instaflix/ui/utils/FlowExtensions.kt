package dairo.aguas.instaflix.ui.utils

import dairo.aguas.instaflix.domain.exception.DomainException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
internal fun <T> Flow<T>.handleViewModelExceptions(onError: (DomainException) -> Unit): Flow<T> =
    flow {
        try {
            collect { value -> emit(value) }
        } catch (e: DomainException) {
            onError(e)
        }
    }