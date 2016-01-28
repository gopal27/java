package com.sample.queue;

public class Main {
	
	private final static int items = 10;
	private final static int QUEUE_SIZE = 2;

	public static void main(String[] args) throws Exception {
		
		BlockingQueue<OrderItem> queue = new BlockingQueue<>(QUEUE_SIZE);
		Thread p = new Thread(new OrderProducer<>(queue, 2), "Producer");
		Thread c = new Thread(new OrderConsumer<>(queue), "Consumer1");
		Thread c1 = new Thread(new OrderConsumer<>(queue), "Consumer2");
		
		long time = System.currentTimeMillis();
		p.start();
		c.start();
		c1.start();
		
		p.join();
		c.join();
		c1.join();
		long endTime = System.currentTimeMillis();
		
		System.out.print("Total time: " + ((endTime - time)/1000) + " seconds!");
		
	}
	

	private static class OrderProducer<T extends QueueItem> implements Runnable {
		private BlockingQueue<T> queue;
		private int consumerSize;
		
		public OrderProducer(BlockingQueue<T> q, int consumerSize) {
			this.queue = q;
			this.consumerSize = consumerSize;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < items; i++) {
				System.out.println(" Producing Item# : SYM" + i);
				OrderItem temp = new OrderItem("SYM" + i, (1 % 10) * 100, i % 2);
				this.queue.put(temp);
			}
			
			for(int i = 0; i < this.consumerSize; i++) {
				this.queue.put(new PoisonFill());
			}
		}
	}
	
	private static class OrderConsumer<T extends QueueItem> implements Runnable {
		private BlockingQueue<T> queue;
		
		public OrderConsumer(BlockingQueue<T> q) {
			this.queue = q;
		}
		
		@Override
		public void run() {
			while(true) {
				QueueItem item = this.queue.get();
				if(item instanceof PoisonFill) {
					System.out.println(Thread.currentThread().getName() + " ...PoisionFill..closing out");
					break;
				}
				else
				{
					System.out.println(" Consuming Item# :" + ((OrderItem)item).symbol);
				}
			}
		}
		
	}
	
	
	private static class OrderItem implements QueueItem {
		String symbol;
		int qty;
		int side;

		public OrderItem(String symbol, int qty, int side) {
			this.symbol = symbol;
			this.qty = qty;
			this.side = side;
		}
	}
	
	
	private static class PoisonFill implements QueueItem {
	}

}
