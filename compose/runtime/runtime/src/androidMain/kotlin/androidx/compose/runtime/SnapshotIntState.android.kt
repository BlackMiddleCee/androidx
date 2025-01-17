/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.runtime

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

internal actual fun createSnapshotMutableIntState(
    value: Int
): MutableIntState = ParcelableSnapshotMutableIntState(value)

@SuppressLint("BanParcelableUsage")
private class ParcelableSnapshotMutableIntState(
    value: Int
) : SnapshotMutableIntStateImpl(value), Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(intValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR = object : Parcelable.Creator<ParcelableSnapshotMutableIntState> {
            override fun createFromParcel(parcel: Parcel): ParcelableSnapshotMutableIntState {
                return ParcelableSnapshotMutableIntState(
                    value = parcel.readInt()
                )
            }

            override fun newArray(size: Int) =
                arrayOfNulls<ParcelableSnapshotMutableIntState>(size)
        }
    }
}
