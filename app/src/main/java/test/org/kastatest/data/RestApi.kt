package test.org.kastatest.data

import retrofit.http.GET
import rx.Observable
import test.org.kastatest.data.entities.CampaignList

interface RestApi {

    @get:GET("/api/v2/campaigns")
    val campaign: Observable<CampaignList>

}
