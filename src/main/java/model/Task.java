package model;

import java.util.List;

public class Task {

    private String description;
    private int voteCount;
    private List<String> votees;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public List<String> getVotees() {
        return votees;
    }

    public void setVotees(List<String> votees) {
        this.votees = votees;
    }
}
