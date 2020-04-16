
import java.util.ArrayList;
import java.util.List;

public class ServerApproximator implements Approximator{

	@Override
	public List<Integer> PiVerify(List<List<Double>> lds) {
		System.out.println("ServerApproximator: recieve "+lds.getClass());
		List<Integer> li = new ArrayList();
		for(List<Double> ld: lds) {
			if(ld.get(0)*ld.get(0)+ld.get(1)*ld.get(1)>1) {
				li.add(0);
			}else {
				li.add(1);
			}
		}
		return li;
	}

}
