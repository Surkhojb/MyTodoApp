package juanjo.googlesamples.mytodoapp.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import juanjo.googlesamples.mytodoapp.data.Task;
import juanjo.googlesamples.mytodoapp.data.source.TasksDataSource;
import juanjo.googlesamples.mytodoapp.data.source.TasksRepository;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

class TasksPresenter implements TaskContract.Presenter {

    TaskContract.View view;
    TasksRepository repository;

    public TasksPresenter(TasksRepository repo, TaskContract.View view) {
        this.view = view;
        this.repository = repo;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    //TODO Handle onResult from EditActivity
    @Override
    public void onResult(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        view.showLoading(true);
        repository.getTasks(new TasksDataSource.LoadTasksCallBack() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                view.showTasks(tasks);
                view.showLoading(false);
            }

            @Override
            public void onDataNoAvaliable() {
                view.showMessage("Error loading");
                view.showLoading(false);
            }
        });
    }

    @Override
    public void addNewTask() {
        view.showAddTask();
    }

    //TODO Create and open detail task view
    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {

    }
    //TODO Update task as completed
    @Override
    public void completeTask(@NonNull Task completedTask) {

    }
    //TODO Create drawable to change background color for active and no active tasks
    @Override
    public void activateTask(@NonNull Task activeTask) {

    }
    //TODO Clear completed tasks on database
    @Override
    public void clearCompletedTasks() {

    }
    //TODO Apply filter
    @Override
    public void setFiltering(TaskFilterType type) {

    }
    //TODO Get filters
    @Override
    public TaskFilterType getFiltering() {
        return null;
    }

}
