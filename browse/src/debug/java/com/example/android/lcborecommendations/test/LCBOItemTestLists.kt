package com.example.android.lcborecommendations.test

import com.example.android.core.vo.LCBOItem
import kotlin.random.Random

fun createRandomBeerAndAlcoholContentList(): List<LCBOItem> {
    val beerCategory = "Beer"
    val maxNumberOfItems = 20
    val numberOfItems = Random.nextInt(maxNumberOfItems)
    val lcboItems = mutableListOf<LCBOItem>()
    for (i in numberOfItems downTo 0) {
        lcboItems.add(createTestLCBOItem(i).apply {
            alcoholContent = i
            primaryCategory = if (Random.nextBoolean()) beerCategory else ""
        })
    }
    lcboItems.shuffle()
    return lcboItems
}

/**
 * @return a pair of lists, the first being the items ordered by alcohol content descending
 * and having the primaryCategory equal to 'Beer', the second being randomly ordered.
 */
fun random(): Pair<List<LCBOItem>, List<LCBOItem>> {
    val beerCategory = "Beer"
    val maxNumberOfItems = 20
    val numberOfItems = Random.nextInt(maxNumberOfItems)
    val lcboItemsOrderedByAlcoholContent = mutableListOf<LCBOItem>()
    val randomLcboItems = mutableListOf<LCBOItem>()
    for (i in numberOfItems downTo 0) {
        val newLCBOItem = createTestLCBOItem(i).apply {
                alcoholContent = i
                primaryCategory = if (Random.nextBoolean()) beerCategory else ""
            }
        lcboItemsOrderedByAlcoholContent.add(newLCBOItem)
        randomLcboItems.add(newLCBOItem)
    }
    randomLcboItems.shuffle()
    return Pair(
        lcboItemsOrderedByAlcoholContent.filter { it.primaryCategory == beerCategory},
        randomLcboItems
    )
}

