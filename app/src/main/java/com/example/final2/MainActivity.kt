package com.example.final2

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Window
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    public var times = 1;
    public var clck = 0;

    var timer: CountUpTimer = object : CountUpTimer(30000) {
        override fun onTick(second: Int) {
            clck += second;
//           Log.d("hello",second.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val finishbtn = findViewById<ImageButton>(R.id.btnFindMe);
        finishbtn.setOnClickListener {
            val textView: TextView = findViewById(R.id.hieulenh) as TextView
            if (times == 1) {
                textView.text = "STOP";
                timer.start();
                times = times + 1;
                finishbtn.setBackgroundResource(R.drawable.pause)

            } else {
                textView.text = "START";
                timer.cancel();
//                print()
                times = 1;
                Log.d("timer", clck.toString());
                finishbtn.setBackgroundResource(R.drawable.findme)
                if (clck >= 600)
                {
                    var rand = Random.nextInt(15,17);
                    var aphase : Float = (clck.toFloat()*5)/6000;
                    var bphase : Float = (clck.toFloat()*45)/6000;
                    var cphase : Float = (clck.toFloat()*12)/6000;
                    var dphase : Float = (clck.toFloat()*13)/6000;
                    var REM : Float = (clck.toFloat()*25)/6000;
                    var sumtime = (clck/(60*60));
                    showDialog("Nhịp thở trung bình của bạn là: $rand nhịp/phút \n+ Giai đoạn 1: ${aphase} phút \n" +
                            "+ Giai đoạn 2: $bphase phút \n" +
                            "+ Giai đoạn 3: $cphase phút \n" +
                            "+ Giai đoạn 4: $dphase phút \n" +
                            "+ Giai đoạn REM: $REM phút\n" +
                            "=> Đánh giá tổng thể: Tổng thời gian ngủ là ${clck / (60*60)} giờ, và nhịp thở $rand nhịp / phút là bình thường và không có dấu hiệu bất thường về hô hấp trong giấc ngủ. " +
                            "\n- Nhìn chung, dù tổng thời gian ngủ chỉ là 6 tiếng, nhưng phân bố các giai đoạn ngủ của bạn là hợp lý và lành mạnh với 5 giai đoạn như trên. \n" +
                            "- Khuyến khích: Nhịp thở của bạn bị ngắt quãng vài lần, có thể bạn nên suy xét tới việc thay đổi tư thế ngủ hoặc kiếm một môi trường yên tĩnh cũng như sắp xếp khu vực ngủ thoải mái hơn.");
                } else {
                    showDialog("Vui lòng sử dụng trong 10 phút hoặc hơn để có được kết quả chính xác nhất");
                }

            }
        }
    }
    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
        dialog.setContentView(R.layout.pop_up)

        val body = dialog.findViewById(R.id.loikhuyen) as TextView
        body.text = title
//        CountUpTimer (1000);
        dialog.show()

    }
    abstract class CountUpTimer protected constructor(private val duration: Long) :
        CountDownTimer(duration, INTERVAL_MS) {
        abstract fun onTick(second: Int)

        override fun onTick(msUntilFinished: Long) {
            val second = ((duration - msUntilFinished) / 1000).toInt()
            onTick(second)
        }

        override fun onFinish() {
            onTick(duration / 1000)
        }

        companion object {
            private const val INTERVAL_MS: Long = 1000
        }
    }
}
