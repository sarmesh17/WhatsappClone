package com.sharkdroid.whatsappclone.domain.usecases.all_use_case

import com.sharkdroid.whatsappclone.domain.usecases.ReadLoginEntry
import com.sharkdroid.whatsappclone.domain.usecases.SaveLoginEntry

data class AppEntryUseCase(
    val readLoginEntry: ReadLoginEntry,
    val saveLoginEntry: SaveLoginEntry
)
