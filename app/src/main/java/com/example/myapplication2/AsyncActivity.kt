package com.example.myapplication2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.loader.content.AsyncTaskLoader
import kotlinx.android.synthetic.main.activity_async.*
import java.lang.Thread.sleep

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        var task: BackgoroundAsyncTask? = null

        val start = findViewById<Button>(R.id.start)
        start.setOnClickListener {
            task = BackgoroundAsyncTask(progressbar, ment) // 재사용이 불가능하다.
            task?.execute()
        }

        val stop = findViewById<Button>(R.id.stop)
        stop.setOnClickListener {
            task?.cancel(true)
        }
    }
}

class BackgoroundAsyncTask(val progressBar: ProgressBar, val progressText: TextView) :
    AsyncTask<Int, Int, Int>() {
    // params -> doInBackground 에서 사용할 타입
    // progress -> onProgressUpdate 에서 사용할 타입
    // result -> onPostExecute 에서 사용할 타입

    var percent: Int = 0

    override fun onPreExecute() {
        percent = 0
        progressBar.setProgress(percent)

    }

    override fun doInBackground(vararg params: Int?): Int {
        while (isCancelled() == false) {
            percent++
            if (percent > 100) {
                break
            } else {
                publishProgress(percent)
            }
            try {
                sleep(100)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return percent
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressBar.setProgress(values[0] ?: 0)
        progressText.text = "퍼센트: " + values[0]

        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Int?) {
        // 메인 스레드에서 실행
        progressText.text = "작업이 완료되었습니다."
    }

    override fun onCancelled() {
        progressBar.setProgress(0)
        progressText.text = "작업이 취소되었습니다."
    }
}