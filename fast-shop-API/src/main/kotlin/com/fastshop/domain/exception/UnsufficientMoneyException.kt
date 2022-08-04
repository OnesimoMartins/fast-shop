package com.fastshop.domain.exception

import java.math.BigDecimal

class UnsufficientMoneyException(remainder: BigDecimal) : RuntimeException(
    "No sufficient Money , consider add  $remainder "
) {}

