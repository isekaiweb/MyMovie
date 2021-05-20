package submission.dicoding.jetpack.mymovie.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

object Function {
    fun createToastNetworkError(state: Boolean, context: Context) {
        val toast = Toast.makeText(context, "lost connection", Toast.LENGTH_LONG)
        if (state) toast.show()
        else return
    }

    fun Fragment.isPortrait() =
        resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    fun EditText.hideKeyboard(): Boolean {
        return (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)
    }

    fun EditText.showKeyboard(): Boolean {
        return (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, 0)
    }

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