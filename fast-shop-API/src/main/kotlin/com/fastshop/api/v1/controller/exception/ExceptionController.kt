package com.fastshop.api.v1.controller.exception


import com.fastshop.api.v1.dto.response.exception.CustomFieldError
import com.fastshop.api.v1.dto.response.exception.ExceptionModel
import com.fastshop.domain.exception.BusinessException
import com.fastshop.domain.exception.SaleAlreadyConfirmedException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class ExceptionController : ResponseEntityExceptionHandler() {

//    @ExceptionHandler(Exception::class)
//    fun custromExceptionHadler(ex: Exception) = "xxxx @ex"

    override fun handleNoHandlerFoundException(ex: NoHandlerFoundException, headers: HttpHeaders,
        status: HttpStatus, request: WebRequest): ResponseEntity<Any> {

        val body= ExceptionModel(status=400, tittle = "resource does not exists",
            details = "The resource '${ex.httpMethod}' : '${ex.requestURL}' does not exists")

        return handleExceptionInternal(ex,body, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

    override fun handleHttpMediaTypeNotSupported(ex: HttpMediaTypeNotSupportedException,headers: HttpHeaders,
        status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val body= ExceptionModel(415, tittle = "Unsupported media type ",
            details = " the unique media type supported by this api is 'application/json' ")
        return handleExceptionInternal(ex,body, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders,
        status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val body= ExceptionModel(400, tittle = "Invalid body",
            details = "make sure you respected json object rules ")
        return handleExceptionInternal(ex,body, headers, HttpStatus.BAD_REQUEST, request)
    }

    override fun handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException, headers: HttpHeaders,
        status: HttpStatus, request: WebRequest): ResponseEntity<Any> {

        val body= ExceptionModel(HttpStatus.METHOD_NOT_ALLOWED.value(), tittle = "Mehtod not supported",
            details = "The Mehtod '${ex.method}' isn't supported on this request. Allowed methods:  "
        + ex.supportedHttpMethods!!.joinToString(",", prefix = "'", postfix = "'") { it.name })
        return handleExceptionInternal(ex,body, headers, HttpStatus.METHOD_NOT_ALLOWED, request)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders,
        status: HttpStatus, request: WebRequest): ResponseEntity<Any> {

        fun handleFieldError(fieldError: FieldError)=
            fieldError.let { return@let CustomFieldError(it.field,it.defaultMessage!!) }

         val body= ExceptionModel(status = 400, tittle = "Fields with error",
             details = " Was found ${ex.errorCount} error(s) in the content body",
                fiedErrors =ex.bindingResult.fieldErrors.map { handleFieldError(it) }.toList()
             )

        return handleExceptionInternal(ex, body, HttpHeaders(),HttpStatus.BAD_REQUEST,request)
    }

//    override fun handleTypeMismatch(ex: TypeMismatchException, headers: HttpHeaders,
//              status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
//        val body=ExceptionModel(status=400, tittle = "Invalid method argument",
//            details = "was given '${ex.value}' of type  in '${ex.propertyName}' but " +
//                    "type ${ex.requiredType} is required")
//        return handleExceptionInternal(ex,body, HttpHeaders(),HttpStatus.BAD_REQUEST,request);
//    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException
                                      ,webRequest: WebRequest):ResponseEntity<Any> =
        ex.let { val body= ExceptionModel(404, tittle = "Entity not found", details = ex.message!!)

            handleExceptionInternal(ex,body, HttpHeaders(),HttpStatus.NOT_FOUND,webRequest)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleDomainException(ex: BusinessException, req: WebRequest): ResponseEntity<Any> {

        lateinit var body:ExceptionModel
        lateinit var httpStatus:HttpStatus

        if(ex is SaleAlreadyConfirmedException) {
            httpStatus=HttpStatus.ALREADY_REPORTED
            body = ExceptionModel(httpStatus.value(), tittle = "Sale already confirmed", details = ex.message!!)
        }
        else{
            httpStatus=HttpStatus.BAD_REQUEST
            body = ExceptionModel(httpStatus.value(), tittle = "Bad request", details = ex.message!!)
        }
        return handleExceptionInternal(ex,body, HttpHeaders(),httpStatus,req)
    }

    override fun handleMissingPathVariable(ex: MissingPathVariableException, headers: HttpHeaders,
        status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val body=ExceptionModel(400, tittle = "Missing template variable", details = ex.message)
        return handleExceptionInternal(ex,body, headers, HttpStatus.BAD_REQUEST, request)
    }
//    override fun handleExceptionInternal(
//        ex: java.lang.Exception,
//        body: Any?,
//        headers: HttpHeaders,
//        status: HttpStatus,
//        request: WebRequest
//    ): ResponseEntity<Any> {
//
//        val body = ExceptionModel(
//            status = status.value()
//        )
//        val headers = HttpHeaders()
//
//        return super.handleExceptionInternal(ex, body, headers, status, request)
//    }
}