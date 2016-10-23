package com.asu.plp.visualizer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JFrame;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;


interface SnapshotListener {
	void receiveSnapshot(String color);
}

abstract class SnapshotEventHandler implements SnapshotListener {
	@Override
	public void receiveSnapshot(String color) {};
}

public class PLPVisualizer extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	/** 
	 * Default Constructor
	 */
	public PLPVisualizer()
	{
		super("PLP Visualizer");

		myGraph graph = new myGraph();
		Object parent = graph.getDefaultParent();
		// nodes
		Object pc;
		Object add1;
		Object instruction_memory;
		Object shift1;
		Object control;
		Object mux1;
		Object sign_extend;
		Object registers;
		Object shift2;
		Object mux2;
		Object alu_control;
		Object add2;
		Object alu;
		Object mux3;
		Object mux4;
		Object and_gate;
		Object data_memory;
		Object mux5;
		// edges
		Object pc_add1;
		Object add1_add2;
		Object pc_im;
		Object im_shift1;
		Object im_control;
		Object im_mux1_upper;
		Object im_mux1_lower;
		Object im_registers_upper;
		Object im_registers_lower;
		Object im_sign;
		Object im_aluc;
		Object control_mux1;
		Object control_registers;
		Object control_mux4;
		Object control_andgate;
		Object control_mux5;
		Object control_data;
		
		Object add1_mux4;
		Object aluc_alu;
		Object alu_andgate;
		Object andgate_mux3;
		
		graph.getModel().beginUpdate();
		try
		{
			pc = graph.insertVertex(parent, "pc", "PC", 40, 280, 20, 60);
			add1 = graph.insertVertex(parent, null, "Add", 120, 20, 60, 80);
			instruction_memory = graph.insertVertex(parent, null, "Instruction\nMemeory", 120, 300, 60, 80);
			shift1 = graph.insertVertex(parent, null, "Shift left 2", 260, 40, 20, 40);
			control = graph.insertVertex(parent, null, "Control", 280, 140, 60, 120);
			mux1 = graph.insertVertex(parent, null, "Mux", 290, 300, 20, 40);
			sign_extend =graph.insertVertex(parent, null, "Sign-extend", 320, 420, 40, 60);
			registers = graph.insertVertex(parent, null, "Registers", 340, 280, 80, 120);
			shift2 = graph.insertVertex(parent, null, "Shift left 2", 480, 130, 20, 40);
			mux2 = graph.insertVertex(parent, null, "Mux", 480, 340, 20, 40);
			alu_control = graph.insertVertex(parent, null, "ALU Control", 500, 480, 40, 60);
			add2 = graph.insertVertex(parent, null, "Add", 520, 90, 60, 80);
			alu = graph.insertVertex(parent, null, "ALU", 520, 300, 60, 80);
			mux3 = graph.insertVertex(parent, null, "Mux", 620, 70, 20, 40);
			mux4 = graph.insertVertex(parent, null, "Mux", 660, 40, 20, 40);
			and_gate = graph.insertVertex(parent, null, "", 600, 170, 20, 20);
			data_memory = graph.insertVertex(parent, null, "Data\nmemory", 600, 340, 60, 90);
			mux5 = graph.insertVertex(parent, null, "Mux", 680, 320, 20, 40);

			pc_add1 = graph.insertEdge(parent, null, "", pc, add1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.3;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			add1_add2 = graph.insertEdge(parent, null, "", add1, add2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			pc_im = graph.insertEdge(parent, null, "", pc, instruction_memory, "orthogonal=0;exitX=1;exitY=0.7;exitPerimeter=1;"
					+ "entryX=0;entryY=0.3;entryPerimeter=1;");
			im_shift1 = graph.insertEdge(parent, null, "", instruction_memory, shift1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			im_control = graph.insertEdge(parent, null, "", instruction_memory, control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.2;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			im_mux1_upper = graph.insertEdge(parent, null, "", instruction_memory, mux1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.3;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			im_mux1_lower = graph.insertEdge(parent, null, "", instruction_memory, mux1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.4;exitPerimeter=1;entryX=0;entryY=0.7;entryPerimeter=1;");
			im_registers_upper = graph.insertEdge(parent, null, "", instruction_memory, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.55;entryPerimeter=1;");
			im_registers_lower = graph.insertEdge(parent, null, "", instruction_memory, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.7;exitPerimeter=1;entryX=0;entryY=0.65;entryPerimeter=1;");
			im_sign = graph.insertEdge(parent, null, "", instruction_memory, sign_extend, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0;entryY=0.5;entryPerimeter=1;");
			im_aluc = graph.insertEdge(parent, null, "", instruction_memory, alu_control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.9;exitPerimeter=1;entryX=0;entryY=0.4;entryPerimeter=1;");
			control_mux1 = graph.insertEdge(parent, null, "", control, mux1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.3;exitY=1;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			control_registers = graph.insertEdge(parent, null, "", control, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.7;exitY=1;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			control_mux4 = graph.insertEdge(parent, null, "", control, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.6;entryPerimeter=1;");
			control_andgate = graph.insertEdge(parent, null, "", control, and_gate, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.2;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			control_mux5 = graph.insertEdge(parent, null, "", control, mux5, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			control_data = graph.insertEdge(parent, null, "", control, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.6;exitPerimeter=1;entryX=0.7;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.7;exitPerimeter=1;entryX=0.3;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, mux2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, alu_control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.9;exitPerimeter=1;entryX=0;entryY=0.1;entryPerimeter=1;");
			aluc_alu = graph.insertEdge(parent, null, "", alu_control, alu, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0.5;entryY=1;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", mux1, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", registers, alu, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.3;exitPerimeter=1;entryX=0;entryY=0.2;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", registers, mux2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.6;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", registers, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.9;exitPerimeter=1;entryX=0;entryY=0.8;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", mux3, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.8;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", sign_extend, shift2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.5;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", sign_extend, mux2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.7;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", mux2, alu, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.75;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", add1, mux3, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0;entryY=0.35;entryPerimeter=1;");
			add1_mux4 = graph.insertEdge(parent, null, "", add1, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.2;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", shift1, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.4;exitPerimeter=1;entryX=0;entryY=0.4;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", shift2, add2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.5;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", add2, mux3, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.7;entryPerimeter=1;");
			alu_andgate = graph.insertEdge(parent, null, "", alu, and_gate, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.6;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", alu, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.35;exitPerimeter=1;entryX=0;entryY=0.2;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", alu, mux5, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.35;exitPerimeter=1;entryX=0;entryY=0.2;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", data_memory, mux5, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.8;entryPerimeter=1;");
			andgate_mux3 = graph.insertEdge(parent, null, "", and_gate, mux3, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0.5;entryY=1;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", mux5, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.5;exitY=1;exitPerimeter=1;entryX=0.5;entryY=1;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", mux4, pc, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.5;exitY=0;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		graphComponent.setToolTips(true);
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
		
			public void mouseReleased(MouseEvent e)
			{
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());
				if (cell != null)
				{
					System.out.println("cell="+graph.getLabel(cell));
				}
			}
			public void mouseEntered(MouseEvent e)
			{
				System.out.println("Hover");
				graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{pc, add1, alu, mux3, mux4, and_gate, instruction_memory, control, alu_control});
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", new Object[]{pc_add1, add1_mux4, pc_im, im_aluc, im_control, control_mux4, aluc_alu, alu_andgate, andgate_mux3});
			}
			public void mouseExited(MouseEvent e)
			{
				System.out.println("Leave");
				graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", new Object[]{pc, add1, mux3, alu, mux4, and_gate, instruction_memory, control, alu_control});
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", new Object[]{pc_add1, add1_mux4, pc_im, im_aluc, im_control, control_mux4, aluc_alu, alu_andgate, andgate_mux3});
			}
			public void mouseMoved(MouseEvent e)
			{
				System.out.println("Moved");
			}
		});
		FrontendConsumer frontend = new FrontendConsumer();
		frontend.addListener(new SnapshotEventHandler(){
			public void receiveSnapshot(String color)
			{
				graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, color, new Object[]{pc, add1, mux3, alu, mux4, and_gate, instruction_memory, control, alu_control});
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, color, new Object[]{pc_add1, add1_mux4, pc_im, im_aluc, im_control, control_mux4, aluc_alu, alu_andgate, andgate_mux3});
			}
		});
		thread(frontend, false);
	}

	public static void main(String[] args)
	{
		PLPVisualizer frame = new PLPVisualizer();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		thread(new BackendProducer(), false);
	}
	
	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

	public static class BackendProducer implements Runnable {
		public void run() {
			String colors[] = {"red", "green"};
			int color = 0;
			while (true) {
				try {
					Thread.sleep(1000);
					// Create a ConnectionFactory
					ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
	
					// Create a Connection
					Connection connection = connectionFactory.createConnection();
					connection.start();
	
					// Create a Session
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
					// Create the destination (Topic or Queue)
					Destination destination = session.createQueue("TEST.FOO");
	
					// Create a MessageProducer from the Session to the Topic or Queue
					MessageProducer producer = session.createProducer(destination);
					producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	
					// Create a messages
					if (color == 0)
						color = 1;
					else
						color = 0;
					String text = colors[color];
					TextMessage message = session.createTextMessage(text);
	
					// Tell the producer to send the message
					System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
					producer.send(message);
	
					// Clean up
					session.close();
					connection.close();
				}
				catch (Exception e) {
					System.out.println("Caught: " + e);
					e.printStackTrace();
				}
			}
		}
	}

	public static class FrontendConsumer implements Runnable, ExceptionListener {
		private List<SnapshotListener> listeners = new ArrayList<SnapshotListener>();

		public void addListener(SnapshotListener toAdd) {
			listeners.add(toAdd);
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					// Create a ConnectionFactory
					ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
	
					// Create a Connection
					Connection connection = connectionFactory.createConnection();
					connection.start();
	
					connection.setExceptionListener(this);
	
					// Create a Session
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
					// Create the destination (Topic or Queue)
					Destination destination = session.createQueue("TEST.FOO");
	
					// Create a MessageConsumer from the Session to the Topic or Queue
					MessageConsumer consumer = session.createConsumer(destination);
	
					// Wait for a message
					Message message = consumer.receive(1000);
	
					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						String text = textMessage.getText();
						System.out.println("Received: " + text);
						for (SnapshotListener hl : listeners)
							hl.receiveSnapshot(text);
					} else {
						System.out.println("Received: " + message);
					}
	
					consumer.close();
					session.close();
					connection.close();
				} catch (Exception e) {
					System.out.println("Caught: " + e);
					e.printStackTrace();
				}
			}
		}

		public synchronized void onException(JMSException ex) {
			System.out.println("JMS Exception occured.  Shutting down client.");
		}
	}
}
