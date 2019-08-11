package com.test.ahmedorabi.movieapp.view.ui;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.ahmedorabi.movieapp.R;
import com.test.ahmedorabi.movieapp.databinding.FragmentMainBinding;
import com.test.ahmedorabi.movieapp.di.Injectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ahmed Orabi on 11/09/2016.
 */
public class MainActivityFrgment extends Fragment implements Injectable {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        (Objects.requireNonNull(((AppCompatActivity) (Objects.requireNonNull(getActivity()))).getSupportActionBar())).setTitle(R.string.app_title);

        MainFragmentHandlers handlers = new MainFragmentHandlers(getActivity());
        binding.setMainHandlers(handlers);

        setupViewPager();

        binding.tabs.setupWithViewPager(binding.viewpager);


        return binding.getRoot();
    }


    public class MainFragmentHandlers {

        Context context;

        MainFragmentHandlers(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {
            Intent intent = new Intent(getActivity(), FavouriteActivity.class);
            startActivity(intent);
        }
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new MoviesFragment(), "Movies");
        adapter.addFragment(new TVShowFragemnt(), "Tv Shows");
        binding.viewpager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragemntList = new ArrayList<>();
        private final List<String> mFragmnetTitle = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragemntList.get(position);
        }


        @Override
        public int getCount() {
            return mFragemntList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragemntList.add(fragment);
            mFragmnetTitle.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmnetTitle.get(position);
        }

    }


}
