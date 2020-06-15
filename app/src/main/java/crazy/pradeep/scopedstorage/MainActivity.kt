package crazy.pradeep.scopedstorage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import com.geeksmediapicker.GeeksMediaPicker
import com.geeksmediapicker.GeeksMediaType
import crazy.pradeep.scopedstorage.utils.ImageLoadUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun pickImage(view: View) {
        GeeksMediaPicker.with(this)
            .setMediaType(GeeksMediaType.IMAGE)
            .startSingle { mediaStoreData ->
                val imageUri = mediaStoreData.content_uri
                ImageLoadUtils.loadImage(this@MainActivity, imagePicked, imageUri)
            }
    }


    fun pickVideo(view: View) {
        GeeksMediaPicker.with(this)
            .setMediaType(GeeksMediaType.VIDEO)
            .startSingle { mediaStoreData ->
                val videoUri = mediaStoreData.content_uri

                val millis = mediaStoreData.media_duration

                if (videoUri != null) {

                    val videoDuration = String.format(
                        "%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                    );

                    Toast.makeText(
                        this@MainActivity,
                        "Video Selected: ${videoUri}\nDuration: $videoDuration",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }
}