package com.example.aquatrack.di

import android.app.Application
import androidx.room.Room
import com.example.aquatrack.feature_home.data.local.AquaTrackDao
import com.example.aquatrack.feature_home.data.local.AquaTrackDatabase
import com.example.aquatrack.feature_home.data.repository.WaterRepositoryImpl
import com.example.aquatrack.feature_home.domain.repository.WaterRepository
import com.example.aquatrack.feature_home.domain.usecase.AddWaterInTakeUseCase
import com.example.aquatrack.feature_home.domain.usecase.GetAllWaterInTakesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    /**
     * Provides the AquaTrackDatabase instance.
     *
     * @param app The application context.
     * @return An instance of AquaTrackDatabase.
     */
    @Provides
    @Singleton
    fun provideAquaTrackDatabase(app: Application): AquaTrackDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = AquaTrackDatabase::class.java,
            name = AquaTrackDatabase.DATABASE_NAME
        ).build()
    }

    /**
     * Provides the DAO for AquaTrackDatabase.
     *
     * @param db The AquaTrackDatabase instance.
     * @return The DAO for AquaTrackDatabase.
     */
    @Provides
    @Singleton
    fun provideAquaTrackDao(db: AquaTrackDatabase): AquaTrackDao = db.aquaTrackDao()


    /**
     * Provides the WaterRepository implementation.
     *
     * @param dao The AquaTrackDao instance.
     * @return An instance of WaterRepository.
     */
    @Provides
    @Singleton
    fun provideWaterRepository(dao: AquaTrackDao): WaterRepository {
        return WaterRepositoryImpl(dao)
    }

    @Provides
    fun provideAddWaterIntakeUseCase(repository: WaterRepository): AddWaterInTakeUseCase {
        return AddWaterInTakeUseCase(repository)
    }

    @Provides
    fun provideGetAllWaterIntakesUseCase(repository: WaterRepository): GetAllWaterInTakesUseCase {
        return GetAllWaterInTakesUseCase(repository)
    }


}