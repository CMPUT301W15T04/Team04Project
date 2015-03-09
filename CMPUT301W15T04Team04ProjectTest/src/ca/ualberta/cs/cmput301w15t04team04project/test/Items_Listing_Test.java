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
package ca.ualberta.cs.cmput301w15t04team04project.test;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;
import android.widget.TextView;

import ca.ualberta.cs.cmput301w15t04team04project.AddEditExpenseActivity;
import ca.ualberta.cs.cmput301w15t04team04project.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.ClaimList;
import ca.ualberta.cs.cmput301w15t04team04project.Destination;
import ca.ualberta.cs.cmput301w15t04team04project.Manager;
import ca.ualberta.cs.cmput301w15t04team04project.OneClaimActivity;
import ca.ualberta.cs.cmput301w15t04team04project.models.Item;
import junit.framework.TestCase;

public class Items_Listing_Test extends
		ActivityInstrumentationTestCase2<OneClaimActivity> {
	final ClaimList claimList = Manager.getClaimList();
	Activity activity;
	ListView itemList;

	public Items_Listing_Test() {
		super(OneClaimActivity.class);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
		// add a claim to test on
		Claim claim = new Claim("Test");
		Item itemA = new Item("food");
		Item itemB = new Item("texi");
		Item itemC = new Item("hotel");
		itemA.setAmount(12);
		itemB.setAmount(15);
		itemB.setAmount(38);
		claim.addItem(itemA);
		claim.addItem(itemB);
		claim.addItem(itemC);
		Intent intent = new Intent();
		intent.putExtra("Index", 0);
		setActivityIntent(intent);
		activity = getActivity();
	}

	/*
	 * US05.01.01
	 */
	
	public void listItemInOneClaimDetailtest() {
		Claim claim = claimList.getPosition(0);
		int amount = claim.getTotalCurrency();
		assertEquals("total currency is right", amount, 65);
		int claimCount = itemList.getCount();
		for (int i = 0; i < claimCount; i++) {

			TextView itemOverAlldes = (TextView) itemList
					.getItemAtPosition(i);

			String viewText = itemOverAlldes.getText().toString();

			Item item = claim.getExpense(i);
			String itemInfo = item.toString();
			String itemText = ((OneClaimActivity) activity).claim
					.toString();
			assertEquals("DisplayError", itemInfo, itemText);
		}
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(),
				itemList);

	}
}
