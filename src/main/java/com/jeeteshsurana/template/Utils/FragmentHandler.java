package com.jeeteshsurana.template.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jeeteshsurana.template.R;

public class FragmentHandler {

    /*------------------onlyreplaceFragment-----------------*/
    public static void onlyreplaceFragment(Fragment fragment, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    /*------------------onlyreplaceFragment with id-----------------*/
    public static void onlyreplaceFragment(Fragment fragment, int id, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(id, fragment).commit();
    }


    /*------------------onlyreplaceFragment With bundle-----------------*/
    public static void onlyreplaceFragment(Fragment fragment, FragmentManager fragmentManager, Bundle bundle) {
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    /*------------------onlyreplaceFragment With bundle +id -----------------*/
    public static void onlyreplaceFragment(Fragment fragment, int id, FragmentManager fragmentManager, Bundle bundle) {
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(id, fragment).commit();
    }

    /*------------------FRAGMENT replace-----------------*/
    public static void replaceFragment(Fragment fragment, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    /*------------------FRAGMENT replace with id-----------------*/
    public static void replaceFragment(Fragment fragment, int id, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(id, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    /*------------------FRAGMENT replace With Bundle-----------------*/
    public static void replaceFragment(Fragment fragment, FragmentManager fragmentManager, Bundle bundle) {
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    /*------------------FRAGMENT replace With Bundle +id -----------------*/
    public static void replaceFragment(Fragment fragment, int id, FragmentManager fragmentManager, Bundle bundle) {
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(id, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    /*------------------FRAGMENT ADD-----------------*/
    public static void addFragment(Fragment fragment, Fragment hideFragment, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.getClass().getSimpleName()).hide(hideFragment).commit();
    }
    /*------------------FRAGMENT ADD+id-----------------*/
    public static void addFragment(Fragment fragment, int id, Fragment hideFragment, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(id, fragment, fragment.getClass().getSimpleName()).hide(hideFragment).commit();
    }

    /*------------------FRAGMENT ADD With Bundle-----------------*/
    public static void addFragment(Fragment fragment, Fragment hideFragment, FragmentManager fragmentManager, Bundle bundle) {
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.getClass().getSimpleName()).hide(hideFragment).commit();
    }

    /*------------------FRAGMENT ADD With Bundle+id-----------------*/
    public static void addFragment(Fragment fragment, int id, Fragment hideFragment, FragmentManager fragmentManager, Bundle bundle) {
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(id, fragment, fragment.getClass().getSimpleName()).hide(hideFragment).commit();
    }
}
