package custom.subway.subway.Utility

import com.kakao.auth.*

class KakaoSDKAdapter {
    companion object {
        class SDKAdapter : KakaoAdapter() {
            /**
             * Session Config에 대해서는 default값들이 존재한다.
             * 필요한 상황에서만 override해서 사용하면 됨.
             * @return Session의 설정값.
             */
            override fun getSessionConfig(): ISessionConfig {
                return object : ISessionConfig {
                    override fun isSaveFormData(): Boolean {
                        return true
                    }

                    override fun getAuthTypes(): Array<AuthType> {
                        return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                    }

                    override fun isSecureMode(): Boolean {
                        return false
                    }

                    override fun getApprovalType(): ApprovalType {
                        return ApprovalType.INDIVIDUAL
                    }

                    override fun isUsingWebviewTimer(): Boolean {
                        return false
                    }

                }
            }

            override fun getApplicationConfig(): IApplicationConfig {
                return IApplicationConfig { SubwayApplication.getSubwayApplicationContext() }
            }
        }
    }
}