package com.gahov.musenergy.data.mapper.common

/**
 * An interface for mapping between domain model data and database model data.
 *
 * @param InputDomainModel The type of the input domain model.
 * @param DbModel The type of the database model.
 */
interface DbMapper<InputDomainModel, DbModel> {

    /**
     * Converts an input domain model to a corresponding database model.
     *
     * @param domainModel The input domain model to be converted.
     * @return The resulting database model.
     */
    fun toDatabase(domainModel: InputDomainModel): DbModel

    /**
     * Converts an input domain model to a corresponding database model.
     *
     * @param domainModelList The list of input domain models to be converted.
     * @return The resulting database model list.
     */
    fun toDatabase(domainModelList: List<InputDomainModel>?): List<DbModel>

    /**
     * Converts a database model to the corresponding domain model.
     *
     * @param dbModel The database model to be converted.
     * @return The resulting domain model.
     */
    fun toDomain(dbModel: DbModel): InputDomainModel

    /**
     * Converts a database model to the corresponding domain model.
     *
     * @param dbModelList The list of database model to be converted.
     * @return The resulting domain model list.
     */
    fun toDomain(dbModelList: List<DbModel>?): List<InputDomainModel>
}