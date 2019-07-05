import java.io.*;
public class Tester

{
	int num_testcases = 3;
	int get_num_testcases(){ return num_testcases;}

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

	Boolean test_case_1()
	{

		System.out.println("\n*******************Test Case 1*****************");

		JobGraph jg = new JobGraph();
		if(!jg.add_job('a')) return false;
		if(!jg.add_job('b')) return false;
		if(!jg.add_job('c')) return false;
		if(!jg.add_job('d')) return false;
		if(!jg.add_job('e')) return false;
		if(!jg.add_job('f')) return false;

		if(!jg.add_dependency('d','a')) return false;
		if(!jg.add_dependency('d','b')) return false;
		if(!jg.add_dependency('b','c')) return false;
		if(!jg.add_dependency('c','f')) return false;
		if(!jg.add_dependency('e','b')) return false;

		jg.print_graph();

		
		String seqString = jg.get_job_sequence();
		System.out.println("Job Sequence: " + seqString);
		return jg.is_sequence_valid(seqString);
		
	}

	Boolean test_case_2()
	{

		System.out.println("\n*******************Test Case 2*****************");

		JobGraph jg = new JobGraph();
		if(!jg.add_job('a')) return false;
		if(!jg.add_job('b')) return false;
		if(!jg.add_job('c')) return false;
		if(!jg.add_job('d')) return false;
		if(!jg.add_job('e')) return false;
		if(!jg.add_job('f')) return false;

		if(!jg.add_dependency('d','a')) return false;
		if(!jg.add_dependency('d','b')) return false;
		if(!jg.add_dependency('b','c')) return false;
		if(!jg.add_dependency('c','f')) return false;
		if(!jg.add_dependency('e','b')) return false;
		if(!jg.add_dependency('f','b')) return false;

		jg.print_graph();

		
		String seqString = jg.get_job_sequence();
		if(jg.has_cycle)
		{
			System.out.println(seqString);
			return true;
		}
		return false;
		
	}
}