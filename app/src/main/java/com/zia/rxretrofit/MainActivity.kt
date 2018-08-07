package com.zia.rxretrofit

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zia.rxretrofit.databinding.ActivityMainBinding
import com.zia.rxretrofit.encapsulation.repository.StudentRes
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bt.setOnClickListener {
            val id = edit.text.toString()
            disposable = StudentRes.getInstance().getStudent(id).subscribe {
                binding.stu = it[0]
            }
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}


