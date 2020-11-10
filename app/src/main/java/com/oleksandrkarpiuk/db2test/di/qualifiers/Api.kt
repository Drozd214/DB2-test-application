package com.oleksandrkarpiuk.db2test.di.qualifiers

import javax.inject.Qualifier

@Qualifier
annotation class Api(val type: Type) {


    enum class Type {

        NBU,
        PRIVAT_BANK
    }
}