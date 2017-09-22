package juanjo.googlesamples.mytodoapp.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import juanjo.googlesamples.mytodoapp.data.Task;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public interface TasksDataSource {
    interface LoadTasksCallBack{

        void onTasksLoaded(List<Task> tasks);

        void onDataNoAvaliable();
    }

    interface GetTaskCallback{

        void onTaskLoaded(Task task);

        void onDataNoAvaliable();

    }

    void getTasks(@NonNull LoadTasksCallBack callback);

    void getTask(@NonNull String taskId, GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void completeTask(@NonNull Task task);

    void completeTask(@NonNull String taskId);

    void activateTask(@NonNull Task task);

    void activateTask(@NonNull String taskId);

    void clearCompletedTasks();

    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(@NonNull String taskId);

}
