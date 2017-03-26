package org.androidopenblas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import static org.bytedeco.javacpp.openblas.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExampleDGELSrowmajor ex = new ExampleDGELSrowmajor();
        ex.runExample();
    }
}


class ExampleDGELSrowmajor {

    static void print_matrix_rowmajor(String desc, int m, int n, double[] mat, int ldm) {
        int i, j;

        Log.d("openblastest", "\n " + desc + "\n");

        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) System.out.printf(" %6.2f", mat[i*ldm+j]);
            System.out.printf("\n");
        }
    }

    static void print_matrix_colmajor(String desc, int m, int n, double[] mat, int ldm) {
        int i, j;

        Log.d("openblastest", "\n " + desc + "\n");

        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) System.out.printf(" %6.2f", mat[i+j*ldm]);
            System.out.printf("\n");
        }
    }

    static void print_vector(String desc, int n, int[] vec) {
        int j;

        Log.d("openblastest", "\n " + desc + "\n");

        for (j = 0; j < n; j++) System.out.printf(" %6i", vec[j]);
        System.out.printf("\n");
    }

    public void runExample() {
        blas_set_num_threads(4);
        Log.d("openblastest", "vendor = " + blas_get_vendor() + ", num_threads = " + blas_get_num_threads());

        /* Locals */
        double[] A = {1, 1, 1, 2, 3, 4, 3, 5, 2, 4, 2, 5, 5, 4, 3};
        double[] b = {-10, -3, 12, 14, 14, 12, 16, 16, 18, 16};
        int info, m, n, lda, ldb, nrhs;
        int i, j;

        /* Initialization */
        m = 5;
        n = 3;
        nrhs = 2;
        lda = 3;
        ldb = 2;

        print_matrix_rowmajor("Entry Matrix A", m, n, A, lda);
        print_matrix_rowmajor("Right Hand Side b", n, nrhs, b, ldb);

        Log.d("openblastest", "LAPACKE_dgels (row-major, high-level) Example Program Results");


        info = LAPACKE_dgels(LAPACK_ROW_MAJOR, (byte)'N', m, n, nrhs, A, lda, b, ldb);

        print_matrix_rowmajor("Solution", n, nrhs, b, ldb);
    }
}