package team.hackm.android.qolk.store.qolk

import retrofit.RestAdapter
import rx.Observable
import team.hackm.android.qolk.store.qolk.entity.Qolk

/**
 * Created by shunhosaka on 2016/02/14.
 */
public class RemoteStore(restAdapter: RestAdapter) {
    val qolkService: QolkService

    init {
        qolkService = restAdapter.create(QolkService::class.java)
    }

    fun getLastData(): Observable<Qolk> =
            qolkService.getLastData()

}