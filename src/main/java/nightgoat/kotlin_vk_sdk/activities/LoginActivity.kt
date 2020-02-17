package nightgoat.kotlin_vk_sdk.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.utils.VKUtils
import kotlinx.android.synthetic.main.activity_login.*
import nightgoat.kotlin_vk_sdk.R
import nightgoat.kotlin_vk_sdk.presenters.LoginPresenter
import nightgoat.kotlin_vk_sdk.views.LoginView


class LoginActivity : MvpAppCompatActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (VK.isLoggedIn()) {
            startActivity(Intent(this, FriendsActivity::class.java))
            finish()
        }

        btn_login_enter.setOnClickListener {
            VK.login(this@LoginActivity, listOf(VKScope.FRIENDS))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Пусть презентер разбирает результат логина, создаем функцию
        if (!loginPresenter.loginVK(
                requestCode = requestCode,
                resultCode = resultCode,
                data = data
            )
        ) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

        override fun setLoading(isLoading: Boolean) {
            if (isLoading) {
                btn_login_enter.visibility = View.GONE
                progress_circular_login.visibility = View.VISIBLE
            } else {
                btn_login_enter.visibility = View.VISIBLE
                progress_circular_login.visibility = View.GONE
            }
        }

        override fun openFriends() {
            startActivity(Intent(applicationContext, FriendsActivity::class.java))
            finish()
        }

        override fun showError(textResource: Int) {
            Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_LONG).show()
        }
}
