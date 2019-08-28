class MultipleChecksException extends Exception {
    private int taskNum;

    public MultipleChecksException(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public String getMessage() {
        return String.format("You have completed task %d earlier!", taskNum);
    }
}