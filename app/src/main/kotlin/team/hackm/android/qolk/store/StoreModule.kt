package team.hackm.android.qolk.store

import dagger.Module
import dagger.Provides
import io.realm.Realm
import team.hackm.android.qolk.store.realm.LocalStore

/**
 * Created by shunhosaka on 15/10/03.
 */
@Module
public class StoreModule {

    @Provides
    fun provideLocalStore(realm: Realm): LocalStore =
            LocalStore(realm)

}
