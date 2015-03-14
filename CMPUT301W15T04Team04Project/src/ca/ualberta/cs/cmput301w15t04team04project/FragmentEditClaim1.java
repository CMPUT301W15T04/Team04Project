package ca.ualberta.cs.cmput301w15t04team04project;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This is the fragment part 1 for adding/editing a claim.
 * 
 * @author Ji Yang
 * @author Yang Zhang
 * @version 1.0
 * @since 2015-03-10
 */
public class FragmentEditClaim1 extends Fragment {
	private TextView claimname;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		
		return inflater.inflate(R.layout.fragment_edit_claim_1, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		claimname = (TextView) getView().findViewById(R.id.claimNameEditText);
		claimname.setText("A");
	}

}
