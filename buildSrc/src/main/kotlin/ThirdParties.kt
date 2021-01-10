/**
 * des: 管理第三方库
 * @author: Muppet
 * @date: 2021/1/9
 */
@Suppress("SpellCheckingInspection")
object ThirdParties {

    val Interceptor = LogginInterceptor
    object LogginInterceptor{
        const val module = "json"
        const val group = "org.json"
        const val loggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:3.1.0"
    }

    val retrofit = Retrofit
    object Retrofit{
        private const val retrofit_version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val gson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
    }

}