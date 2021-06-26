import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowSearching {

	private JFrame frame;
	private JTextField txtA;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowSearching window = new WindowSearching();
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
	public WindowSearching() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 259);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("HSR \u67E5\u8A62\u7CFB\u7D71");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("標楷體", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(12, 12, 257, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UID         :");
		lblNewLabel_1.setFont(new Font("標楷體", Font.BOLD, 20));
		lblNewLabel_1.setBounds(49, 72, 152, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ticket Code :");
		lblNewLabel_1_1.setFont(new Font("標楷體", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(49, 115, 152, 31);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtA = new JTextField();
		txtA.setText("A123456789");
		txtA.setBounds(199, 76, 197, 24);
		frame.getContentPane().add(txtA);
		txtA.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("123456789");
		textField_1.setColumns(10);
		textField_1.setBounds(199, 115, 197, 24);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("\u958B\u59CB\u67E5\u8A62");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchOrder s = new SearchOrder();
				
				WindowSearchResult w = new WindowSearchResult();
				w.var(s.search(txtA.getText(), textField_1.getText()),txtA.getText(),textField_1.getText(),s.getTicketType(),s.getTicketCount(),s.getPrice());
				
			}
		});
		btnNewButton.setBackground(new Color(230,230,230));
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 20));
		btnNewButton.setBounds(299, 179, 125, 31);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBackground(new Color(230,230,230));
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		btnNewButton_1.setBounds(12, 179, 113, 31);
		frame.getContentPane().add(btnNewButton_1);
	}

}
