package com.ai2023.memze321

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ai2023.memze321.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {

    var currimgurl: String?=null


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Loadmemz()



    }


    fun Loadmemz(){
        binding.prgcirc.visibility=View.VISIBLE

        val url = "https://meme-api.com/gimme"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,Response.Listener { response ->
                currimgurl=response.getString("url")

                Glide.with(this).load(currimgurl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.prgcirc.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.prgcirc.visibility=View.GONE
                        return false
                    }


                }).into(binding.memeziv)


            },
            Response.ErrorListener {
                Toast.makeText(this,"erred",Toast.LENGTH_SHORT).show()

            })




        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


    }


    fun Sharememz(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"hey $currimgurl")
        val userre=Intent.createChooser(intent,"Share using...")
        startActivity(userre)
    }
    fun Xtmemz(view: View) {
        Loadmemz()


    }
}