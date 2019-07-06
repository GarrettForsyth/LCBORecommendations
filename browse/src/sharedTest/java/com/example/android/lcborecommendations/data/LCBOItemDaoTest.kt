package com.example.android.lcborecommendations.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.core.data.LCBODatabase
import com.example.android.core.data.LCBOItemDao
import com.example.android.lcborecommendations.test.createTestLCBOItem
import com.example.android.lcborecommendations.test.ktx.getValueBlocking
import com.example.android.lcborecommendations.test.listOfLcboItems
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@MediumTest
@RunWith(AndroidJUnit4::class)
class LCBOItemDaoTest  {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: LCBODatabase
    private lateinit var lcboItemDao: LCBOItemDao

    @Before
    fun createAndSeedDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            LCBODatabase::class.java
        ).allowMainThreadQueries().build()
        lcboItemDao = db.lcboItemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = db.close()

    @Test
    @Throws(IOException::class)
    fun insertAndReadByIdLcboItem() {
        // GIVEN - an lcbo item
        val itemId = 1
        val item = createTestLCBOItem(itemId)

        // WHEN - it is inserted into the database
        lcboItemDao.insertLcboItem(item)

        // WHEN - the item is fetched by its id
        val result = lcboItemDao.getLcboItemById(itemId)

        // THEN - the result is the same as the item inserted
        assertEquals(result.getValueBlocking(), item)
    }

    @Test
    @Throws(IOException::class)
    fun insertListAndReadByIdLcboItem() {
        // GIVEN - list of LCBOItems
        val numberOfItems = 3
        val items = listOfLcboItems(numberOfItems)

        // WHEN - it is inserted into the database
        lcboItemDao.insertLcboItems(items)

        for (id in 0..numberOfItems) {
            // WHEN - the item is fetched by its id
            var result = lcboItemDao.getLcboItemById(id)
            // THEN - the result is the same as the item inserted
            assertEquals(result.getValueBlocking(), items[id])
        }
    }

}