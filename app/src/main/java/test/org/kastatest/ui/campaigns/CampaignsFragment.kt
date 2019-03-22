package test.org.kastatest.ui.campaigns

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit.Ok3Client

import java.util.Date

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit.Endpoints
import retrofit.RestAdapter
import retrofit.converter.GsonConverter
import test.org.kastatest.data.RestApi
import test.org.kastatest.data.facilities.RestDateTypeAdapter
import test.org.kastatest.BuildConfig
import test.org.kastatest.R

class CampaignsFragment : Fragment() {

    private lateinit var viewModel: CampaignsViewModel
    private lateinit var mRestApi: RestApi
    private val dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
    private val baseUrl = "https://kasta.ua"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, RestDateTypeAdapter(dateFormat))
                .create()

        mRestApi = RestAdapter.Builder()
                .setClient(Ok3Client(httpClientBuilder.build()))
                .setConverter(GsonConverter(gson))
                .setEndpoint(Endpoints.newFixedEndpoint(baseUrl))
                .build()
                .create(RestApi::class.java)

        viewModel = ViewModelProviders.of(this).get(CampaignsViewModel::class.java)
        viewModel.init(mRestApi)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_content, container, false) as CampaignsView
        view.setup(viewModel, this)
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCampaigns()
    }

}
