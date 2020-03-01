package model;

import utility.BotConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskHandler {

    public static TaskHandler instance;

    private ArrayList<Task> taskList;

    private TaskHandler() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static TaskHandler getInstance() {
        if (instance == null) {
            instance = new TaskHandler();
        }
        return instance;
    }

    public String addTask(String task) {

        String result;

        if (!checkIfTaskExists(task)) {
            Task newTask = new Task();
            newTask.setDescription(task);
            newTask.setVoteCount(0);
            newTask.setVotees(new ArrayList<>());
            instance.getTaskList().add(newTask);
            result = taskListPrinter();

        } else {
            result = BotConstants.errorAddTask;
        }

        return result;

    }

    public String addVote(String user, String option) {

        String result;
        if (!checkIfVoteExists(user)) {

            for (Task t : instance.getTaskList()) {
                if (t.getDescription().equalsIgnoreCase(option)) {

                    t.setVoteCount(t.getVoteCount() + 1);
                    t.getVotees().add(user);
                }
            }

            result = taskListPrinter();
        } else {
            result = BotConstants.errorAddVote;
        }

        return result;

    }

    public String removeVote(String user) {
        for (Task t : instance.getTaskList()) {
            if (t.getVotees().contains(user)) {

                t.setVoteCount(t.getVoteCount() - 1);
                t.getVotees().remove(user);
            }
        }

        return taskListPrinter();
    }

    private boolean checkIfVoteExists(String user) {
        return instance.getTaskList().stream().anyMatch(t -> t.getVotees().contains(user));
    }

    private boolean checkIfTaskExists(String taskDescription) {
        return instance.getTaskList().stream().anyMatch(t -> t.getDescription().equalsIgnoreCase(taskDescription));
    }

    private String taskListPrinter() {
        StringBuilder taskList = new StringBuilder(BotConstants.pollHeader);
        for (int i = 0; i < instance.getTaskList().size(); i++) {
            String task = "\n[" + (i + 1) + "] " + instance.getTaskList().get(i).getDescription();
            String votees = String.join(",", instance.getTaskList().get(i).getVotees());
            taskList.append(task).append(" : ").append(votees);
        }
        return taskList.toString();
    }

    public String clearTaskHandler() {
        instance = null;
        return BotConstants.pollHeader;
    }

}
