package com.mobile.e2m.playmusic.domain.usecase

import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.playmusic.domain.repository.SongsRepository
import kotlinx.coroutines.flow.Flow

class GetSongsUseCaseImpl(
    private val songsRepository: SongsRepository
) : GetSongsUseCase {
    override fun invoke(): Flow<Result<List<SongsEntity>>> {
        return songsRepository.getAllSongs()
    }
}
