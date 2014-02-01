import java.util.Scanner;

public class Queue {
	
	private int mainQueue[];
	private int queueItemCount;
	private int indexE;
	private int indexD;

	public Queue(int size)
	{
		//constructor
		mainQueue = new int[size];
		indexD = 0;
		indexE = 0;
		queueItemCount = 0;	
	}
	
	public boolean enqueue(int number) {
		
		boolean isAbleToEnqueue;
		
		if (queueItemCount < mainQueue.length) {
			// insert the number at the index of the current queue index
			mainQueue[indexE] = number;
			//if the index is equal to the length of the queue make the index 0. otherwise keep it as it is
			indexE = indexE++ == mainQueue.length ? 0 : indexE;
			//update the variable that gives us the current queue count
			queueItemCount++;
			// we are able to enqueue, return true.
			isAbleToEnqueue = true; 
		} else {
			// we are unable to enqueue, return false.
			isAbleToEnqueue = false; 
		}
		
		return isAbleToEnqueue; 
	}
	
	public int dequeue() {
		
		int itemToDequeue = 0;
		
		if (queueItemCount > 0) {
			//get the item at the dequeuing index and return it
			itemToDequeue = mainQueue[indexD];
			//make whatever value is at the dequeuing index 0
			mainQueue[indexD] = 0;
			//move the dequeuing index up one iff the dequeuing index is less than the length of our queue
			indexD = indexD++ == mainQueue.length ? 0 : indexD;
			//we removed one item from the items in the queue, so let's adjust the counter accordingly
			queueItemCount--;
		} else {
			throw new IllegalStateException("Nothing to dequeue"); //we can't dequeue if there is nothing to dequeue
		}
		return itemToDequeue;
	}

}

class QueueTest {
	
	public static void main (String args[]) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Choose the size of the queue");
		int size = keyboard.nextInt();
		
		boolean print;
		System.out.println("Print dequeued items? Y/N");
		if (keyboard.nextLine().toLowerCase().equals("y") || keyboard.nextLine().toLowerCase().equals("yes")) {
			print = true;
		} else {
			print = false; 
		}

		if (size > 0) {
			
			boolean successfulEnqueues;
			int totalSuccess = 0;

			Queue testQueue = new Queue(size);
			for (int i = 0; i < size; i++) {
				successfulEnqueues = testQueue.enqueue(i);
				totalSuccess += (successfulEnqueues) ? 1 : 0;
			}
			String notifier = String.format("We had %d successful enqueues out of %d total",totalSuccess, size);
			System.out.println(notifier);

			int dequeuedItems[] = new int[size];
			for (int i = 0; i < size; i++) {
				
				if (print) {
					System.out.println(testQueue.dequeue()) ; //print all the items that were dequeued.
				}
				
				dequeuedItems[i] = testQueue.dequeue();
			}
		}
	}
}
