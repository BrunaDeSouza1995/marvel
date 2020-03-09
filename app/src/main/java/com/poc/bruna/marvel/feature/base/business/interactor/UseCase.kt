package com.poc.bruna.marvel.feature.base.business.interactor

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.poc.bruna.marvel.R
import com.poc.bruna.marvel.feature.base.business.interactor.BusinessError.GENERIC_ERROR
import com.poc.bruna.marvel.feature.base.business.interactor.BusinessError.INTERNET_CONNECTION_UNAVAILABLE
import com.poc.bruna.marvel.feature.base.business.interactor.BusinessError.INVALID_UNRECOGNIZED_PARAMETER
import com.poc.bruna.marvel.feature.base.business.interactor.BusinessError.NOT_FOUND
import com.poc.bruna.marvel.feature.search.business.expection.NoResultException
import com.poc.bruna.marvel.utils.extensions.asSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class UseCase<P, R> {

    @Throws(RuntimeException::class)
    abstract suspend fun execute(param: P? = null): R

    operator fun invoke(
        param: P? = null,
        result: MutableLiveData<Result<R>>,
        error: MutableLiveData<Result.Error>? = null
    ) {
        result.value = Result.Loading

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = withContext(Dispatchers.IO) { execute(param) }.asSuccess()
                result.postValue(response)
            } catch (e: Exception) {
                result.postValue(e.handleError())
                error?.postValue(e.handleError())
            }
        }
    }
}

sealed class Result<out P> {
    data class Success<out R>(val data: R) : Result<R>()
    data class Error(val exception: BusinessError) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

enum class BusinessError(@StringRes val message: Int) {
    GENERIC_ERROR(R.string.error_generic_error),
    INTERNET_CONNECTION_UNAVAILABLE(R.string.error_internet_connection_unavailable),
    INVALID_UNRECOGNIZED_PARAMETER(R.string.error_invalid_unrecognized_parameter),
    NOT_FOUND(R.string.error_not_found);
}

fun Exception.handleError(): Result.Error {
    val error = when (this) {
        is UnknownHostException -> INTERNET_CONNECTION_UNAVAILABLE
        is NoResultException -> NOT_FOUND
        is NoSuchElementException -> NOT_FOUND
        is HttpException -> {
            when (code()) {
                404 -> NOT_FOUND
                else -> INVALID_UNRECOGNIZED_PARAMETER
            }
        }
        else -> GENERIC_ERROR
    }

    return Result.Error(error)
}