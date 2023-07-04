package com.example.kotlinbdretrofitglidegithub

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinbdretrofitglidegithub.api.RetrofitBuilder.githubApi
import com.example.kotlinbdretrofitglidegithub.databinding.ActivityMainBinding
import com.example.kotlinbdretrofitglidegithub.databinding.SingleUserGithubBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ToastUiNotification {

    private lateinit var dataTextView: TextView
    private lateinit var btnRequest: Button
    private lateinit var btnSaveToDb: Button
    private lateinit var btnDeleteAll: Button
    private lateinit var recyclerView: RecyclerView

    private lateinit var editTextLogin: EditText
    private lateinit var loginTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var adapter: GithubUsersAdapter
    private lateinit var users: List<UserGithub>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.requestBtn.setOnClickListener { //viewModel.userClicked()
            request()
            hideKeyboard(this)
        }


        binding.btnSaveToDb.setOnClickListener { //метод для добавления в рум, сам метод в корутинах +
            //метод для отображения самого списка
        }

        binding.btnDeleteAll.setOnClickListener { //метод для удаления, сам метод ниже в корутинах
        }
    }

    private fun request() {

        val uiNotification: ToastUiNotification = this

        //Sets up up the API call
        val call: Call<List<UserGithub>> = githubApi.loadUsers(binding.editTextLogin.text)

        //Runs the call on a different thread
        call.enqueue(object : Callback<List<UserGithub>> {
            //Once the call has finished
            override fun onResponse(
                call: Call<List<UserGithub>>?,
                response: Response<List<UserGithub>>?
            ) {
                //Gets the list of followers
                val users: List<UserGithub>? = response?.body()

                if (!users.isNullOrEmpty()) {
                    binding.recyclerView.adapter = GithubUsersAdapter(users)
                } else {
                    uiNotification.showToast("not find user")
                }
            }

            override fun onFailure(call: Call<List<UserGithub>>, t: Throwable) {

                uiNotification.showToast("not connection to net")
            }
        })
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard(activity: Activity) {
        //Находим View с фокусом, так мы сможем получить правильный window token
        //Если такого View нет, то создадим одно, это для получения window token из него
        val view = activity.currentFocus ?: View(activity)
        val inputMethod =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.SHOW_IMPLICIT
        )
    }
}


interface ToastUiNotification {
    fun showToast(text: String)
}