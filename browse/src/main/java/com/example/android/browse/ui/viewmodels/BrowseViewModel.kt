package com.example.android.browse.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.browse.repo.LCBOItemRepository
import com.example.android.browse.vo.LCBOItemQueryParameters
import com.example.android.core.ObservedMutableLiveData
import com.example.android.core.vo.LCBOItem
import javax.inject.Inject

class BrowseViewModel @Inject constructor(
    val lcboItemRepository: LCBOItemRepository
): ViewModel() {

    private val _searchResults = MutableLiveData<List<LCBOItem>>()
    val searchResults: LiveData<List<LCBOItem>> = _searchResults

    val queryParameters = LCBOItemQueryParameters()
    private val _queryParameters = ObservedMutableLiveData<LCBOItemQueryParameters>()
        .also { it.postValue(queryParameters) }

    private val _sortOptionsBottomSheetVisible = MutableLiveData<Boolean>()
    val sortOptionsBottomSheetVisible: LiveData<Boolean>
            = _sortOptionsBottomSheetVisible.also {
        it.value = false
    }

    fun search() = lcboItemRepository.searchLcboItems(queryParameters)

    fun toggleSortOptionsBottomSheetVisibility() {
        val oldValue = _sortOptionsBottomSheetVisible.value!!
        _sortOptionsBottomSheetVisible.value = !oldValue
    }

}
