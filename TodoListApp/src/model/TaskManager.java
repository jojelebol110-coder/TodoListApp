package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.IdGenerator;

/** Stores records and persists them in taskslist.ser. */
public class TaskManager {
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    private static final String FILE_PATH = "taskslist.ser";
    private HashMap<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        String id;
        do { id = IdGenerator.generateId(java.time.Year.now().getValue()); }
        while (tasks.containsKey(id));
        task.setPId(id);
        tasks.put(id, task);
    }
    public Task deleteTask(String id) { return tasks.remove(id); }
    public Task findTask(String id) { return tasks.get(id); }
    public HashMap<String, Task> findAll() { return tasks; }
    public void setTask(Task task) {
        if (task != null && task.getPId() != null) tasks.put(task.getPId(), task);
    }
    @SuppressWarnings("unchecked")
    public void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.isFile()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object data = in.readObject();
            if (data instanceof Map<?, ?>) {
                tasks = new HashMap<>();
                ((Map<?, ?>) data).forEach((key, value) -> {
                    if (key instanceof String && value instanceof Task)
                        tasks.put((String) key, (Task) value);
                });
            }
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.WARNING, "Could not load saved records. Starting with an empty list.", ex);
            tasks = new HashMap<>();
        }
    }
    public int saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(tasks);
            return 0;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Could not save records.", ex);
            return 1;
        }
    }
}
