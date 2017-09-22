package juanjo.googlesamples.mytodoapp.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import juanjo.googlesamples.mytodoapp.R;
import juanjo.googlesamples.mytodoapp.addtask.AddEditTaskActivity;
import juanjo.googlesamples.mytodoapp.data.Task;
import juanjo.googlesamples.mytodoapp.tasks.adapter.TaskItemListener;
import juanjo.googlesamples.mytodoapp.tasks.adapter.TasksAdapter;
import juanjo.googlesamples.mytodoapp.util.ScrollAtTopSwipeRefreshLayout;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public class TasksFragment extends Fragment implements TaskContract.View{

    @BindView(R.id.tasks_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.noTasks)
    View noTasksView;
    @BindView(R.id.refresh_layout)
    ScrollAtTopSwipeRefreshLayout swipeRefreshLayout;

    FloatingActionButton fabAdd;

    TasksAdapter mAdapter;

    TaskContract.Presenter presenter;

    public TasksFragment() {
        // Requires empty public constructor
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new TasksAdapter();
        mAdapter.setItemListener(taskItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_task,container,false);
        ButterKnife.bind(this,rootView);
        setUpSwipeRefresh();
        setUpFabAdd();
        presenter.loadTasks(true);
        return rootView;
    }

    private void setUpSwipeRefresh() {
        // Set up progress indicator
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(mRecyclerView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadTasks(false);
            }
        });
    }

    private void setUpFabAdd() {
        fabAdd = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addNewTask();
            }
        });
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTasks(List<Task> tasks) {
        if(tasks.isEmpty())
            showEmptyTasks();

        mAdapter.setTasks(tasks);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showLoading(final boolean state) {
        if (getView() == null) {
            return;
        }

        // Make sure setRefreshing() is called after the layout is done with everything else.
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(state);
            }
        });
    }

    @Override
    public void showAddTask() {
        startActivityForResult(new Intent(getContext(), AddEditTaskActivity.class)
                , AddEditTaskActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void showEmptyTasks() {
        noTasksView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    //TODO Use strings from R.string.*
    TaskItemListener taskItemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            //TODO Open detail task
            showMessage("Task Clicked!");
        }

        @Override
        public void onCompleteTaskClick(Task completedTask) {
            presenter.completeTask(completedTask);
            showMessage("Completed clicker");
        }
    };
}
