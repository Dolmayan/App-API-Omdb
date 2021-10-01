package com.example.utils.extension

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object ApiNoSessionRequest : Qualifier {
    override val value: QualifierValue
        get() = "ApiNoSessionRequest"
}

object ApiSessionRequest : Qualifier {
    override val value: QualifierValue
        get() = "ApiSessionRequest"
}