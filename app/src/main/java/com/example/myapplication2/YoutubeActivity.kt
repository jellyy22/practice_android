package com.example.myapplication2

import android.app.Activity
import android.content.Intent
import android.media.session.PlaybackState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_youtube.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YoutubeActivity : AppCompatActivity() {

    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        (application as MasterApplication).service.getYoutubeList()
            .enqueue(object : Callback<ArrayList<Youtube>> {
                override fun onResponse(
                    call: Call<ArrayList<Youtube>>,
                    response: Response<ArrayList<Youtube>>
                ) {
                    if (response.isSuccessful) {
                        glide = Glide.with(this@YoutubeActivity)

                        val youtubeList = response.body()
                        val adpater =
                            YoutubeAdapter(
                                youtubeList!!,
                                LayoutInflater.from(this@YoutubeActivity),
                                glide,
                                this@YoutubeActivity
                            )
                        youtube_list_recycler.adapter = adpater
                    }
                }

                override fun onFailure(call: Call<ArrayList<Youtube>>, t: Throwable) {
                }
            })
    }

}

class YoutubeAdapter(
    val youtubeList: ArrayList<Youtube>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val activity: Activity
) : RecyclerView.Adapter<YoutubeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView
        val thumbnail: ImageView
        val content: TextView

        init {
            title = itemView.findViewById(R.id.youtube_title)
            thumbnail = itemView.findViewById(R.id.youtube_thumbnail)
            content = itemView.findViewById(R.id.youtube_content)


            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(activity, YoutubeDetailActivity::class.java)
                intent.putExtra("video_url", youtubeList[position].video)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.youtube_item_view, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = youtubeList[position].title
        holder.content.text = youtubeList[position].content
        glide.load(youtubeList[position].thumbnail).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return youtubeList.size
    }
}
