package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MainActivity.Constants.DATE
import com.example.MainActivity.Constants.RATING
import com.example.MainActivity.Constants.TITLE
import com.example.MainActivity.Constants.YEAR
import java.util.*

class MainActivity : AppCompatActivity() {
    object Constants {
        const val MOVIE_INFO = "movie_info"
        const val RATING = "rating"
        const val TITLE = "title"
        const val YEAR = "year"
        const val DATE = "date"
    }

    private lateinit var addressAdapter: AddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addressAdapter = AddressAdapter(onClick = {
            item -> var bundle = Bundle()
            bundle.putString(RATING,item.rating)
            bundle.putString(TITLE, item.title)
            bundle.putString(YEAR, item.year)
            bundle.putString(DATE, item.date)
            var intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("extra",bundle)
            startActivity(intent)
        })
        findViewById<RecyclerView>(R.id.main_activity_recyclerView).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.main_activity_recyclerView).adapter = addressAdapter

        findViewById<Button>(R.id.main_activity_button).setOnClickListener { updateRecyclerView() }

        findViewById<ProgressBar>(R.id.main_activity_progress_bar).visibility = View.GONE

    }

    private fun updateRecyclerView() {
        findViewById<ProgressBar>(R.id.main_activity_progress_bar).visibility = View.VISIBLE
        val list = ArrayList<ResultEntity>()
        list.add(ResultEntity("1","1","1","1"))
        list.add(ResultEntity("2","2","2","2"))
        addressAdapter.updateList(list)
        addressAdapter.notifyDataSetChanged()
        findViewById<ProgressBar>(R.id.main_activity_progress_bar).visibility = View.GONE
    }


    class AddressAdapter(val onClick:(item:ResultEntity)->Unit):RecyclerView.Adapter<AddressAdapter.Holder>() {
        var mList:List<ResultEntity> = ArrayList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.item_textView).text = "${mList[position].year}:${mList[position].title}"
            holder.itemView.setOnClickListener{onClick(mList[position])}
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        fun updateList(list:List<ResultEntity>){
            mList = list;
        }
        class Holder(itemView:View):RecyclerView.ViewHolder(itemView)
    }

    class ResultEntity(val title:String, val rating:String, val date:String, val year:String)
}