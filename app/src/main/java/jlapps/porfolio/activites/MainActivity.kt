package jlapps.porfolio.activites

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import kotlinx.android.synthetic.main.activity_main.*
import jlapps.porfolio.R
import jlapps.porfolio.fragments.Information

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        setupButtons()
        setupIcons()

    }

    private fun setupActionBar(){
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,R.color.teal_900)))
    }

    private fun setupButtons(){
        btn_education.setOnClickListener {
            var titles = ArrayList<String>()
            titles.add("University of Central Florida")
            var subtitles = ArrayList<String>()
            subtitles.add("Bachelors of Computer Science")
            subtitles.add("3.4 GPA")
            subtitles.add("Focus on Android Development")

            supportFragmentManager.commit {
                getFragmentInfo(this,"education",
                    bundleOf(Pair("header","Education"),Pair("titles",titles),Pair("subtitles",subtitles)))
            }
        }
        btn_experience.setOnClickListener {
            var titles = ArrayList<String>()
            titles.add("Paladin Holdings Inc. (Aug 2017 - Dec 2020)")
            titles.add("Mears Transportation (Sep 2018 - July 2020)")
            var subtitles = ArrayList<String>()
            subtitles.add("Unique application built from scratch")
            subtitles.add("Integration of BLE remote controller")
            subtitles.add("Ability to set favorite remote controllers for auto connect")
            subtitles.add("Ease of access with phone functions at a button press")
            subtitles.add("Ability to control music")
            subtitles.add("Ability to create voice recordings")
            subtitles.add("Ability to send/respond to messages/calls")
            subtitles.add("Built login system and profile data with PHP and mySQL backend")

            subtitles.add("***")

            subtitles.add("Created Uber styled transportation application")
            subtitles.add("Added backend support with AWS lambda and python functions")
            subtitles.add("Kotlin/Java codebase with from scratch UI elements")
            subtitles.add("Vehicle tracking and voice/text driver functionality")
            subtitles.add("Added Orlando International Airport flights support API with pickup locations")

            supportFragmentManager.commit {
                getFragmentInfo(this,"experience",
                    bundleOf(Pair("header","Experience"),Pair("titles",titles),Pair("subtitles",subtitles)))
            }
        }
        btn_projects.setOnClickListener {
            var titles = ArrayList<String>()
            titles.add("Super Smash Bros. Statistics (Smash Stats)")
            var subtitles = ArrayList<String>()
            subtitles.add("Webscraped data/images with python")
            subtitles.add("UI built from beautiful dribbble design")
            subtitles.add("AWS/Python backend for character data storage")
            subtitles.add("Custom quick wheel character skin selector")
            subtitles.add("Favorite characters skin widget slideshow")
            subtitles.add("-*link*-https://github.com/jlopez29/SSBU")
            supportFragmentManager.commit {
                getFragmentInfo(this,"projects",
                    bundleOf(Pair("header","Projects"),Pair("titles",titles),Pair("subtitles",subtitles)))
            }
        }
        btn_skills.setOnClickListener {
            var titles = ArrayList<String>()
            titles.add("Operating Systems")
            titles.add("Languages")
            var subtitles = ArrayList<String>()
            subtitles.add("Windows, Linux(Ubuntu), macOS")
            subtitles.add("***")
            subtitles.add("Kotlin, Java, Javascript, Python, PHP")
            supportFragmentManager.commit {
                getFragmentInfo(this,"skills",
                    bundleOf(Pair("header","Skills"),Pair("titles",titles),Pair("subtitles",subtitles)))
            }
        }
        btn_showcase.setOnClickListener {
            supportFragmentManager.commit {
                getFragmentInfo(this,"showcase",
                    bundleOf(Pair("header","Showcase")))
            }
        }
    }

    private fun getFragmentInfo(fragTransaction:FragmentTransaction, frag:String, bundle: Bundle){
        fragTransaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
        fragTransaction.setReorderingAllowed(true)
        fragTransaction.addToBackStack(frag)
        fragTransaction.replace<Information>(R.id.container_main,args = bundle)
    }

    private fun setupIcons(){
        ic_github.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.github.com/jlopez29")))
        }
        ic_linkedin.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/in/jessealexanderlopez222/")))
        }
    }
}