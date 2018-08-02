package neural_net;
import java.io.*;
import org.jfree.chart.*;

public class neuralNetwork {
	static File dad, mom, mymy;
	static int[] father = new int[200];
	static int[] mother = new int[200];
	static int[] me = new int[200];
	static int dad_num = 0, mom_num = 0, son_num = 0;
	static boolean boyAndgirl = true;
	
	static void fileProcessing() {
		int i = 0;
		
		if(boyAndgirl) {
			dad = new File("father.txt");
			mom = new File("mother.txt");
			mymy = new File("Me.txt");
		}
		
		else {
			dad = new File("gFather.txt");
			mom = new File("gMother.txt");
			mymy = new File("gMymy.txt");
		}
		BufferedReader dad_br = null;
		BufferedReader mom_br = null;
		BufferedReader son_br = null;
		try {
			dad_br = new BufferedReader(new FileReader(dad));
			mom_br = new BufferedReader(new FileReader(mom));
			son_br = new BufferedReader(new FileReader(mymy));
			String line = "";
			while((line = dad_br.readLine()) != null) 
				father[i++] = Integer.parseInt(line);
			dad_num = i-1;
			i = 0; 
			while((line = mom_br.readLine()) != null)
				mother[i++] = Integer.parseInt(line);
			mom_num = i-1;
			i = 0;
			while((line = son_br.readLine()) != null) 
				me[i++] = Integer.parseInt(line);
			son_num = i-1;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} 
	}
	
	static void updateWeight(Visualization visual, ChartFrame frame1, JFreeChart chart ) {
		visual.dataset1.addValue(Visualization.wIH[0][0], "Father's weight", "w11");
		visual.dataset1.addValue(Visualization.wIH[0][1], "Father's weight", "w12");
		visual.dataset1.addValue(Visualization.wIH[0][2], "Father's weight", "w13");
		visual.dataset1.addValue(Visualization.wIH[0][3], "Father's weight", "w14");
		visual.dataset1.addValue(Visualization.wIH[0][4], "Father's weight", "w15");
        
		visual.dataset1.addValue(Visualization.wIH[1][0], "Mother's weight", "w21");
		visual.dataset1.addValue(Visualization.wIH[1][1], "Mother's weight", "w22");
		visual.dataset1.addValue(Visualization.wIH[1][2], "Mother's weight", "w23");
		visual.dataset1.addValue(Visualization.wIH[1][3], "Mother's weight", "w24");
		visual.dataset1.addValue(Visualization.wIH[1][4], "Mother's weight", "w25");
        
		visual.dataset1.addValue(Visualization.wHO[0][0], "My weight", "w31");
		visual.dataset1.addValue(Visualization.wHO[1][0], "My weight", "w32");
		visual.dataset1.addValue(Visualization.wHO[2][0], "My weight",  "w33");
		visual.dataset1.addValue(Visualization.wHO[3][0], "My weight",  "w34");
		visual.dataset1.addValue(Visualization.wHO[4][0],  "My weight",  "w35");
	}
	
	static double[][] query = new double[1][2];
	static Neuron n;
	public static void main(String[] args)  {
		//number of input, hidden and output nodes
		int i = 0;
		gender choice = new gender();
		fileProcessing();
		double[][] inputs = new double[dad_num][2];
		double[][] outputs = new double[son_num][1];
		
		for(i = 0;i<inputs.length;i++) {
			inputs[i][0] = (double)father[i]/1000;
			inputs[i][1] = (double)mother[i]/1000;
		}
		
		for(i = 0;i<outputs.length;i++) 
			outputs[i][0] = (double)me[i] / 1000;
		
		int input_nodes = 2;
		int hidden_nodes = 5;
		int output_nodes = 1;
		
		//learning rate
		float lr = 0.5f;
		
		//create instance of neural network
		n = new Neuron(input_nodes, hidden_nodes, output_nodes, lr);
		
		Visualization visual = new Visualization();
		ChartFrame frame1 = visual.returnChart();
		JFreeChart chart = visual.getChart();
		chart = visual.getChart();
		frame1=new ChartFrame("Weight Change Graph",chart);
		frame1.setSize(800,600); 
		
		for(i = 0;i<10000;i++) {
			frame1.setVisible(true);
			updateWeight(visual, frame1, chart);
			n.train(inputs, outputs, visual);
		}
		
		GUI window = new GUI();
		window.setVisible(true);
	}
}
