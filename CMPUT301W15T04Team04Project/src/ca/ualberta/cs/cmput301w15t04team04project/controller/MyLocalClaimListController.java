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
package ca.ualberta.cs.cmput301w15t04team04project.controller;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import ca.ualberta.cs.cmput301w15t04team04project.EditClaimActivity;
import ca.ualberta.cs.cmput301w15t04team04project.MainActivity;
import ca.ualberta.cs.cmput301w15t04team04project.OneClaimActivity;
import ca.ualberta.cs.cmput301w15t04team04project.SearchActivity;
import ca.ualberta.cs.cmput301w15t04team04project.CLmanager.CLmanager;
import ca.ualberta.cs.cmput301w15t04team04project.CLmanager.MyLocalClaimListManager;
import ca.ualberta.cs.cmput301w15t04team04project.models.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.models.ClaimList;
import ca.ualberta.cs.cmput301w15t04team04project.models.Listener;

/**
 * The MyLocalClaimList Controller is a controller of the MyClaimActivity simply
 * Change the Thing that Activity related
 * 
 * @param claimlist
 *            is the ClaimList get from Manager
 * @author Youdong Ma
 * @version 1.0
 * @since 2015-03-12
 * 
 */
public class MyLocalClaimListController {
	private static ClaimList claimList;

	/**
	 * MyLocalClaimListController is initialed with a claimList
	 * 
	 * @author Youdong Ma
	 * @param claimList
	 * @version 1.1
	 * @since 2015-03-12
	 */
	public ClaimList getClaimList(){
		if (claimList == null) {
			claimList = new ClaimList();
		}
		return claimList;
	}
	
	public void addall(ArrayList<Claim> arrayList){
		getClaimList().getClaimArrayList().addAll(arrayList);
	}
	
	public void clear(){
		getClaimList().getClaimArrayList().clear();
	}

	/**
	 * getClaimsByIndex is get the claims by the given status
	 * 
	 * @param indexList
	 * @author Youdong Ma
	 * @return claims
	 * @version 1.0
	 * @since 2015-03-12
	 */

	@SuppressWarnings("null")
	public ArrayList<Claim> getClaimsByIndex(ArrayList<Integer> indexList) {
		ArrayList<Claim> claims = new ArrayList<Claim>();
		for (int i = 0; i < indexList.size(); i++) {
			claims.add(getClaims().get(indexList.get(i)));
		}
		return claims;
	}

	/**
	 * getClaimList is get the claim list in the Manager
	 * 
	 * @param indexList
	 * @author Youdong Ma
	 * @version 1.0
	 * @since 2015-03-12
	 */
	public ArrayList<Integer> getIndexList(String string) {
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for (int i = 0; i < getClaims().size(); i++) {
			if (getClaims().get(i).getStatus().equals(string)) {
				indexList.add(i);
			}
		}
		return indexList;
	}

	/**
	 * get ArrayList of Claim
	 * 
	 * @return ArrayList of Claim
	 */
	public ArrayList<Claim> getClaims() {

		return getClaimList().getClaimArrayList();
	}

	/**
	 * delete the claim by given index
	 * 
	 * @author Youdong Ma
	 * @param index
	 */
	public void deleteClaim(int index) {
		getClaims().remove(index);
		//getClaimList().notifyListeners();
	}
	
	public void sortClaim(){
		for (int i = 0; i < (getClaims().size() - 1); i++) {
			for (int j = i; j < (getClaimList().size() - 1 - i); j++) {
				if (getClaims().get(j).getStartDate().getTime()  > getClaims().get(j+1).getStartDate().getTime()) {
					Claim bigger = getClaims().get(j);
					getClaims().remove(bigger);
					getClaims().add(bigger);
				}else{
					Claim bigger = getClaims().get(j+1);
					getClaims().remove(bigger);
					getClaims().add(bigger);
				}
				
			}
		}
	}

}