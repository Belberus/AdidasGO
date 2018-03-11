package iosoft.adidasgo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_product_recommendation.*

class ProductRecommendationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_recommendation)
        setSupportActionBar(toolbar)
        link.setOnClickListener { view ->
            Snackbar.make(view, "Go to website.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
