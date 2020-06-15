package crazy.pradeep.scopedstorage.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoadUtils {

    fun loadImage(context: Context, imageView: ImageView, uri: Uri?) {
        if (uri == null) {
            return
        }

        Glide.with(context)
            .load(uri)
            .thumbnail(0.3f)
            .into(imageView)
    }
}