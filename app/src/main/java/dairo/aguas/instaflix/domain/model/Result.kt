package dairo.aguas.instaflix.domain.model

import dairo.aguas.instaflix.domain.exception.DomainException

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val domainException: DomainException) : Result<T>()
}

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R
): R = when (this) {
    is Result.Success -> onSuccess(data)
    is Result.Failure -> onFailure(domainException)
}

fun <T> Result<T>.isSuccess(): Boolean = when (this) {
    is Result.Success -> true
    is Result.Failure -> false
}

fun <T> Result<T>.isFailure(): Boolean = when (this) {
    is Result.Failure -> true
    is Result.Success -> false
}

fun <T> Result<T>.getFailure() = when (this) {
    is Result.Failure -> domainException
    else -> null
}

fun <T> Result<T>.getSuccess() = when (this) {
    is Result.Success -> data
    else -> null
}