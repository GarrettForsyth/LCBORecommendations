package com.example.android.browse.vo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.android.browse.BR
import com.example.android.core.vo.SortOrder

/**
 * Object that encapsulates the parameters of a query for LCBO items.
 * Parameters are observed and changes notify the data binding.
 */
//data class LCBOItemQueryParameters(
//    private var _filterString: String = "",
//    private var _sortOrder: SortOrder = SortOrder.NONE
//): BaseObservable() {
//
//    var filterString: String
//        @Bindable get() = _filterString
//        set(value) {
//            if (value != _filterString) {
//                _filterString = value
//                notifyPropertyChanged(BR.browseViewModel)
//            }
//        }
//
//    var sortOrder: SortOrder
//        @Bindable get() = _sortOrder
//        set(value) {
//            if (value != _sortOrder) {
//                _sortOrder = value
//                notifyPropertyChanged(BR.browseViewModel)
//            }
//        }
//
//}