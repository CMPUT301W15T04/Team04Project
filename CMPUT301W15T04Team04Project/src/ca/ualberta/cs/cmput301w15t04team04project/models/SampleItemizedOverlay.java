/*
* Copyright 2015 Weijie Sun
* Copyright 2015 Youdong Ma
* Copyright 2015 Yufei Zhang
* Copyright 2015 Chenrui Lei
* Copyright 2015 Yang Zhang
* Copyright 2015 Ji Yang
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


package ca.ualberta.cs.cmput301w15t04team04project.models;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy.bitmap;
import org.osmdroid.api.IMapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.OverlayItem.HotspotPlace;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class SampleItemizedOverlay extends ItemizedOverlay<SampleOverlayItem>
		implements ItemizedOverlay.OnFocusChangeListener {
	private boolean mFocusChanged = false;
	private View mPopupView = null;

	public SampleItemizedOverlay(Drawable pDefaultMarker, Context pContext) {
		super(pDefaultMarker, new DefaultResourceProxyImpl(pContext));
		populate();
		setOnFocusChangeListener(this);
	}

	@Override
	protected SampleOverlayItem createItem(int i) {
		SampleOverlayItem item;
		if (i == 0)
			item = new SampleOverlayItem("CentralPark", "Central Park",
					"Central Park in New York City", new GeoPoint(40.7820,
							-73.9660), null, HotspotPlace.BOTTOM_CENTER);
		else
			item = new SampleOverlayItem("NorthCentralPark",
					"North Central Park",
					"North of Central Park in New York City", new GeoPoint(
							41.7820, -73.9660),
					mResourceProxy.getDrawable(bitmap.person),
					HotspotPlace.CENTER);
		return item;
	}

	@Override
	public void onFocusChanged(ItemizedOverlay<?> overlay, OverlayItem newFocus) {
		mFocusChanged = true;
	}

	@Override
	protected boolean onTap(int index) {
		setFocus(getItem(index));
		return true;
	}

	@Override
	protected void draw(Canvas c, MapView mapView, boolean shadow) {
		if (mFocusChanged) {
			mFocusChanged = false;
			// Remove any current focus
			if (mPopupView != null)
				mapView.removeView(mPopupView);
			SampleOverlayItem item = this.getFocus();
			if (item != null) {
				mPopupView = getPopupView(mapView.getContext(), item);
				MapView.LayoutParams lp = new MapView.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
						item.getPoint(), MapView.LayoutParams.TOP_CENTER, 0, 0);
				mapView.addView(mPopupView, lp);
			}
		}
		super.draw(c, mapView, shadow);
	}

	protected View getPopupView(Context context, SampleOverlayItem item) {
		TextView tv = new TextView(context);
		tv.setText(item.getTitle());
		tv.setBackgroundColor(Color.BLACK);
		return tv;
	}

	@Override
	protected void onDrawItem(Canvas canvas, SampleOverlayItem item,
			Point curScreenCoords, final float aMapOrientation) {
		super.onDrawItem(canvas, item, curScreenCoords, aMapOrientation);
	}

	@Override
	public int size() {
		return 2;
	}

	@Override
	public boolean onSnapToItem(int arg0, int arg1, Point arg2, IMapView arg3) {
		return false;
	}
}
