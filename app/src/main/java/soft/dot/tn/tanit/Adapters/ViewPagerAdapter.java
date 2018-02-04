package soft.dot.tn.tanit.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import soft.dot.tn.tanit.Fragments.IntroFragment;
import soft.dot.tn.tanit.Fragments.LoginFragment;
import soft.dot.tn.tanit.Fragments.ResetPasswordFragment;
import soft.dot.tn.tanit.Fragments.SignUpFragment;

/**
 * Created by Wafee on 04/02/2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                IntroFragment tab1 = new IntroFragment();
                return tab1;

            case 1:
                LoginFragment tab2 = new LoginFragment();
                return tab2;
            case 2:
                SignUpFragment tab3 = new SignUpFragment();
                return tab3;

            case 3:
                ResetPasswordFragment tab4 = new ResetPasswordFragment();
                return tab4;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}