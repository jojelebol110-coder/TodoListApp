package controller;

import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.TaskManager;
import ui.MainForm;

/** Connects the MainForm with the data model. */
public class TaskController {
    private final TaskManager model;
    private MainForm view;

    public TaskController(TaskManager model) { this.model = model; }
    public void setView(MainForm view) { this.view = view; }

    public boolean handleAddTask(String name, String product, String price, String status) {
        if (blank(name) || blank(product) || blank(price) || blank(status)) return false;
        Task task = new Task(name.trim(), product.trim(), price.trim(), status.trim());
        model.addTask(task);
        model.saveTasks();
        refresh();
        return true;
    }
    public boolean handleUpdateTask(String id, String name, String product, String price, String status) {
        if (blank(id) || blank(name) || blank(product) || blank(price) || blank(status)) return false;
        Task task = model.findTask(id);
        if (task == null) return false;
        task.setName(name.trim()); task.setProduct(product.trim());
        task.setPrice(price.trim()); task.setStatus(status.trim());
        model.setTask(task); model.saveTasks(); refresh();
        return true;
    }
    public boolean handleDeleteTask(String id) {
        if (model.deleteTask(id) == null) return false;
        model.saveTasks(); refresh(); return true;
    }
    public List<Task> getAllTask() { return new ArrayList<>(model.findAll().values()); }
    public Task getTaskDetails(String id) { return model.findTask(id); }
    private boolean blank(String value) { return value == null || value.trim().isEmpty(); }
    private void refresh() { if (view != null) view.refreshTaskList(); }
}
