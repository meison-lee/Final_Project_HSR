import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class stationtrainNo {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JTextField textField_2;
	private String selected = "";
	private JButton btnNewButton;
	private int num;
	private String Date,Start,End,Time,Type,TrainNo;
	private String [] ticketArray = new String [5];
	private JTextField textField3;
	private JTextField textField4;
	private JButton btnNewButton2_1;
	

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					stationtrainNo window = new stationtrainNo();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public stationtrainNo() {
		//initialize();
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inputTrainNo(String Date , String TrainNo ,String Start , String End , String type , String[] array) {
		this.Date = Date;
		this.End = End;
		this.Start = Start;
		this.TrainNo = TrainNo;
		this.Type = type;
		for (int i =0; i < 5; i++) {
			ticketArray[i] = array[i];
		}
		initialize();
	}
	public void inputTime(String Date , String Time ,String Start , String End , String type , String[] array) {
		this.Date = Date;
		this.End = End;
		this.Start = Start;
		this.Time = Time;
		this.Type = type;
		for (int i =0; i < 5; i++) {
			ticketArray[i] = array[i];
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
  
		String[] columns2 = new String[] {
	            "行程","日期","車次","起程站","到達站","出發時間","到達時間"
	        };
		Object[][] zero = new Object[][] {
			{}
		};
		String[] columns = new String[] {
	            "車次","早鳥優惠","大學生優惠","出發時間","抵達時間"
	        };
	         
	        //actual data for the table in a 2d array
	    Object[][] data = new Object[][] {
	        {"817","75折","","12:30","15:21"},
	        {"Button" ,"vm",9},
	        {"Button","bf",6},
	        {"Button","bf",6},
	        {"Button" ,"vm",9},
	        {"Button","bf",6},
	        {"Button","bf",6},
	        {"Button" ,"vm",9},
	        {"Button","bf",6},
	        {"Button","bf",6},
	        {"Button" ,"vm",9},
	        {"Button","bf",6},
	        {"Button","bf",6},
	        {"Button" ,"vm",9},
	        {"Button","bf",6},
	        {"Button","bf",6},
	        {"Button" ,"vm",9},
	        {"Button","bf",6},
	        {"Button","bf",6},
	        {"Button" ,"vm",9},
	        {"Button","bf",6},
	        };
		
		frame = new JFrame();
		frame.getContentPane().setForeground(SystemColor.activeCaption);frame.setAlwaysOnTop(true);
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 776, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(149, 497, 552, 21);
		frame.getContentPane().add(textField4);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 81, 500, 173);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
        DefaultTableModel dm = new DefaultTableModel();
        dm.setColumnIdentifiers(columns);
        table.setModel(dm);
        
        lblNewLabel_1 = new JLabel("\u8A02\u4F4D\u660E\u7D30 :");
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setFont(new Font("標楷體", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel_1.setBounds(33, 401, 159, 48);
        frame.getContentPane().add(lblNewLabel_1);
        
        
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(58, 459, 643, 39);
        frame.getContentPane().add(scrollPane_1);
        
        table_1 = new JTable();
        scrollPane_1.setViewportView(table_1);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(columns2);
        table_1.setModel(dtm);
        
        textField_2 = new JTextField();
        textField_2.setBounds(127, 264, 382, 32);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);
        
        btnNewButton = new JButton("Select");
        btnNewButton.setBackground(new Color(230,230,230));
        btnNewButton.setOpaque(true);
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String [][] selectArray = new String [1][7];
        		selectArray[0][0] = "去程";
        		selectArray[0][1] = Date;
        		selectArray[0][2] = table.getValueAt(num, 0).toString();
        		selectArray[0][3] = Start;
        		selectArray[0][4] = End;
        		selectArray[0][5] = table.getValueAt(num, 3).toString();
        		selectArray[0][6] = table.getValueAt(num, 4).toString();
        		dtm.setDataVector(selectArray, columns2);
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
        		textField4.setText("票數 : "+var);
        		
        		textField3.setText("車廂 : "+Type);
        	}
        });
        btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 20));
        btnNewButton.setBounds(585, 264, 104, 32);
        frame.getContentPane().add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("標楷體", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel.setBounds(33, 23, 656, 48);
        frame.getContentPane().add(lblNewLabel);
        dtm.setDataVector(zero, columns2);
        lblNewLabel.setText("去程 :"+Start+"-"+End+" "+Date);
        
        JButton btnNewButton2 = new JButton("\u78BA\u8A8D\u8ECA\u6B21");
        btnNewButton2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		////////////////////////////////////////////////////選擇了row幾 
        		frame.setAlwaysOnTop(false);
        		windowInputUID W = new windowInputUID();//輸入uid
        	}
        });
        btnNewButton2.setBackground(new Color(230,230,230));
        btnNewButton2.setOpaque(true);
        btnNewButton2.setFont(new Font("新細明體", Font.PLAIN, 20));
        btnNewButton2.setBounds(569, 553, 132, 39);
        frame.getContentPane().add(btnNewButton2);
        
        textField3 = new JTextField();
        textField3.setBounds(58, 497, 93, 21);
        frame.getContentPane().add(textField3);
        textField3.setColumns(10);
        
        btnNewButton2_1 = new JButton("\u91CD\u65B0\u67E5\u8A62");
        btnNewButton2_1.setBackground(new Color(230,230,230));
        btnNewButton2_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setVisible(false);
        	}
        });
        btnNewButton2_1.setOpaque(true);
        btnNewButton2_1.setForeground(Color.DARK_GRAY);
        btnNewButton2_1.setFont(new Font("新細明體", Font.PLAIN, 20));
        btnNewButton2_1.setBounds(58, 553, 132, 39);
        frame.getContentPane().add(btnNewButton2_1);
        
        dm.setDataVector(data, columns);
        table.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				num = table.getSelectedRow();
				for (int i = 0; i < table.getColumnCount(); i++) {
					selected = selected +table.getValueAt(num, i)+" ";
				}
				textField_2.setText(selected);
				selected = "";
				
			}
		});
        
        
		
	}
}
