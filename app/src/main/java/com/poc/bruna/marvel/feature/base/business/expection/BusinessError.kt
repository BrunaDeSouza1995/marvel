package com.poc.bruna.marvel.feature.base.business.expection

import androidx.annotation.StringRes
import com.poc.bruna.marvel.R
import com.poc.bruna.marvel.feature.base.business.data.Result
import com.poc.bruna.marvel.feature.base.business.expection.BusinessError.GENERIC_ERROR
import com.poc.bruna.marvel.feature.base.business.expection.BusinessError.INTERNET_CONNECTION_UNAVAILABLE
import com.poc.bruna.marvel.feature.base.business.expection.BusinessError.INVALID_UNRECOGNIZED_PARAMETER
import com.poc.bruna.marvel.feature.base.business.expection.BusinessError.NOT_FOUND
import com.poc.bruna.marvel.feature.search.business.expection.NoResultException
import retrofit2.HttpException
import java.net.UnknownHostException

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