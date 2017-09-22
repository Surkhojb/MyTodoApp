package juanjo.googlesamples.mytodoapp.tasks.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import juanjo.googlesamples.mytodoapp.R;
import juanjo.googlesamples.mytodoapp.data.Task;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private  List<Task> tasks = new ArrayList<>();
    private TaskItemListener listener;

    public TasksAdapter() {}

    public void setTasks(List<Task> newListOfTasks){
        tasks = newListOfTasks;
        notifyDataSetChanged();
    }
    public void setItemListener(TaskItemListener itemListener){
        this.listener = itemListener;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent,false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, final int position) {
        holder.textTitle.setText(tasks.get(position).getTitle());
        holder.completeCheckBox.setChecked(tasks.get(position).isCompleted());
        holder.completeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tasks.get(position).isCompleted()) {
                    listener.onCompleteTaskClick(tasks.get(position));
                }
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTaskClick(tasks.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView textTitle;
        public CheckBox completeCheckBox;

        public TasksViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_item);
            textTitle = (TextView) itemView.findViewById(R.id.tv_title);
            completeCheckBox = (CheckBox) itemView.findViewById(R.id.cb_complete);
        }
    }
}
