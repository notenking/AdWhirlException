package me.test.admob;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;

public class AdWhirlTestActivity extends FragmentActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ViewPager viewPager =(ViewPager)this.findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		
        LinearLayout adView = (LinearLayout)this.findViewById(R.id.adView);
        AdWhirlLayout  adWhirlLayout = new AdWhirlLayout(this, "Your adwhirl ID");
        AdWhirlManager.setConfigExpireTimeout(45*1000);
        AdWhirlTargeting.setTestMode(false);
        adView.addView(adWhirlLayout);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    
    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }
    }

    public static class ArrayListFragment extends ListFragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }
        
        public void onActivityCreated(Bundle savedInstanceState) {
    		// TODO Auto-generated method stub
    		super.onActivityCreated(savedInstanceState);
    		setListAdapter(new ArrayAdapter<String>(getActivity(),
                     R.layout.simple_list_item_checkable_1,
                     android.R.id.text1, Shakespeare.TITLES));

    	}
    }
}