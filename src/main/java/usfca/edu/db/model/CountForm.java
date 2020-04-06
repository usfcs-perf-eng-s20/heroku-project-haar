package usfca.edu.db.model;

public class CountForm {
    public CountForm() {
    }

    public CountForm(long count, boolean success) {
        this.count = count;
        this.success = success;
    }

    private long count;
    private boolean success;

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
