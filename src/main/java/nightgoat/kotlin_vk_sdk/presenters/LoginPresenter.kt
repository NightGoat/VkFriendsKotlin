package nightgoat.kotlin_vk_sdk.presenters

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import nightgoat.kotlin_vk_sdk.R
import nightgoat.kotlin_vk_sdk.views.LoginView

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {


    fun loginVK(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (!VK.onActivityResult(requestCode = requestCode, resultCode = resultCode, data = data, callback = object :
                VKAuthCallback {

                override fun onLogin(token: VKAccessToken) {
                    viewState.openFriends()
                }

                override fun onLoginFailed(errorCode: Int) {
                    viewState.showError(textResource = R.string.login_error_credentials)
                }
            })){
            return false
        }
        return true
    }
}