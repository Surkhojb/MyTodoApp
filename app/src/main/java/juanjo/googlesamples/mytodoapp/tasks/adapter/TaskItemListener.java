package juanjo.googlesamples.mytodoapp.tasks.adapter;

import juanjo.googlesamples.mytodoapp.data.Task;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public interface TaskItemListener {

        void onTaskClick(Task clickedTask);

        void onCompleteTaskClick(Task completedTask);
}
