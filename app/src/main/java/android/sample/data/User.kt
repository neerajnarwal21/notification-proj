package android.sample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.sample.BR
import com.google.gson.annotations.SerializedName

class User : BaseObservable() {

    @get:Bindable
    @SerializedName("name")
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    @get:Bindable
    @SerializedName("notification")
    var notification: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.notification)
        }

    @get:Bindable
    @SerializedName("time")
    var time: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.time)
        }

    @get:Bindable
    @SerializedName("url")
    var image: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.image)
        }
}