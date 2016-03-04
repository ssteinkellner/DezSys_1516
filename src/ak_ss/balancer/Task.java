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
	private Callback finish;
	
	public Task(String command){
		System.out.println("[DEBUG][Task] " + command);
		cmd = command;
		sessId = (lastSessId ++);
		startTime = System.nanoTime();
	}
	
	public void onFinish(Callback callback){
		finish = callback;
	}

	public int getSessId() {
		return sessId;
	}
	
	public void setState(int state) {
		if(state < 0 || state > 2) return;
		
		this.state = state;
		
		if(state == FINISHED){
			endTime = System.nanoTime();
			System.out.println("[DEBUG][Task] Runtime: " + getRunTime());
			if(finish != null) finish.call();
		}
	}

	public int getState() {
		return state;
	}

	public String getCmd() {
		return cmd;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getRunTime() {
		return endTime - startTime;
	}
	
	@Override
	public String toString() {
		return "Task[sessId=" + sessId + ",state=" + statusnamen[state] + ",cmd=" + cmd + ",result=" + result + ",startTime=" + startTime + ",endTime=" + endTime + "]";
	}
}
