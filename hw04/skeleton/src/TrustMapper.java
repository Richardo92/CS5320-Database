import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.*;


public class TrustMapper extends Mapper<IntWritable, Node, IntWritable, NodeOrDouble> {
    public void map(IntWritable key, Node value, Context context) throws IOException, InterruptedException {
        //Implement 
    	context.write(key, new NodeOrDouble(value)); // Emit(nid n, node N);
    	context.getCounter("myCounter", "SIZE").increment(1); // increment the number of nodes by 1
    	if (value.outgoingSize() == 0) { // dangling node
    		double pageRank = value.getPageRank();
    		Configuration conf = context.getConfiguration();
    		long inc = (long) (pageRank * 100000000); // multiply one hundred million as long
    		context.getCounter("myCounter", "COUNTER").increment(inc); // add page rank of this node to counter
    	}
    	else {
    		double p = value.getPageRank() / value.outgoingSize();
    		for (Iterator iter = value.iterator(); iter.hasNext();) {
    			int mid = (Integer) iter.next();
    			IntWritable m = new IntWritable(mid);
    			context.write(m, new NodeOrDouble(p)); // Emit(nid m, p);
    		}
    	}
    }
}
