package com.gahov.musenergy.feature.stories.model

import com.gahov.domain.entities.stories.StoryEntity


data class StoryModel(
    val items: List<StoryEntity>,
    val durations: LongArray?,
    val itemsCount: Int
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}