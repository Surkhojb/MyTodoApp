package juanjo.googlesamples.mytodoapp.base;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showMessage(String message);
    void showError(String message);
}
