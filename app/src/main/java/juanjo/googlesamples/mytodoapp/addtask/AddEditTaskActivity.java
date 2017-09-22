package juanjo.googlesamples.mytodoapp.addtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import juanjo.googlesamples.mytodoapp.R;
import juanjo.googlesamples.mytodoapp.data.source.TasksRepository;
import juanjo.googlesamples.mytodoapp.data.source.local.TasksLocalDataSource;
import juanjo.googlesamples.mytodoapp.data.source.remote.TasksRemoteDataSource;
import juanjo.googlesamples.mytodoapp.util.ActivityUtil;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 1;

    private AddEditTaskPresenter editTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        addFragmentToActivity();
    }

    private void addFragmentToActivity() {
        AddEditTaskFragment editTaskFragment = (AddEditTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if(editTaskFragment == null){
            editTaskFragment = editTaskFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),editTaskFragment
                    ,R.id.contentFrame);
            attachViewToPresenter(editTaskFragment);
        }

    }

    void attachViewToPresenter(AddTaskEditContract.View view){
        editTaskPresenter = new AddEditTaskPresenter(TasksRepository.getInstance(
                TasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(this)), view);
    }

}
