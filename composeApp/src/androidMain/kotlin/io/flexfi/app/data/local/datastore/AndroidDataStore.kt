package io.flexfi.app.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.flexfi.app.data.local.createDataStore

private const val DATASTORE_FILENAME = "flexfi.preferences_pb"

fun createAndroidDataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(DATASTORE_FILENAME).absolutePath }
    )
