package com.group_15.bta.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.group_15.bta.R;
import com.group_15.bta.objects.SectionListAdapter;
import com.group_15.bta.objects.Courses;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseCodeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView sectionsList;
    private SectionListAdapter sectionsAdapted;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourseCodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_a_course_with_code.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseCodeFragment newInstance(String param1, String param2) {
        AddCourseCodeFragment fragment = new AddCourseCodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_a_course_with_code, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Courses selectedCourse = AddCourseCodeFragmentArgs.fromBundle(requireArguments()).getCourse();
        sectionsList = view.findViewById(R.id.sections_list_fragment);
        sectionsAdapted = new SectionListAdapter(getContext(), R.layout.section_list_item, selectedCourse.getSections());
        sectionsList.setAdapter(sectionsAdapted);

        NavController navController = NavHostFragment.findNavController(this);
        sectionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                navController.navigate(AddCourseCodeFragmentDirections.actionAddACourseWithCodeToAddACourseWithSectionConfirmation(selectedCourse.getSections().get(i)));
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.courses_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.categories_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Sections");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sectionsAdapted.getFilter().filter(newText);
                return false;
            }
        });
    }
}