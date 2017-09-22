package juanjo.googlesamples.mytodoapp.addtask;

import juanjo.googlesamples.mytodoapp.base.BasePresenter;
import juanjo.googlesamples.mytodoapp.base.BaseView;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public interface AddTaskEditContract {
    interface View extends BaseView<Presenter>{

        void showEmptyTaskError();

        void showTasksList();

        void setTitle(String title);

        void setDescription(String description);

        boolean isActive();
    }

    interface  Presenter extends BasePresenter{

        void saveTask(String title, String description);

        void populateTask();

        boolean isDataMissing();
    }
}
