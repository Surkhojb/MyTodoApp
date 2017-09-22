package juanjo.googlesamples.mytodoapp.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import juanjo.googlesamples.mytodoapp.base.BasePresenter;
import juanjo.googlesamples.mytodoapp.base.BaseView;
import juanjo.googlesamples.mytodoapp.data.Task;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public interface TaskContract {
    //View
    interface View extends BaseView<Presenter>{
        void showTasks(List<Task> tasks);

        void showLoading(boolean state);

        void showAddTask();

        void showEmptyTasks();

        boolean isActive();

    }
    //Presenter
    interface Presenter extends BasePresenter{
        void onResult(int requestCode,int resultCode);

        void loadTasks(boolean forceUpdate);

        void addNewTask();

        void openTaskDetails(@NonNull Task requestedTask);

        void completeTask(@NonNull Task completedTask);

        void activateTask(@NonNull Task activeTask);

        void clearCompletedTasks();

        void setFiltering(TaskFilterType type);

        TaskFilterType getFiltering();

    }
}
