/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.android.settings.bluetooth;

import com.android.settings.bluetooth.LocalBluetoothProfileManager.Profile;
import com.android.settings.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * BluetoothProfilePreference is the preference type used to display each profile for a
 * particular bluetooth device.
 */
public class BluetoothProfilePreference extends Preference implements OnClickListener {

    private static final String TAG = "BluetoothProfilePreference";

    private Drawable mProfileDrawable;
    private boolean mExpanded;
    private ImageView mProfileExpandView;
    private Profile mProfile;

    private OnClickListener mOnExpandClickListener;

    public BluetoothProfilePreference(Context context, Profile profile) {
        super(context);

        mProfile = profile;

        setWidgetLayoutResource(R.layout.preference_bluetooth_profile);
        setExpanded(false);
    }

    public void setOnExpandClickListener(OnClickListener listener) {
        mOnExpandClickListener = listener;
    }

    public void setExpanded(boolean expanded) {
        mExpanded = expanded;
        notifyChanged();
    }

    public boolean isExpanded() {
        return mExpanded;
    }

    public void setProfileDrawable(Drawable drawable) {
        mProfileDrawable = drawable;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        ImageView btProfile = (ImageView) view.findViewById(android.R.id.icon);
        btProfile.setImageDrawable(mProfileDrawable);

        mProfileExpandView = (ImageView) view.findViewById(R.id.profileExpand);
        mProfileExpandView.setOnClickListener(this);
        mProfileExpandView.setTag(mProfile);

        mProfileExpandView.setImageResource(mExpanded ? R.drawable.ic_preferences_expanded
                : R.drawable.ic_preferences_collapsed);
    }

    public void onClick(View v) {
        if (v == mProfileExpandView) {
            if (mOnExpandClickListener != null) {
                setExpanded(!mExpanded);
                mOnExpandClickListener.onClick(v);
            }
        }
    }
}