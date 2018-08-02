package neural_net;

import java.util.Random;

public class Mat  {
	private int rows;
	private int cols;
	double[][] mat;

	Mat(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.mat = new double[rows][cols];
		
		for(int i = 0; i < this.rows;i++) {
			for(int j = 0;j<this.cols;j++) {
				this.mat[i][j] = 0;
			}
		}
	}
	
	
	static Mat MatSub(Mat target, Mat final_output) {
		Mat result = new Mat(target.mat.length, target.mat[0].length);
		for(int i = 0;i<target.mat.length;i++){
			for(int j = 0;j<target.mat[0].length;j++)
				result.mat[i][j] = target.mat[i][j] - final_output.mat[i][j];
		}
		return result;
	}
	Mat DotMul(Mat matrix1) {
		Mat result = new Mat(this.mat.length,matrix1.mat[0].length);
		for (int i = 0; i < this.mat.length; i++) { 
		    for (int j = 0; j < matrix1.mat[0].length; j++) { 
		        for (int k = 0; k < this.mat[0].length; k++) { 
		            result.mat[i][j] += this.mat[i][k] * matrix1.mat[k][j];
		        }
		    }
		}
		return result;
	}
	
	static double[][] MatToArray(Mat m) {
		double[][] matrix = new double[m.mat.length][m.mat[0].length];
		for(int i = 0;i<matrix.length;i++)
			for(int j = 0;j<matrix[0].length;j++)
				matrix[i][j] = m.mat[i][j];
		
		return matrix;
	}
	
	void MatrixRandomize() {
		Random rng = new Random();
		for(int i = 0;i<this.rows;i++) {
			for(int j = 0;j<this.cols;j++) {
				this.mat[i][j] = rng.nextDouble();
			}
		}
	}
	
	Mat MatrixMul(double n) {
		Mat result = new Mat(this.rows, this.cols);
		for(int i = 0;i<this.rows;i++) {
			for(int j = 0;j<this.cols;j++) {
				this.mat[i][j] *= n;
			}
		}
		return result;
	}
	
	void MatrixAdd(Mat M) {
		for(int i = 0;i<this.rows;i++) {
			for(int j = 0;j<this.cols;j++) {
				this.mat[i][j] += M.mat[i][j];
			}
		}
	}
	
	Mat MatrixSub(int n) {
		Mat result = new Mat(this.rows, this.cols);
		for(int i = 0;i<this.rows;i++) {
			for(int j = 0;j<this.cols;j++) {
				result.mat[i][j] -= n;
			}
		}
		return result;
	}
	
	static void showTable(Mat matrix) {
		for(int i = 0;i<matrix.mat.length;i++) {
			for(int j = 0;j<matrix.mat[0].length;j++) {
				System.out.printf("%f ", matrix.mat[i][j]);
			}
			System.out.println("");
		}	
	}

	void MatrixSet(double[][] in) {
		// TODO Auto-generated method stub
		for(int i = 0;i<this.rows;i++) {
			for(int j = 0;j<this.cols;j++) {
				this.mat[i][j] = in[i][j];
			}
		}
	}
	
	 Mat MatrixTranspose() {
		Mat temp = new Mat(this.mat[0].length, this.mat.length);
        for (int i = 0; i < this.mat.length; i++)
            for (int j = 0; j < this.mat[0].length; j++)
                temp.mat[j][i] = this.mat[i][j];
        return temp;
	}
}