import java.io.*;
public class Tester

{
	Boolean test_case_0()
	{
		System.out.println("\n*******************Test Case 0*****************");
		JobGraph jg = new JobGraph();
		
		jg.print_graph();

		String seqString = jg.get_job_sequence();
		// System.out.println(seqString);
		if(seqString.length()==0)
			return true;
		return false;
			
		
	}

	static Boolean test_case_1()
	{

		System.out.println("\n*******************Test Case 1*****************");

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
		System.out.println("Job Sequence: " + seqString);
		return jg.is_sequence_valid(seqString);
		
	}

	static Boolean test_case_2()
	{

		System.out.println("\n*******************Test Case 2*****************");

		JobGraph jg = new JobGraph();
		jg.add_job('a');
		jg.add_job('b');
		jg.add_job('c');
		jg.add_job('d');
		

		jg.add_dependency('a','c');
		jg.add_dependency('b','c');
		jg.add_dependency('c','d');
		

		jg.print_graph();

		
		String seqString = jg.get_job_sequence();
		System.out.println("Job Sequence: " + seqString + "\n");
		return jg.is_sequence_valid(seqString);
		
	}
}