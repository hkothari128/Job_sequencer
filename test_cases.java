import java.lang.reflect.Method;
import ./.*;

public class test_cases
{
	public static void main(String[] args) {
		JobGraph jg = new JobGraph();
		jg.add_job('a');
		jg.add_job('b');
		jg.add_job('c');
		jg.add_job('d');
		jg.add_job('e');
		jg.add_job('f');

		jg.add_dependency('d','a');
		jg.add_dependency('d','b');
		jg.add_dependency('b','c');
		jg.add_dependency('c','f');
		
		jg.print_graph();

		String seqString = jg.get_job_sequence();
		System.out.println(seqString);
		System.out.println(jg.is_sequence_valid("e,d,c,b,f,a"));
	}
}