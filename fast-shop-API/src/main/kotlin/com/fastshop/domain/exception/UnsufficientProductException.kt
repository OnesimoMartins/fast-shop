package com.fastshop.domain.exception

class UnsufficientProductException(available: Long,requested:Int) : RuntimeException(
    "No sufficient Products , was requested $requested but there is only $available available  "
) {}

