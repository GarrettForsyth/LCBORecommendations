package com.example.android.core.bindable

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.SimpleAdapter
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior

@BindingAdapter("bottomSheetState")
fun bindingBottomSheet(container: LinearLayout, state:Int) {
    val behavior= BottomSheetBehavior.from(container)
    behavior.state = state
}

//@BindingAdapter(value = ["rvItemlayout", "rvList", "rvOnItemClick"], requireAll = false)
//fun <T> setSimpleRecyclerViewAdapter(
//    recyclerView: RecyclerView,
//    rvItemLayout: Int,
//    rvList: List<T>,
//    onItemClickListener: AdapterView.OnItemClickListener)
//{
//    if (rvItemLayout != 0 && rvList.isNotEmpty()) {
//        recyclerView.adapter =
//    }
//}
