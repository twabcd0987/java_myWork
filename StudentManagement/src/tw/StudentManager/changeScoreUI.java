package tw.StudentManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class changeScoreUI extends JFrame {
	public changeScoreUI(String username) {
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocation(400, 150);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		chooseClassLabel = new JLabel("选择课程");
		northPanel.add(chooseClassLabel);
		classes = new JComboBox();
		try {
			ps = conn.prepareStatement(sql_combo);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				classes.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		northPanel.add(classes);
		chooseClassButton = new JButton("确认");
		northPanel.add(chooseClassButton);
		//设置列名
		columnNames = new Vector<String>();
		columnNames.add("授课号");
		columnNames.add("课程号");
		columnNames.add("课程名");
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("平时成绩");
		columnNames.add("期末成绩");
		model = new DefaultTableModel();
		 model.setColumnIdentifiers(columnNames);
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		pane=new JScrollPane(table);
		this.add(pane, BorderLayout.CENTER);
		this.add(northPanel, BorderLayout.NORTH);
		chooseClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object item = classes.getSelectedItem();
				System.out.println(model.getRowCount());
				int num=model.getRowCount();
				if(model.getRowCount()>0)
					//这里有个问题：不能从j=0开始遍历，因为每次删除一行之后，行数都会-1，即行数是会变化的；
					//解决1：遍历model.getRowCount()次，但每次都是删除第0行。
					//解决2：如下行注释，每次都高出开始遍历，每次都删除最后一行；
					//for(int   j=table.getRowCount()-1;j>=0;j--   )
					for(int   j=0;j<num;j++   ) 
							{model.removeRow(0);System.out.println(table.getRowCount());}
					
					// table初始化
				try {
					ps = conn.prepareStatement(sql_showTable);
					ps.setString(1, (String) item);
					rs = ps.executeQuery();
					System.out.println(item);
					while (rs.next()) {
						Vector<Object> values = new Vector<Object>();
						values.add(rs.getObject(1));
						values.add(rs.getObject(2));
						values.add(rs.getObject(3));
						values.add(rs.getObject(4));
						values.add(rs.getObject(5));
						values.add(rs.getObject(6));
						values.add(rs.getObject(7));
						model.addRow(values);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		dengfen = new JButton("登分");
		dengfen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.fireTableRowsUpdated(0, model.getRowCount()-1);
				for(int i=0;i<model.getRowCount();i++){
					String agrade=""+ model.getValueAt(i, 5);
					String fgrade=""+ model.getValueAt(i,6);
					System.out.println("agrade:"+agrade);
					System.out.println("fgrade:"+fgrade);
					String sno= model.getValueAt(i, 3)+"";
					String tcno=""+model.getValueAt(i, 0);
					try{
						ps=conn.prepareStatement(sql_agrade);
						ps.setString(1, agrade+"");
						ps.setString(2,sno);
						System.out.println(sno);
						ps.setString(3, tcno);
						System.out.println(tcno);
						if(ps.execute())
							System .out.println("登分成功");
							
						ps=conn.prepareStatement(sql_fgrade);
						ps.setString(1, fgrade+"");
						ps.setString(2,sno);
						ps.setString(3, tcno);
						if(ps.execute())
							System .out.println("登分成功");
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}
			}
		});
		JPanel southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(dengfen);
		exit=new JButton("退出");
		southPanel.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeScoreUI.this.dispose();
			}
		});
	}
	//从数据库中找出tno老师教的课
	private String sql_combo = "select cname "
			+ " from class inner join tc on tc.cno=class.cno  "
			+ " where tno= ? and class.term="+MainUI.CURRENT_TERM;
	//从数据库中检索学习该课程学生的详细信息
	private String sql_showTable = "select  tc.tcno,class.cno,cname,student.sno,sname,agrade,fgrade"
			+ " from class,student,tc,sc "
			+ " where class.cno=tc.cno "
			+ " and sc.tcno=tc.tcno "
			+ " and sc.sno=student.sno "
			+ " and class.cname=?";
	//更新agrade
	private String sql_agrade="update sc set agrade=? where sno=? and tcno=?";
	//更新fgrade；
	private String sql_fgrade="update sc set fgrade=? where sno=? and tcno=?";
	//表格列名
	private Vector<String> columnNames;
	private JLabel chooseClassLabel;
	private JComboBox classes;
	private JButton chooseClassButton;
	private DefaultTableModel model;
	private JTable table;
	private JButton dengfen;
	private Connection conn = DBConnection.getInstance();
	private PreparedStatement ps;
	private ResultSet rs;
	private JScrollPane pane;
	private JButton exit;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				JFrame f = new changeScoreUI("2001");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		});
	}
}
