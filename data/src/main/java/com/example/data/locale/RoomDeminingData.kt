package com.example.data.locale

import com.example.data.locale.dao.DeminingDao
import com.example.data.locale.entity.DeminingEntity

class RoomDeminingData(private val deminingDao: DeminingDao) {

    suspend fun insertDeminingData(demining: DeminingEntity): Result<Unit> = runCatching {
        deminingDao.insertDeminingInfo(demining)
    }

    suspend fun getDeminingData(token: String): Result<List<DeminingEntity>> = runCatching {
        deminingDao.getDetails(token)
    }
}