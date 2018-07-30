/* ListSorts.java */
package list;

import java.util.Random;

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
	LinkedQueue newQueue = new LinkedQueue();
	try {
		while (!q.isEmpty()) {
			LinkedQueue tempQueue = new LinkedQueue();
			tempQueue.enqueue(q.dequeue());
			newQueue.enqueue(tempQueue);
		}
	} catch (QueueEmptyException e) {
		System.out.println(e);
	}
	return newQueue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue q3 = new LinkedQueue();
    try {
    	while ((!q1.isEmpty()) && (!q2.isEmpty())) {
    		if (((Comparable)q1.front()).compareTo(((Comparable)q2.front()))<0) {
    			q3.enqueue(q1.dequeue());
    		} else {
    			q3.enqueue(q2.dequeue());
    		}
    	}
    	if ((q1.isEmpty()) && (!q2.isEmpty())) {
    		q3.append(q2);
    	} else if ((!q1.isEmpty()) && (q2.isEmpty())){
    		q3.append(q1);
    	}
    } catch (QueueEmptyException e) {
    	System.out.println(e);
    }
    return q3;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
      try {
    	  while (!qIn.isEmpty()) {
    		  Comparable temp = (Comparable) qIn.dequeue();
    		  if (pivot.compareTo(temp)<0) {
    			  qLarge.enqueue(temp);
    		  } else if (pivot.compareTo(temp)==0) {
    			  qEquals.enqueue(temp);
    		  } else {
    			  qSmall.enqueue(temp);
    		  }
    	  }
      } catch (QueueEmptyException e) {
    	  System.out.println(e);
      }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    LinkedQueue rawQueue = makeQueueOfQueues(q);
    try {
    	while (rawQueue.size()>1) {
    		LinkedQueue temp1 = (LinkedQueue) rawQueue.dequeue();
    		LinkedQueue temp2 = (LinkedQueue) rawQueue.dequeue();
    		rawQueue.enqueue(mergeSortedQueues(temp1, temp2));
    	}
    	if (!rawQueue.isEmpty()) {
    		q.append((LinkedQueue) rawQueue.dequeue());
    	}
    } catch (QueueEmptyException e) {
    	System.out.println(e);
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
	Random rand = new Random();
	Integer m = rand.nextInt(q.size()) + 1;
	Comparable pivot = (Comparable) q.nth(m);
	LinkedQueue qSmall = new LinkedQueue();
	LinkedQueue qEquals = new LinkedQueue();
	LinkedQueue qLarge = new LinkedQueue();
	partition(q, pivot, qSmall, qEquals, qLarge);
	if (qSmall.size()>0) quickSort(qSmall);
	if (qLarge.size()>0) quickSort(qLarge);
	q.append(qSmall);
	q.append(qEquals);
	q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }


}
