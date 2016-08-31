package com.brandonwever.android.conductorstarter.data.lcbo.model

data class Pager(val recordsPerPage: Int,
                 val totalRecordCount: Int,
                 val currentPageRecordCount: Int,
                 val isFirstPage: Boolean,
                 val isFinalPage: Boolean,
                 val currentPage: Int,
                 val currentPagePath: String,
                 val nextPage: Int,
                 val nextPagePath: String,
                 val previousPage: Int,
                 val previousPagePath: String,
                 val totalPages: Int,
                 val totalPagesPath: String) {

}