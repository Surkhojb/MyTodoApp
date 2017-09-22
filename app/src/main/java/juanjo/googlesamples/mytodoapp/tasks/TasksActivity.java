package juanjo.googlesamples.mytodoapp.tasks;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import butterknife.BindView;
import butterknife.ButterKnife;
import juanjo.googlesamples.mytodoapp.R;
import juanjo.googlesamples.mytodoapp.data.source.TasksRepository;
import juanjo.googlesamples.mytodoapp.data.source.local.TasksLocalDataSource;
import juanjo.googlesamples.mytodoapp.data.source.remote.TasksRemoteDataSource;
import juanjo.googlesamples.mytodoapp.util.ActivityUtil;

public class TasksActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    TasksPresenter tasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ButterKnife.bind(this);

        initializeViewComponents();
        addFragmentToActivity();

    }

    private void initializeViewComponents() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(mNavigationView);
    }

    private void addFragmentToActivity() {
        TasksFragment tasksFragment = (TasksFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if(tasksFragment == null){
            tasksFragment = tasksFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),tasksFragment
                    ,R.id.contentFrame);
        }

        attachViewToPresenter(tasksFragment);
    }

    void attachViewToPresenter(TaskContract.View view){
        tasksPresenter = new TasksPresenter(TasksRepository.getInstance(
                TasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(this)
        ), view);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO Handle item clicks
    private void setUpDrawerContent(NavigationView mNavigationView) {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.list_navigation_menu:
                        break;
                    case R.id.statistics_navigation_menu:
                        break;
                    default:
                        break;
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

}
