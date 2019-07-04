/*

Assumptions:
All jobs are named with a character.
Case sensitive, but no '\0' character allowed.


*/


package job_sequencer;
import java.util.*;
import java.io.*;
import java.lang.String;

public class JobGraph
{
	class Job
	{
		char name;
		List <Job> children;
		Job parent;						//Can be used in future processes, currently used here in verifying validity of job sequence.
		boolean is_visited;				//required for sorting
		boolean temp_is_visited;		// required to check for cycles

		Job(char name)
		{
			this.name = name;
			children = new LinkedList<>();
			parent = null;							
			is_visited = false;					
			temp_is_visited = false;			
		}
	}

	List<Job> jobs;
	Map <Character,Job> job_index;
	boolean has_cycle;

	JobGraph()
	{
		jobs = new LinkedList<>();
		job_index = new HashMap<>();
		has_cycle = false;
	}

	void add_job(char name)
	{
		Job job = new Job(name);
		jobs.add(job);

		if(job_index.get(name) == null)
			job_index.put(name,job);
		else
			System.out.println("This job name already exists! Please use another name.");
	}

	void add_dependency(char parent,char child)
	{
		if(job_index.get(parent) == null)
			System.out.println("The job '" + parent +"' doesnt exists! Please create the job first");
		else if(job_index.get(child) == null)
			System.out.println("The job '" + child +"' doesnt exists! Please create the job first");
		else
		{
			job_index.get(parent).children.add(job_index.get(child));
			job_index.get(child).parent = job_index.get(parent);
		}
	}

	void print_graph()
	{
		for(Job job : jobs)
		{
			System.out.print(job.name + " => ");
			for(Job child : job.children)
				System.out.print(child.name + " => ");
			System.out.println();
		}
	}


	String get_job_sequence()
	{
		Stack<Character> sequence = new Stack<>();
		// boolean [] is_visited = new boolean[jobs.size()];
		// boolean [] temp_is_visited = new boolean[jobs.size()];

		for(Job j:jobs)
		{
			if(!j.is_visited)
			{

				if(!job_sequence_helper(j,sequence))					
				{
					this.has_cycle = true;
					return "Error: Graph has a cycle!";				
				}
			}
		}

		for(Job j:jobs)
		{
			j.is_visited = false;
			j.temp_is_visited = false;
		}

		String seqString = "";
		while(!sequence.isEmpty())
		{
			seqString +=  sequence.pop() + ",";
		}
		return seqString.substring(0,seqString.length()-1);

	}

	boolean job_sequence_helper(Job current,Stack<Character> sequence)
	{
		if(current.temp_is_visited)
			return false;

		if(current.is_visited)
			return true;

		current.is_visited = true;
		current.temp_is_visited = true;
		for(Job child:current.children)
		{
			if(!job_sequence_helper(child,sequence))
				return false;
			
			
		}
		sequence.push(current.name);
		current.temp_is_visited = false;

		return true;

	}

	boolean is_sequence_valid(String seqString)
	{
		String sequence[] = seqString.split(",");
		Set <Character> seen = new HashSet<>();
		for(int i = sequence.length - 1; i>=0; i--)
		{
			Job curr = job_index.get(sequence[i].charAt(0));
			Job p = curr.parent;
			if( p!=null && seen.contains(p.name))
				return false;
			seen.add(curr.name);
		}
		return true;
	}


}


// public class job_sequencer
// {
// 	public static void main(String[] args) {
// 		JobGraph jg = new JobGraph();
// 		jg.add_job('a');
// 		jg.add_job('b');
// 		jg.add_job('c');
// 		jg.add_job('d');
// 		jg.add_job('e');
// 		jg.add_job('f');

// 		jg.add_dependency('d','a');
// 		jg.add_dependency('d','b');
// 		jg.add_dependency('b','c');
// 		jg.add_dependency('c','f');
		
// 		jg.print_graph();

// 		String seqString = jg.get_job_sequence();
// 		System.out.println(seqString);
// 		System.out.println(jg.is_sequence_valid("e,d,c,b,f,a"));

// 	}
// }