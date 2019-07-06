package com.example.android.lcborecommendations.test

import com.example.android.core.vo.LCBOItem

/**
 * Expect the results to be sorted by alcohol content, then name and only of category 'Beer'
 */
fun orderByAlcoholContentAndFilterPrimaryCategoryByBeer(items: List<LCBOItem>): List<LCBOItem> {
    return items
        .filter { it.primaryCategory == "Beer" }
        .sortedWith(compareBy( {it.alcoholContent }, { it.name }))
}
