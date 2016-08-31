package com.brandonwever.android.conductorstarter.data.lcbo.model

data class Pager(val recordsPerPage: Int = 0,
                 val totalRecordCount: Int = 0,
                 val currentPageRecordCount: Int = 0,
                 val isFirstPage: Boolean = false,
                 val isFinalPage: Boolean = false,
                 val currentPage: Int = 0,
                 val currentPagePath: String = "",
                 val nextPage: Int = 0,
                 val nextPagePath: String = "",
                 val previousPage: Int = 0,
                 val previousPagePath: String = "",
                 val totalPages: Int = 0,
                 val totalPagesPath: String = "") {

}