package team.hackm.android.qolk.store.qolk

import retrofit.http.GET
import rx.Observable
import team.hackm.android.qolk.store.qolk.entity.Qolk

interface QolkService {

    @GET("/")
    public fun getLastData(): Observable<Qolk>

}