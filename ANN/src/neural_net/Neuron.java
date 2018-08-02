package neural_net;


class Neuron {
	private static int input_nodes;
	private static int hidden_nodes;
	private static int output_nodes;
	private float lr;
	
	public static Mat weightsIH;
	public static Mat weightsHO;
	public static Mat visualWIH; 
	public static Mat visualWHO;
	
	Neuron(int inputnodes, int hiddennodes, int outputnodes, float learningRate) {
		this.input_nodes = inputnodes;
		this.hidden_nodes = hiddennodes;
		this.output_nodes = outputnodes;
		
		this.lr = learningRate;
		
		weightsIH = new Mat(hidden_nodes, input_nodes);
		weightsHO = new Mat(output_nodes, hidden_nodes);
		
		weightsIH.MatrixRandomize();
		weightsHO.MatrixRandomize();
	}
	
	static double[][] getWIH() {
		visualWIH = new Mat(input_nodes, hidden_nodes);
		visualWIH = weightsIH.MatrixTranspose();
		
		double[][] wih = new double[input_nodes][hidden_nodes];
		for(int i = 0;i<wih.length;i++)
			for(int j = 0;j<wih[0].length;j++)
				wih[i][j] = visualWIH.mat[i][j];
		
		return wih;
	}
	
	static double[][] getWHO() {
		visualWHO = new Mat(hidden_nodes, output_nodes);
		visualWHO = weightsHO.MatrixTranspose();
		
		double[][] who = new double[hidden_nodes][output_nodes];
		
		for(int i = 0;i<who.length;i++)
			for(int j = 0;j<who[0].length;j++)
				who[i][j] = visualWHO.mat[i][j];
		
		return who;
	}
	
	void train(double[][] input, double[][] target, Visualization visual) {
		Mat inputs = new Mat(input.length, input[0].length);
		Mat targets = new Mat(target.length, target[0].length);
		Mat hidden_inputs = new Mat(hidden_nodes, 1);
		Mat hidden_outputs = new Mat(hidden_nodes, 1);
		Mat final_inputs = new Mat(hidden_nodes, output_nodes);
		Mat final_outputs = new Mat(output_nodes, 1);
		
		inputs.MatrixSet(input);
		Mat inputs_T = inputs.MatrixTranspose();
		targets.MatrixSet(target);
		Mat targets_T = targets.MatrixTranspose();
		hidden_inputs = weightsIH.DotMul(inputs_T);
		hidden_outputs = Activation_Function.actFunc(hidden_inputs);
		
		final_inputs = weightsHO.DotMul(hidden_outputs);
		final_outputs = Activation_Function.actFunc(final_inputs);
		
		Mat output_errors = Mat.MatSub(targets_T, final_outputs);
		Mat hidden_errors = (weightsHO.MatrixTranspose()).DotMul(output_errors);
		
		// back propagation.
		Mat delta = new Mat(output_errors.mat.length, output_errors.mat[0].length);
		for(int i = 0;i<output_errors.mat.length;i++) 
			for(int j = 0;j<output_errors.mat[0].length;j++) 
				delta.mat[i][j] = output_errors.mat[i][j] * final_outputs.mat[i][j] * (1-final_outputs.mat[i][j]);
		
		delta = delta.DotMul(hidden_outputs.MatrixTranspose());
		
		for(int i = 0 ;i<delta.mat.length;i++) 
			for(int j = 0;j<delta.mat[0].length;j++)
				delta.mat[i][j] *=lr;	
		
		weightsHO.MatrixAdd(delta);
		
		// weightIH update
		Mat deltaIH = new Mat(hidden_errors.mat.length, hidden_errors.mat[0].length);
		for(int i = 0;i<hidden_errors.mat.length;i++) 
			for(int j = 0;j<hidden_errors.mat[0].length;j++) 
				deltaIH.mat[i][j] = hidden_errors.mat[i][j] * hidden_outputs.mat[i][j] * (1-hidden_outputs.mat[i][j]);
		
		deltaIH = deltaIH.DotMul(inputs);
		
		for(int i = 0 ;i<deltaIH.mat.length;i++) {
			for(int j = 0;j<deltaIH.mat[0].length;j++)
				deltaIH.mat[i][j] *=lr;
		}
		weightsIH.MatrixAdd(deltaIH);
		
		visual.wIH = Mat.MatToArray(weightsIH.MatrixTranspose());
		visual.wHO = Mat.MatToArray(weightsHO.MatrixTranspose());
	}
	
	Mat query(double[][] in) {
		Mat inputs = new Mat(in.length, in[0].length);
		Mat hidden_inputs = new Mat(hidden_nodes, 1);
		Mat hidden_outputs = new Mat(hidden_nodes, 1);
		Mat final_inputs = new Mat(hidden_nodes, output_nodes);
		Mat final_outputs = new Mat(output_nodes, 1);
		
		inputs.MatrixSet(in);
		Mat inputs_T = inputs.MatrixTranspose();
		
		hidden_inputs = weightsIH.DotMul(inputs_T);
		hidden_outputs = Activation_Function.actFunc(hidden_inputs);
		final_inputs = weightsHO.DotMul(hidden_outputs);
		final_outputs = Activation_Function.actFunc(final_inputs);
		
		return final_outputs;
	}
}