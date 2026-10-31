package ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

public class TaskTableModel extends AbstractTableModel {
    private final String[] columnNames = {"PId", "Name", "Product", "Price", "Status"};
    private List<Task> tasks = new ArrayList<>();
    @Override public int getRowCount() { return tasks.size(); }
    @Override public int getColumnCount() { return columnNames.length; }
    @Override public String getColumnName(int column) { return columnNames[column]; }
    @Override public Object getValueAt(int row, int column) {
        Task task = tasks.get(row);
        return switch (column) {
            case 0 -> task.getPId();
            case 1 -> task.getName();
            case 2 -> task.getProduct();
            case 3 -> task.getPrice();
            case 4 -> task.getStatus();
            default -> null;
        };
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : new ArrayList<>(tasks);
        fireTableDataChanged();
    }
    public String getTaskId(int row) { return tasks.get(row).getPId(); }
}
