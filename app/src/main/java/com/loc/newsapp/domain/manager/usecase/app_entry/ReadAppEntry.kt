package com.loc.newsapp.domain.manager.usecase.app_entry

import com.loc.newsapp.domain.manager.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (private val localUserManger: LocalUserManger){

    operator fun invoke(): Flow<Boolean>{
        return localUserManger.getAppEntry()

    }
}