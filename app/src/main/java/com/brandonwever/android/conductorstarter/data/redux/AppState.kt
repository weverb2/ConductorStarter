package com.brandonwever.android.conductorstarter.data.redux

import com.brandonwever.android.conductorstarter.data.lcbo.LCBOApiState

data class AppState(val lcboApiState: LCBOApiState = LCBOApiState()) {

    companion object {
        val INITIAL = AppState()
    }
}