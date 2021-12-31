package com.example.myapplication2

import android.app.Activity
import android.media.MediaPlayer
import android.net.Uri
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
import kotlinx.android.synthetic.main.activity_melon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MelonActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon)

        (application as MasterApplication).service.getSongList()
            .enqueue(object : Callback<ArrayList<Song>> {
                override fun onResponse(
                    call: Call<ArrayList<Song>>,
                    response: Response<ArrayList<Song>>
                ) {

                    val songList = response.body()
                    if (response.isSuccessful) {
                        val adapter = MelonAdapter(
                            songList!!,
                            LayoutInflater.from(this@MelonActivity),
                            Glide.with(this@MelonActivity),
                            this@MelonActivity
                        )
                        song_list.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<ArrayList<Song>>, t: Throwable) {
                }
            })
    }

    override fun onPause() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        super.onPause()
    }

    inner class MelonAdapter(
        val songList: ArrayList<Song>,
        val inflater: LayoutInflater,
        val glide: RequestManager,
        val activity: Activity
    ) :
        RecyclerView.Adapter<MelonAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val title: TextView
            val thumbnail: ImageView
            val play: ImageView

            init {
                title = itemView.findViewById(R.id.song_title)
                thumbnail = itemView.findViewById(R.id.song_img)
                play = itemView.findViewById(R.id.song_play)

                play.setOnClickListener {
                    val position: Int = adapterPosition
                    val path = songList[position].song
//                    val mediaPlayer = MediaPlayer.create(this@MelonActivity, Uri.parse(path))
//                    mediaPlayer.start()
                    try {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        mediaPlayer = MediaPlayer.create(this@MelonActivity, Uri.parse(path))
                        mediaPlayer?.start()
                    } catch (e: Exception) {

                    }

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.song_item_view, parent, false)

            val view = inflater.inflate(R.layout.song_item_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = songList[position].title
            glide.load(songList[position].thumbnail).into(holder.thumbnail)

        }

        override fun getItemCount(): Int {
            return songList.size
        }
    }
}
