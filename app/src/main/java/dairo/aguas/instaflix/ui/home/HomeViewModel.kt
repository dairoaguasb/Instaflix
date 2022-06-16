package dairo.aguas.instaflix.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableState<HomeTabs> = mutableStateOf(HomeTabs.MOVIES)
    val state: State<HomeTabs> get() = _state

    fun selectTab(tab: HomeTabs) {
        _state.value = tab
    }
}
