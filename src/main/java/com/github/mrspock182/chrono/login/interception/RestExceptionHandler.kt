package com.github.mrspock182.chrono.login.interception

import com.github.mrspock182.chrono.login.domain.dto.ErrorResponse
import com.github.mrspock182.chrono.login.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(NotFound::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(notFound: NotFound, request: ServerHttpRequest) : ErrorResponse {
        return ErrorResponse(request.id,
                Date(),
                request.uri.path,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name,
                notFound.message)
    }

    @ExceptionHandler(BadRequest::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(badRequest: BadRequest, request: ServerHttpRequest) : ErrorResponse {
        return ErrorResponse(request.id,
                Date(),
                request.uri.path,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name,
                badRequest.message)
    }

    @ExceptionHandler(InternalServerService::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerService(internalServerService: InternalServerService,
                                    request: ServerHttpRequest) : ErrorResponse {
        return ErrorResponse(request.id,
                Date(),
                request.uri.path,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name,
                internalServerService.message)
    }

    @ExceptionHandler(Conflict::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleConflict(conflict: Conflict, request: ServerHttpRequest) : ErrorResponse {
        return ErrorResponse(request.id,
                Date(),
                request.uri.path,
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name,
                conflict.message)
    }


    @ExceptionHandler(Unauthorized::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnauthorized(unauthorized: Unauthorized, request: ServerHttpRequest) : ErrorResponse {
        return ErrorResponse(request.id,
                Date(),
                request.uri.path,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name,
                unauthorized.message)
    }

}