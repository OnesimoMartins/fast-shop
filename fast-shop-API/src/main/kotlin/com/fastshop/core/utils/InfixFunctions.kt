package com.fastshop.core.utils

import java.math.BigDecimal

//infix fun BigDecimal.lessThan(other:BigDecimal)= BigDecimal.ZERO > this.subtract(other)

infix fun Number.lessThan(other:Number)= 0 > this.toLong()-other.toLong()

fun BigDecimal.isPositiveOrZero():Boolean= this>= BigDecimal.ZERO

fun BigDecimal.isPositive():Boolean= this>BigDecimal.ZERO