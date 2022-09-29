package com.mandeep.applaunchtask.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.gson.JsonObject
import com.mandeep.applaunchtask.R
import com.mandeep.applaunchtask.data.model.weather.WeatherModel
import com.mandeep.applaunchtask.databinding.FragmentWeatherReportBinding
import com.mandeep.applaunchtask.ui.viewModel.AppViewModel
import com.mandeep.applaunchtask.util.CommonPojoUtils
import com.mandeep.applaunchtask.util.extension.*
import com.mandeep.talkchargerassignment.data.remote.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherReportFragment : Fragment() {
    private val TAG = WeatherReportFragment::class.java.simpleName
    private var _binding: FragmentWeatherReportBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherReportBinding.inflate(inflater, container, false)
        setObservers()
        callAPI()
        onClickHandlers()
        return binding.root
    }

    private fun onClickHandlers() {
        binding.apply {
            ivBack onClick {
                findNavController().popBackStack()
            }

            ivLogout onClick {
                findNavController().navigate(R.id.action_weatherReportFragment_to_loginFragment)
            }
        }
    }

    private fun callAPI() {
        val map =
            hashMapOf(
                Pair("appid", "b143bb707b2ee117e62649b358207d3e"),
                Pair("lat", "12.9082847623315"),
                Pair("lon", "77.65197822993314"),
                Pair("units", "imperial")
            )
        viewModel.getCurrentWeather(map)
    }

    private fun setObservers() {
        viewModel.currentWeather.observe(viewLifecycleOwner) {
            when (it!!.status) {
                Status.SUCCESS -> setCurrentWeatherData(it.data)
                Status.ERROR -> null
                Status.LOADING -> null
                Status.UNAUTHORIZE -> null
                Status.GATEWAY -> null
            }
        }
    }

    private fun setCurrentWeatherData(data: JsonObject?) {
        Log.d(TAG, "setCurrentWeatherData() called with: data = $data")
        val currentWeatherModel: WeatherModel =
            CommonPojoUtils.getResponse<WeatherModel>(data)
        binding.apply {
            tvTemperature.text = this toCelsius currentWeatherModel.current.temp
            tvWeatherType.text = currentWeatherModel.current.weather[0].main
            tvHumidity.text = this convertHumidity currentWeatherModel.current.humidity.toDouble()
            tvWindSpeed.text = this convertWindSpeed currentWeatherModel.current.windSpeed
            tvPressure.text = this presureHpa currentWeatherModel.current.pressure.toDouble()
            tvVisibilty.text =
                this convertVisibilty currentWeatherModel.current.visibility.toDouble()
            ivWeather.load(this generateImgUrl currentWeatherModel.current.weather[0].icon)
        }
    }


}