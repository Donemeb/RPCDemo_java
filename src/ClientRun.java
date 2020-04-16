
public class ClientRun {
	public static void main(String[] args){
		ClientStub cs = new ClientStub();
		ClientApproximator ca = new ClientApproximator(cs);
		ca.PiApproximator();
	}
}
