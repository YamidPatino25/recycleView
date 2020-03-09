package com.example.recycleview


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.recycleview.R
import com.example.recycleview.data.DailyWeather
import com.example.recycleview.data.RandomUser
import com.example.recycleview.data.User
import com.example.recycleview.databinding.FragmentPersonalBinding

/**
 * A simple [Fragment] subclass.
 */
class PersonalFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var user:User
    lateinit var  mBinding:FragmentPersonalBinding


    private lateinit var viewModel: RandomUserViewModel
    private var days = mutableListOf<DailyWeather>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_personal, container, false)
    mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_personal,container,false)
        user= arguments!!.getParcelable("user")!!
        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        user= arguments!!.getParcelable("user")!!
        //mBinding= DataBindingUtil.setContentView(this.requireActivity(),R.layout.fragment_personal)

        viewModel.forecast(user.nombre)
        viewModel.getDays().observe(viewLifecycleOwner, Observer { obsUsers ->
            run {
                days = obsUsers as MutableList<DailyWeather>

                for (randUser in days) {


                    var day = User (
                        randUser.name, randUser.temp, randUser.description, randUser.picture, randUser.humidity
                    )
                    Log.d("Ejemplo",randUser.name)
                }
                //adapter!!.updateData()

            } })
        mBinding.user=user
    }
}
