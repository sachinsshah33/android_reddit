package extension.domain.androidreddit.extensions

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan

fun CharSequence.bold(startIndex: Int = 0, endIndex: Int? = null): Spanned {
    val word: Spannable = SpannableString(this)
    word.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex?:this.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return word
}
