package org.oppia.android.testing

import dagger.Binds
import dagger.Module
import org.oppia.android.util.logging.EventLogger
import org.oppia.android.util.logging.ExceptionLogger
import org.oppia.android.util.logging.SyncStatusManager

/** Provides fake log reporting dependencies. */
@Module
interface TestLogReportingModule {

  @Binds
  fun bindFakeExceptionLogger(fakeExceptionLogger: FakeExceptionLogger): ExceptionLogger

  @Binds
  fun bindFakeEventLogger(fakeEventLogger: FakeEventLogger): EventLogger

  @Binds
  fun bindFakeSyncStateManager(fakeSyncStatusManager: FakeSyncStatusManager): SyncStatusManager
}
