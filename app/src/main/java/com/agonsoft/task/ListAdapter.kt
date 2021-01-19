package com.agonsoft.task


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ListAdapter(private val listEmplo: userListData):RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.memName)
        val email = view.findViewById<TextView>(R.id.mememail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
       holder.name.text = listEmplo[position].name
        holder.email.text = listEmplo[position].email.toLowerCase(Locale.ROOT)

        holder.itemView.setOnClickListener {
            val intent = Intent(context,UserDet::class.java)
            intent.putExtra("EMPID",listEmplo[position].id.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listEmplo!!.size
    }
}
