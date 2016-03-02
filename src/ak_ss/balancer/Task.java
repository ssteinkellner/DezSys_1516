package ak_ss.balancer;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Task {
	public static final int WAITING = 0, RUNNING = 1, FINISHED = 2;
	private static final String[] statusnamen = {"WAITING","RUNNING","FINISHED"};
	private static int lastSessId = 0;
	
	private int sessId, state;
	private String cmd, result;
	private long startTime, endTime;
	
	public Task(String command){
		cmd = command;
		sessId = (lastSessId ++);
		startTime = System.nanoTime();
	}
	
	public int getSessId() {
		return sessId;
	}

	public int getState() {
		return state;
	}

	public String getCmd() {
		return cmd;
	}

	public long getRunTime() {
		return endTime - startTime;
	}
	
	@Override
	public String toString() {
		return "Task[sessId=" + sessId + ",state=" + statusnamen[state] + ",cmd=" + cmd + ",result=" + result + ",startTime=" + startTime + ",endTime=" + endTime + "]";
	}
}
