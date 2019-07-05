/*

Assumptions:
All jobs are named with a character.
Case sensitive, but no '\0' character allowed.


*/



import java.util.*;
import java.io.*;
import java.lang.String;
import java.lang.reflect.*;
class JobGraph
{
	class Job
	{
		char name;
		Set <Job> children;
		boolean has_parent;
		boolean is_visited;				//required for sorting
		boolean temp_is_visited;		// required to check for cycles

		Job(char name)
		{
			this.name = name;
			children = new HashSet<>();
			has_parent = false;
			is_visited = false;					
			temp_is_visited = false;			
		}
	}

	List<Job> jobs;
	Map <Character,Job> job_index;	// Maps jobs names to job nodes
	boolean has_cycle;		//indicates if the job graph has a cycle

	JobGraph()
	{
		jobs = new LinkedList<>();
		job_index = new HashMap<>();
		has_cycle = false;
	}

	boolean add_job(char name)
	{
		Job job = new Job(name);
		jobs.add(job);

		// avoid duplicate naming
		if(job_index.get(name) == null)
		{
			job_index.put(name,job);
			return true;
		}
		
		System.out.println("This job name already exists! Please use another name.");
		return false;
	}

	boolean add_dependency(char parent,char child)
	{
		if(child == parent)
		{
			System.out.println("Error: A job can't depend on itself!");
			return false;
		}

		if(job_index.get(parent) == null)
		{	
			System.out.println("Error: The job '" + parent +"' doesnt exists! Please create the job first");
			return false;
		}
		if(job_index.get(child) == null)
		{
			System.out.println("Error: The job '" + child +"' doesnt exists! Please create the job first");
			return false;
		}

		
		job_index.get(parent).children.add(job_index.get(child));
		job_index.get(child).has_parent = true;		// mark that this job is a child of some parent
		return true;		
	}

	void print_graph()
	{
		System.out.println("Job graph: " + jobs.size() + " jobs" );
		System.out.println("(Job => Depends on)\n");
		
		for(Job job : jobs)
		{
			
			// System.out.print("\n"+ job.name + " => ");
			if(!job.has_parent)
				System.out.println(job.name + " => ");
			for(Job child: job.children)			
				System.out.println(child.name + " => " + job.name);

		}
		
	}


	String get_job_sequence()
	{
		Stack<Character> sequence = new Stack<>();

		for(Job j:jobs)
		{
			if(!j.is_visited)		// if we have seen this job in another path before
			{

				if(!job_sequence_helper(j,sequence))					
				{
					this.has_cycle = true;		//graph has cycle
					return "Error: Graph has a cycle!";				
				}
			}
		}

		// resetting the visited statuses of the jobs
		for(Job j:jobs)
		{
			j.is_visited = false;
			j.temp_is_visited = false;
		}

		String seqString = "";
		// Getting the sequence string as reverse of sequence stored
		while(!sequence.isEmpty())
		{
			seqString +=  sequence.pop() + ",";
		}
		return seqString.substring(0,Math.max(0,seqString.length()-1));

	}

	boolean job_sequence_helper(Job current,Stack<Character> sequence)
	{
		if(current.temp_is_visited)		//if we have seen this job in the same path being traversed, there is a cycle in the graph
			return false;

		if(current.is_visited)			
			return true;

		current.is_visited = true;		// permanent mark, to mark that this job and its children have been considered already
		current.temp_is_visited = true;	// temporary mark, to mark that this job has been visited in the same traversal before
		for(Job child:current.children)
		{
			if(!job_sequence_helper(child,sequence))
				return false;
			
			
		}
		sequence.push(current.name);		//push the jobs onto the stack from child to parent order in the traversal
		current.temp_is_visited = false;	// remove temporary mark once the current traversal concludes

		return true;

	}

	boolean is_sequence_valid(String seqString)
	{
		String sequence[] = seqString.split(",");
		Set <Character> seen = new HashSet<>();		//keeps a record of all jobs seen so far in the generated job sequence
		for(int i = 0; i<sequence.length; i++)
		{
			Job curr = job_index.get(sequence[i].charAt(0));
			if(!Collections.disjoint(curr.children,seen))			//Check if a child occurs before a parent
				return false;
			seen.add(curr.name);
		}
		return true;
	}


}



public class job_sequencer
{
	
	public static void main(String[] args) throws Exception {
				
		Tester t = new Tester();
		if(t.test_case_0())	
			System.out.println("Status: Passed");
		else
			System.out.println("Status: Failed!");

		
		if(t.test_case_1())	
			System.out.println("Status: Passed");
		else
			System.out.println("Status: Failed!");

		if(t.test_case_2())	
			System.out.println("Status: Passed");
		else
			System.out.println("Status: Failed!");

		
		

	}
}


