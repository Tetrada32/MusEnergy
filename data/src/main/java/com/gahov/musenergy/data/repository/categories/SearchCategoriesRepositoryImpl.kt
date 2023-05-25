package com.gahov.musenergy.data.repository.categories

import com.gahov.domain.entities.common.Either
import com.gahov.domain.entities.common.right
import com.gahov.domain.entities.failure.Failure
import com.gahov.domain.entities.news.CategoryEntity
import com.gahov.domain.entities.search.SearchNewsCategory
import com.gahov.domain.repository.news.SearchCategoriesRepository

class SearchCategoriesRepositoryImpl : SearchCategoriesRepository {

    override suspend fun getSearchCategories(): Either<Failure, List<CategoryEntity>> {
        return right(hardcodeCategories())
    }

    private fun hardcodeCategories(): List<CategoryEntity> {
        val listOfCategories = mutableListOf<CategoryEntity>()
        listOfCategories.clear()
        listOfCategories.add(
            CategoryEntity(
                id = SearchNewsCategory.CATEGORY_ALL.id,
                title = SearchNewsCategory.CATEGORY_ALL.id,
                description = "",
                url = "",
                imageUrl = "https://images.pexels.com/photos/154147/pexels-photo-154147.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )
        listOfCategories.add(
            CategoryEntity(
                id = "1",
                title = SearchNewsCategory.CATEGORY_ROCK.id,
                description = "",
                url = "",
                imageUrl = "https://images.pexels.com/photos/144428/pexels-photo-144428.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )
        listOfCategories.add(
            CategoryEntity(
                id = "2",
                title = SearchNewsCategory.CATEGORY_POP.id,
                description = "",
                url = "",
                imageUrl = "https://images.pexels.com/photos/7586647/pexels-photo-7586647.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )
        listOfCategories.add(
            CategoryEntity(
                id = "3",
                title = SearchNewsCategory.CATEGORY_RAP.id,
                description = "",
                url = "",
                imageUrl = "https://images.pexels.com/photos/1587927/pexels-photo-1587927.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )
        listOfCategories.add(
            CategoryEntity(
                id = "4",
                title = SearchNewsCategory.CATEGORY_CLASSIC.id,
                description = "",
                url = "",
                imageUrl = "https://images.pexels.com/photos/16099499/pexels-photo-16099499/free-photo-of-people-woman-festival-music.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )
        listOfCategories.add(
            CategoryEntity(
                id = "5",
                title = SearchNewsCategory.CATEGORY_JAZZ.id,
                description = "",
                url = "",
                imageUrl = "https://images.pexels.com/photos/8044098/pexels-photo-8044098.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )
        listOfCategories.add(
            CategoryEntity(
                id = "6",
                title = SearchNewsCategory.CATEGORY_BLUES.id,
                description = "",
                url = "",
                imageUrl = "https://images.pexels.com/photos/8044225/pexels-photo-8044225.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            )
        )
        return listOfCategories
    }
}