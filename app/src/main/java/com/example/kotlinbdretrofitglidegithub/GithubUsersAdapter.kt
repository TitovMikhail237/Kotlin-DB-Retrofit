package com.example.kotlinbdretrofitglidegithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinbdretrofitglidegithub.api.UserGithub2
import com.example.kotlinbdretrofitglidegithub.databinding.SingleUserGithubBinding

class GithubUsersAdapter(private val usersGithub: List<UserGithub>) :
    RecyclerView.Adapter<GithubUsersAdapter.MyViewHolder>() {

    class MyViewHolder(val singleUserGithubBinding: SingleUserGithubBinding) :
        RecyclerView.ViewHolder(singleUserGithubBinding.root) {

        fun bind(userGithub2: UserGithub2) {
            singleUserGithubBinding.loginTextView.text = userGithub2.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val singleUserGithubBinding = SingleUserGithubBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(singleUserGithubBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = usersGithub[position]


        holder.singleUserGithubBinding.loginTextView.text = user.login


        Glide
            .with(holder.itemView.context)
            .load(user.avatar_url)
            .into(holder.singleUserGithubBinding.imageView)
    }

    override fun getItemCount() = usersGithub.size
}
