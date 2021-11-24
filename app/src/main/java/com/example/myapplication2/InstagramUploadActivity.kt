package com.example.myapplication2

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_instagram_upload.*
import androidx.core.app.ActivityCompat.startActivityForResult


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

        }
    }

    fun getImageFilePath(contentUri: Uri): String {
        var columnIndex = 0
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri, projection, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }
}