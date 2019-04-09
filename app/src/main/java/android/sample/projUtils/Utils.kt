package android.sample.projUtils

import android.databinding.BindingAdapter
import android.graphics.*
import android.sample.R
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.text.SpannableString
import android.text.format.DateUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import java.text.SimpleDateFormat
import java.util.*


//This method is binding variables name and notification with this method which are provided through xml
@BindingAdapter("name", "notification")
fun TextView.getBoldName(name: String?, notificationMsg: String?) {
    if (name != null && name.isEmpty()) return
    val str = SpannableString("$name $notificationMsg")
    str.setSpan(StyleSpan(Typeface.BOLD), 0, name!!.length, 0)

//Uncomment if want to click on name too

//    val clickableSpan = object : ClickableSpan() {
//        override fun onClick(view: View) {
//            Toast.makeText(context, "$name click", Toast.LENGTH_LONG).show()
//        }
//    }

//    str.setSpan(clickableSpan, 0, name.length, 0)

    this.movementMethod = LinkMovementMethod.getInstance()
    this.text = str
}

//Relative time according to current time
fun getNotificationTime(time: String?): String? {
    if (time != null && time.isEmpty()) return ""
    val notiTime = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(time).time
    val currentTime = System.currentTimeMillis()
    val ago = DateUtils.getRelativeTimeSpanString(notiTime, currentTime, DateUtils.MINUTE_IN_MILLIS)
    return ago.toString()
}

//Border add on tranformed bitmap
fun Bitmap.addBorder(borderSize: Float): Bitmap {
    val borderOffset = (borderSize * 2).toInt()
    val radius = Math.min(height / 2, width / 2).toFloat()
    val output = Bitmap.createBitmap(width + borderOffset, height + borderOffset, Bitmap.Config.ARGB_8888)
    val paint = Paint()
    val borderX = width / 2 + borderSize
    val borderY = height / 2 + borderSize
    val canvas = Canvas(output)
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.style = Paint.Style.FILL
    canvas.drawCircle(borderX, borderY, radius, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, borderSize, borderSize, paint)
    paint.xfermode = null
    paint.style = Paint.Style.STROKE
    paint.color = Color.BLACK
    paint.strokeWidth = borderSize
    canvas.drawCircle(borderX, borderY, radius, paint)
    return output
}
//Using Glide and its transformation to convert URL image into circular bitmap
@BindingAdapter("imageUrl")
fun <T> ImageView.circleImage(uri: T) {
    val borderSize = 2.0f
    GlideApp.with(context)
        .asBitmap()
        .load(uri)
        .placeholder(R.mipmap.ic_default)
        .apply(RequestOptions.circleCropTransform())
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(resource: Bitmap?) {
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(
                    context.resources,
                    resource?.addBorder(borderSize)
                )
                circularBitmapDrawable.isCircular = true
                setImageDrawable(circularBitmapDrawable)
            }
        })
}
