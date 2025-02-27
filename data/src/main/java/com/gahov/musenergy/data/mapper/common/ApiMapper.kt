package com.gahov.musenergy.data.mapper.common

interface ApiMapper<in InputApiModel, DomainModel> {

    fun toDomain(apiModel: InputApiModel): DomainModel

    fun toDomain(list: List<InputApiModel>?): List<DomainModel>
}