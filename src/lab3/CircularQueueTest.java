public class CircularQueueTest {
    public static void main(String[] args) {
        LinkedListCircularQueue queue = new LinkedListCircularQueue();
        queue.enqueue("1");
        queue.enqueue("2");
        System.out.println("Queue size: " + queue.size());
        System.out.println("Queue contents: "+queue+"\n");
        queue.dequeue();
        queue.dequeue();
        System.out.println("Queue size: " + queue.size());
        queue.enqueue("3");
        System.out.println("Queue contents: "+queue+"\n");
        queue.dequeue();
        queue.dequeue();// should show a message (e.g., "Queue is empty, can't remove any item") as the queue is empty
        System.out.println();
        queue.enqueue("4");
        queue.enqueue("5");
        System.out.println("First item: "+queue.first());
        System.out.println("Queue contents: "+queue);
    }
}
