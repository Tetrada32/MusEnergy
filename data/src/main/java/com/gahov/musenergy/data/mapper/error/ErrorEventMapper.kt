package com.gahov.musenergy.data.mapper.error

import com.gahov.domain.common.converter.Mapper
import com.gahov.domain.entities.failure.ErrorEntity
import com.gahov.musenergy.data.remote.entities.error.ErrorResponse


object ErrorEventMapper {
    class ErrorMessageResponseToEntity : Mapper<ErrorResponse, ErrorEntity>() {
        override fun map(from: ErrorResponse): ErrorEntity {
            return ErrorEntity(
                message = from.message.orEmpty(),
                status = from.status.orEmpty(),
                code = from.code.orEmpty()
            )
        }
    }
}