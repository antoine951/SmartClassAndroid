package be.henallux.smartclass.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Task;

public class TaskAdapter  extends  RecyclerView.Adapter<TaskAdapter.ViewHolderTask> {

    private ArrayList<Task> tasks;
    private Context context;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolderTask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        return new TaskAdapter.ViewHolderTask(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolderTask holder, int position) {
        Task task = tasks.get(position);
        holder.textViewTitle.setText(task.getTitle());
        if(task.getType().equals("devoir")){
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_task_homework));
        }else if(task.getType().equals("annonce")){
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_task_announcement));
        }else{
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_task_lesson));
        }
    }

    @Override
    public int getItemCount() {
        return (tasks==null)? 0:tasks.size();
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks=tasks;
        notifyDataSetChanged();
    }

    public class ViewHolderTask extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        ImageView imageView;

        public ViewHolderTask (@NonNull View itemView){
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.type);

        }
    }
}
