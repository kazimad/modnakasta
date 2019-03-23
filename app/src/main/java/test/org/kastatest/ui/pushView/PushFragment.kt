package test.org.kastatest.ui.pushView

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_push.*
import test.org.kastatest.R
import test.org.kastatest.utils.Constants


class PushFragment : Fragment() {

    private lateinit var mainView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_push, container, false)
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage(arguments!!.getString(Constants.urlParam), imageView)
    }

    private fun loadImage(imageUrl: String?, imageView: ImageView) {

        val requestOptions = RequestOptions()
//        requestOptions.placeholder(R.drawable.ic_image_holder)
        requestOptions.error(R.drawable.ic_no_image)
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.context!!)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade(5000))
                    .into(imageView)
        } else {
            Toast.makeText(imageView.context, R.string.image_error, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance(iconUrl: String?): PushFragment {
            val fragment = PushFragment()
            val bundle = Bundle(1)
            bundle.putString(Constants.urlParam, iconUrl)
            fragment.arguments = bundle
            return fragment
        }
    }
}