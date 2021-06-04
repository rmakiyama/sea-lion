package com.rmakiyama.sealion.usecase

import kotlinx.coroutines.flow.Flow

abstract class LoadUseCase<in P, out T> {

    operator fun invoke(params: P) = load(params)

    protected abstract fun load(params: P): Flow<T>
}
