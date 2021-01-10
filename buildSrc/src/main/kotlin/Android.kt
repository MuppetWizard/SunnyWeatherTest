/**
 * des: 管理AndroidX相关
 *
 * @author: Muppet
 * @date:   2021/1/10
 */
object Android {

    const val coreKtx = "androidx.core:core-ktx:1.3.2"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"

    val lifecycle = LifeCycle
    object LifeCycle{
        private const val lifeCycleVersion = "2.2.0"
    }
}