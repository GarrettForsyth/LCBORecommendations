package com.example.android.lcborecommendations.test.fixtures

import com.example.android.core.vo.LCBOItem
import com.example.android.lcborecommendations.test.createTestLCBOItem


/**
 * Query sort order should go by
 *  name,
 *  producerName,
 *  origin,
 *  primaryCategory,
 *  secondaryCategory,
 *  tertiaryCategory,
 *  tags,
 *  style,
 *  description,
 *  tastingNote,
 *  servingSuggestion
 */

var id = 0
val fixtureForBeerQuery: List<LCBOItem> = listOf(
        // Item with beer as name
        createTestLCBOItem(id++).apply {
            name = "Water"
        },

        // Item with beer in its name
        createTestLCBOItem(id++).apply {
            name = "Beer"
        },

        // Item with beer in name
        createTestLCBOItem(id++).apply {
            name = "The Best Beer Ever"
        },

        // Item with beer in producer's name
        createTestLCBOItem(id++).apply {
            producerName = "Beer"
        },

        // Item with beer in origin's name
        createTestLCBOItem(id++).apply {
            origin = "Beer"
        },

        // Item with beer in primary category
        createTestLCBOItem(id++).apply {
            primaryCategory = "Beer"
        },

        // Item with beer in secondary category
        createTestLCBOItem(id++).apply {
            secondaryCategory = "Beer"
        },

        // Item with beer in tertiary category
        createTestLCBOItem(id++).apply {
            tertiaryCategory = "Beer"
        },

        // Item with beer in tags
        createTestLCBOItem(id++).apply {
            tags = "Hoppy Bitter Beer"
        },

        // Item with beer in style
        createTestLCBOItem(id++).apply {
            style = "Beer"
        },

        // Item with beer in description
        createTestLCBOItem(id++).apply {
            style = "Beer"
        },

        // Item with beer in tasting note
        createTestLCBOItem(id++).apply {
            style = "Beer"
        },

        // Item with beer in serving suggestion
        createTestLCBOItem(id++).apply {
            style = "Beer"
        }
)

val fixtureForBeerQuerySortedAndFiltered = fixtureForBeerQuery.drop(1)

