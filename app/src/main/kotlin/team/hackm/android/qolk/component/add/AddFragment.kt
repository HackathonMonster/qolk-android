package team.hackm.android.qolk.component.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import butterknife.bindView
import com.bumptech.glide.Glide
import team.hackm.android.qolk.R
import team.hackm.android.qolk.util.ImageUtil

class AddFragment : Fragment() {

    companion object {
        val CODE_INTENT_CAMERA: Int = 0
        fun createInstance(): AddFragment = AddFragment()
    }

    val editId: EditText by bindView(R.id.add_edit_id)
    val editBottleName: EditText by bindView(R.id.add_edit_bottle_name)
    val imageView: ImageView by bindView(R.id.add_image)

    private var imageUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.findViewById(R.id.add_image_button)?.setOnClickListener {
            imageUri = ImageUtil.getPhotoUri(activity)
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            }, CODE_INTENT_CAMERA)
        }
        view?.findViewById(R.id.add_button)?.setOnClickListener {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_INTENT_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Glide.with(context)
                        .load(imageUri)
                        .asBitmap()
                        // リサイズ（縦横の最大サイズを指定して、収める）
                        .override(600, 600)
                        .into(imageView)
            }
        }
    }

}
