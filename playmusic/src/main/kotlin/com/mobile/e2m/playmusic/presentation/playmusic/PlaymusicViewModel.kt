package com.mobile.e2m.playmusic.presentation.playmusic

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mobile.e2m.core.ui.base.E2MBaseViewModel
import com.mobile.e2m.playmusic.domain.usecase.GetSongsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaymusicViewModel(
    private val getSongsUseCase: GetSongsUseCase,
) : E2MBaseViewModel<PlaymusicState, Unit, Unit>(
    initialState = PlaymusicState()
) {

    init {
        getSongsInfo()
    }

    override fun handleAction(action: Unit) {

    }

    private fun getSongsInfo() {
        viewModelScope.launch {
            getSongsUseCase.invoke()
                .onStart {
                    Log.d("EManh Debug", "Get Songs Info: OnStart")
                    mutableStateFlow.update { it.copy(isLoadingSong = true) }
                }
                .catch { error ->
                    Log.d("EManh Debug", "Get Songs Info: ${error.message}")
                    mutableStateFlow.update { it.copy(isLoadingSong = false) }
                }
                .collect { result ->
                    result.onSuccess { data ->
                        Log.d("EManh Debug", "Get Songs Info: $data")
                        data.let {
                            mutableStateFlow.update {
                                state.copy(
                                    songsList = data,
                                    isLoadingSong = false,
                                )
                            }
                        }
                    }

                    result.onFailure { error ->
                        Log.d("EManh Debug", "Get Songs Info: ${error.message}")
                        mutableStateFlow.update { it.copy(isLoadingSong = false) }
                    }
                }
        }
    }

    fun parseTimeToSeconds(time: String?): Float {
        return time?.split(":")?.let { parts ->
            if (parts.size == 2) {
                val minutes = parts[0].toFloatOrNull() ?: 0f
                val seconds = parts[1].toFloatOrNull() ?: 0f
                minutes * 60 + seconds
            } else 0f
        } ?: 0f
    }
}
