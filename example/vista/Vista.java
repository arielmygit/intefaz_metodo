package org.example.vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame {

    private JLabel lblMini;
    private JLabel lblvariable;
    private JLabel lblTitulo;

    private JTextField txtMini;
    private JTextField txtVariable;
    private JButton btnTabla;
    private JButton btnSolucion;
    private JPanel panel1;
    private JPanel panel2;
    private GridLayout layout;
    private JTable tabla;
    private JScrollPane scroll;
    private JLabel lblInstruccion;
    private JLabel lblInstruccion1;
    private JLabel lblInstruccion2;
    private JLabel lblSolucion;

    private Font fnt;


    public Vista(String title) throws HeadlessException {
        this.setVisible(true);
        this.setSize(1000, 600);
        layout = new GridLayout(1, 2);
        this.getContentPane().setLayout(layout);
        fnt = new Font("Arial", Font.BOLD, 14);


        // Panel 1
        panel1 = new JPanel(new FlowLayout());
        this.getContentPane().add(panel1, 0);
        panel1.setBackground(new Color(93, 173, 226));

        lblTitulo = new JLabel("Calculadora metodo Quine-McCluskey");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panel1.add(lblTitulo);

        lblInstruccion = new JLabel("Instrucciones:");
        panel1.add(lblInstruccion);
        panel1.add(Box.createRigidArea(new Dimension(50, 5)));
        lblInstruccion.setFont(fnt);

        lblInstruccion1 = new JLabel("- Ingresa los miniterminos de la funcion separados por comas, Ej: 2,5,7,8,10,...");
        panel1.add(lblInstruccion1);
        lblInstruccion1.setFont(fnt);

        lblInstruccion2 = new JLabel("- Ingresa las variables de tu funcion separadas por comas, Ej: a,b,c,d,...");
        panel1.add(lblInstruccion2);
        panel1.add(Box.createRigidArea(new Dimension(25, 5)));
        lblInstruccion2.setFont(fnt);


        lblMini = new JLabel("Miniterminos");
        lblMini.setFont(fnt);
        panel1.add(lblMini);
        txtMini = new JTextField(10);
        panel1.add(txtMini);

        lblvariable = new JLabel("Variables");
        lblvariable.setFont(fnt);
        panel1.add(lblvariable);
        txtVariable = new JTextField(10);
        panel1.add(txtVariable);

        btnTabla = new JButton("Mostar tabla");
        panel1.add(btnTabla);

        btnSolucion = new JButton("Solucionar");
        //panel1.add(btnSolucion);

        //Panel 2
        panel2 = new JPanel(new FlowLayout());
        this.getContentPane().add(panel2, 1);
        panel2.setBackground(new Color(174, 214, 241));
        tabla = new JTable();
        scroll = new JScrollPane(tabla);
        lblSolucion = new JLabel("Solucion: ");
        lblSolucion.setFont(fnt);
        //panel2.add(lblSolucion);

        btnTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTabla();

            }
        });

        btnSolucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularFuncion();

            }
        });



        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    private void mostrarTabla() {
        String variablesInput = txtVariable.getText();
        String miniterminosInput = txtMini.getText();


        String[] variables = variablesInput.split(",");
        int numVariables = variables.length;


        int numFilas = (int) Math.pow(2, numVariables);


        String[] columnas = new String[numVariables + 2];
        columnas[0] = "No. de Fila";
        System.arraycopy(variables, 0, columnas, 1, numVariables);
        columnas[numVariables + 1] = "F";


        Object[][] data = new Object[numFilas][numVariables + 2];
        for (int i = 0; i < numFilas; i++) {
            data[i][0] = i;


            String binario = String.format("%" + numVariables + "s", Integer.toBinaryString(i)).replace(' ', '0');
            for (int j = 0; j < numVariables; j++) {
                data[i][j + 1] = binario.charAt(j); //
            }

            data[i][numVariables + 1] = 0;
        }

        if (!miniterminosInput.isEmpty()) {
            String[] miniterminos = miniterminosInput.split(",");
            for (String minitermino : miniterminos) {
                int indice = Integer.parseInt(minitermino.trim());
                if (indice < numFilas) {
                    data[indice][numVariables + 1] = 1; //
                }
            }
        }


        DefaultTableModel model = new DefaultTableModel(data, columnas);
        tabla.setModel(model);


        panel2.removeAll();
        scroll = new JScrollPane(tabla);
        panel2.add(scroll);
        panel2.revalidate();
        panel2.repaint();

        panel1.add(btnSolucion);
    }

    private void calcularFuncion(){
        panel2.add(lblSolucion);

        panel2.revalidate();
        panel2.repaint();
    }

}
