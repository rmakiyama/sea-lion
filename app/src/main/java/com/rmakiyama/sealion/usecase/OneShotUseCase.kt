package com.rmakiyama.sealion.usecase

abstract class OneShotUseCase<in P, out T> {

    suspend operator fun invoke(params: P) = execute(params)

    protected abstract suspend fun execute(params: P): T
}
