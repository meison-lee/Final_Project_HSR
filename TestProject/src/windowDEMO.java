import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class windowDEMO {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowDEMO window = new windowDEMO();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public windowDEMO() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setAlwaysOnTop(true);
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 741, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.setEditable(true);
		comboBox1.setFont(new Font("新細明體", Font.PLAIN, 20));
		comboBox1.setBounds(227, 36, 125, 51);
		frame.getContentPane().add(comboBox1);
		comboBox1.addItem("訂票");
		comboBox1.addItem("查詢");
		comboBox1.addItem("退票");
		comboBox1.setSelectedItem("請選擇");
		
		JPanel panel = new JPanel();
		panel.setBounds(62, 97, 308, 206);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setBounds(453, 142, 75, 42);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox1.getSelectedIndex() == 0) {
					WindowBooking a = new WindowBooking();
					
				}
				if (comboBox1.getSelectedIndex() == 1) {
					
				}
				if (comboBox1.getSelectedIndex() == 2) {
					Refunding refundClass = new Refunding();
					
					JFrame refund = new JFrame();
					refund.setAlwaysOnTop(true);
					refund.getContentPane().setEnabled(false);
					refund.setBounds(100, 100, 741, 386);
					refund.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					refund.getContentPane().setLayout(null);
					refund.setVisible(true);
					
					JTextField textField = new JTextField();
					textField.setBounds(147, 61, 204, 41);
					refund.getContentPane().add(textField);
					textField.setColumns(10);
					
					JLabel lblNewLabel = new JLabel("UID :");
					lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
					lblNewLabel.setBounds(24, 60, 62, 41);
					refund.getContentPane().add(lblNewLabel);
					
					JLabel lblTicketCode = new JLabel("Ticket Code :");
					lblTicketCode.setFont(new Font("新細明體", Font.PLAIN, 20));
					lblTicketCode.setBounds(24, 151, 113, 41);
					refund.getContentPane().add(lblTicketCode);
					
					JTextField textField_1 = new JTextField();
					textField_1.setColumns(10);
					textField_1.setBounds(147, 151, 204, 41);
					refund.getContentPane().add(textField_1);
					
					JLabel lblTicketCode_1 = new JLabel("\u522A\u6E1B\u4EBA\u6578 :");
					lblTicketCode_1.setFont(new Font("新細明體", Font.PLAIN, 20));
					lblTicketCode_1.setBounds(24, 249, 113, 41);
					refund.getContentPane().add(lblTicketCode_1);
					
					JTextField textField_2 = new JTextField();
					textField_2.setColumns(10);
					textField_2.setBounds(147, 249, 204, 41);
					refund.getContentPane().add(textField_2);
					
					JButton btnNewButton = new JButton("OK");
					btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 20));
					btnNewButton.setBounds(503, 249, 113, 41);
					refund.getContentPane().add(btnNewButton);

					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
								JFrame output = new JFrame();
								output.setAlwaysOnTop(true);
								output.setBounds(100, 100, 352, 201);
								output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								output.getContentPane().setLayout(null);
								output.setVisible(true);
								
								JTextField refundOutput = new JTextField();
								refundOutput.setBounds(10, 10, 305, 95);
								output.getContentPane().add(refundOutput);
								refundOutput.setColumns(10);
								
								try {
									refundOutput.setText(refundClass.Refund(textField.getText(), textField_1.getText(), Integer.parseInt(textField_2.getText())));
								} catch (NumberFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JButton ok = new JButton("OK");
								ok.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										output.setVisible(false);
									}
								});
								ok.setFont(new Font("新細明體", Font.PLAIN, 20));
								ok.setBounds(230, 115, 85, 39);
								output.getContentPane().add(ok);
								//System.out.println(refundClass.Refund(textField.getText(), textField_1.getText(), Integer.parseInt(textField_2.getText())));
							
						}
					});
				}
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		
	}
}
