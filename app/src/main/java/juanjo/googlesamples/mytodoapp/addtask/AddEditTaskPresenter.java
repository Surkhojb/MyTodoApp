package juanjo.googlesamples.mytodoapp.addtask;

import android.support.annotation.Nullable;

import juanjo.googlesamples.mytodoapp.data.Task;
import juanjo.googlesamples.mytodoapp.data.source.TasksDataSource;
import juanjo.googlesamples.mytodoapp.data.source.TasksRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public class AddEditTaskPresenter implements AddTaskEditContract.Presenter,TasksDataSource.GetTaskCallback {
    AddTaskEditContract.View mAddTaskView;
    TasksRepository mTasksRepository;

    @Nullable
    String mTaskId;

    boolean mIsDataMissing = false;

    public AddEditTaskPresenter(TasksRepository repo, AddTaskEditContract.View v) {

        this.mTasksRepository = checkNotNull(repo);
        this.mAddTaskView = checkNotNull(v);
        this.mAddTaskView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!isNewTask() && mIsDataMissing) {
            populateTask();
        }
    }

    @Override
    public void saveTask(String title, String description) {
        if (isNewTask()) {
            createTask(title, description);
        } else {
            updateTask(title, description);
        }
    }

    @Override
    public void populateTask() {
        if (isNewTask()) {
            throw new RuntimeException("populateTask() was called but task is new.");
        }
        mTasksRepository.getTask(mTaskId, this);
    }

    @Override
    public void onTaskLoaded(Task task) {
        // The view may not be able to handle UI updates anymore
        if (mAddTaskView.isActive()) {
            mAddTaskView.setTitle(task.getTitle());
            mAddTaskView.setDescription(task.getDescription());
        }
        mIsDataMissing = false;
    }

    @Override
    public void onDataNoAvaliable() {
        if (mAddTaskView.isActive()) {
            mAddTaskView.showEmptyTaskError();
        }
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    private boolean isNewTask() {
        return mTaskId == null;
    }

    private void createTask(String title, String description) {
        Task newTask = new Task(title, description);
        if (newTask.isEmpty()) {
            mAddTaskView.showEmptyTaskError();
        } else {
            mTasksRepository.saveTask(newTask);
            mAddTaskView.showTasksList();
        }
    }

    private void updateTask(String title, String description) {
        if (isNewTask()) {
            throw new RuntimeException("updateTask() was called but task is new.");
        }
        mTasksRepository.saveTask(new Task(title, description, mTaskId));
        mAddTaskView.showTasksList(); // After an edit, go back to the list.
    }
}
