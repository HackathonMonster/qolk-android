package team.hackm.android.qolk.component.details

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState
import rx.Subscriber
import rx.schedulers.Schedulers
import team.hackm.android.qolk.R
import team.hackm.android.qolk.store.qolk.RemoteStore
import team.hackm.android.qolk.store.qolk.entity.Qolk
import team.hackm.android.qolk.store.realm.LocalStore
import team.hackm.android.qolk.store.realm.entity.Wine
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetailsFragment : Fragment() {

    companion object {
        val DATE_FORMAT = SimpleDateFormat("yyyy/MM/DD")
        val PARALLAX_RATE: Float = 0.5f
        val BADGE_IMAGES: IntArray = intArrayOf(
                R.mipmap.img_badge_bad,
                R.mipmap.img_badge_good,
                R.mipmap.img_badge_best
        )

        val KEY_NAME: String = "key_name"
        val KEY_DATE: String = "key_date"

        fun createInstance(name: String, date: Long): DetailsFragment =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(KEY_NAME, name)
                        putLong(KEY_DATE, date)
                    }
                }
    }

    val headerImageView: ImageView by bindView(R.id.details_image_header)
    val scrollView: ObservableScrollView by bindView(R.id.details_scrollview)
    val textViewName: TextView by bindView(R.id.details_text_name)
    val badgeImageView: ImageView by bindView(R.id.details_image_badge)
    val textViewDate: TextView by bindView(R.id.details_text_date)
    val textViewAlcohol: TextView by bindView(R.id.details_text_alcohol)
    val textViewHumidity: TextView by bindView(R.id.details_text_humidity)
    val textViewTemp: TextView by bindView(R.id.details_text_temperature)
    val textViewAdvise: TextView by bindView(R.id.details_text_advise)

    @Inject lateinit var localStore: LocalStore
    @Inject lateinit var remoteStore: RemoteStore
    var wine: Wine? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DetailsComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val display: Display = activity.windowManager.defaultDisplay
        val point: Point = Point()
        display.getSize(point)

        headerImageView.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, point.x / 3 * 2)
        scrollView.setPadding(0, point.x / 3 * 2, 0, resources.getDimensionPixelOffset(R.dimen.element_spacing_xxxlarge))
        scrollView.setScrollViewCallbacks(object : ObservableScrollViewCallbacks {
            override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
            }

            override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
                val translation: Float = Math.min(Math.max(0.0F, scrollY * PARALLAX_RATE), headerImageView.height * 1.0F);
                ViewCompat.setTranslationY(headerImageView, -translation)
            }

            override fun onDownMotionEvent() {
            }
        })

        localStore.getItem(arguments.getString(KEY_NAME), Date(arguments.getLong(KEY_DATE))).filter { wines ->
            wines.isLoaded
        }.subscribe { wines ->
            wine = wines.firstOrNull()
            wine?.let {
                ensureViewWine(it)
            }
        }
        remoteStore.getLastData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Qolk>() {
                    override fun onCompleted() {

                    }

                    override fun onError(error: Throwable?) {

                    }

                    override fun onNext(qolk: Qolk?) {
                        qolk?.let {
                            textViewAlcohol.text = getString(R.string.common_percentage, it.alcohol)
                            textViewHumidity.text = "${it.humidity}%"
                            textViewTemp.text = "${it.temperature}℃"
                        }
                    }
                })
    }

    private fun ensureViewWine(wine: Wine) {
        val random: Random = Random()
        Glide.with(activity)
                .load(wine.image)
                .asBitmap()
                .into(headerImageView)
        textViewName.text = wine.name
        Timber.d(wine.name)
        badgeImageView.setImageResource(BADGE_IMAGES[random.nextInt(2) + 1])
        textViewDate.text = DATE_FORMAT.format(wine.date)
        textViewAlcohol.text = getString(R.string.common_percentage, random.nextInt(12) + 5)
        textViewHumidity.text = getString(R.string.common_percentage, random.nextInt(70) + 30)
        textViewTemp.text = "${random.nextInt(15) + 10}℃"
        textViewAdvise.text = "あなたのワインはまだ大丈夫です。"
    }

}