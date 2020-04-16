
public class ServerRun {
	public static void main(String[] args){
		ServerStub ss = new ServerStub();
		ss.register(Approximator.class, ServerApproximator.class);
		ss.run();
	}
}
