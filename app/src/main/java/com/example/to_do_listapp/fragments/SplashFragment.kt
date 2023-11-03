package com.example.to_do_listapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.enableSavedStateHandles
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.to_do_listapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class SplashFragment : Fragment() {

private lateinit var nAuth: FirebaseAuth
private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         init(view)

        val islogin: Boolean =nAuth.currentUser != null

        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            if (islogin){
                navController.navigate(R.id.action_splashFragment_to_homeFragment)}
            else{
                navController.navigate((R.id.action_splashFragment_to_sigInFragment)}
              },2000)
    }

    private fun init(view: View){
        nAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
    }



}