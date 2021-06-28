package com.example.mealbox1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class lunchActivity: Fragment(){

    lateinit var calendarView: CalendarView
    var day=calendarView.date

    lateinit var mealText2: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view2=inflater.inflate(R.layout.lunch, container, false)
        calendarView=view2.findViewById(R.id.calendarView)
        mealText2=view2.findViewById(R.id.tvItemTitle2)

        setRetrofit()

        return view2
    }

    private fun setRetrofit() {
        val retrofit = Retrofit.Builder()   // Retrofit 초기화 - baseUrl
            .baseUrl("https://open.neis.go.kr/hub/")
            .addConverterFactory(GsonConverterFactory.create())    // Retrofit 응답 변환 - addConverterFactory(~~~)
            .client(createOkHttpClient())
            .build()

        val service = retrofit.create(MealService::class.java)    // Retrofit 생성 - UserService

        val call: Call<Meal> = service.ApiService("1d3419d33851476cb4054041572c6cce","json",1,10,"F10","7380292", "${day}","2")    // call 객체 생성
        call.enqueue(object : Callback<Meal> {    // enqueue() 메소드를 사용한 요청 처리, Callback interface 구현
            override fun onFailure(call: Call<Meal>, t: Throwable) {    // 요청이 실패되었을 경우의 처리
                //Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()    // 화면에 Toast 메세지 [실패] 출력
                println("실패")
                Log.d("Test", t.toString())
            }

            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {    // 요청이 성공되었을 경우의 처리
                if (response.body()!=null) {     // 받아온 결과에 body() 부분이 null이 아닐 경우
                    //Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()    // 화면에 Toast 메세지 [성공] 출력
                    println("성공")
                    val res = response.body()!!.mealServiceDietInfo[1].row
                    Log.d("success", response.body().toString())
                    if (response.isSuccessful) {
                        for (i in res.indices) {
                            val obj = res[i]
                            mealText2.text= "${obj.DDISH_NM}"
                        }
                    }
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

    }

}

private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.addInterceptor(interceptor)
    return builder.build()
}
