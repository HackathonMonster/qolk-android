package team.hackm.android.qolk

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences.RxSharedPreferences
import com.google.gson.Gson
import com.hosshan.android.salad.util.GsonUtil
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit.Endpoint
import retrofit.Endpoints
import retrofit.RequestInterceptor
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import team.hackm.android.qolk.store.StoreModule
import javax.inject.Named

@Module(includes = arrayOf(StoreModule::class)) // ApiModule::class,
class DataModule {

    companion object {
        const val PREF_NAME: String = "gitsalad_debug"
        const val DB_NAME: String = "gitsalad_debug"
        const val DB_VERSION: Long = 0L
    }

    @Provides
    @Named("Api")
    fun provideApiClient(client: OkHttpClient): OkHttpClient = client.clone()

    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient {
        val client = OkHttpClient()
        return client
    }

    @Provides
    fun provideEndpoint(): Endpoint = Endpoints.newFixedEndpoint("")

    @Provides
    fun provideGson(): Gson = GsonUtil.getInstance()

    @Provides
    fun provideRequestInterceptor(): RequestInterceptor =
            RequestInterceptor {
            }

    @Provides
    fun provideRestAdapter(@Named("Api") client: OkHttpClient, endpoint: Endpoint, gson: Gson, requestInterceptor: RequestInterceptor): RestAdapter =
            RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(OkClient(client))
                    .setEndpoint(endpoint)
                    .setConverter(GsonConverter(gson))
                    .setRequestInterceptor(requestInterceptor)
                    .build()

    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences =
            RxSharedPreferences.create(sharedPreferences)

    @Provides
    fun provideRealm(app: Application): Realm =
            Realm.getInstance(RealmConfiguration.Builder(app)
                    .name(DB_NAME)
                    .schemaVersion(DB_VERSION)
                    // .migration(Migration())
                    .build())

}
