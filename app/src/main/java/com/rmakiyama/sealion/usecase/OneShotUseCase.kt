package com.rmakiyama.sealion.usecase

abstract class OneShotUseCase<in P, out T> {

    operator fun invoke(params: P) = execute(params)

    protected abstract fun execute(params: P): T
}
