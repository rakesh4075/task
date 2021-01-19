package com.agonsoft.task


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var service: GetData
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        progressBar = findViewById(R.id.progress)

        toolbar.title = "Employee List"
        toolbar.setTitleTextColor(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)
        service = RetrofitClient.getRetrofitInstance().create(GetData::class.java)
       val  call: Call<userListData> = service.getAllUsers()
        if (Constant.isNetworkAvailable(this)){
            progressBar.visibility = View.VISIBLE
            call.enqueue(object : Callback<userListData> {
                override fun onResponse(
                    call: Call<userListData>,
                    response: Response<userListData>
                ) {
                    progressBar.visibility = View.GONE
                    loadDataList(response.body())
                }

                override fun onFailure(call: Call<userListData>, t: Throwable) {
                    progressBar.visibility = View.GONE
                }
            })
        }else{
            Toast.makeText(this,"No internet connect",Toast.LENGTH_LONG).show()
        }

    }

    fun loadDataList(empList: userListData?) {
       val myRecyclerView = findViewById<RecyclerView>(R.id.listemployer)
        myRecyclerView.visibility = View.VISIBLE
        val myAdapter = ListAdapter(empList!!)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
        myRecyclerView.layoutManager = layoutManager
        myRecyclerView.adapter = myAdapter
    }
}