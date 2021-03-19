package com.app.quickquiz.arcade

import android.animation.Animator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.quickquiz.R
import com.app.quickquiz.classic.QuestionData
import com.app.quickquiz.classic.QuestionDataJson
import com.app.quickquiz.databinding.FragmentArcadeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class ArcadeFragment : Fragment() {

    private lateinit var binding: FragmentArcadeBinding
    private val args: ArcadeFragmentArgs by navArgs()
    private lateinit var dbQuestion: ArrayList<QuestionDataJson>
    private lateinit var qsIndex: ArrayList<String>
    private lateinit var optionContainer: LinearLayout
    private lateinit var timer: CountDownTimer
    private var milliSeconds: Long = 0
    private var rightAns = 0L
    private var wrongAns = 0L
    private var index = 0
    private var count = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_arcade, container, false)

        // Hide Bottom navigation view
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.GONE

        val categoryName = args.categoryName
        milliSeconds = args.minutes * 60000L
        optionContainer = binding.allOption

        dbQuestion = ArrayList()
        qsIndex = ArrayList()

        var cateName: String?
        val charset: Charset = Charsets.UTF_8
        try {
            if (categoryName == "Random") {
                //token = "Classic"
                val cateNameFile =
                    listOf("Ordinary",
                        "Geography",
                        "History",
                        "Science",
                        "Sports",
                        "Universe")

                for (fileName in cateNameFile) {
                    val myUsersJSONFile = requireContext().assets.open("${fileName}.json")
                    val size = myUsersJSONFile.available()
                    val buffer = ByteArray(size)
                    myUsersJSONFile.read(buffer)
                    myUsersJSONFile.close()
                    cateName = String(buffer, charset)
                    getDataFromJson(cateName)
                }
            } else {
                //token = categoryName
                val jsonFile = "${categoryName}.json"
                val myUsersJSONFile = requireContext().assets.open(jsonFile)
                val size = myUsersJSONFile.available()
                val buffer = ByteArray(size)
                myUsersJSONFile.read(buffer)
                myUsersJSONFile.close()
                cateName = String(buffer, charset)
                getDataFromJson(cateName)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        for (i in 0..3) {
            optionContainer.getChildAt(i).setOnClickListener {
                if (optionContainer.getChildAt(i) == binding.option1) {
                    if (binding.option1.text != "") {
                        checkAnswer(binding.option1)
                    }
                } else if (optionContainer.getChildAt(i) == binding.option2) {
                    if (binding.option2.text != "") {
                        checkAnswer(binding.option2)
                    }
                } else if (optionContainer.getChildAt(i) == binding.option3) {
                    if (binding.option3.text != "") {
                        checkAnswer(binding.option3)
                    }
                } else if (optionContainer.getChildAt(i) == binding.option4) {
                    if (binding.option4.text != "") {
                        checkAnswer(binding.option4)
                    }
                }
            }
        }

        binding.btnNext.setOnClickListener {
            binding.apply {
                questionTxt.text = ""
                option1.text = ""
                option2.text = ""
                option3.text = ""
                option4.text = ""
            }

            binding.btnNext.isEnabled = false
            binding.btnNext.alpha = 0.5F
            enableOption(true)
            index++
            if (index < dbQuestion.size) {
                count = 0
                playAnim(binding.questionTxt, 0, dbQuestion[index].question)
            } else {
                findNavController()
                    .navigate(
                        ArcadeFragmentDirections.actionArcadeFragmentToScoreFragment(
                            rightAns,
                            wrongAns,
                            0,
                            "Arcade"
                        )
                    )
                timer.cancel()
            }
        }

        binding.arcadeQuitBtn.setOnClickListener{
            onQuit()
        }

        countDownTimer()
        return binding.root
    }

    private fun countDownTimer() {
        timer = object : CountDownTimer(milliSeconds+1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timerClock.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                findNavController()
                    .navigate(
                        ArcadeFragmentDirections.actionArcadeFragmentToScoreFragment(
                            rightAns,
                            wrongAns,
                            0,
                            "Arcade"
                        )
                    )
            }
        }.start()
    }

    private fun playAnim(view: View, value: Int, data: String) {
        view.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat())
            .setDuration(500).setStartDelay(100)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    if (value == 0 && count < 4) {
                        var option = ""
                        val questionIndex = dbQuestion[index]

                        when (count) {
                            0 -> option = questionIndex.option1
                            1 -> option = questionIndex.option2
                            2 -> option = questionIndex.option3
                            3 -> option = questionIndex.option4
                        }
                        playAnim(optionContainer.getChildAt(count), 0, option)
                        count++
                    }
                }

                override fun onAnimationEnd(p0: Animator?) {
                    if (value == 0) {
                        (view as TextView).text = data
                        view.setTag(data)
                        playAnim(view, 1, data)
                    }
                }

                override fun onAnimationCancel(p0: Animator?) {}
                override fun onAnimationRepeat(p0: Animator?) {}
            })
    }

    private fun checkAnswer(selectedOption: TextView) {
        enableOption(false)
        binding.btnNext.isEnabled = true
        binding.btnNext.alpha = 1F

        if (selectedOption.text.toString() == dbQuestion[index].answer) {
            //correct answer
            selectedOption.setBackgroundResource(R.drawable.option_right)
            rightAns++
        } else {
            //wrong answer
            selectedOption.setBackgroundResource(R.drawable.option_wrong)
            wrongAns++
            val correctOption: TextView = optionContainer.findViewWithTag(dbQuestion[index].answer)
            correctOption.setBackgroundResource(R.drawable.option_right)
        }
    }

    private fun enableOption(enable: Boolean) {

        for (i in 0..3) {
            optionContainer.getChildAt(i).isEnabled = enable
            if (enable) {
                optionContainer.getChildAt(i).setBackgroundResource(R.drawable.option_unselected)
            }
        }
    }

    private fun onQuit(){
        val builder = AlertDialog.Builder(requireActivity())
        builder.apply {
            setCancelable(true)
            setTitle("Are you sure?")
            setMessage("Do you want to leave?")
            setPositiveButton("Yes"){_,_ -> findNavController().navigate(ArcadeFragmentDirections.actionArcadeFragmentToHomeFragment())}
            setNegativeButton("No",null)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun getDataFromJson(catName: String) {
        val obj = JSONObject(catName)
        val qsData = obj.getJSONArray("questionsData")
        for (i in 0 until qsData.length()) {
            val qsObj = qsData.getJSONObject(i)

            val id = qsObj.getInt("id")
            val qs = qsObj.getString("qs")
            val option1 = qsObj.getString("option1")
            val option2 = qsObj.getString("option2")
            val option3 = qsObj.getString("option3")
            val option4 = qsObj.getString("option4")
            val answer = qsObj.getString("answer")

            val qsDetails =
                QuestionDataJson(id, qs, option1, option2, option3, option4, answer)
            dbQuestion.add(qsDetails)

            dbQuestion.shuffle()
            playAnim(binding.questionTxt, 0, dbQuestion[index].question)

            continue
        }
    }
}