package dk.easj.anbo.mvvmrestpostsexample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dk.easj.anbo.mvvmrestpostsexample.databinding.FragmentFirstBinding;
import dk.easj.anbo.mvvmrestpostsexample.model.Post;
import dk.easj.anbo.mvvmrestpostsexample.model.PostsViewModel;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("klimpe", "First onViewCreated");

        PostsViewModel postsViewModel = new ViewModelProvider(getActivity()).get(PostsViewModel.class);
        //postsViewModel.getPostsLiveData().observe(getViewLifecycleOwner(), posts -> Log.d("klimpe", posts.toString()));

        /*binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/

        postsViewModel.getPostsLiveData().observe(getViewLifecycleOwner(), posts ->
        {
            RecyclerView postsRecyclerview = binding.postsRecyclerview;
            postsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            // TODO need adapter class: Should create adapter object only once
            RecyclerViewSimpleAdapter<Post> adapter = new RecyclerViewSimpleAdapter<>(posts);
            postsRecyclerview.setAdapter(adapter);
        });

        postsViewModel.getPosts(); // event handler for button?
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}