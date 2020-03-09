package com.poc.bruna.marvel.utils.extensions

import com.poc.bruna.marvel.feature.base.business.interactor.BusinessError
import com.poc.bruna.marvel.feature.base.business.interactor.Result

fun <T> T.asSuccess(): Result.Success<T> = Result.Success(this)

fun <T : BusinessError> T.asError(): Result.Error = Result.Error(this)