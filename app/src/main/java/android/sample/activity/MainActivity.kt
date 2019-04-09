package android.sample.activity

import android.os.Bundle
import android.sample.R
import android.sample.adapter.NotificationAdapter
import android.sample.data.User
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.act_main_data_binding_list.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main_data_binding_list)


        //A custom json to imagine as response recieved from server

        val jsonString = """[
            {"name":"Neeraj",
            "notification":"starts following you",
            "time":"2019-04-08 17:08",
            "url":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbdVyEWsO3vz8QUl1g49mZLc7p7HiAvU5otAeDQEzX0nlaeauC"},
            {"name":"Jenny",
            "notification":"commented on your post",
            "time":"2019-04-07 14:08",
            "url":""},
            {"name":"Stewan",
            "notification":"posted a picture",
            "time":"2019-04-04 11:40",
            "url":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBwRDpymbIBLU9lVMDgRD9w168ZXsc8RnY3_qnNEVNuCuBsAC5"}]"""

        //Convert into ArrayList of type User
        val jsonArray = JsonParser().parse(jsonString).asJsonArray
        val objectType = object : TypeToken<ArrayList<User>>() {}.type
        val list = Gson().fromJson<ArrayList<User>>(jsonArray, objectType)

        //Add divider into list
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        listRV.addItemDecoration(dividerItemDecoration)

        //Uncomment to replicate the data to check for view reuse in recycler view
        /*val list2 = list
        list2.addAll(list)
        list2.addAll(list)
        listRV.adapter = NotificationAdapter(list2)*/

        //Setting adapter on list
        listRV.adapter = NotificationAdapter(list)

        //Toolbar title set
        titleTBTV.setText("NOTIFICATION")
    }
}
