/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package syntactic;

import java.io.StringReader;

import helpers.Utils;
import java_cup.runtime.Symbol;

/**
 *
 * @author victor
 */
public class Editor extends javax.swing.JFrame {

    /**
     * Creates new form Editor
     */
    public Editor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jsonOut = new javax.swing.JTextArea();
        labelXMLEntry = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        xmlEntry = new javax.swing.JTextArea();
        labelJSONOut = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        consoleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("XML to JSON compiler");
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setSize(new java.awt.Dimension(700, 500));

        jsonOut.setEditable(false);
        jsonOut.setColumns(20);
        jsonOut.setRows(5);
        jsonOut.setTabSize(2);
        jScrollPane1.setViewportView(jsonOut);

        labelXMLEntry.setLabelFor(jsonOut);
        labelXMLEntry.setText("Entrada (XML)");

        xmlEntry.setColumns(20);
        xmlEntry.setRows(5);
        xmlEntry.setTabSize(2);
        xmlEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                xmlEntryKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(xmlEntry);

        labelJSONOut.setLabelFor(jsonOut);
        labelJSONOut.setText("Salida (JSON)");

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        console.setTabSize(2);
        jScrollPane3.setViewportView(console);

        consoleLabel.setText("Consola");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(labelXMLEntry))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jScrollPane2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 325,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38,
                                                        Short.MAX_VALUE)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 325,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelJSONOut)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                layout.createSequentialGroup()
                                                                        .addComponent(consoleLabel)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane3)
                                                        .addComponent(jSeparator1))))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelXMLEntry)
                                        .addComponent(labelJSONOut))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 338,
                                                Short.MAX_VALUE)
                                        .addComponent(jScrollPane1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(consoleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addContainerGap()));

        pack();
    }// </editor-fold>

    private void xmlEntryKeyReleased(java.awt.event.KeyEvent evt) {
        String content = xmlEntry.getText();

        if (content.isEmpty()) {
            jsonOut.setText("");
            console.setText("");
        } else {
            Syntax syntax = new Syntax(new LexerCup(new StringReader(content)));

            try {
                syntax.parse();
                String jsonOutput = Utils.toJson(syntax.action_obj.variables);
                jsonOut.setText(jsonOutput);
                console.setText("Código ejecutado satisfactoriamente");
            } catch (Exception e) {
                e.printStackTrace();
                Symbol sym = syntax.getS();
                jsonOut.setText("Entrada XML no válida");

                if (sym != null)
                    console.setText("Error de sintáxis en la linea " + (sym.right + 1) + " columna " + (sym.left + 1)
                            + "; Error de sintáxis: " + sym.value);
                else
                    System.out.println(e);
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JTextArea console;
    private javax.swing.JLabel consoleLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jsonOut;
    private javax.swing.JLabel labelJSONOut;
    private javax.swing.JLabel labelXMLEntry;
    private javax.swing.JTextArea xmlEntry;
    // End of variables declaration
}
