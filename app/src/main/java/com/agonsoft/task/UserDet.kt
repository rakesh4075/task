package com.agonsoft.task

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDet : AppCompatActivity() {
    private lateinit var service: GetData
    private var userData:UserData?=null
    private var memId = ""
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_det)
        progressBar = findViewById(R.id.progress)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("Employee Detail")
        toolbar.setTitleTextColor(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)
        if (intent.getStringExtra("EMPID") != null) {
            memId = intent.getStringExtra("EMPID") as String

            if (Constant.isNetworkAvailable(this)){
                progressBar.visibility = View.VISIBLE
                service = RetrofitClient.getRetrofitInstance().create(GetData::class.java)
                val  call: Call<UserData>? = service.getPostWithID(memId.toInt())
                    call?.enqueue(object :Callback<UserData>{
                        override fun onResponse(
                            call: Call<UserData>,
                            response: Response<UserData>
                        ) {
                            progressBar.visibility = View.GONE
                            userData = response.body()
                            updateUserData(userData)

                        }

                        override fun onFailure(call: Call<UserData>, t: Throwable) {
                            progressBar.visibility = View.GONE
                        }
                    })
            }else{
                Toast.makeText(this,"No internet connect", Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun updateUserData(userData: UserData?) {
        if (userData!=null){
            findViewById<CardView>(R.id.empdet).visibility = View.VISIBLE
            findViewById<CardView>(R.id.empcom).visibility = View.VISIBLE
            findViewById<TextView>(R.id.emplid).text = Constant.fromAppHtml(getString(R.string.employeid,userData.id.toString()))
            findViewById<TextView>(R.id.emplname).text = Constant.fromAppHtml(getString(R.string.employename,userData.name.toString()))
            findViewById<TextView>(R.id.emplemail).text = Constant.fromAppHtml(getString(R.string.employeemail,userData.email.toString()))
            findViewById<TextView>(R.id.empladdress).text = Constant.fromAppHtml(getString(R.string.employeaddres,userData.address.toString()))
            findViewById<TextView>(R.id.emplphone).text = Constant.fromAppHtml(getString(R.string.employephone,userData.phone.toString()))
            findViewById<TextView>(R.id.companyname).text = Constant.fromAppHtml(getString(R.string.employecompanyname,userData.company.toString()))
            findViewById<TextView>(R.id.companyweb).text = Constant.fromAppHtml(getString(R.string.employecompanyweb,userData.website.toString()))

        }
    }
}