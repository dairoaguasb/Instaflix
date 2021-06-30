package dairo.aguas.instaflix.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
abstract class BaseViewModel<T>(initialState: T) : ViewModel() {

    protected val mutableState = MutableStateFlow(initialState)
    val state: Flow<T> get() = mutableState
}