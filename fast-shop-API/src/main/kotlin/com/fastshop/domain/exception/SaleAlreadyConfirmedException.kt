package com.fastshop.domain.exception

import java.time.LocalDateTime

class SaleAlreadyConfirmedException(saleId:Long,cofirmationDate:LocalDateTime) :
    BusinessException("The Sale with id '$saleId' waas already confirmed. " +
            "Confirmation date:  $cofirmationDate ")

