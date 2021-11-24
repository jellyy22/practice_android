package com.example.myapplication2

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_instagram_upload.*
import androidx.core.app.ActivityCompat.startActivityForResult
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class InstagramUploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram_upload)

        view_pictures.setOnClickListener {
            getPicture()
        }
    }


    fun getPicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000) {
            val uri: Uri = data!!.data!!
            val a = getImageFilePath(uri)
            Log.d("pathh", "path: $a")
        }
    }

    fun getImageFilePath(contentUri: Uri): String { // 절대경로를 얻기 위한 과정
        var columnIndex = 0
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri, projection, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }

    fun uploadPost(filePath: String) {
        val file = File(filePath)
        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)
        val content = RequestBody.create(MediaType.parse("text/plain"), getContent())

        (application as MasterApplication).service.uploadPost(part, content)
            .enqueue(object : Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {

                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }
            })
    }

    fun getContent(): String {
        return content_input.text.toString()
    }
}