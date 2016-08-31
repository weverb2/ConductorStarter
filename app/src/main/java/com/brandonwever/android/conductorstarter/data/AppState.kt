package com.brandonwever.android.conductorstarter.data

import com.brandonwever.android.conductorstarter.data.lcbo.LCBOApiState

data class AppState(val lcboApiState: LCBOApiState) {

    companion object {
        val INITIAL = AppState(LCBOApiState.INITIAL)
    }
}