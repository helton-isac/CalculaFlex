package br.com.calculaflex.data.remote.datasource

import br.com.calculaflex.domain.entity.RequestState
import br.com.calculaflex.domain.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@ExperimentalCoroutinesApi
class UserRemoteFakeDataSourceImpl : UserRemoteDataSource {

    override suspend fun getUserLogged(): RequestState<User> {
        delay(1000)
        return RequestState.Success(User("Helton"))
    }

}