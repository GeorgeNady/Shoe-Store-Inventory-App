package com.udacity.shoestore.ui.screens.shoe_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    init {
        val list = mutableListOf(
            Shoe("Chuck Taylor All Star", 42.0, "Converse", "The original basketball sneakers are now defined as a stylish modern-day fashion staple! The Converse® Chuck Taylor® All Star® Core Ox sneakers are a great complement to any casual ensemble.", listOf(R.drawable.shoes_1)),
            Shoe("Swift Run W", 43.0, "Adidas", "Make a break for the finish line with the adidas® Originals Swift Run W sneaker.", listOf(R.drawable.shoes_2)),
            Shoe("Jefferson (Little Kid/Big Kid)", 30.5, "Native Shoes Kids", "NA", listOf(R.drawable.shoes_3)),
            Shoe("Kinetic™ Impact Lace", 30.5, "SOREL", "NA", listOf(R.drawable.shoes_4)),
            Shoe("Club C 85", 30.5, "Reebok Lifestyle", "Bring classic tennis style to your wardrobe with the Reebok® Lifestyle Club C 85 sneakers", listOf(R.drawable.shoes_5))
        )
        _shoeList.value = list
    }

    fun saveShoe(s: Shoe) : Boolean {
         _shoeList.value?.let { shoeList ->
            if (shoeList.contains(s)) {
                val shoe = shoeList.first { shoe -> shoe.name == s.name }
                val index = shoeList.indexOf(shoe)
                val copy = shoe.copy(s.name, s.size, s.company, s.description, s.images)
                shoeList[index] = copy
                _shoeList.value = shoeList
                return true
            }

             shoeList.add(s)
             return true
        }
        return false
    }

}