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

import java.util.Date;

import android.widget.ImageView;

/**
 * The Item model is just a rough Item's information simply store set and get
 * all of the Item
 * 
 * @param itemName
 *            is the name of the item
 * @author Weijie Sun
 * @author Chenrui Lei
 * @version 1.0
 * @since 2015-03-08
 * 
 */

public class Item {
	protected String itemName;
	protected String itemCategory;
	protected String itemDescription;
	protected Currency itemCurrency;
	protected Date itemDate;
	// private ImageView receipt;
	protected boolean isComplete = false;
	protected String receipt;

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	/**
	 * The Item model is just a rough Item's information simply store set and
	 * get all of the Item
	 * 
	 * @param itemName
	 *            is the name of the item
	 * @author Weijie Sun
	 * @version 1.0
	 * @since 2015-03-08
	 */
	public Item(String itemName) {
		this.setItemName(itemName);

	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Currency getItemCurrency() {
		return itemCurrency;
	}

	public void setItemCurrency(Currency itemCurrency) {
		this.itemCurrency = itemCurrency;
	}

	public Date getItemDate() {
		return itemDate;
	}

	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

	public void takeAPhoto() {
		// TODO Auto-generated method stub

	}

	public void deleteAPhoto() {
		// TODO Auto-generated method stub

	}

	public Object getPhoto() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * public ImageView getReceipt() {
	 * 
	 * return receipt; }
	 * 
	 * public void setReceipt(ImageView receipt) {
	 * 
	 * this.receipt = receipt; }
	 */
	public boolean isComplete() {

		return isComplete;
	}

	public void changeIsComplete() {
		if (this.isComplete == false) {
			this.isComplete = true;
		} else {
			this.isComplete = false;
		}
	}

}
