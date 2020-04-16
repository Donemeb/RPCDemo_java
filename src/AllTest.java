
import java.io.IOException;

public class AllTest {
	public static void main(String[] args){
	    new Thread(new Runnable() {
	    	public void run() {
	    		ServerStub ss = new ServerStub();
	    		ss.register(Approximator.class, ServerApproximator.class);
	    		ss.run();
	    	}
	    }).start();
	    

	    new Thread(new Runnable() {
	    	public void run() {
	    		ClientStub cs = new ClientStub();
	    		ClientApproximator ca = new ClientApproximator(cs);
	    		ca.PiApproximator();
	    	}
	    }).start();
	}
}
