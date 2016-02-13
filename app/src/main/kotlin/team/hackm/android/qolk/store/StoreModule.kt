package team.hackm.android.qolk.store

import dagger.Module
import dagger.Provides
import io.realm.Realm
import retrofit.RestAdapter
import team.hackm.android.qolk.store.qolk.RemoteStore
import team.hackm.android.qolk.store.realm.LocalStore

@Module
class StoreModule {

    @Provides
    public fun provideLocalStore(realm: Realm): LocalStore =
            LocalStore(realm)

    @Provides
    public fun provideRemoteStore(restAdapter: RestAdapter): RemoteStore =
            RemoteStore(restAdapter)

}
