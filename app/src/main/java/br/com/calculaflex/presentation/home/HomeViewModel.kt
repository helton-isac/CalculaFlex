package br.com.calculaflex.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.calculaflex.domain.entity.DashboardMenu
import br.com.calculaflex.domain.entity.RequestState
import br.com.calculaflex.domain.usecases.GetDashboardMenuUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDashboardMenuUseCase: GetDashboardMenuUseCase
) : ViewModel() {
    var dashboardMenuState = MutableLiveData<RequestState<DashboardMenu>>()
    fun getDashboardMenu() {
        viewModelScope.launch {
            dashboardMenuState.value = getDashboardMenuUseCase.getDashboardMenu()
        }
    }
}