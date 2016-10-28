package com.asu.plp.visualizer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;

import org.json.JSONObject;

import com.asu.plp.communication.BackendProducer;
import com.asu.plp.communication.FrontendConsumer;
import com.asu.plp.event.SnapshotEventHandler;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;

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

		String conf_file = "conf/graph_blue_print.json";
		BufferedReader reader;
		String line = null;
		String jsonString = "";
		try {
			reader = new BufferedReader(new FileReader (conf_file));
			while((line = reader.readLine()) != null) {
				jsonString += line;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject conf = new JSONObject(jsonString);
		JSONObject vertices = conf.getJSONObject("vertices");
		JSONObject edges = conf.getJSONObject("edges");

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
		Object pc_im;
		Object im_control;
		Object im_aluc;
		Object control_mux4;
		
		Object add1_mux4;
		Object aluc_alu;
		Object alu_andgate;
		Object andgate_mux3;
		
		graph.getModel().beginUpdate();
		try
		{
			Iterator<?> json_keys = vertices.keys();
			
			while( json_keys.hasNext() ){
				String json_key = (String)json_keys.next();
				JSONObject node = vertices.getJSONObject(json_key);
				graph.insertVertex(parent, node.getString("id"), (Object)node.getString("name"), node.getDouble("pos_x"), node.getDouble("pos_y"), node.getDouble("width"), node.getDouble("height"));
			}
			pc = (mxCell) ((mxGraphModel)graph.getModel()).getCell("pc");
			add1 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("add1");
			instruction_memory = (mxCell) ((mxGraphModel)graph.getModel()).getCell("instruction_memory");
			shift1 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("shift1");
			control = (mxCell) ((mxGraphModel)graph.getModel()).getCell("control");
			mux1 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("mux1");
			sign_extend = (mxCell) ((mxGraphModel)graph.getModel()).getCell("sign_extend");
			registers = (mxCell) ((mxGraphModel)graph.getModel()).getCell("registers");
			shift2 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("shift2");
			mux2 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("mux2");
			alu_control = (mxCell) ((mxGraphModel)graph.getModel()).getCell("alu_control");
			add2 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("add2");
			alu = (mxCell) ((mxGraphModel)graph.getModel()).getCell("alu");
			mux3 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("mux3");
			mux4 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("mux4");
			and_gate = (mxCell) ((mxGraphModel)graph.getModel()).getCell("and_gate");
			data_memory = (mxCell) ((mxGraphModel)graph.getModel()).getCell("data_memory");
			mux5 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("mux5");

			json_keys = edges.keys();
			
			while( json_keys.hasNext() ){
				String json_key = (String)json_keys.next();
				JSONObject node = edges.getJSONObject(json_key);
				Object first_node = (mxCell) ((mxGraphModel)graph.getModel()).getCell(node.getString("vertex_id_from"));
				Object second_node = (mxCell) ((mxGraphModel)graph.getModel()).getCell(node.getString("vertex_id_to"));
				graph.insertEdge(parent, node.getString("id"), (Object)node.getString("name"), first_node, second_node, node.getString("style"));
			}
			pc_add1 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("pc_add1");
			pc_im = (mxCell) ((mxGraphModel)graph.getModel()).getCell("pc_im");
			im_control = graph.insertEdge(parent, null, "", instruction_memory, control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.2;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			Object im_registers_upper = graph.insertEdge(parent, null, "", instruction_memory, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.55;entryPerimeter=1;");
			Object im_registers_lower = graph.insertEdge(parent, null, "", instruction_memory, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.7;exitPerimeter=1;entryX=0;entryY=0.65;entryPerimeter=1;");
			Object im_sign = graph.insertEdge(parent, null, "", instruction_memory, sign_extend, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0;entryY=0.5;entryPerimeter=1;");
			im_aluc = graph.insertEdge(parent, null, "", instruction_memory, alu_control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.9;exitPerimeter=1;entryX=0;entryY=0.4;entryPerimeter=1;");
			Object control_mux1 = graph.insertEdge(parent, null, "", control, mux1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.3;exitY=1;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			Object control_registers = graph.insertEdge(parent, null, "", control, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.7;exitY=1;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			control_mux4 = graph.insertEdge(parent, null, "", control, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.6;entryPerimeter=1;");
			Object control_andgate = graph.insertEdge(parent, null, "", control, and_gate, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.2;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			Object control_mux5 = graph.insertEdge(parent, null, "", control, mux5, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			Object control_data = graph.insertEdge(parent, null, "", control, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
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
				mxCell myCell = (mxCell) ((mxGraphModel)graph.getModel()).getCell("pc");
				graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, color, new Object[]{myCell, add1, mux3, alu, mux4, and_gate, instruction_memory, control, alu_control});
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

}
