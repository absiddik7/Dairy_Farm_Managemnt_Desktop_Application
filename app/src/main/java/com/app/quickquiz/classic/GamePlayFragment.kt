package com.app.quickquiz.classic

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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.quickquiz.R
import com.app.quickquiz.bookmarkDB.BookmarkData
import com.app.quickquiz.bookmarkDB.BookmarkDatabase
import com.app.quickquiz.databinding.FragmentGamePlayBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class GamePlayFragment : Fragment() {

    private lateinit var binding: FragmentGamePlayBinding
    private lateinit var dbQuestion: ArrayList<QuestionDataJson>
    private lateinit var qsId: ArrayList<BookmarkData>
    private lateinit var optionContainer: LinearLayout
    private lateinit var timer: CountDownTimer
    private val args: GamePlayFragmentArgs by navArgs()
    private lateinit var gamePlayViewModel: GamePlayViewModel
    private var index = 0
    private var count = 0
    private var rightAns = 0L
    private var wrongAns = 0L
    private var unAnswered = 0L
    private var life = 3
    private var token = ""
    private var categoryName = ""
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_play,
            container,
            false
        )
        // Hide BottomNavigationView
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.navView)
        navBar.visibility = View.GONE

        val application = requireNotNull(this.activity).application
        val dataSource = BookmarkDatabase.getInstance(application).bookmarkDatabaseDao
        val viewModelFactory = GamePlayViewModelFactory(categoryName, dataSource)

        gamePlayViewModel =
            ViewModelProvider(this, viewModelFactory).get(GamePlayViewModel::class.java)
        binding.lifecycleOwner = this

        categoryName = args.catName
        optionContainer = binding.optionContainer
        binding.gameLife.text = life.toString()

        dbQuestion = ArrayList()
        qsId = ArrayList()

        var cateName: String?
        val charset: Charset = Charsets.UTF_8

        try {
            if (categoryName == "Random") {
                token = "Classic"
                val cateNameFile =
                    listOf("Ordinary.json",
                        "Geography.json",
                        "History.json",
                        "Science.json",
                        "Sports.json",
                        "Universe.json")

                for (fileName in cateNameFile) {
                    val myUsersJSONFile = requireContext().assets.open(fileName)
                    val size = myUsersJSONFile.available()
                    val buffer = ByteArray(size)
                    myUsersJSONFile.read(buffer)
                    myUsersJSONFile.close()
                    cateName = String(buffer, charset)
                    getDataFromJson(cateName)
                }
            } else {
                token = categoryName
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

        countDownTimer()

        binding.bookmarkButton.setOnClickListener{
            binding.bookmarkButton.setImageResource(R.drawable.ic_bookmark_fill)
            insertBookmark()
        }

        binding.nextBtn.setOnClickListener {
            binding.apply {
                questionText.text = ""
                option1.text = ""
                option2.text = ""
                option3.text = ""
                option4.text = ""
            }

            binding.nextBtn.isEnabled = false
            binding.nextBtn.alpha = 0.5F
            enableOption(true)
            timer.start()
            index++
            if (index < dbQuestion.size) {
                count = 0
                playAnim(binding.questionText, 0, dbQuestion[index].question)
                //duplicateQSCheck()

                timer.cancel()
            } else {
                this.findNavController()
                    .navigate(
                        GamePlayFragmentDirections.actionGamePlayFragmentToScoreFragment(
                            rightAns,
                            wrongAns,
                            unAnswered,
                            token
                        )
                    )
                timer.cancel()
            }
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
                            GamePlayFragmentDirections.actionGamePlayFragmentToScoreFragment(
                                rightAns,
                                wrongAns,
                                unAnswered,
                                token
                            )
                        )
                }

                when (dbQuestion[index].answer) {
                    binding.option1.text -> binding.option1.setBackgroundResource(R.drawable.option_right)
                    binding.option2.text -> binding.option2.setBackgroundResource(R.drawable.option_right)
                    binding.option3.text -> binding.option3.setBackgroundResource(R.drawable.option_right)
                    binding.option4.text -> binding.option4.setBackgroundResource(R.drawable.option_right)
                }

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
                        timer.cancel()
                        timer.start()
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
        binding.nextBtn.isEnabled = true
        binding.nextBtn.alpha = 1F

        if (selectedOption.text.toString() == dbQuestion[index].answer) {
            //correct answer
            timer.cancel()
            selectedOption.setBackgroundResource(R.drawable.option_right)
            rightAns++
        } else {
            //wrong answer
            timer.cancel()
            selectedOption.setBackgroundResource(R.drawable.option_wrong)
            wrongAns++
            val correctOption: TextView = optionContainer.findViewWithTag(dbQuestion[index].answer)
            correctOption.setBackgroundResource(R.drawable.option_right)
            life--
            binding.gameLife.text = life.toString()

            if (life < 1) {
                this.findNavController()
                    .navigate(
                        GamePlayFragmentDirections.actionGamePlayFragmentToScoreFragment(
                            rightAns,
                            wrongAns,
                            unAnswered,
                            token
                        )
                    )
            }
        }
    }

    private fun enableOption(enable: Boolean) {
        timer.cancel()
        timer.start()
        for (i in 0..3) {
            optionContainer.getChildAt(i).isEnabled = enable
            if (enable) {
                optionContainer.getChildAt(i).setBackgroundResource(R.drawable.option_unselected)
            }
        }
    }

    private fun onQuiting() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.apply {
            setCancelable(true)
            setTitle("Are you sure?")
            setMessage("Do you want to leave?")
            setPositiveButton("Yes") { _, _ ->
                findNavController().navigate(GamePlayFragmentDirections.actionGamePlayFragmentToScoreFragment(
                    rightAns,
                    wrongAns,
                    unAnswered,
                    token
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

            val qsDetails = QuestionDataJson(id, qs, option1, option2, option3, option4, answer)
            dbQuestion.add(qsDetails)
            dbQuestion.shuffle()
            playAnim(binding.questionText, 0, dbQuestion[index].question)
            //insertBookmark()

            //duplicateQSCheck()
            continue

        }
    }

    private fun insertBookmark() {
        val application = requireNotNull(this.activity).application
        val dataSource = BookmarkDatabase.getInstance(application).bookmarkDatabaseDao
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val qsExists = dataSource.exists(dbQuestion[index].question)!!
                val bookmarkDetails =
                    BookmarkData(0L, dbQuestion[index].question, true, dbQuestion[index].answer)
                if (!qsExists) {
                    dataSource.insert(bookmarkDetails)
                }
            }
        }
    }
}