package com.undabot.jobfair.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.undabot.jobfair.R
import com.undabot.jobfair.utils.ImageUtils.Companion.CACHE_SIZE

class ImageUtils {

    companion object {
        internal const val CACHE_SIZE: Long = 1024 * 1024 * 20 // 20MB

        fun load(
            context: Context,
            imageUrl: String,
            imageView: ImageView,
            @DrawableRes placeholder: Int = R.drawable.ic_placeholder,
            includeRadius: Boolean = true,
            transformOption: Transform = Transform.FIT_CENTER,
            roundImage: Boolean = false
        ) {
            val corners = if (includeRadius) {
                RoundedCorners(context.resources.getDimensionPixelSize(R.dimen.image_radius))
            } else {
                RoundedCorners(1)
            }
            var requestOptions = RequestOptions()
                .placeholder(placeholder)
                .error(placeholder)
                .transform(transformationFrom(transformOption), corners)

            if (roundImage) {
                requestOptions = requestOptions.apply { circleCrop() }
            }

            GlideApp.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView)
        }

        private fun transformationFrom(transformOption: Transform): Transformation<Bitmap>? =
            when (transformOption) {
                Transform.CENTER_CROP -> CenterCrop()
                else -> FitCenter()
            }
    }

    enum class Transform {
        CENTER_CROP, FIT_CENTER
    }
}

@GlideModule
class GlideAppModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, CACHE_SIZE))
    }
}