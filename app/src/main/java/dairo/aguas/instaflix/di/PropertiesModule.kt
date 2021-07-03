package dairo.aguas.instaflix.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dairo.aguas.instaflix.BuildConfig.API_KEY
import dairo.aguas.instaflix.BuildConfig.PROPERTIES_FILE
import java.util.*
import javax.inject.Singleton

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object PropertiesModule {

    @Provides
    @Singleton
    fun apiKeyPropertiesProvider(application: Application): String {
        val properties = Properties()
        val assetManager = application.assets
        val inputStream = assetManager.open(PROPERTIES_FILE)
        properties.load(inputStream)
        return properties.getProperty(API_KEY)
    }
}