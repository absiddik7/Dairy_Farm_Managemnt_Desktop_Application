package com.app.quickquiz.trueFalse

import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.quickquiz.R
import com.app.quickquiz.classic.QuestionDataJson
import com.app.quickquiz.database.ScoreDatabase
import com.app.quickquiz.databinding.FragmentTrueFalseGameBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class TrueFalseGame : Fragment() {
    private lateinit var binding: FragmentTrueFalseGameBinding
    private lateinit var timer: CountDownTimer
    private lateinit var questionDB: ArrayList<QsData>
    private val args: TrueFalseGame by navArgs()
    private lateinit var trueFalseViewModel: TrueFalseViewModel
    private val viewModelJob = Job()
    private var index = 0
    private var count = 0
    private var rightAns = 0L
    private var wrongAns = 0L
    private var unAnswered = 0L
    private var life = 3
    private var token = "TrueFalse"
    private var categoryName = "TrueFalse"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_true_false_game, container, false)

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.GONE

        val application = requireNotNull(this.activity).application
        val dataSource = ScoreDatabase.getInstance(application).scoreDatabaseDao
        val viewModelFactory = TrueFalseViewModelFactory(categoryName, dataSource)

        trueFalseViewModel =
            ViewModelProvider(this, viewModelFactory).get(TrueFalseViewModel::class.java)
        binding.lifecycleOwner = this

        trueFalseViewModel.getIndexNo()
        trueFalseViewModel.indexNo.observe(viewLifecycleOwner, { it ->
            it?.let {
                index = it
            }
        })


        questionDB = ArrayList()
        countDownTimer()


        val cateName: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = requireContext().assets.open("TrueFalse.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            cateName = String(buffer, charset)
            getDataFromJson(cateName)

        } catch (e: JSONException) {
            e.printStackTrace()
        }


        binding.gameLife.text = life.toString()
        binding.rightAnsTxt.visibility = View.GONE
        binding.trueBtn.setOnClickListener {
            checkAnswer(true)
        }
        binding.falseBtn.setOnClickListener {
            checkAnswer(false)
        }
        binding.nextBtn.setOnClickListener {
            onNext()
        }
        binding.gamePlayQuitBtn.setOnClickListener {
            onQuiting()
        }


        return binding.root

    }

    private fun countDownTimer() {
        timer = object : CountDownTimer(16000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countDownTimer.text = (millisUntilFinished / 1000).toString()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                enableOption(false)
                timer.cancel()
                life--
                unAnswered++
                binding.nextBtn.isEnabled = true
                binding.nextBtn.alpha = 1F
                binding.gameLife.text = life.toString()

                if (life < 1) {
                    findNavController()
                        .navigate(
                            TrueFalseGameDirections.actionTrueFalseGameToScoreFragment(
                                rightAns,
                                wrongAns,
                                unAnswered,
                                token,
                                index,
                                questionDB.size
                            ))
                }

                binding.apply {
                    rightAnsTxt.visibility = View.VISIBLE
                    rightAnsTxt.text = "Answer: ${questionDB[index].qsAns}"
                }
            }
        }.start()
    }

    private fun enableOption(enable: Boolean) {
        timer.cancel()
        timer.start()

        binding.trueBtn.isEnabled = enable
        binding.falseBtn.isEnabled = enable
        if (!enable) {
            binding.trueBtn.alpha = 0.5F
            binding.falseBtn.alpha = 0.5F
        } else{
            binding.ansIcon.setImageResource(R.drawable.ic_question)
            binding.trueBtn.alpha = 1F
            binding.falseBtn.alpha = 1F
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(selectedOption: Boolean) {
        enableOption(false)
        binding.nextBtn.isEnabled = true
        binding.nextBtn.alpha = 1F

        if (selectedOption && questionDB[index].answer == "True") {
            //correct answer
            timer.cancel()
            binding.ansIcon.setImageResource(R.drawable.ic_right)
            rightAns++
        } else if(!selectedOption && questionDB[index].answer == "False"){
            binding.apply {
                rightAnsTxt.visibility = View.VISIBLE
                rightAnsTxt.text = "Answer: ${questionDB[index].qsAns}"
                ansIcon.setImageResource(R.drawable.ic_right)
            }
            timer.cancel()
            rightAns++
        }
        else {
            //wrong answer
            timer.cancel()
            binding.ansIcon.setImageResource(R.drawable.ic_wrong)
            wrongAns++
            binding.apply {
                rightAnsTxt.visibility = View.VISIBLE
                rightAnsTxt.text = "Answer: ${questionDB[index].qsAns}"
            }

            life--
            binding.gameLife.text = life.toString()

            if (life < 1) {
                this.findNavController()
                    .navigate(
                        TrueFalseGameDirections.actionTrueFalseGameToScoreFragment(
                            rightAns,
                            wrongAns,
                            unAnswered,
                            token,
                            index,
                            questionDB.size
                        )
                    )
            }
        }
    }

    private fun playAnim(view: View, value: Int, data: String) {
        view.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat())
            .setDuration(500).setStartDelay(100)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    if (value == 0 && count < 4) {
                        timer.cancel()
                        timer.start()
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

    private fun getDataFromJson(catName: String) {

        val obj = JSONObject(catName)
        val qsData = obj.getJSONArray("questionsData")
        for (i in 0 until qsData.length()) {
            val qsObj = qsData.getJSONObject(i)
            val qs = qsObj.getString("qs")
            val qsAns = qsObj.getString("qsAns")
            val answer = qsObj.getString("answer")

            val qsDetails = QsData(qs, qsAns, answer)
            questionDB.add(qsDetails)

            continue
        }

        trueFalseViewModel.indexNo.observe(viewLifecycleOwner, { it ->
            it?.let {
                if (it == 0) {
                    questionDB.shuffle()
                }
                index = it
                if (index < questionDB.size) {
                    playAnim(binding.questionText, 0, questionDB[index].question)
                } else {
                    //endOfQSBankDialog()
                    index = 0
                    playAnim(binding.questionText, 0, questionDB[index].question)
                }
            }
        })
    }

    private fun onQuiting() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.apply {
            setCancelable(true)
            setTitle("Are you sure?")
            setMessage("Do you want to leave?")
            setPositiveButton("Yes") { _, _ ->
                findNavController().navigate(TrueFalseGameDirections.actionTrueFalseGameToScoreFragment(
                    rightAns,
                    wrongAns,
                    unAnswered,
                    token,
                    index,
                    questionDB.size
                ))
            }
            setNegativeButton("No", null)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun onNext() {
        binding.apply{
            questionText.text = ""
            rightAnsTxt.visibility = View.GONE
            nextBtn.isEnabled = false
            nextBtn.alpha = 0.5F
        }
        enableOption(true)
        timer.start()
        index++
        if (index < questionDB.size) {
            count = 0

            playAnim(binding.questionText, 0, questionDB[index].question)
            timer.cancel()
        } else {
            this.findNavController()
                .navigate(
                    TrueFalseGameDirections.actionTrueFalseGameToScoreFragment(
                        rightAns,
                        wrongAns,
                        unAnswered,
                        token,
                        index,
                        questionDB.size
                    )
                )
            timer.cancel()
        }
    }
}