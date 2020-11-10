package com.oleksandrkarpiuk.db2test.di.modules.core

import android.content.Context
import com.oleksandrkarpiuk.db2test.di.subcomponents.SubcomponentsModule
import com.oleksandrkarpiuk.db2test.utils.DateFormatter
import com.oleksandrkarpiuk.db2test.utils.StringProvider
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        SubcomponentsModule::class
    ]
)
object CoreModule {


    @Provides
    fun provideDateTimeStringFormatter(): DateFormatter {
        return DateFormatter()
    }


    @Provides
    fun provideStringProvider(context: Context): StringProvider {
        return StringProvider(context)
    }


}