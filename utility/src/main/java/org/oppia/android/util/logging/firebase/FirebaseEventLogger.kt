package org.oppia.android.util.logging.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import org.oppia.android.app.model.EventLog
import org.oppia.android.util.logging.EventBundleCreator
import org.oppia.android.util.logging.EventLogger
import org.oppia.android.util.networking.NetworkConnectionUtil
import java.util.Locale
import javax.inject.Singleton

private const val NETWORK_USER_PROPERTY = "NETWORK"
private const val COUNTRY_USER_PROPERTY = "COUNTRY"

/** Logger for event logging to Firebase Analytics. */
@Singleton
class FirebaseEventLogger(
  private val firebaseAnalytics: FirebaseAnalytics,
  private val eventBundleCreator: EventBundleCreator,
  private val networkConnectionUtil: NetworkConnectionUtil
) : EventLogger {
  private var bundle = Bundle()

  /** Logs an event to Firebase Analytics with [NETWORK_USER_PROPERTY] and [COUNTRY_USER_PROPERTY]. */
  override fun logEvent(eventLog: EventLog) {
    bundle = eventBundleCreator.createEventBundle(eventLog)
    firebaseAnalytics.logEvent(eventLog.context.activityContextCase.name, bundle)
    // TODO(#3792): Remove this usage of Locale.
    firebaseAnalytics.setUserProperty(COUNTRY_USER_PROPERTY, Locale.getDefault().displayCountry)
    firebaseAnalytics.setUserProperty(NETWORK_USER_PROPERTY, getNetworkStatus())
  }

  private fun getNetworkStatus(): String {
    return when (networkConnectionUtil.getCurrentConnectionStatus()) {
      NetworkConnectionUtil.ProdConnectionStatus.LOCAL ->
        NetworkConnectionUtil.ProdConnectionStatus.LOCAL.logName
      NetworkConnectionUtil.ProdConnectionStatus.CELLULAR ->
        NetworkConnectionUtil.ProdConnectionStatus.CELLULAR.logName
      else -> NetworkConnectionUtil.ProdConnectionStatus.NONE.logName
    }
  }
}
