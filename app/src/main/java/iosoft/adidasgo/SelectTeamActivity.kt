package iosoft.adidasgo

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_select_team.*
import org.json.*;

class SelectTeamActivity : AppCompatActivity() {

    private lateinit var familyName : String
    private lateinit var id : String
    private lateinit var displayName : String
    private lateinit var email : String
    private lateinit var urlPhoto : String

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_team)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        familyName = intent.getStringExtra("familyName")!!
        id = intent.getStringExtra("id")
        displayName = intent.getStringExtra("displayName")
        email = intent.getStringExtra("email")
        urlPhoto = intent.getStringExtra("urlPhoto")

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter
    }

    fun joinTechniqueTeam (v: View) {
        var json : JSONObject = JSONObject()
        json.put("name", displayName)
        json.put("email", email)
        json.put("displayName", displayName)
        json.put("familyName", familyName)
        json.put("idGoogle", id)
        json.put("token", "")
        json.put("team", 1)
        json.put("urlPhoto", urlPhoto)

        ("http://" + ApiHandler.IP + ":" + ApiHandler.Port + "/signIn")
                .httpPost().body(json.toString())
                .responseString { request, response, result ->
                    var zero : Long = 0
                    when (result) {
                        is Result.Failure -> {
                            //Pafuera
                        }
                        is Result.Success -> {
                            //Padentro
                            val mapsactivity = Intent(this, MapsActivity::class.java)
                            mapsactivity.putExtra("team", "technique")
                            startActivity(mapsactivity)
                        }
                    }
                }
    }

    fun joinControlTeam (v: View) {
        var json : JSONObject = JSONObject()
        json.put("name", displayName)
        json.put("email", email)
        json.put("displayName", displayName)
        json.put("familyName", familyName)
        json.put("idGoogle", id)
        json.put("token", "")
        json.put("team", 2)
        json.put("urlPhoto", urlPhoto)

        ("http://" + ApiHandler.IP + ":" + ApiHandler.Port + "/signIn").
                httpPost().body(json.toString())
                .responseString { request, response, result ->
                    var zero : Long = 0
                    when (result) {
                        is Result.Failure -> {
                            //Pafuera
                        }
                        is Result.Success -> {
                            //Padentro
                            val mapsactivity = Intent(this, MapsActivity::class.java)
                            mapsactivity.putExtra("team", "technique")
                            startActivity(mapsactivity)
                        }
                    }
                }
    }

    fun joinPowerTeam (v: View) {
        var json : JSONObject = JSONObject()
        json.put("name", displayName)
        json.put("email", email)
        json.put("displayName", displayName)
        json.put("familyName", familyName)
        json.put("idGoogle", id)
        json.put("token", "")
        json.put("team", 3)
        json.put("urlPhoto", urlPhoto)

        ("http://" + ApiHandler.IP + ":" + ApiHandler.Port + "/signIn").
                httpPost().body(json.toString())
                .responseString { request, response, result ->
                    var zero : Long = 0
                    when (result) {
                        is Result.Failure -> {
                            //Pafuera
                        }
                        is Result.Success -> {
                            //Padentro
                            val mapsactivity = Intent(this, MapsActivity::class.java)
                            mapsactivity.putExtra("team", "technique")
                            startActivity(mapsactivity)
                        }
                    }
                }
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            when(position) {
                0 -> return InitialFragment.newInstance(position)
                1 -> return TechniqueFragment.newInstance(position)
                2 -> return ControlFragment.newInstance(position)
                3 -> return PowerFragment.newInstance(position)
                else -> return InitialFragment.newInstance(position)
            }

        }

        override fun getCount(): Int {
            return 4
        }
    }

    class InitialFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_select_team_initial, container, false)

            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): InitialFragment {
                val fragment = InitialFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }


    class TechniqueFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_select_team_technique, container, false)
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): TechniqueFragment {
                val fragment = TechniqueFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    class ControlFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_select_team_control, container, false)
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): ControlFragment {
                val fragment = ControlFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    class PowerFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_select_team_power, container, false)
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PowerFragment {
                val fragment = PowerFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

}
