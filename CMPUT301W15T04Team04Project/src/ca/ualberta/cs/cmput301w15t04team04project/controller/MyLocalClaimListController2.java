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

import java.util.Date;

import ca.ualberta.cs.cmput301w15t04team04project.models.Claim;
import ca.ualberta.cs.cmput301w15t04team04project.models.ClaimList;

/**
 * @author youdong
 * 
 */
public class MyLocalClaimListController2 extends MyLocalClaimListController {

	/**
	 * MyLocalClaimListController2 is initialed with a claimList
	 * @param claimList
	 */
	public MyLocalClaimListController2(ClaimList claimList) {
		super(claimList);
	}

	
	public Claim setClaim(Claim claim, String cName, String cDescription, String cTag, Date sDate, Date eDate ){
		claim.setClaim(cName);
		claim.setDescription(cDescription);
		claim.setTag(cTag);
		claim.setStartDate(sDate);
		claim.setEndDate(eDate);
		return claim;
	}
	
	public void tagSplit(){
		
	}
	
	
	/**
	 * insert an claim
	 * @param claim
	 */
	public void addClaim(Claim claim) {
		getClaims().add(0, claim);
		getClaimList().notifyListeners();
	}

}
