package com.brandonwever.android.conductorstarter.data.lcbo

import com.brandonwever.android.conductorstarter.data.redux.Action
import com.brandonwever.android.conductorstarter.data.redux.AppState
import com.brandonwever.android.conductorstarter.data.redux.Reducer

class LCBOReducer : Reducer<AppState, Action> {
    override fun call(state: AppState, action: Action): AppState {
        when (action) {
            is LCBOProductNewestResponse -> {
                return state.copy(lcboApiState = LCBOApiState(action.response.result, action.response.pager))
            }
            is LCBOProductNextResponse -> {
                val combined = state.lcboApiState.products + action.response.result
                return state.copy(lcboApiState = LCBOApiState(combined, action.response.pager))
            }
            is LCBOProductError -> return state

        }
        return state
    }
}
