import android.widget.Toast
import com.sylvan.kotlin_wanandroid.BuildConfig

/**
 * Constant
 */
object Constant {
    /**
     * baseUrl
     */
    const val REQUEST_BASE_URL = "https://wanandroid.com/"
    /**
     * Share preferences name
     */
    const val SHARED_NAME = "_preferences"
    const val LOGIN_KEY = "login"
    const val USERNAME_KEY = "username"
    const val PASSWORD_KEY = "password"
    /**
     * result null
     */
    const val RESULT_NULL = "result null!"
    /**
     * Toast
     */
    @JvmField
    var showToast: Toast? = null
    /**
     * Debug
     */
    var INTERCEPTOR_ENABLE = BuildConfig.DEBUG
    /**
     * url key
     */
    const val CONTENT_URL_KEY = "url"
    /**
     * title key
     */
    const val CONTENT_TITLE_KEY = "title"
    /**
     * id key
     */
    const val CONTENT_ID_KEY = "id"
    /**
     * cid key
     */
    const val CONTENT_CID_KEY = "cid"
    /**
     * childrenData key
     */
    const val CONTENT_CHILDREN_DATA_KEY = "childrenData"
    /**
     * content data key
     */
    const val CONTENT_DATA_KEY = "content_data"
    /**
     * target key
     */
    const val CONTENT_TARGET_KEY = "target"
    /**
     * share key
     */
    const val CONTENT_SHARE_TYPE = "text/plain"
    const val SEARCH_KEY = "search"
    const val LIKE_KEY = "like"

    const val MAIN_REQUEST_CODE = 100
    const val MAIN_LIKE_REQUEST_CODE = 101
}