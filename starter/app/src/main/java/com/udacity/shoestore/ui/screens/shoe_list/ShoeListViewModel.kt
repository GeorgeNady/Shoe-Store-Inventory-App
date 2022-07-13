package com.udacity.shoestore.ui.screens.shoe_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {

    val shoeList = MutableLiveData<List<String>>()

}