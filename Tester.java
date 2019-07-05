import java.io.*;
public class Tester

{
	int num_testcases = 6;
	int get_num_testcases(){ return num_testcases;}

	Boolean test_case_0()	//empty graph
	{
		
		JobGraph jg = new JobGraph();
		
		jg.print_graph();

		String seqString = jg.get_job_sequence();
		// System.out.println(seqString);
		if(seqString.length()==0)
			return true;
		return false;
			
		
	}

	Boolean test_case_1()	//simple graph
	{

		
		JobGraph jg = new JobGraph();
		if(!jg.add_job('a')) return false;
		if(!jg.add_job('b')) return false;
		if(!jg.add_job('c')) return false;		

		jg.print_graph();
		
		String seqString = jg.get_job_sequence();
		System.out.println("Job Sequence: " + seqString);
		return jg.is_sequence_valid(seqString);
		
	}

	Boolean test_case_2()	//simple graph
	{

		
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

	Boolean test_case_3()	//check for cycle
	{

		

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

	Boolean test_case_4()	//check for duplicate names
	{

		

		JobGraph jg = new JobGraph();	
		System.out.println("adding job a");
		if(!jg.add_job('a')) return false;
		System.out.println("adding job a again");
		if(!jg.add_job('a')) return true;
		
		return false;
		
	}

	Boolean test_case_5()	//check for job exists
	{	

		JobGraph jg = new JobGraph();	
		System.out.println("adding job a");
		if(!jg.add_job('a')) return false;
		System.out.println("adding edge a->b");
		if(!jg.add_dependency('a','b')) return true;
		 
		return false;
		
	}


}