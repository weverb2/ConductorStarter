package com.brandonwever.android.conductorstarter.ui.login

import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.brandonwever.android.conductorstarter.R
import com.brandonwever.android.conductorstarter.app.App
import com.brandonwever.android.conductorstarter.ui.productlist.ProductListController
import com.brandonwever.android.conductorstarter.ui.util.NavDrawerOwner
import com.brandonwever.android.conductorstarter.util.Keyboards
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.appcompat.v7.toolbar
import javax.inject.Inject

class LoginController : Controller() {

    @Inject lateinit var navDrawerOwner: NavDrawerOwner

    var name = ""
    var password = ""

    init {
        App.graph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return HomeControllerUI().createView(AnkoContext.create(inflater.context, this))
    }

    fun onNavigationClicked() {
        Keyboards.hideKeyboard(view.context, view)
        navDrawerOwner.openDrawer()
    }

    fun nameUpdated(newName: String) {
        name = newName
    }

    fun passwordUpdated(newPassword: String) {
        password = newPassword
    }

    fun onLoginClicked() {
        Keyboards.hideKeyboard(view.context, view)
        if (name.equals("user") && password.equals("test")) {
            router.pushController(RouterTransaction.with(ProductListController()))
            return
        }
        Toast.makeText(view.context, "Login Failed", Toast.LENGTH_SHORT).show()
    }

    fun handleEditorAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            onLoginClicked()
            return true
        }
        return false
    }

    inner class HomeControllerUI() : AnkoComponent<LoginController> {
        override fun createView(ui: AnkoContext<LoginController>) = with(ui) {
            verticalLayout {
                toolbar() {
                    setTitleTextColor(ContextCompat.getColor(ctx, android.R.color.white))
                    title = "Login"
                    backgroundResource = R.color.colorPrimary
                    navigationIconResource = R.drawable.ic_menu
                    setNavigationOnClickListener({ onNavigationClicked() })
                }
                editText(name) {
                    hint = "Username"
                    imeOptions = EditorInfo.IME_ACTION_NEXT
                    singleLine = true
                    textChangedListener {
                        onTextChanged { text, start, before, count ->
                            nameUpdated(text.toString())
                        }
                    }
                }
                editText(password) {
                    hint = "Password"
                    singleLine = true
                    imeOptions = EditorInfo.IME_ACTION_SEND
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    textChangedListener {
                        onTextChanged { text, start, before, count ->
                            passwordUpdated(text.toString())
                        }
                    }
                    onEditorAction { textView, actionId, keyEvent -> handleEditorAction(actionId) }

                }
                button("Login") {
                    onClick { onLoginClicked() }
                }
            }
        }
    }
}

