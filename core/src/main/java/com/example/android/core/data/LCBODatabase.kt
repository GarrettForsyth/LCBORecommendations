package com.example.android.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.core.vo.LCBOItem

@Database(
    entities = [LCBOItem::class],
    version = 1,
    exportSchema = false
)
abstract class LCBODatabase: RoomDatabase() {
    abstract fun lcboItemDao(): LCBOItemDao
}
