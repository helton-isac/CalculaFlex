package br.com.calculaflex.data.remote.datasource

import br.com.calculaflex.domain.entity.RequestState
import br.com.calculaflex.domain.entity.User
import br.com.calculaflex.domain.entity.UserLogin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@ExperimentalCoroutinesApi
class UserRemoteFirebaseDataSourceImpl : UserRemoteDataSource {

    override suspend fun getUserLogged(): RequestState<User> {
        delay(1000)
        return RequestState.Success(User("Helton"))
    }

    override suspend fun doLogin(userLogin: UserLogin): RequestState<User> {
        return RequestState.Success(User("Helton"))
    }

}