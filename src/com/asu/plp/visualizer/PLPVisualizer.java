package com.asu.plp.visualizer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;

public class PLPVisualizer extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	public PLPVisualizer()
	{
		super("PLP Visualizer");

		myGraph graph = new myGraph();
		Object parent = graph.getDefaultParent();
		Object pc;
		Object pc_add;
		graph.getModel().beginUpdate();
		try
		{
			pc = graph.insertVertex(parent, null, "PC", 40, 280, 20,
					60);
			Object add1 = graph.insertVertex(parent, null, "Add", 120, 20,
					60, 80);
			Object instruction_memory = graph.insertVertex(parent, null, "Instruction\nMemeory",
					120, 300, 60, 80);
			Object shift1 = graph.insertVertex(parent, null, "Shift left 2",
					260, 40, 20, 40);
			Object control = graph.insertVertex(parent, null, "Control",
					280, 140, 60, 120);
			Object mux1 = graph.insertVertex(parent, null, "Mux",
					290, 300, 20, 40);
			Object sign_extend =graph.insertVertex(parent, null, "Sign-extend",
					320, 420, 40, 60);
			Object registers = graph.insertVertex(parent, null, "Registers",
					340, 280, 80, 120);
			Object shift2 = graph.insertVertex(parent, null, "Shift left 2",
					480, 130, 20, 40);
			Object mux2 = graph.insertVertex(parent, null, "Mux",
					480, 340, 20, 40);
			Object alu_control = graph.insertVertex(parent, null, "ALU Control",
					500, 480, 40, 60);
			Object add2 = graph.insertVertex(parent, null, "Add",
					520, 90, 60, 80);
			Object alu = graph.insertVertex(parent, null, "ALU",
					520, 300, 60, 80);
			Object mux3 = graph.insertVertex(parent, null, "Mux",
					620, 70, 20, 40);
			Object mux4 = graph.insertVertex(parent, null, "Mux",
					660, 40, 20, 40);
			Object and_gate = graph.insertVertex(parent, null, "",
					600, 170, 20, 20);
			Object data_memory = graph.insertVertex(parent, null, "Data\nmemory",
					600, 340, 60, 90);
			Object mux5 = graph.insertVertex(parent, null, "Mux",
					680, 320, 20, 40);

			pc_add = graph.insertEdge(parent, null, "", pc, add1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.3;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", add1, add2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", pc, instruction_memory, "orthogonal=0;exitX=1;exitY=0.7;exitPerimeter=1;"
					+ "entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, shift1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.2;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, mux1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.3;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, mux1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.4;exitPerimeter=1;entryX=0;entryY=0.7;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.55;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.7;exitPerimeter=1;entryX=0;entryY=0.65;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, sign_extend, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0;entryY=0.5;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", instruction_memory, alu_control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.9;exitPerimeter=1;entryX=0;entryY=0.4;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, mux1, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.3;exitY=1;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, registers, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=0.7;exitY=1;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.6;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, and_gate, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.2;exitPerimeter=1;entryX=0;entryY=0.3;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, mux5, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.6;exitPerimeter=1;entryX=0.7;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.7;exitPerimeter=1;entryX=0.3;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, mux2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.8;exitPerimeter=1;entryX=0.5;entryY=0;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", control, alu_control, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.9;exitPerimeter=1;entryX=0;entryY=0.1;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", alu_control, alu, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
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
			graph.insertEdge(parent, null, "", add1, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.2;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", shift1, mux4, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.4;exitPerimeter=1;entryX=0;entryY=0.4;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", shift2, add2, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.5;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", add2, mux3, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.5;exitPerimeter=1;entryX=0;entryY=0.7;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", alu, and_gate, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.6;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", alu, data_memory, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.35;exitPerimeter=1;entryX=0;entryY=0.2;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", alu, mux5, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.35;exitPerimeter=1;entryX=0;entryY=0.2;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", data_memory, mux5, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
					+ "exitX=1;exitY=0.1;exitPerimeter=1;entryX=0;entryY=0.8;entryPerimeter=1;");
			graph.insertEdge(parent, null, "", and_gate, mux3, "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
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
				graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{pc});
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", new Object[]{pc_add});
			}
			public void mouseExited(MouseEvent e)
			{
				System.out.println("Leave");
				graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", new Object[]{pc});
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", new Object[]{pc_add});
			}
			public void mouseMoved(MouseEvent e)
			{
				System.out.println("Moved");
			}
		});
	}

	public static void main(String[] args)
	{
		PLPVisualizer frame = new PLPVisualizer();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

}
