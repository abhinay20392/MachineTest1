package com.net.machinetest


import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.util.*


fun Context.toast(message: String) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun Context.loadUrl(url: String) {
    val builder = CustomTabsIntent.Builder();
    val customTabsIntent = builder.build();
    customTabsIntent.launchUrl(this, Uri.parse(url));
}



fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Dismiss") {
            snackbar.dismiss()
        }
    }.show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

/*
fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}
*/

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.fetchColor(id: Int): Int = ContextCompat.getColor(this, id)

fun ImageView.setImageFromUrl(url: String?) {
    //Picasso.get().load(url).into(this);
    //Timber.d("***Image URL "+url)

    if(url == null)
        return

    url.replace(",", "")


    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    //val drawable = ContextCompat.getDrawable(context, R.drawable.placeholder)

    val requestOption = RequestOptions()
    Glide.with(context).load(url)
        .apply(requestOption)
        .transition(DrawableTransitionOptions.withCrossFade(400))
        //.placeholder(drawable)
        //.error(drawable)
        .into(this)
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}


fun TextView.openDateChooser(minDatee: Long) {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    val dpd = DatePickerDialog(
        context as Activity,
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            text = ("" + (monthOfYear + 1) + "/" + dayOfMonth + "/" + year)
        },
        year,
        month,
        day
    )
    if (minDatee == 0L) {
        dpd.datePicker.minDate = minDatee - 2000
    }
    dpd.show()
}

fun TextView.openDateChooserString(minDatee: Long) {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    val dpd = DatePickerDialog(
        context as Activity,
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            text = ("" + +dayOfMonth + "/" + (monthOfYear + 1))
        },
        year,
        month,
        day
    )
    if (minDatee == 0L) {
        dpd.datePicker.minDate = minDatee - 2000
    }
    dpd.show()
}

fun EditText.setErrorWithFocus(message: String) {
    requestFocus()
    error = message
}

fun EditText.checkIfEmpty(): Boolean {
    return text.toString().isEmpty()
}


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

/*fun TextView.openDateChooser(context: Context, minDatee: Long) {
    val newFragment = SelectDateFragment(this, minDatee)
    newFragment.show(
        (context as BaseActivity).supportFragmentManager.beginTransaction(),
        "DatePicker"
    )
}*/


/*fun TextView.openSpinnerList(list : ArrayList<String>, ft: FragmentTransaction , name: String){
    val newFragment = SpinnerDialog.newInstance(list, name)
    newFragment.show(ft, "Spinner Dialog")
}*/
