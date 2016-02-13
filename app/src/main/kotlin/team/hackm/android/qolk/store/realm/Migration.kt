package team.hackm.android.qolk.store.realm

import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.RealmSchema

/**
 * Created by shunhosaka on 2015/12/20.
 */
public class Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        realm ?: return
        // DynamicRealmからは変更可能なスキーマインスタンスを取得できます
        val schema: RealmSchema = realm.schema
        if (oldVersion == 0L) {
            // writing migration
            oldVersion.inc()
        }
    }

    /* Kotlinのマイグレーション参考記事
     * https://realm.io/docs/java/latest/#migrations
     * http://tiro105.hateblo.jp/entry/2015/11/12/153435
     * https://realm.io/jp/news/realm-java-0.86.0/
     */
}
