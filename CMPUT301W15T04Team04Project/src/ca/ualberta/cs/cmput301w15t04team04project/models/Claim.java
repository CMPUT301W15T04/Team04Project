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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * The Claim model is just a rough Claim's information simply store set and get
 * all of the Claim
 * 
 * @param String
 *            claimName is to store the name of the claim
 * @author Ji Yang
 * @author Yang Zhang
 * @version 1.1
 * @since 2015-03-09
 */
public class Claim {

	protected String claimName;
	protected Date startDate;
	protected Date endDate;
	protected String status;
	protected String description;
	protected Destination destination;
	protected ArrayList<Item> items;
	protected ArrayList<String> amount;
	protected transient ArrayList<Listener> itemListener;

	/**
	 * The constructor of the class
	 * 
	 * @param claimName
	 *            The name of the claim.
	 */
	public Claim(String claimName) {

		this.claimName = claimName;
		items = new ArrayList<Item>();
		itemListener = new ArrayList<Listener>();
	}

	/**
	 * get an arraylist of listeners to do the notification things
	 * 
	 * @return itemListener
	 */
	private ArrayList<Listener> getListeners() {
		if (itemListener == null) {
			itemListener = new ArrayList<Listener>();
		}
		return itemListener;
	}

	/**
	 * get a collection of items which are contained in this claim by convert
	 * the arraylist of items to a collection.
	 * 
	 * @return items
	 */
	public Collection<Item> getItem() {
		return items;
	}

	/**
	 * add an new item to this claim and notify the listener in order to update
	 * the view
	 * 
	 * @param aItem
	 *            the added item .
	 */
	public void addItem(Item aItem) {
		items.add(aItem);
		notifyListener();
	}

	// http://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fswt%2Fwidgets%2FWidget.html
	/**
	 * Notifies all of the receiver's listeners for events.
	 */
	private void notifyListener() {
		for (Listener listener : itemListener) {
			listener.update();
		}
	}

	/**
	 * remove an item in this claim and notify the listener in order to update
	 * the view
	 * 
	 * @param oldItem
	 *            the removed item .
	 */
	public void removeItem(Item oldItem) {
		items.remove(oldItem);
		notifyListener();
	}

	// http://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fswt%2Fwidgets%2FWidget.html
	/**
	 * 
	 * Adds the listener to the collection of listeners who will be notified
	 * when an event of the given type occurs.
	 * 
	 * @param l
	 *            the new listener
	 */

	public void addListener(Listener l) {
		getListeners().add(l);
	}

	/**
	 * get the position of the selected item in the item list of this claim.
	 * 
	 * @return items.get(position)
	 */
	public Item getPosition(int position) {
		return items.get(position);
	}

	// http://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fswt%2Fwidgets%2FWidget.html
	/**
	 * Removes the listener from the collection of listeners who will be
	 * notified when an event of the given type occurs.
	 * 
	 * @param l
	 *            the removed listener .
	 */
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}

	/**
	 * get the claim name of this claim
	 * 
	 * @return claimName
	 */
	public String getClaim() {
		return claimName;
	}

	/**
	 * set the claim name of this claim
	 * 
	 * @param claimName
	 *            the claim name of this claim
	 */
	public void setClaim(String claimName) {
		this.claimName = claimName;
	}

	public String toString() {
		return getClaim();
	}

	/**
	 * get the StartDate of this claim
	 * 
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * set the StartDate of this claim
	 * 
	 * @param startDate
	 *            the startDate of this claim
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * get the EndDate of this claim
	 * 
	 * @return EndDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * set the EndDate of this claim
	 * 
	 * @param EndDate
	 *            the EndDate of this claim
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * get the status of this claim
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * set the status of this claim
	 * 
	 * @param status
	 *            the status of this claim
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * get the description of this claim
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set the description of this claim
	 * 
	 * @param description
	 *            the description of this claim
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get the item list of this claim
	 * 
	 * @return items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * set the item list of this claim
	 * 
	 * @param items
	 *            the item list of this claim
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * get the amount of this claim
	 * 
	 * @return amount
	 */
	public ArrayList<String> getAmount() {
		return amount;
	}

	/**
	 * set the amount of this claim
	 * 
	 * @param amount
	 *            the amount of this claim
	 */
	public void setAmount(ArrayList<String> amount) {
		this.amount = amount;
	}

	/**
	 * get the destination of this claim
	 * 
	 * @return destination
	 */
	public Destination getDestination() {
		return destination;
	}

	/**
	 * set the destination of this claim
	 * 
	 * @param destination
	 *            the destination of this claim
	 */
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}