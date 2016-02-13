package com.hosshan.android.salad.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.text.toByteArray

public object ImageUtil {

    /**
     * 画像のディレクトリパスを取得する

     * @return
     */
    private fun getDirPath(context: Context): String {
        var dirPath = ""
        var photoDir: File? = null
        val extStorageDir = Environment.getExternalStorageDirectory()
        if (extStorageDir.canWrite()) {
            photoDir = File(extStorageDir.path + "/" + context.packageName)
        }
        photoDir?.let {
            if (!it.exists()) {
                it.mkdirs()
            }
            if (it.canWrite()) {
                dirPath = it.path
            }
        }
        return dirPath
    }

    public fun resize(bitmap: Bitmap?, parcent: Float): Bitmap? {
        if (bitmap == null || parcent < 0) {
            return null
        }
        return resize(bitmap, (bitmap.width * parcent).toInt(), (bitmap.height * parcent).toInt())
    }

    public fun resize(bitmap: Bitmap?, targetWidth: Int, targetHeight: Int): Bitmap? {
        if (bitmap == null || targetWidth < 0 || targetHeight < 0) {
            return null
        }
        val pictureWidth = bitmap.width
        val pictureHeight = bitmap.height
        val scale = Math.min(targetWidth.toFloat() / pictureWidth, targetHeight.toFloat() / pictureHeight) // (1)

        val matrix = Matrix()
        matrix.postScale(scale, scale)

        return Bitmap.createBitmap(bitmap, 0, 0, pictureWidth, pictureHeight, matrix, true)
    }

    public fun writeImage(file: File, mBitmap: Bitmap): Boolean {
        try {
            val fo = FileOutputStream(file)
            mBitmap.compress(CompressFormat.JPEG, 100, fo)
            fo.flush()
            fo.close()
        } catch (e: Exception) {
            return false
        }

        return true
    }

    @Throws(IOException::class)
    public fun loadImage(context: Context, uri: Uri, isLandscape: Boolean): Bitmap {
        //画面の向きを管理する。
        var landscape = false
        var bm: Bitmap
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        context.contentResolver.openInputStream(uri)?.let {
            BitmapFactory.decodeStream(it, null, options)
            it.close()
        }
        var ow = options.outWidth
        var oh = options.outHeight
        //画面が横になっていたら。
        if (ow > oh && isLandscape) {
            landscape = true
            //縦と横を逆にする。
            oh = options.outWidth
            ow = options.outHeight
        }
        val width = (ow * 0.6).toInt()
        val height = (oh * 0.6).toInt()

        options.inJustDecodeBounds = false
        options.inSampleSize = Math.max(ow / width, oh / height)
        val is2 = context.contentResolver.openInputStream(uri)
        bm = BitmapFactory.decodeStream(is2, null, options)
        is2.close()
        if (landscape) {
            val matrix = Matrix()
            matrix.setRotate(90.0f)
            bm = Bitmap.createBitmap(bm, 0, 0, bm.width, bm.height, matrix, false)
        }
        bm = Bitmap.createScaledBitmap(bm, width, (width * (oh.toDouble() / ow.toDouble())).toInt(), false)
        val offBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val offCanvas = Canvas(offBitmap)
        offCanvas.drawBitmap(bm, 0f, ((height - bm.height) / 2).toFloat(), null)
        bm = offBitmap
        return bm
    }

    public fun encodeImageBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 100, outputStream)
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
    }

    public fun decodeImageBase64(bitmapString: String): Bitmap {
        val bytes = Base64.decode(bitmapString.toByteArray(), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

}
