package test.org.kastatest.ui.push_view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import test.org.kastatest.R
import test.org.kastatest.utils.Constants

class PushFragment : Fragment() {

    lateinit var mainView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_push, container, false)
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage()
    }

    private fun loadImage(imageUrl: String, imageView: ImageView) {

        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_image_holder)
        requestOptions.error(R.drawable.ic_no_image)
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.context!!)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
        }
    }

    companion object {
        fun newInstance(iconUrl: String): PushFragment {
            val fragment = PushFragment()
            val bundle = Bundle(1)
            bundle.putString(Constants.urlParam, iconUrl)
            fragment.arguments = bundle
            return fragment
        }
    }
}