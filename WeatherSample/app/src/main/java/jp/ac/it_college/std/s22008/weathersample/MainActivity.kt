package jp.ac.it_college.std.s22008.weathersample

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s22008.weathersample.databinding.ActivityMainBinding
//import jp.ac.it_college.std.s22008.WeatherSample.databinding.ActivityMainBinding
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DEBUG_TAG = "AsyncSample"
        private const val WEATHER_INFO_URL =
            "https://api.openweathermap.org/data/2.5/weather?lang=ja"
        private const val APP_ID = BuildConfig.APP_ID
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lvCityList.apply {
            var adapter = CityAdapter {
                receiveWeatherInfo(it.q)
            }
            var layoutManager = LinearLayoutManager(context)
        }
    }

    @UiThread
    private fun receiveWeatherInfo(q: String) {
        val url = "$WEATHER_INFO_URL&q=$q&appid=$APP_ID"
        val executorService = Executors.newSingleThreadExecutor()
        val backgroundReceiver = WeatherInfoBackgroundReceiver(url)
        val future = executorService.submit(backgroundReceiver)
        val result = future.get()
        showWeatherInfo(result)
    }

    @UiThread
    private fun showWeatherInfo(result: String) {
        val root = JSONObject(result)
        val cityName = root.getString("name")
        val coord = root.getJSONObject("coord")
        val latitude = coord.getDouble("lat")
        val longitude = coord.getDouble("lon")
        val weatherArray = root.getJSONArray("weather")
        val current = weatherArray.getJSONObject(0)
        val weather = current.getString("description")


        binding.tvWeatherTelop.text = getString(R.string.tv_telop, cityName)
        binding.tvWeatherDesc.text = getString(
            R.string.tv_desc, weather, latitude, longitude
        )
    }

    private class WeatherInfoBackgroundReceiver(val urlString: String) : Callable<String>,
        Parcelable {
        constructor(parcel : Parcel) : this(parcel.readString()) {
        }

        override fun writeToParcel(parcel : Parcel, flags : Int) {
            parcel.writeString(urlString)
        }

        override fun describeContents() : Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<WeatherInfoBackgroundReceiver> {
            override fun createFromParcel(parcel : Parcel) : WeatherInfoBackgroundReceiver {
                return WeatherInfoBackgroundReceiver(parcel)
            }

            override fun newArray(size : Int) : Array<WeatherInfoBackgroundReceiver?> {
                return arrayOfNulls(size)
            }
        }

        @WorkerThread
        override fun call(): String {
            val url = URL(urlString)
            val con = url.openConnection() as HttpURLConnection
            con.apply {
                connectTimeout = 1000
                readTimeout = 1000
                requestMethod = "GET"
            }
            return try {
                con.connect()
                val result = con.inputStream.reader().readText()

                con.disconnect()
                result
            }