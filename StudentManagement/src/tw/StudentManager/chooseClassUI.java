package tw.StudentManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class chooseClassUI extends JFrame {
	public chooseClassUI(final String username) throws Exception {
		this.setTitle("选课");
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocation(400, 150);
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 2));
		// private Vector ColumnNames={"授课号","课程号","课程名","学分","老师","容量","已选人数"};
		this.ColumnNames.add("授课号");
		this.ColumnNames.add("课程号");
		this.ColumnNames.add("课程名");
		this.ColumnNames.add("学分");
		this.ColumnNames.add("老师");
		this.ColumnNames.add("容量");
		this.ColumnNames.add("已选人数");
		this.username = username;
		setCreditLabel();
		getIsPane();
		getCanPane();
		this.add(canChoose);
		System.out.println("whu nto");
		// this.setCreditLabel(isChoosedTable);
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		this.add(left);
		this.add(right);
		left.add(canPane, BorderLayout.CENTER);
		JPanel left_NORTH = new JPanel();
		left.add(left_NORTH, BorderLayout.NORTH);
		left_NORTH.add(canChoose);
		left_NORTH.add(choose);
		JPanel right_NORTH = new JPanel();
		right.add(right_NORTH, BorderLayout.NORTH);
		right.add(isPane, BorderLayout.CENTER);
		right_NORTH.add(creditLabel);
		right_NORTH.add(drop);
		/*
		 * this.add(canChoose,new
		 * GBC(0,0).setAnchor(GBC.CENTER).setWeight(0,0));
		 * this.add(isChoosed,new
		 * GBC(3,0).setAnchor(GBC.CENTER).setWeight(0,0)); this.add(canPane,new
		 * GBC(1,0).setAnchor(GBC.CENTER).setWeight(0,0)); this.add(isPane,new
		 * GBC(1,4).setAnchor(GBC.CENTER).setWeight(0,0)); this.add(choose,new
		 * GBC(6,0).setAnchor(GBC.CENTER).setWeight(0,0)); this.add(drop,new
		 * GBC(3,7).setAnchor(GBC.CENTER).setWeight(0,0));
		 * this.add(creditLabel,new
		 * GBC(4,7).setAnchor(GBC.CENTER).setWeight(0,0)); this.add(exit,new
		 * GBC(5,7).setAnchor(GBC.CENTER).setWeight(0,0));
		 */
		
		//我是选课，
		choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] n = canChooseTable.getSelectedRows();
				System.out.println(n[0] + "");
				Object tcno = canChooseTable.getValueAt(n[0], 0);
				Object s= canChooseTable.getValueAt(n[0], 6);
				int isChoosenNum=Integer.parseInt(s.toString());
				isChoosenNum=isChoosenNum+1;
				//该门课的选课人数加一
				try{
					ps=conn.prepareStatement(sql_update_IsChoosedNum);
					ps.setString(1, isChoosenNum+"");
					ps.setString(2, tcno+"");
					ps.execute();
				}catch(SQLException e1){
					e1.printStackTrace();
				}
				Vector rowData = new Vector();
				int i=0;
				for ( i = 0; i < 6; i++) {
					rowData.add(canChooseTable.getValueAt(n[0], i));
				}
				rowData.add(isChoosenNum);
				
				canDataModel.removeRow(n[0]);
				int lineNum = isDataModel.getRowCount();
				isDataModel.insertRow(lineNum, rowData);
				System.out.println(tcno);
				// 将sno=username,tcno,插入到sc表
				try {
					ps = conn
							.prepareStatement("insert into sc(sno,tcno) values("
									+ username + "," + tcno + ")");
					ps.execute();
					// chooseClassUI.this.repaint();
				} catch (Exception e1) {
					// e1.printStackTrace();
					System.out.println("选择为空");
				} finally {
					setCreditLabel();
				}
				
				// 你们四个没用的东西
				// chooseClassUI.this.repaint();
				// canChooseTable.updateUI();
				// canChooseTable.addNotify();
				// canChooseTable.revalidate();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// 我是退课，一门课退了，就自动把它加到可选列表中去
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] n = isChoosedTable.getSelectedRows();
				System.out.println(n[0] + "");
				Object tcno = isChoosedTable.getValueAt(n[0], 0);
				Object s= isChoosedTable.getValueAt(n[0], 6);
				int isChoosenNum=Integer.parseInt(s.toString());
				isChoosenNum=isChoosenNum-1;
				try{
					ps=conn.prepareStatement(sql_update_IsChoosedNum);
					ps.setString(1, isChoosenNum+"");
					ps.setString(2, tcno+"");
					ps.execute();
				}catch(SQLException e1){
					e1.printStackTrace();
				}
				Vector rowData = new Vector();
				int i=0;
				for ( i = 0; i < 6; i++) {
					rowData.add(isChoosedTable.getValueAt(n[0], i));
				}
				rowData.add(isChoosenNum);
				
				isDataModel.removeRow(n[0]);
				int lineNum = canDataModel.getRowCount();
				canDataModel.insertRow(lineNum, rowData);
				System.out.println(tcno);
				// 将sno=username,tcno,插入到sc表
				try {
					ps = conn.prepareStatement("delete from sc where sno= "
							+ username + " and tcno= " + tcno);
					ps.execute();
					// chooseClassUI.this.repaint();
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					setCreditLabel();
				}
				
			}
		});

	}

	// 获得当前已修的学分
	public void setCreditLabel() {
		try {
			ps = conn.prepareStatement(sql_getCredit);
			ps.setString(1, username);
			rs = ps.executeQuery();
			System.out.println("当前学期尾:"+MainUI.CURRENT_TERM);
			if (rs.next())
				creditLabel.setText("已经选修的学分为： " + rs.getInt(1) + "");
			else
				creditLabel.setText("已经选修的学分为： " + 0 + "");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// 设置已选课程
	public JScrollPane getIsPane() throws Exception {
		ps = conn.prepareStatement(search_isChoosedClass);
		ps.setInt(1, MainUI.CURRENT_TERM);
		ps.setString(2, username);
		rs = ps.executeQuery();
		isDataModel=new DefaultTableModel(){
			public   boolean   isCellEditable(int   row,int   column){ 
		        return   false; 
		    } 
		};
		isDataModel.setColumnIdentifiers(ColumnNames);
		isChoosedTable = new JTable(isDataModel);
		isChoosedTable.getColumnModel().getColumn(2).setPreferredWidth(140);
		isChoosedTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		isChoosedTable.setAutoCreateRowSorter(true);
		isChoosedTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		while (rs.next()) {
			Vector values = new Vector();
			System.out.println("" + rs.getInt(1));
			values.add(rs.getObject(1));
			values.add(rs.getObject(2));
			values.add(rs.getObject(3));
			values.add(rs.getObject(4));
			values.add(rs.getObject(5));
			values.add(rs.getObject(6));
			values.add(rs.getObject(7));
			isDataModel.addRow(values);
		}
		return isPane = new JScrollPane(isChoosedTable);
	}

	// 可选课程
	public JScrollPane getCanPane() throws Exception {
		ps = conn.prepareStatement(search_canChooseClass);
		ps.setString(1, username);
		rs = ps.executeQuery();
		canDataModel=new DefaultTableModel(){
			public   boolean   isCellEditable(int   row,int   column){ 
		        return   false; 
		    } 
		};
		canDataModel.setColumnIdentifiers(ColumnNames);
		canChooseTable = new JTable(canDataModel);
		canChooseTable.getColumnModel().getColumn(2).setPreferredWidth(140);
		canChooseTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		canChooseTable.setAutoCreateRowSorter(true);
		canChooseTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		while (rs.next()) {
			Vector values = new Vector();
			System.out.println("" + rs.getInt(1));
			values.add(rs.getObject(1));
			values.add(rs.getObject(2));
			values.add(rs.getObject(3));
			values.add(rs.getObject(4));
			values.add(rs.getObject(5));
			values.add(rs.getObject(6));
			values.add(rs.getObject(7));
			canDataModel.addRow(values);
		}
		return canPane = new JScrollPane(canChooseTable);
	}
	

	private DefaultTableModel canDataModel;
	private DefaultTableModel isDataModel;
	private Connection conn = DBConnection.getInstance();
	private PreparedStatement ps;
	private ResultSet rs;
	private String username;
	private String search_canChooseClass = "select Y.tcno,Y.cno,cname,credit,tname,max,is_choosen "
			 								+"from class,teacher inner join tc as Y on Y.tno=teacher.tno "
			 								+" where  class.term= "+MainUI.CURRENT_TERM
			 								+" and Y.cno=class.cno and  Y.cno not in ( "
			 								+" select distinct X.cno from sc,tc as X "
			 								+" where sc.tcno=X.tcno and sc.sno=? ) ";
			 
	private String search_isChoosedClass = " select tc.tcno,tc.cno,cname,credit,tname,max,is_choosen "
			+ " from tc,class,teacher,sc "
			+ " where tc.cno=class.cno "
			+ " and tc.tcno=sc.tcno "
			+ " and tc.tno=teacher.tno "
			+ " and term=? " + " and sno=?";
	private String sql_getCredit = "select sum(credit) " + " from sc,tc,class "
			+ " where sc.tcno=tc.tcno " + "and tc.cno=class.cno "
			+ " and class.term = "+MainUI.CURRENT_TERM
			+ " and sc.sno=? ";
	private String delete_class = "delete from sc where sno=? and tcno=?";
	// private Vector ColumnNames={"授课号","课程号","课程名","学分","老师","容量","已选人数"};
	private String sql_update_IsChoosedNum="update tc "
										+" set  tc.is_choosen=? "
										+"  where tcno=? "; 
	private Vector ColumnNames = new Vector();

	private JLabel canChoose = new JLabel("可选课程       ");
	private JLabel isChoosed = new JLabel("已选课程        ");
	// 如果一门课被选了，那么其他相同的课程就不应该显示出来了，对不对
	private JTable canChooseTable;
	private JTable isChoosedTable;
	private JScrollPane canPane;
	private JScrollPane isPane;
	private JButton choose = new JButton("选课");
	private JButton drop = new JButton("退课");
	private JButton exit = new JButton("退出");
	private JLabel creditLabel = new JLabel();
	private int credit;

	public static void main(String[] ages) {
		JFrame f = null;
		try {
			f = new chooseClassUI("1001");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
