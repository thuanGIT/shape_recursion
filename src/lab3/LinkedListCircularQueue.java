public class LinkedListCircularQueue {

    private int count; // Keep track on the size of queue
    private Node front, rear;

    // Creates an empty queue
    public LinkedListCircularQueue(){
       count = 0;
       front = rear = null;
    }

    // Adds the specified element to the rear of the queue
    public void enqueue(String element) {      
        // Create a new node for the given data
        Node temp = new Node(element,null);
        // In case the queue is empty
        if (isEmpty()) {
            // Both points to the new node
            front = rear = temp;
        } else {//Otherwise
            // Set the last node to point to the new node
            rear.setLink(temp);
            // Update rear to point to new node
            rear = temp;
        }
        // Update the new node to point itself
        rear.setLink(front);
        //Increment count by 1
        count++;  
    }

    // Removes the element at the front of the queue and returns a reference to it
    public String dequeue()  {
        // In case the queue is empty
        if (isEmpty()) {
            System.out.println("Queue is empty, can't remove any item.");
            return null;
        } 
         // Have a temporary variable to hold the node
        Node current = front;

        // In case of 1 element
        if (count == 1) {   
            // Update the front and rear to null -> no element left
            front = rear = null;
            // Decrement count by 1    
        } else {
            // Update head to point to the second element
            front = front.getLink();
            
            // Update the last element to point to the new front
            rear.setLink(front);
        }
        //Decrement count by 1
        count--;

        //Return the data in current
        return current.getData(); 
    }

    // Returns the element at the front of the queue without removing it
    public String first() {
       // In case of empty
       if (isEmpty()) {
           System.out.println("Queue is empty. No item to check.");
           return null;
       }
       //Otherwise return the data in the first node
        return front.getData();
    }

    // Returns true if the queue contains no elements, false otherwise
    public boolean isEmpty() {
        return count == 0;
    }

    // Returns the number of elements in the queue
    public int size() {
        return count; 

    }

    // Returns a string representation of the queue
    public String toString() {
        String result = "";
        Node current = front;
        if (!isEmpty())
            do {
                result = result + (current.getData()).toString() + "\t";
                current = current.getLink();
            } while (current != front);
        return result;
    }


}
