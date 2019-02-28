package edu.miracosta.cs134.Model;

import java.util.Objects;

public class Task {

    private String mDescription;
    private long mId;
    private boolean mIsDone;

    public Task(long id, String description, boolean isDone) {
        mDescription = description;
        mId = id;
        mIsDone = isDone;
    }

    public Task(String description) {
        this (-1, description, false);
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getId() {
        return mId;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }

    public void setId(long id) {
        mId = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + mId +
                ", Description='" + mDescription +
                ", IsDone=" + mIsDone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return mId == task.mId &&
                mIsDone == task.mIsDone &&
                Objects.equals(mDescription, task.mDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDescription, mId, mIsDone);
    }
}
