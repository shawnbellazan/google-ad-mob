package com.tooensure.googleadmob

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class GoogleAdMobViewModel: ViewModel() {
    var mInterstitialAd: MutableState<InterstitialAd?> = mutableStateOf(null)

    // Create a full screen content callback.
    var fullScreenContentCallback: FullScreenContentCallback =
        object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                mInterstitialAd.value = null
                // Proceed to the next level.
            }
        }

    fun loadAd(context: Context) {
        InterstitialAd.load(
            context,
            AD_UNIT_ID,
            AdRequest.Builder().build(),

            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd.value = ad
//                    mInterstitialAd.value?.fullScreenContentCallback = fullScreenContentCallback
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Code to be executed when an ad request fails.
                }
            })

    }


    fun showInterstitial(context: Context, onAdDismissed: () -> Unit) {
        val activity = context.findActivity()

        if (mInterstitialAd != null && activity != null) {
            mInterstitialAd.value?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdFailedToShowFullScreenContent(e: AdError) {
                    mInterstitialAd.value = null
                }

                override fun onAdDismissedFullScreenContent() {
                    mInterstitialAd.value = null
                    onAdDismissed()
                }
            }
            mInterstitialAd.value?.show(activity)
        }

    }
}