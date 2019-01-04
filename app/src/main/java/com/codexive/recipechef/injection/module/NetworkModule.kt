package com.codexive.recipechef.injection.module

/*import com.codexive.recipechef.network.RecipeAPI
import com.codexive.recipechef.utils.BASE_URL*/
import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    /*@Provides
    @Singleton
    internal fun provideRecipeAPI(retrofit: Retrofit): RecipeAPI {
        return retrofit.create(RecipeAPI::class.java)
    }*/


    /**
     * Return the Retrofit object.
     * To fully configure Retrofit we require a HTTP client (okHTTP),
     * a converterFactory that can create a converter that parses JSON into model objects, and
     * a callAdapterFactory that can create an Adapter that allows us to use RxJava
     *  to handle async requests instead of the Calls that Retrofit provides itself.
     *
     * The function paramaters are interfaces, not the types of the specific kind of factories.
     * This allows us to easily swap out different kind of factories.
     */
    /*@Provides
    @Singleton
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient,
                                          converterFactory: retrofit2.Converter.Factory,
                                          callAdapterFactory: retrofit2.CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }*/

    @Provides
    @Singleton
    internal fun provideFirebaseDatabase() : FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    /**
     * Returns the OkHttpClient.
     * RetroFit uses OkHTTP by default and we normally don't have to add the client explicitly
     * to the builder. We slightly modify the client however to intercept calls and log them
     * for easier debugging.
     */
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        //To debug Retrofit/OkHttp we can intercept the calls and log them.
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
    }

    /**
     * The return type specifies the Factory interface.
     * Currently we choose to use a MoshiConverterFactory,
     * but this choice can easily be changed without needing any further changes.
     */
    @Provides
    @Singleton
    internal fun provideJSONConverter(): retrofit2.Converter.Factory {
        return MoshiConverterFactory.create()
    }

    /**
     * Here the return type is the interface as well.
     * We choose to create an RxJavaCallAdapterFactory, but changing this is easily done
     * without requiring further changes.
     */
    @Provides
    @Singleton
    internal fun provideCallAdapterFactory(): retrofit2.CallAdapter.Factory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }
}