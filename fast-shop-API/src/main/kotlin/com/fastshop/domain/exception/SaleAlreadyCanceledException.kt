package com.fastshop.domain.exception

import java.time.LocalDateTime

class SaleAlreadyCanceledException(id: Long, date: LocalDateTime) :
    BusinessException("The Sale with id '$id' waas already Canceled. " +
            "Cancelation date:  $date ")