package br.com.calculaflex.data.remote.datasource

import br.com.calculaflex.domain.entity.NewUser
import br.com.calculaflex.domain.entity.RequestState
import br.com.calculaflex.domain.entity.User
import br.com.calculaflex.domain.entity.UserLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await

@ExperimentalCoroutinesApi
class UserRemoteFirebaseDataSourceImpl(
    private val mAuth: FirebaseAuth
) : UserRemoteDataSource {

    override suspend fun getUserLogged(): RequestState<User> {

        mAuth.currentUser?.reload()

        val firebaseUser = mAuth.currentUser

        return if (firebaseUser == null) {
            RequestState.Error(Exception("Usuário não logado"))
        } else {
            RequestState.Success(User(firebaseUser.displayName ?: ""))
        }

    }

    override suspend fun doLogin(userLogin: UserLogin): RequestState<User> {

        return try {
            mAuth.signInWithEmailAndPassword(userLogin.email, userLogin.password).await()

            val firebaseUser = mAuth.currentUser

            if (firebaseUser == null) {
                RequestState.Error(Exception("Usuário ou senha inválido"))
            } else {
                RequestState.Success(User(firebaseUser.displayName ?: ""))
            }

        } catch (e: Exception) {
            RequestState.Error(Exception(e))
        }
    }

    override suspend fun resetPassword(email: String): RequestState<String> {
        return try {
            Firebase.auth.sendPasswordResetEmail(email).await()
            RequestState.Success("Verifique sua caixa de e-mail")
        } catch (e: java.lang.Exception) {
            RequestState.Error(e)
        }
    }

    override suspend fun create(newUser: NewUser): RequestState<User> {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(newUser.email, newUser.password).await()
            RequestState.Success(User(newUser.name))
        } catch (e: java.lang.Exception) {
            RequestState.Error(e)
        }
    }
}