package com.example.mealbox1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class breakfastActivity  : Fragment(){

    lateinit var custom1:CalendarView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view1=inflater.inflate(R.layout.breakfast, container, false)
        custom1=view1.findViewById(R.id.calendarView)

        setRetrofit()

        return view1
    }

    private fun setRetrofit() {
        val retrofit = Retrofit.Builder()   // Retrofit 초기화 - baseUrl
            .baseUrl("https://open.neis.go.kr/hub/")
            .addConverterFactory(GsonConverterFactory.create())    // Retrofit 응답 변환 - addConverterFactory(~~~)
            .client(createOkHttpClient())
            .build()

        val service = retrofit.create(MealService::class.java)    // Retrofit 생성 - UserService

        val call: Call<MealService> = service.ApiService("1d3419d33851476cb4054041572c6cce","json",1,10,"F10","7380292", "${custom1.date}","1")    // call 객체 생성
        call.enqueue(object : Callback<MealService> {    // enqueue() 메소드를 사용한 요청 처리, Callback interface 구현
            override fun onFailure(call: Call<MealService>, t: Throwable) {    // 요청이 실패되었을 경우의 처리
                //Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()    // 화면에 Toast 메세지 [실패] 출력
                println("실패")
                Log.d("Test", t.toString())
            }

            override fun onResponse(call: Call<MealService>, response: Response<MealService>) {    // 요청이 성공되었을 경우의 처리
                if (response.body()!=null) {     // 받아온 결과에 body() 부분이 null이 아닐 경우
                    //Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()    // 화면에 Toast 메세지 [성공] 출력
                    println("성공")
                    val res = response.body()!!.mealServiceDietInfo[1].row
                    Log.d("success", response.body().toString())
                    if (response.isSuccessful) {
                        for (i in res.indices) {
                            val obj = res[i]
                            custom1.date = "${obj.DDISH_NM}"
                        }
                    }
                }
            }
        })
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }

}
