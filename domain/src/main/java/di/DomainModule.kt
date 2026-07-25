package di

import com.ceej.common.domain.usecase.auth.IsUserLoggedInUseCase
import com.ceej.common.domain.usecase.auth.SignInUseCase
import com.ceej.common.domain.usecase.auth.SignOutUseCase
import com.ceej.common.domain.usecase.auth.SignUpUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        SignUpUseCase(
            authRepository = get()
        )
    }
    factory {
        SignInUseCase(
            authRepository = get()
        )
    }
    factory {
        SignOutUseCase(
            authRepository = get()
        )
    }
    factory {
        IsUserLoggedInUseCase(
            authRepository = get()
        )
    }
}
