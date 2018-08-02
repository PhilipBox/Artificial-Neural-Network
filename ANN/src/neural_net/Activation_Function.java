package neural_net;

public class Activation_Function {
	static double sigmoid(double x) {
		   return (1/( 1 + Math.pow(Math.E,(-x))));
	}

	static Mat actFunc(Mat matrix) {
		Mat output = new Mat(matrix.mat.length, matrix.mat[0].length);
		for(int i = 0;i<matrix.mat.length;i++) {
			for(int j = 0;j<matrix.mat[0].length;j++) {
				output.mat[i][j] = sigmoid(matrix.mat[i][j]);
			}
		}
		return output;
	}	
}
