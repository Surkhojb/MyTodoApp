package juanjo.googlesamples.mytodoapp.addtask;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import juanjo.googlesamples.mytodoapp.R;

public class AddEditTaskFragment extends Fragment implements AddTaskEditContract.View{

    private AddTaskEditContract.Presenter presenter;


    @BindView(R.id.add_task_title)
    EditText mTitle;
    @BindView(R.id.add_task_description)
    EditText mDescription;
    FloatingActionButton fabEdit;

    public AddEditTaskFragment() {
        // Required empty public constructor
    }
    public static AddEditTaskFragment newInstance() {
        AddEditTaskFragment fragment = new AddEditTaskFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_edit_task, container, false);
        ButterKnife.bind(this,v);
        initializeFabEdit();
        return v;
    }

    private void initializeFabEdit() {
        fabEdit = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveTask(mTitle.getText().toString(),mDescription.getText().toString());
            }
        });
    }

    @Override
    public void setPresenter(AddTaskEditContract.Presenter presenter) {
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
    //TODO: Show empty task
    @Override
    public void showEmptyTaskError() {
    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
