package com.loc.newsapp.domain.manager.usecase.app_entry

import com.loc.newsapp.domain.manager.LocalUserManger

 class SaveAppEntry(private val localUserManger: LocalUserManger)  {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}
