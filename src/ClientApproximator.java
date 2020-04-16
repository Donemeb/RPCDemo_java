
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientApproximator implements Approximator{
	private ClientStub cs;
	public ClientApproximator(ClientStub cs) {
		this.cs = cs;
	}
	public void PiApproximator() {
		int num = 1000;
		List<List<Double>> lds = new ArrayList();
		for(int i=0;i<num;i++) {
			Double i1 = Math.random();
			Double i2 = Math.random();
			lds.add(Arrays.asList(i1,i2));
		}
		System.out.println("Client send:"+lds.getClass());
		List<Integer> result = PiVerify(lds);
		System.out.println("Client: receive "+result.getClass());
		int sum = 0;
		for(int i:result) {
			sum = sum + i;
		}
		Double pi_a = sum * 4.0 / num;
		System.out.println("Pi Approximate: "+pi_a);
	}
	
	@Override
	public List<Integer> PiVerify(List<List<Double>> lds) {
		//ServerApproximator server_class = cs.ClientProxy(Approximator.class);
		Approximator server_class = cs.getRemoteProxyObj(Approximator.class,new InetSocketAddress("localhost", 23333));
		List<Integer> result = server_class.PiVerify(lds);
		return result;
	}
}
