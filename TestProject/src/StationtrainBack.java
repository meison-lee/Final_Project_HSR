import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StationtrainBack {

	private JFrame frame;
	private String Date,Start,End,Time,Type;
	private String BDate ,BTime,BTrainNo;
	private String [] ticketArray = new String [5];
	private JTable table;
	private JTable table_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel1;
	private JLabel lblNewLabel1_1;
	private JTable table_2;
	private JScrollPane scrollPane_2;
	private int num1,num2;
	private String selected = "";
	private String[][] selectArray = new String [2][7];
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StationtrainBack window = new StationtrainBack();
//					window.frame.setVisible(true);
//					
//				} catch (Exception e) {
//					
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public StationtrainBack() {
		
		//initialize();
		
	}
	public void BackTime(String Date , String Time ,String Start , String End , String type , String[] array,String bDate , String bTime ) {
		this.Date = Date;
		this.End = End;
		this.Time = Time;
		this.Start = Start;
		this.Type = type;
		for (int i = 0; i < ticketArray.length; i++) {
			ticketArray[i] = array[i];
		}
		BDate = bDate;
		BTime = bTime;
		initialize();
	}
	public void BackTrain(String Date , String Time ,String Start , String End , String type , String[] array,String bDate , String TrainNo) {
		this.Date = Date;
		this.End = End;
		this.Time = Time;
		this.Start = Start;
		this.Type = type;
		for (int i = 0; i < ticketArray.length; i++) {
			ticketArray[i] = array[i];
		}
		BDate = bDate;
		BTrainNo = TrainNo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] columns2 = new String[] {
	            "行程","日期","車次","起程站","到達站","出發時間","到達時間"//定位明細
	        };
		String[] columns = new String[] {
	            "車次","早鳥優惠","大學生優惠","出發時間","抵達時間"//去程、回程
	        };
		Object[][] Gdata = new Object[][] {
	        {"817","75折","","12:30","15:21"},
	        {"300","9折","8折","09:17","11:20"}//去程
		};
		Object[][] Bdata = new Object[][] {
	        {"517","75折","3折","11:30","12:37"},
	        {"450","9折","5折","10:38","12:46"}//回程
		};
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("標楷體", Font.BOLD | Font.ITALIC, 30));
		frame.setBounds(100, 100, 724, 906);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("\u55E8");
		lblNewLabel.setFont(new Font("標楷體", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(35, 37, 525, 50);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setText("去程 :"+Start+"-"+End+" "+Date);
		
		lblNewLabel1 = new JLabel("\u55E8");
		lblNewLabel1.setFont(new Font("標楷體", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel1.setBounds(35, 289, 525, 50);
		frame.getContentPane().add(lblNewLabel1);
		lblNewLabel1.setText("回程 :"+End+"-"+Start+" "+BDate);
	    
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 99, 485, 178);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				num1 = table.getSelectedRow();
		
				selectArray[0][0] = "去程";
				selectArray[0][1] = Date;
				selectArray[0][2] = table.getValueAt(num1, 0).toString();
				selectArray[0][3] = Start;
				selectArray[0][4] = End;
				selectArray[0][5] = table.getValueAt(num1, 3).toString();
				selectArray[0][6] = table.getValueAt(num1, 4).toString();
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(75, 351, 485, 178);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				num2 = table_1.getSelectedRow();
				
				selectArray[1][0] = "去程";
				selectArray[1][1] = Date;
				selectArray[1][2] = table_1.getValueAt(num2, 0).toString();
				selectArray[1][3] = Start;
				selectArray[1][4] = End;
				selectArray[1][5] = table_1.getValueAt(num2, 3).toString();
				selectArray[1][6] = table_1.getValueAt(num2, 4).toString();
			}
		});
		scrollPane_1.setViewportView(table_1);
		frame.setVisible(true);
		
		DefaultTableModel dm1 = new DefaultTableModel();
        dm1.setColumnIdentifiers(columns);  
        dm1.setDataVector(Gdata, columns);
        table.setModel(dm1);
        
		DefaultTableModel dm2 = new DefaultTableModel();
        dm2.setColumnIdentifiers(columns);
        dm2.setDataVector(Bdata, columns);
        table_1.setModel(dm2);
        
        lblNewLabel1_1 = new JLabel("\u8A02\u7968\u660E\u7D30");
        lblNewLabel1_1.setFont(new Font("標楷體", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel1_1.setBounds(35, 586, 525, 50);
        frame.getContentPane().add(lblNewLabel1_1);
        
        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(75, 648, 572, 55);
        frame.getContentPane().add(scrollPane_2);
        
        table_2 = new JTable();
        scrollPane_2.setViewportView(table_2);
        
        DefaultTableModel dm3 = new DefaultTableModel();
        dm3.setColumnIdentifiers(columns2);
        //dm3.setDataVector(Bdata, columns);
        table_2.setModel(dm3);
        
        JButton btnNewButton = new JButton("Selected");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dm3.setDataVector(selectArray, columns2);
        		String var = "";
        		if (Integer.parseInt(ticketArray[0]) != 0) {
        			var +=  "全票"+Integer.parseInt(ticketArray[0])+"張"+" | ";
        			
        		}
        		if (Integer.parseInt(ticketArray[1]) != 0) {
        			var += "孩童票"+Integer.parseInt(ticketArray[1])+"張"+" | ";
        		
        		}
        		if (Integer.parseInt(ticketArray[2]) != 0) {
        			var += "愛心票"+Integer.parseInt(ticketArray[2])+"張"+" | ";
        			
        		}
        		if (Integer.parseInt(ticketArray[3]) != 0) {
        			var += "敬老票"+Integer.parseInt(ticketArray[3])+"張"+" | ";
        			
        		}
        		if (Integer.parseInt(ticketArray[4]) != 0) {
        			var += "大學生優惠票"+Integer.parseInt(ticketArray[4])+"張"+" | ";
        			
        		}
        		textField.setText("車廂 : "+Type);
        		textField_1.setText("票數 : "+var);
        	}
        });
        btnNewButton.setFont(new Font("新細明體", Font.BOLD, 20));
        btnNewButton.setBounds(544, 541, 103, 33);
        frame.getContentPane().add(btnNewButton);
        
        textField = new JTextField();
        textField.setBounds(75, 702, 116, 24);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(190, 702, 457, 24);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        btnNewButton_1 = new JButton("\u78BA\u8A8D\u8ECA\u6B21");
        btnNewButton_1.setForeground(Color.DARK_GRAY);
        btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 20));
        btnNewButton_1.setBounds(514, 754, 132, 39);
        frame.getContentPane().add(btnNewButton_1);
        btnNewButton_1.setBackground(Color.orange);
        btnNewButton_1.setOpaque(true);
        
        btnNewButton_2 = new JButton("\u91CD\u65B0\u67E5\u8A62");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setVisible(false);
        	}
        });
        btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 20));
        btnNewButton_2.setBounds(75, 754, 132, 39);
        frame.getContentPane().add(btnNewButton_2);
        
        
        
        
        
        
	}
}
