package submission.dicoding.jetpack.mymovie.util

import android.content.Context
import android.widget.Toast

object Function {
    fun createToastNetworkError(state: Boolean, context: Context) {
        val toast = Toast.makeText(context, "lost connection", Toast.LENGTH_LONG)
        if (state) toast.show()
        else return
    }
}