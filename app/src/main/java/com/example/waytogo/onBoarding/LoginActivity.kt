package com.example.waytogo.onBoarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.waytogo.utils.Constants.TAG
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import missing.namespace.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        fun newInstance() = LoginActivity()
    }
    // 카카오 로그인
    // 카카오 계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = {token, error ->
        if (error != null){
            Log.d(TAG, "로그인 실패 ${error.localizedMessage}")
        }
        else if (token != null){
            Log.d(TAG, "로그인 성공 ! ${token.accessToken} ")
            GoMain()
        }
    }

    private fun initView() {
        val context: Context = this
        //  버튼 클릭했을 때 로그인
        with(binding){
            kakaoLoginBtn.setOnClickListener {
                Log.d(TAG, "버튼 클릭 되긴 함 ㅋㅋ")

                // 카카오톡이 설치되어 있으면 카카오톡 로그인, 아니면 카카오계정 로그인
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    UserApiClient.instance.loginWithKakaoTalk(context){ token, error ->
                        if (error != null) {
                            Log.d(TAG, "initView: 로그인 실패! ${error.localizedMessage}")

                            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                            // 의도적인 로그인 취소로 보 카카오계정으로 로그인 시도 없이 로그인 취소 처리 (예 : 뒤로가기)
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }
                        }
                        else if (token != null) {
                            Log.d(TAG, "initView: 로그인 성공 ${token.accessToken}")
                            GoMain()
                        }
                    }
                }
                else {
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        Log.d("KeyHash", "${Utility.getKeyHash(this)}")
    }

    private fun GoMain(){
        // 로그인 -> 온보딩 액티비티
        // OnBoardingActivity로 이동
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }
}