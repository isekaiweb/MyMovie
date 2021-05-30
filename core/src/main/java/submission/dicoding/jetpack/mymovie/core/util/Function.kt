package submission.dicoding.jetpack.mymovie.core.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import submission.dicoding.jetpack.mymovie.core.R

object Function {
    fun View.glide(url: String, imgView: ImageView) {
        Glide.with(this).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .centerCrop()
        ).load("https://image.tmdb.org/t/p/w500${url}")
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgView)
    }

    fun createToastNetworkError(state: Boolean, context: Context) {
        val toast = Toast.makeText(context, "lost connection", Toast.LENGTH_LONG)
        if (state) toast.show()
        else return
    }

    fun Fragment.isPortrait() =
        resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    fun EditText.hideKeyboard() =
         (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)


    fun EditText.showKeyboard()=
         (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, 0)


    fun setOnPressEnter(
        editText: EditText,
        btn: View
    ) {
        editText.setOnEditorActionListener { _, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                btn.performClick()
            }
            return@setOnEditorActionListener false
        }
    }
}