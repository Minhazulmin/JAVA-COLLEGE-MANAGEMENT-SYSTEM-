package cms_project;




import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MINHAZUL ISLAM MIN
 */
public class main_page extends javax.swing.JFrame {

    /**
     * Creates new form main_page
     */
    public main_page() {
        initComponents();
        setTitle("COLLAGE MANAGEMENT SYSTEM");
        
         main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(welcome);
       main.repaint();
       main.revalidate();
      show_student_table();
      show_Teacher_table();
      show_student_information();
      show_Teacher_information();
      show_student_result();
       show_student_result_panel();
       finduser();
       finduser2();
       finduser3();
    }
    
    public Connection getconnection()
    {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collage_management_system","root","");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
       
    }
    public ArrayList<user> getStudentlist()
    {
        ArrayList<user>  studentlist = new ArrayList<user>();
        Connection connection = getconnection();
        String quary = "SELECT * FROM `student_information`"; 
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(quary);
            user User;
            while(rs.next())
            {
               User = new  user(rs.getInt("student_id"),
                       rs.getString("student_name"),
                        rs.getString("Father_name"),
                        rs.getString("Mother_name"),
                        rs.getString("password"),
                        rs.getInt ("contact"),
                        rs.getInt("age"),
                        rs.getString("Address"),
                         rs.getString("gender"),
                         rs.getString("blood"));
               studentlist.add(User);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  studentlist;
    }
    
    
    //disply student table..........
      public void show_student_table()
    {
        ArrayList<user> list =  getStudentlist();
        DefaultTableModel model = (DefaultTableModel)student_table.getModel();
        Object [] row = new Object[10];
        for(int i = 0; i<list.size();i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getname();
            row[2] = list.get(i).getFname();
            row[3] = list.get(i).getmname();
            row[4] = list.get(i).getpassword();
            row[5] = list.get(i).getcontact();
            row[6] = list.get(i).getage();
            row[7] = list.get(i).getaddress();
            row[8] = list.get(i).getgender();
            row[9] = list.get(i).getblood();
            model.addRow(row);
            
        }
    }
//disply show the data........................
    public void show_student_information()
    {
        ArrayList<user> list =  getStudentlist();
        DefaultTableModel model = (DefaultTableModel)student_information.getModel();
        Object [] row = new Object[15];
        for(int i = 0; i<list.size();i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getname();
            row[2] = list.get(i).getFname();
            row[3] = list.get(i).getmname();
            row[4] = list.get(i).getpassword();
            row[5] = list.get(i).getcontact();
            row[6] = list.get(i).getage();
            row[7] = list.get(i).getaddress();
            row[8] = list.get(i).getgender();
            row[9] = list.get(i).getblood();
            
            model.addRow(row);
            
        }
    }
    public void executeSQlQuary(String query,String Massage)
    {
        Connection con = getconnection();
        Statement stu;
        try {
            stu = con.createStatement();
            if((stu.executeUpdate(query))==1)
            {
                //refresh jtable
                DefaultTableModel model = (DefaultTableModel)student_table.getModel();
                model.setRowCount(0);
                show_student_table();
                
                JOptionPane.showMessageDialog(null, "data"+Massage+"succefully");
                 s_text_id.setText(null);
                  S_text_name.setText(null);
                    F_name.setText(null);
                     mother_name.setText(null);
                   s_text_password.setText(null);
                   s_text_contact.setText(null);
                  s_text_age.setText(null);
                  address.setText(null);
            }else
            {
                JOptionPane.showMessageDialog(null, "data not"+Massage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //search ing 
    public ArrayList<user>ListUsers(String valTosearch)
  {
      ArrayList<user>  studentlist = new ArrayList<user>();
     
      Statement st;
        ResultSet rs;
        try {
             Connection con = getconnection();
             st = con.createStatement();
              String sql= "SELECT * FROM `student_information` WHERE CONCAT(`student_id`,`student_name`,`Father_name`)LIKE'%"+valTosearch+"%'";
              rs  = st.executeQuery(sql);
               user User;
            while(rs.next())
            {
               User = new  user(rs.getInt("student_id"),
                       rs.getString("student_name"),
                        rs.getString("Father_name"),
                        rs.getString("Mother_name"),
                        rs.getString("password"),
                        rs.getInt ("contact"),
                        rs.getInt("age"),
                        rs.getString("Address"),
                         rs.getString("gender"),
                         rs.getString("blood"));
               studentlist.add(User);
            }
              
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
        return studentlist;
  }
    public void finduser()
    {
         ArrayList<user>  user = ListUsers(search.getText());
         DefaultTableModel model = new DefaultTableModel();
         model.setColumnIdentifiers(new Object[]{"student_id","student_name","Father Name","Mother Name","password","contact","Age","Address","gender","Blood"});
       
        
        Object [] row = new Object[15];
        for(int i = 0; i<user.size();i++)
        {
            row[0] = user.get(i).getId();
            row[1] = user.get(i).getname();
            row[2] = user.get(i).getFname();
            row[3] = user.get(i).getmname();
            row[4] = user.get(i).getpassword();
            row[5] = user.get(i).getcontact();
            row[6] = user.get(i).getage();
            row[7] = user.get(i).getaddress();
            row[8] = user.get(i).getgender();
            row[9] = user.get(i).getblood();
            
            model.addRow(row);
            
        }
        student_table.setModel(model);
    }
            
   //teacher part.....................
    public ArrayList<user2> getTeacherlist()
    {
        ArrayList<user2>  Teacherlist = new ArrayList<user2>();
        Connection connection = getconnection();
        String quary2 = "SELECT * FROM `teacher_information`"; 
        Statement te;
        ResultSet rs;
        try {
            te = connection.createStatement();
            rs = te.executeQuery(quary2);
            user2 User2;
            while(rs.next())
            {
               User2 = new  user2(rs.getInt("teacher_id"),
                       rs.getString("teacher_name"),
                       rs.getString("password"),
                       rs.getString("email"),
                       rs.getInt("age"),
                       rs.getInt("contact"),
                       rs.getString("Room"),
                       rs.getString("Gender"));
               Teacherlist.add(User2);
                t_text_id.setText(null);
                  t_text_name.setText(null);
                   
                   t_password.setText(null);
                   t_text_email.setText(null);
                  t_text_age.setText(null);
                  contact_num.setText(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  Teacherlist;
    }
    //disply show the data........................
    public void show_Teacher_table()
    {
        ArrayList<user2> list2 =  getTeacherlist();
        DefaultTableModel model = (DefaultTableModel)teacher_table.getModel();
        Object [] row = new Object[10];
        for(int i = 0; i<list2.size();i++)
        {
            row[0] = list2.get(i).getID();
            row[1] = list2.get(i).getName();
            row[2] = list2.get(i).getpassword();
            row[3] = list2.get(i).getemail();
            row[4] = list2.get(i).getage();
            row[5] = list2.get(i).getpn();
            row[6] = list2.get(i).getroom();
            row[7] = list2.get(i).getgenderrr();
            model.addRow(row);
            
        }
    }
    //display the teacher information............
    
     public void show_Teacher_information()
    {
        ArrayList<user2> list2 =  getTeacherlist();
        DefaultTableModel model = (DefaultTableModel)teacher_information.getModel();
        Object [] row = new Object[15];
        for(int i = 0; i<list2.size();i++)
        {
            row[0] = list2.get(i).getID();
            row[1] = list2.get(i).getName();
            row[2] = list2.get(i).getpassword();
            row[3] = list2.get(i).getemail();
            row[4] = list2.get(i).getage();
            row[5] = list2.get(i).getpn();
            row[6] = list2.get(i).getroom();
            row[7] = list2.get(i).getgenderrr();
            model.addRow(row);
            
        }
    }
     public void executeSQlQuary2(String query2,String Massage2)
    {
        Connection con = getconnection();
        Statement te;
        try {
            te = con.createStatement();
            if((te.executeUpdate(query2))==1)
            {
                //refresh jtable
                DefaultTableModel model = (DefaultTableModel)teacher_table.getModel();
                model.setRowCount(0);
                show_Teacher_table();
                
                JOptionPane.showMessageDialog(null, "Data" +Massage2+ "Succefully");
                 t_text_id.setText(null);
                  t_text_name.setText(null);
                   
                   t_password.setText(null);
                   t_text_email.setText(null);
                   t_text_age.setText(null); 
                   contact_num.setText(null);
                   
            }else
            {
                JOptionPane.showMessageDialog(null, "Data Not"+Massage2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
     
     //searching part
     public ArrayList<user2>ListUsers2(String valTosearch)
  {
      ArrayList<user2>  Teacherlist = new ArrayList<user2>();
     
      Statement st;
        ResultSet rs;
        try {
             Connection con = getconnection();
             st = con.createStatement();
              String sql= "SELECT * FROM `teacher_information` WHERE CONCAT(`teacher_id`,`teacher_name`)LIKE'%"+valTosearch+"%'";
              rs  = st.executeQuery(sql);
               user2 User2;
            while(rs.next())
            {
               User2 = new  user2(rs.getInt("teacher_id"),
                       rs.getString("teacher_name"),
                       rs.getString("password"),
                       rs.getString("email"),
                       rs.getInt("age"),
                       rs.getInt("contact"),
                       rs.getString("Room"),
                       rs.getString("Gender"));
               Teacherlist.add(User2);
                t_text_id.setText(null);
                  t_text_name.setText(null);
                   t_password.setText(null);
                   t_text_email.setText(null);
                  t_text_age.setText(null);
                  contact_num.setText(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  Teacherlist;
  }

    public void finduser2()
    {
         ArrayList<user2>  user = ListUsers2(T_search.getText());
         DefaultTableModel model = new DefaultTableModel();
         model.setColumnIdentifiers(new Object[]    {"teacher_id","teacher_name","password","password","email","age","contact","Room","Gender"});
       
        
        Object [] row = new Object[15];
        for(int i = 0; i<user.size();i++)
        {
            row[0] = user.get(i).getID();
            row[1] = user.get(i).getName();
            row[2] = user.get(i).getpassword();
            row[3] = user.get(i).getemail();
            row[4] = user.get(i).getage();
            row[5] = user.get(i).getpn();
            row[6] = user.get(i).getroom();
            row[7] = user.get(i).getgenderrr();
            model.addRow(row);
            
        }
        teacher_table.setModel(model);
    }
            
    //result part ...................
      public void executeSQlQuary3(String query3,String Massage3)
    {
        Connection con = getconnection();
        Statement te;
        try {
            te = con.createStatement();
            if((te.executeUpdate(query3))==1)
            {
                //refresh jtable
                DefaultTableModel model = (DefaultTableModel)student_result1.getModel();
                
                model.setRowCount(0);
                show_student_result_panel();
               
                
                JOptionPane.showMessageDialog(null, "Data" +Massage3+ "Succefully");
                 e_st_text_name.setText(null);
                  e_st_text_id.setText(null);
                   e_st_text_first_term.setText(null);
                   e_st_text_mid_term.setText(null);
                   e_st_text_final_term.setText(null);
                   e_st_text_total_mark.setText(null);
                   e_st_text_subject_grd.setText(null);
                   
            }else
            {
                JOptionPane.showMessageDialog(null, "Data Not"+Massage3);
            }  
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      
      //refress teable result panel
      
        public void show_student_result()
    {
        ArrayList<user3> list3 =  getStudent_result();
        DefaultTableModel model = (DefaultTableModel)student_result.getModel();
        Object [] row = new Object[10];
        for(int i = 0; i<list3.size();i++)
        {
            row[0] = list3.get(i).getID();
            row[1] = list3.get(i).getName();
            row[2] = list3.get(i).getr_firt_exam();
            row[3] = list3.get(i).getr_sec_exam();
            row[4] = list3.get(i).getfinal_result();
            row[5] = list3.get(i).gettotal_num();
            row[6] = list3.get(i).getSubject_grd();
           row[7] = list3.get(i).getSubject();
            model.addRow(row);
            
        }
    }
        //display student result in the result panel.......
             public void show_student_result_panel()
    {
        ArrayList<user3> list3 =  getStudent_result();
        DefaultTableModel model = (DefaultTableModel)student_result1.getModel();
        Object [] row = new Object[10];
        for(int i = 0; i<list3.size();i++)
        {
            row[0] = list3.get(i).getID();
            row[1] = list3.get(i).getName();
            row[2] = list3.get(i).getr_firt_exam();
            row[3] = list3.get(i).getr_sec_exam();
            row[4] = list3.get(i).getfinal_result();
            row[5] = list3.get(i).gettotal_num();
            row[6] = list3.get(i).getSubject_grd();
           row[7] = list3.get(i).getSubject();
            model.addRow(row);
            
        }
    }
        
      public ArrayList<user3> getStudent_result()
    {
        ArrayList<user3>  Student_result = new ArrayList<user3>();
        Connection connection = getconnection();
        String quary3 = "SELECT * FROM `student_result`"; 
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(quary3);
            user3 User3;
           
            while(rs.next())
            {
                User3 = new  user3(rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("subject_Grade"),
                        rs.getInt("First_term"),
                        rs.getInt("mid_term"),
                        rs.getInt("Final_exam"),
                        rs.getInt("Total_mark"),
                        rs.getString("subject"));
               Student_result.add(User3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Student_result;
    }
      
      
      //searching ..............
       public ArrayList<user3>ListUsers3(String valTosearch)
  {
      ArrayList<user3>  resultList = new ArrayList<user3>();
     
      Statement st;
        ResultSet rs;
        try {
             Connection con = getconnection();
             st = con.createStatement();
              String sql= "SELECT * FROM `student_result` WHERE CONCAT(`student_id`,`student_name`)LIKE'%"+valTosearch+"%'";
              rs  = st.executeQuery(sql);
               user3 User3;
           
            while(rs.next())
            {
                User3 = new  user3(rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("subject_Grade"),
                        rs.getInt("First_term"),
                        rs.getInt("mid_term"),
                        rs.getInt("Final_exam"),
                        rs.getInt("Total_mark"),
                        rs.getString("subject"));
               resultList.add(User3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  resultList;
  }

    public void finduser3()
    {
         ArrayList<user3>  user3 = ListUsers3(result_search.getText());
         DefaultTableModel model = new DefaultTableModel();
         model.setColumnIdentifiers(new Object[]    {"student_id","student_name","First_term","	mid_term","Final_exam","Total_mark","Total_mark","subject"});
       
        
        Object [] row = new Object[10];
        for(int i = 0; i<user3.size();i++)
        {
            row[0] = user3.get(i).getID();
            row[1] = user3.get(i).getName();
            row[2] = user3.get(i).getr_firt_exam();
            row[3] = user3.get(i).getr_sec_exam();
            row[4] = user3.get(i).getfinal_result();
            row[5] = user3.get(i).gettotal_num();
            row[6] = user3.get(i).getSubject_grd();
           row[7] = user3.get(i).getSubject();
            model.addRow(row);
            
        }
        student_result1.setModel(model);
    }
    //grading system ..............
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        menu_panel = new javax.swing.JPanel();
        student_details = new javax.swing.JButton();
        home = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        result = new javax.swing.JButton();
        A_teacher = new javax.swing.JButton();
        exam = new javax.swing.JButton();
        insert_pnl = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        insert = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        student_dets = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        student_information = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        addresss = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        bloood = new javax.swing.JLabel();
        la_student_age = new javax.swing.JLabel();
        la_student_contact = new javax.swing.JLabel();
        la_student_name = new javax.swing.JLabel();
        la_student_id = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        f_NAME = new javax.swing.JLabel();
        m_name = new javax.swing.JLabel();
        g_nder = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        address1 = new javax.swing.JLabel();
        student = new javax.swing.JPanel();
        s_text_id = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        S_text_name = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        s_text_password = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        s_text_contact = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        student_table = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        s_text_age = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        F_name = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        mother_name = new javax.swing.JTextField();
        gender = new javax.swing.JComboBox<>();
        blood = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        DOWNLOAD = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        number_submit = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        e_st_text_subject_grd = new javax.swing.JTextField();
        e_st_text_name = new javax.swing.JTextField();
        e_st_text_id = new javax.swing.JTextField();
        e_st_text_first_term = new javax.swing.JTextField();
        e_st_text_mid_term = new javax.swing.JTextField();
        e_st_text_final_term = new javax.swing.JTextField();
        e_st_text_total_mark = new javax.swing.JTextField();
        grade_submit = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        subject = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        student_result1 = new javax.swing.JTable();
        result_search = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        Result = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        student_result = new javax.swing.JTable();
        r_student_sub_grd = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        r_student_id_v = new javax.swing.JLabel();
        r_student_name = new javax.swing.JLabel();
        r_student_1st = new javax.swing.JLabel();
        r_student_2st = new javax.swing.JLabel();
        r_student_final = new javax.swing.JLabel();
        r_student_total = new javax.swing.JLabel();
        r_student__sub = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        teacher = new javax.swing.JPanel();
        t_text_id = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        t_text_name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        t_password = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        t_text_email = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        t_text_age = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        teacher_table = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        room = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        contact_num = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        genderr = new javax.swing.JComboBox<>();
        T_search = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        teacher_details = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        teacher_information = new javax.swing.JTable();
        la_age = new javax.swing.JLabel();
        la_teacher_id = new javax.swing.JLabel();
        la_teacher_name = new javax.swing.JLabel();
        la_email1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        GEND = new javax.swing.JLabel();
        M_NUMBER = new javax.swing.JLabel();
        R_NUMBER = new javax.swing.JLabel();
        welcome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1800, 1000));
        getContentPane().setLayout(null);

        menu_panel.setBackground(new java.awt.Color(0, 204, 204));
        menu_panel.setLayout(null);

        student_details.setBackground(new java.awt.Color(204, 255, 204));
        student_details.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        student_details.setForeground(new java.awt.Color(204, 0, 204));
        student_details.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/student.png"))); // NOI18N
        student_details.setText("STUDENT");
        student_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_detailsActionPerformed(evt);
            }
        });
        menu_panel.add(student_details);
        student_details.setBounds(260, 10, 170, 41);

        home.setBackground(new java.awt.Color(204, 255, 204));
        home.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        home.setForeground(new java.awt.Color(204, 0, 204));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        home.setText("HOME");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        menu_panel.add(home);
        home.setBounds(110, 10, 150, 41);

        logout.setBackground(new java.awt.Color(204, 255, 204));
        logout.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logout.setForeground(new java.awt.Color(204, 0, 204));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        logout.setText("LOGOUT");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        menu_panel.add(logout);
        logout.setBounds(920, 10, 150, 41);

        result.setBackground(new java.awt.Color(204, 255, 204));
        result.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        result.setForeground(new java.awt.Color(204, 0, 204));
        result.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/result.png"))); // NOI18N
        result.setText("RESULT");
        result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultActionPerformed(evt);
            }
        });
        menu_panel.add(result);
        result.setBounds(760, 10, 160, 40);

        A_teacher.setBackground(new java.awt.Color(204, 255, 204));
        A_teacher.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        A_teacher.setForeground(new java.awt.Color(204, 0, 204));
        A_teacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Teacher.png"))); // NOI18N
        A_teacher.setText("TEACHER");
        A_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A_teacherActionPerformed(evt);
            }
        });
        menu_panel.add(A_teacher);
        A_teacher.setBounds(430, 10, 170, 41);

        exam.setBackground(new java.awt.Color(204, 255, 204));
        exam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        exam.setForeground(new java.awt.Color(204, 0, 204));
        exam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EXAM.png"))); // NOI18N
        exam.setText("EXAM");
        exam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examActionPerformed(evt);
            }
        });
        menu_panel.add(exam);
        exam.setBounds(600, 10, 160, 41);

        getContentPane().add(menu_panel);
        menu_panel.setBounds(40, 10, 1200, 60);

        insert_pnl.setBackground(new java.awt.Color(204, 204, 255));
        insert_pnl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        insert_pnl.setLayout(null);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Registration.png"))); // NOI18N
        jButton1.setText("Student Registration");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        insert_pnl.add(jButton1);
        jButton1.setBounds(20, 30, 210, 40);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Registration.png"))); // NOI18N
        jButton2.setText("Teacher Registration");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        insert_pnl.add(jButton2);
        jButton2.setBounds(20, 110, 210, 40);

        insert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/INSERT.png"))); // NOI18N
        insert.setText("INSERT");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });
        insert_pnl.add(insert);
        insert.setBounds(20, 190, 210, 41);

        Update.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UPDATE.png"))); // NOI18N
        Update.setText("UPDATE");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        insert_pnl.add(Update);
        Update.setBounds(20, 280, 210, 40);

        delete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DELETE.png"))); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        insert_pnl.add(delete);
        delete.setBounds(20, 360, 210, 41);

        getContentPane().add(insert_pnl);
        insert_pnl.setBounds(40, 90, 250, 620);

        main.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        main.setLayout(null);

        student_dets.setBackground(new java.awt.Color(51, 153, 255));
        student_dets.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        student_dets.setLayout(null);

        student_information.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "student_id", "Student_name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        student_information.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        student_information.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_informationMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(student_information);

        student_dets.add(jScrollPane3);
        jScrollPane3.setBounds(700, 110, 210, 460);

        jLabel34.setBackground(new java.awt.Color(0, 153, 153));
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel34.setText("STUDENT INFORMATION");
        student_dets.add(jLabel34);
        jLabel34.setBounds(220, 60, 260, 25);

        addresss.setBackground(new java.awt.Color(0, 153, 153));
        addresss.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        student_dets.add(addresss);
        addresss.setBounds(290, 400, 130, 0);

        jLabel36.setBackground(new java.awt.Color(0, 153, 153));
        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("STUDENT ID:");
        student_dets.add(jLabel36);
        jLabel36.setBounds(150, 140, 110, 17);

        jLabel37.setBackground(new java.awt.Color(0, 153, 153));
        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText(" BLOOD GROUP");
        student_dets.add(jLabel37);
        jLabel37.setBounds(150, 460, 110, 30);

        bloood.setBackground(new java.awt.Color(0, 153, 153));
        bloood.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        student_dets.add(bloood);
        bloood.setBounds(290, 460, 130, 30);

        la_student_age.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        student_dets.add(la_student_age);
        la_student_age.setBounds(290, 360, 230, 30);

        la_student_contact.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        student_dets.add(la_student_contact);
        la_student_contact.setBounds(290, 310, 230, 30);

        la_student_name.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        student_dets.add(la_student_name);
        la_student_name.setBounds(290, 170, 230, 30);

        la_student_id.setBackground(new java.awt.Color(255, 204, 204));
        la_student_id.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        student_dets.add(la_student_id);
        la_student_id.setBounds(290, 140, 230, 30);

        jLabel50.setBackground(new java.awt.Color(0, 153, 153));
        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("STUDENT NAME:");
        student_dets.add(jLabel50);
        jLabel50.setBounds(150, 180, 110, 17);

        jLabel51.setBackground(new java.awt.Color(0, 153, 153));
        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setText("FATHER NAME:");
        student_dets.add(jLabel51);
        jLabel51.setBounds(150, 210, 110, 30);

        jLabel52.setBackground(new java.awt.Color(0, 153, 153));
        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setText("MOTHER NAME:");
        student_dets.add(jLabel52);
        jLabel52.setBounds(150, 260, 110, 30);

        jLabel53.setBackground(new java.awt.Color(0, 153, 153));
        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("GENDER");
        student_dets.add(jLabel53);
        jLabel53.setBounds(150, 430, 110, 30);

        jLabel54.setBackground(new java.awt.Color(0, 153, 153));
        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText(" PHONE NUMBER:");
        student_dets.add(jLabel54);
        jLabel54.setBounds(150, 310, 130, 17);

        f_NAME.setBackground(new java.awt.Color(0, 153, 153));
        f_NAME.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        student_dets.add(f_NAME);
        f_NAME.setBounds(290, 217, 170, 20);

        m_name.setBackground(new java.awt.Color(0, 153, 153));
        m_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        student_dets.add(m_name);
        m_name.setBounds(290, 260, 160, 30);

        g_nder.setBackground(new java.awt.Color(0, 153, 153));
        g_nder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        student_dets.add(g_nder);
        g_nder.setBounds(290, 430, 150, 30);

        jLabel38.setBackground(new java.awt.Color(0, 153, 153));
        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("STUDENT AGE:");
        student_dets.add(jLabel38);
        jLabel38.setBounds(150, 360, 130, 17);

        jLabel55.setBackground(new java.awt.Color(0, 153, 153));
        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setText("ADDRESS");
        student_dets.add(jLabel55);
        jLabel55.setBounds(150, 400, 110, 17);

        address1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        student_dets.add(address1);
        address1.setBounds(290, 400, 210, 20);

        main.add(student_dets);
        student_dets.setBounds(10, 10, 900, 600);

        student.setBackground(new java.awt.Color(255, 255, 255));
        student.setLayout(null);
        student.add(s_text_id);
        s_text_id.setBounds(140, 110, 170, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Student Id:");
        student.add(jLabel11);
        jLabel11.setBounds(30, 110, 80, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Mother Name");
        student.add(jLabel12);
        jLabel12.setBounds(30, 260, 90, 30);
        student.add(S_text_name);
        S_text_name.setBounds(140, 160, 170, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Password");
        student.add(jLabel13);
        jLabel13.setBounds(30, 300, 60, 30);

        s_text_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_text_passwordActionPerformed(evt);
            }
        });
        student.add(s_text_password);
        s_text_password.setBounds(140, 300, 170, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Contact");
        student.add(jLabel14);
        jLabel14.setBounds(30, 350, 70, 30);

        s_text_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_text_contactActionPerformed(evt);
            }
        });
        student.add(s_text_contact);
        s_text_contact.setBounds(140, 350, 170, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Age");
        student.add(jLabel15);
        jLabel15.setBounds(40, 410, 50, 30);
        student.add(address);
        address.setBounds(140, 460, 170, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 204));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Student11.png"))); // NOI18N
        jLabel2.setText("Student Registration");
        student.add(jLabel2);
        jLabel2.setBounds(30, 20, 240, 32);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Blood Group");
        student.add(jLabel4);
        jLabel4.setBounds(40, 540, 80, 30);

        student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "student_id", "Student_name", "Father Name", "Mother Name", "password", "contact", "Age", "Address", "gender", "Blood"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        student_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        student_table.getTableHeader().setReorderingAllowed(false);
        student_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(student_table);

        student.add(jScrollPane1);
        jScrollPane1.setBounds(360, 110, 530, 460);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 0, 204));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Student11.png"))); // NOI18N
        jLabel16.setText("Student Information");
        student.add(jLabel16);
        jLabel16.setBounds(440, 10, 230, 32);
        student.add(s_text_age);
        s_text_age.setBounds(140, 410, 170, 30);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Student Name:");
        student.add(jLabel19);
        jLabel19.setBounds(30, 170, 100, 15);
        student.add(F_name);
        F_name.setBounds(140, 210, 170, 30);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Father Name:");
        student.add(jLabel20);
        jLabel20.setBounds(30, 210, 90, 30);
        student.add(mother_name);
        mother_name.setBounds(140, 260, 170, 30);

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Female", "Male", "Other" }));
        student.add(gender);
        gender.setBounds(140, 500, 170, 30);

        blood.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A +", "A -", "B +", "B -", "AB +", "AB -" }));
        student.add(blood);
        blood.setBounds(140, 540, 170, 30);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Address");
        student.add(jLabel48);
        jLabel48.setBounds(40, 460, 70, 30);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Gender");
        student.add(jLabel49);
        jLabel49.setBounds(40, 500, 70, 30);

        DOWNLOAD.setText("DOWNLOAD");
        DOWNLOAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOWNLOADActionPerformed(evt);
            }
        });
        student.add(DOWNLOAD);
        DOWNLOAD.setBounds(760, 60, 110, 30);

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        student.add(search);
        search.setBounds(430, 60, 160, 30);

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        student.add(jButton3);
        jButton3.setBounds(600, 60, 90, 30);

        main.add(student);
        student.setBounds(10, 10, 900, 600);

        number_submit.setBackground(new java.awt.Color(153, 153, 255));
        number_submit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        number_submit.setLayout(null);

        jLabel18.setBackground(new java.awt.Color(0, 102, 51));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Exam number submit");
        number_submit.add(jLabel18);
        jLabel18.setBounds(110, 30, 200, 30);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Student Name:");
        number_submit.add(jLabel21);
        jLabel21.setBounds(50, 140, 120, 30);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Student ID:");
        number_submit.add(jLabel22);
        jLabel22.setBounds(50, 100, 120, 30);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("First Term Mark:");
        number_submit.add(jLabel23);
        jLabel23.setBounds(50, 230, 120, 17);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Mid Term Mark:  ");
        number_submit.add(jLabel24);
        jLabel24.setBounds(50, 270, 120, 20);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Final Mark:");
        number_submit.add(jLabel25);
        jLabel25.setBounds(50, 310, 120, 17);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Total Mark");
        number_submit.add(jLabel26);
        jLabel26.setBounds(50, 350, 120, 30);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Subject Grade:");
        number_submit.add(jLabel28);
        jLabel28.setBounds(50, 400, 120, 30);

        e_st_text_subject_grd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                e_st_text_subject_grdMouseClicked(evt);
            }
        });
        number_submit.add(e_st_text_subject_grd);
        e_st_text_subject_grd.setBounds(180, 400, 140, 30);
        number_submit.add(e_st_text_name);
        e_st_text_name.setBounds(180, 140, 140, 30);
        number_submit.add(e_st_text_id);
        e_st_text_id.setBounds(180, 100, 140, 30);
        number_submit.add(e_st_text_first_term);
        e_st_text_first_term.setBounds(180, 230, 140, 30);
        number_submit.add(e_st_text_mid_term);
        e_st_text_mid_term.setBounds(180, 270, 140, 30);
        number_submit.add(e_st_text_final_term);
        e_st_text_final_term.setBounds(180, 310, 140, 30);

        e_st_text_total_mark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                e_st_text_total_markMouseClicked(evt);
            }
        });
        e_st_text_total_mark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e_st_text_total_markActionPerformed(evt);
            }
        });
        number_submit.add(e_st_text_total_mark);
        e_st_text_total_mark.setBounds(180, 360, 140, 30);

        grade_submit.setText("Submit");
        grade_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grade_submitActionPerformed(evt);
            }
        });
        number_submit.add(grade_submit);
        grade_submit.setBounds(180, 450, 140, 30);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Subject");
        number_submit.add(jLabel27);
        jLabel27.setBounds(50, 180, 100, 20);

        subject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSC-383", "CSC-384" }));
        subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectActionPerformed(evt);
            }
        });
        number_submit.add(subject);
        subject.setBounds(180, 180, 140, 30);

        student_result1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "student_id", "student_name", "Fst_term_xm", "mid_term_xm", "final mark", "Total mark", "Grade", "subject"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        student_result1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        student_result1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_result1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(student_result1);

        number_submit.add(jScrollPane6);
        jScrollPane6.setBounds(380, 90, 510, 460);
        number_submit.add(result_search);
        result_search.setBounds(450, 30, 160, 30);

        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        number_submit.add(jButton6);
        jButton6.setBounds(620, 30, 100, 30);

        jButton7.setText("Download");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        number_submit.add(jButton7);
        jButton7.setBounds(770, 30, 110, 30);

        main.add(number_submit);
        number_submit.setBounds(10, 10, 900, 600);

        Result.setBackground(new java.awt.Color(102, 204, 255));
        Result.setLayout(null);

        student_result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "student_id", "student_name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        student_result.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        student_result.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_resultMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(student_result);

        Result.add(jScrollPane5);
        jScrollPane5.setBounds(690, 90, 210, 420);

        r_student_sub_grd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student_sub_grd);
        r_student_sub_grd.setBounds(280, 380, 210, 20);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Student Name   :");
        Result.add(jLabel40);
        jLabel40.setBounds(150, 140, 120, 30);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("First Term Mark :");
        Result.add(jLabel41);
        jLabel41.setBounds(150, 220, 120, 20);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Mid Term Mark  :  ");
        Result.add(jLabel42);
        jLabel42.setBounds(150, 260, 123, 20);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Final Mark          :");
        Result.add(jLabel43);
        jLabel43.setBounds(150, 300, 120, 17);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Total Mark         :");
        Result.add(jLabel44);
        jLabel44.setBounds(150, 330, 120, 30);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Subject               :");
        Result.add(jLabel45);
        jLabel45.setBounds(150, 180, 130, 20);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Subject Grade  :");
        Result.add(jLabel46);
        jLabel46.setBounds(150, 380, 120, 30);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Student ID         :");
        Result.add(jLabel47);
        jLabel47.setBounds(150, 110, 120, 30);

        r_student_id_v.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student_id_v);
        r_student_id_v.setBounds(280, 120, 210, 20);

        r_student_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student_name);
        r_student_name.setBounds(280, 140, 210, 20);

        r_student_1st.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student_1st);
        r_student_1st.setBounds(280, 220, 210, 20);

        r_student_2st.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student_2st);
        r_student_2st.setBounds(280, 260, 210, 20);

        r_student_final.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student_final);
        r_student_final.setBounds(280, 300, 210, 20);

        r_student_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student_total);
        r_student_total.setBounds(280, 340, 210, 20);

        r_student__sub.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Result.add(r_student__sub);
        r_student__sub.setBounds(280, 180, 210, 20);

        jLabel39.setBackground(new java.awt.Color(0, 204, 204));
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel39.setText("EXAM MARK CALCULATION");
        Result.add(jLabel39);
        jLabel39.setBounds(180, 40, 280, 30);

        main.add(Result);
        Result.setBounds(10, 10, 900, 600);

        teacher.setBackground(new java.awt.Color(255, 255, 255));
        teacher.setLayout(null);
        teacher.add(t_text_id);
        t_text_id.setBounds(130, 110, 180, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Teacher Id:");
        teacher.add(jLabel6);
        jLabel6.setBounds(30, 110, 80, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Teacher Name:");
        teacher.add(jLabel7);
        jLabel7.setBounds(30, 170, 100, 15);
        teacher.add(t_text_name);
        t_text_name.setBounds(130, 160, 180, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Password");
        teacher.add(jLabel8);
        jLabel8.setBounds(30, 220, 60, 14);
        teacher.add(t_password);
        t_password.setBounds(130, 210, 180, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("E-mail:");
        teacher.add(jLabel9);
        jLabel9.setBounds(30, 260, 70, 30);

        t_text_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_text_emailActionPerformed(evt);
            }
        });
        teacher.add(t_text_email);
        t_text_email.setBounds(130, 260, 180, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Gender");
        teacher.add(jLabel10);
        jLabel10.setBounds(30, 470, 50, 20);
        teacher.add(t_text_age);
        t_text_age.setBounds(130, 310, 180, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 204));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/teacher_reg.png"))); // NOI18N
        jLabel3.setText("Teacher Information");
        teacher.add(jLabel3);
        jLabel3.setBounds(50, 40, 240, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Room No");
        teacher.add(jLabel5);
        jLabel5.setBounds(30, 420, 70, 15);

        teacher_table.setForeground(new java.awt.Color(0, 102, 102));
        teacher_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher_id", "Teacher_name", "password", "Email", "Age", "Phone Number", "Room Number", "Gender"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        teacher_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacher_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(teacher_table);

        teacher.add(jScrollPane2);
        jScrollPane2.setBounds(360, 102, 540, 480);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 0, 204));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/teacher_reg.png"))); // NOI18N
        jLabel17.setText("Teacher Information");
        teacher.add(jLabel17);
        jLabel17.setBounds(480, 20, 240, 20);

        room.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Room-201", "Room-202", "Room-203", "Room-204", "Room-205", "Room-206", "Room-206", "Room-208" }));
        teacher.add(room);
        room.setBounds(130, 410, 180, 30);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Phone Number");
        teacher.add(jLabel35);
        jLabel35.setBounds(30, 370, 90, 20);

        contact_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contact_numActionPerformed(evt);
            }
        });
        teacher.add(contact_num);
        contact_num.setBounds(130, 360, 180, 30);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Age");
        teacher.add(jLabel56);
        jLabel56.setBounds(30, 320, 50, 15);

        genderr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        teacher.add(genderr);
        genderr.setBounds(130, 470, 180, 30);

        T_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T_searchActionPerformed(evt);
            }
        });
        teacher.add(T_search);
        T_search.setBounds(470, 50, 150, 30);

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        teacher.add(jButton4);
        jButton4.setBounds(630, 50, 90, 30);

        jButton5.setText("Download");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        teacher.add(jButton5);
        jButton5.setBounds(770, 50, 110, 30);

        main.add(teacher);
        teacher.setBounds(10, 10, 900, 600);

        teacher_details.setBackground(new java.awt.Color(102, 204, 255));
        teacher_details.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        teacher_details.setLayout(null);

        teacher_information.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "teacher_id", "teacher_name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        teacher_information.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        teacher_information.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacher_informationMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(teacher_information);

        teacher_details.add(jScrollPane4);
        jScrollPane4.setBounds(690, 20, 210, 460);

        la_age.setBackground(new java.awt.Color(204, 153, 255));
        la_age.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        teacher_details.add(la_age);
        la_age.setBounds(230, 270, 220, 30);

        la_teacher_id.setBackground(new java.awt.Color(255, 204, 204));
        la_teacher_id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        teacher_details.add(la_teacher_id);
        la_teacher_id.setBounds(230, 130, 220, 30);

        la_teacher_name.setBackground(new java.awt.Color(204, 153, 255));
        la_teacher_name.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        teacher_details.add(la_teacher_name);
        la_teacher_name.setBounds(230, 180, 220, 30);

        la_email1.setBackground(new java.awt.Color(204, 153, 255));
        la_email1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        teacher_details.add(la_email1);
        la_email1.setBounds(230, 220, 220, 30);

        jLabel29.setBackground(new java.awt.Color(0, 153, 153));
        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("GENDER");
        teacher_details.add(jLabel29);
        jLabel29.setBounds(90, 400, 110, 17);

        jLabel30.setBackground(new java.awt.Color(0, 153, 153));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel30.setText("TEACHER INFORMATION");
        teacher_details.add(jLabel30);
        jLabel30.setBounds(190, 60, 260, 25);

        jLabel31.setBackground(new java.awt.Color(0, 153, 153));
        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("TEACHER NAME:");
        teacher_details.add(jLabel31);
        jLabel31.setBounds(90, 190, 110, 17);

        jLabel32.setBackground(new java.awt.Color(0, 153, 153));
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("TEACHER E-MAIL:");
        teacher_details.add(jLabel32);
        jLabel32.setBounds(90, 230, 110, 17);

        jLabel33.setBackground(new java.awt.Color(0, 153, 153));
        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("TEACHER ID:");
        teacher_details.add(jLabel33);
        jLabel33.setBounds(90, 140, 110, 17);

        jLabel57.setBackground(new java.awt.Color(0, 153, 153));
        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setText("TEACHER AGE:");
        teacher_details.add(jLabel57);
        jLabel57.setBounds(90, 280, 110, 17);

        jLabel58.setBackground(new java.awt.Color(0, 153, 153));
        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel58.setText("MOBILE NUMBER");
        teacher_details.add(jLabel58);
        jLabel58.setBounds(90, 320, 110, 17);

        jLabel59.setBackground(new java.awt.Color(0, 153, 153));
        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setText("ROOM NO");
        teacher_details.add(jLabel59);
        jLabel59.setBounds(90, 360, 110, 17);

        GEND.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        teacher_details.add(GEND);
        GEND.setBounds(230, 400, 200, 30);

        M_NUMBER.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        teacher_details.add(M_NUMBER);
        M_NUMBER.setBounds(230, 320, 210, 30);

        R_NUMBER.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        teacher_details.add(R_NUMBER);
        R_NUMBER.setBounds(230, 360, 210, 30);

        main.add(teacher_details);
        teacher_details.setBounds(10, 10, 900, 600);

        welcome.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/welco.jpg"))); // NOI18N
        welcome.add(jLabel1);
        jLabel1.setBounds(0, 5, 910, 430);

        main.add(welcome);
        welcome.setBounds(10, 10, 900, 410);

        getContentPane().add(main);
        main.setBounds(320, 90, 920, 620);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(student);
       main.repaint();
       main.revalidate();
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(teacher);
       main.repaint();
       main.revalidate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void t_text_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_text_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_text_emailActionPerformed

    private void s_text_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_text_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_text_contactActionPerformed

    private void student_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_tableMouseClicked
        int i= student_table.getSelectedRow();
        TableModel model = student_table.getModel();
         s_text_id.setText(model.getValueAt(i,0).toString());
        S_text_name.setText(model.getValueAt(i,1).toString());
        F_name.setText(model.getValueAt(i,2).toString());
        mother_name.setText(model.getValueAt(i,3).toString());
         s_text_password.setText(model.getValueAt(i,4).toString());
        s_text_contact.setText(model.getValueAt(i,5).toString());
        s_text_age.setText(model.getValueAt(i,6).toString());
         address.setText(model.getValueAt(i,7).toString());
        
    }//GEN-LAST:event_student_tableMouseClicked

    private void s_text_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_text_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_text_passwordActionPerformed

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
     
        String query1= "INSERT INTO student_information(student_id,student_name,Father_name,Mother_name,password,contact,age,Address,gender,blood) VALUES('"+s_text_id.getText()+"','"+S_text_name.getText()+"','"+F_name.getText()+"','"+mother_name.getText()+"','"+s_text_password.getText()+"','"+ s_text_contact.getText()+"','"+s_text_age.getText()+"','"+address.getText()+"','"+gender.getSelectedItem().toString()+"','"+blood.getSelectedItem().toString()+"')";
         executeSQlQuary(query1, "Inserted");
        
         String query2= "INSERT INTO teacher_information(teacher_id,teacher_name,password,email,age,contact,Room,Gender) VALUES('"+t_text_id.getText()+"','"+t_text_name.getText()+"','"+t_password.getText()+"','"+t_text_email.getText()+"','"+t_text_age.getText()+"','"+contact_num.getText()+"','"+room.getSelectedItem().toString()+"','"+genderr.getSelectedItem().toString()+"')";
        executeSQlQuary2(query2, "Inserted");
       
        String query3= "INSERT INTO student_Result(student_id,student_name,First_term,mid_term,Final_exam,Total_mark,subject_Grade) VALUES('"+e_st_text_id.getText()+"','"+e_st_text_name.getText()+"','"+ e_st_text_first_term.getText()+"','"+e_st_text_mid_term.getText()+"','"+e_st_text_final_term.getText()+"','"+e_st_text_total_mark.getText()+"','"+e_st_text_subject_grd.getText()+"','"+subject.getSelectedItem().toString()+"')";
        executeSQlQuary3(query3, "Inserted");
    }//GEN-LAST:event_insertActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
       
        String query = "UPDATE `student_information` SET `student_name`='"+S_text_name.getText()+"',`Father_name`='"+ F_name.getText()+"',`Mother_name`='"+ mother_name.getText()+"',`password`='"+s_text_password.getText()+"',`contact`='"+ s_text_contact.getText()+"',`age`='"+s_text_age.getText()+"',`Address`='"+ address.getText()+"' WHERE `student_id` = "+s_text_id.getText();
         executeSQlQuary(query, "Update");
         
         String query2 = "UPDATE `teacher_information` SET `teacher_name`='"+t_text_name.getText()+"',`password`='"+t_password.getText()+"',`email`='"+ t_text_email.getText()+"',`age`='"+t_text_age.getText()+"',contact = '"+contact_num.getText()+"' WHERE `teacher_id` = "+t_text_id.getText();
         executeSQlQuary2(query2, "Update");
        String query3= "UPDATE `student_result` SET `student_name`= '"+e_st_text_name.getText()+"',First_term='"+ e_st_text_first_term.getText()+"',mid_term ='"+e_st_text_mid_term.getText()+"',Final_exam='"+e_st_text_final_term.getText()+"',Total_mark='"+e_st_text_total_mark.getText()+"',subject_Grade='"+e_st_text_subject_grd.getText()+"'WHERE student_id="+e_st_text_id.getText();
        executeSQlQuary3(query3, "Inserted");
         
    }//GEN-LAST:event_UpdateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
       String query ="DELETE FROM student_information WHERE `student_id`="+s_text_id.getText();
        executeSQlQuary(query, "delete");
        String query2 ="DELETE FROM teacher_information WHERE teacher_id ="+t_text_id.getText();
        executeSQlQuary2(query2, "delete");
        
         String query3 ="DELETE FROM student_result WHERE `student_id`="+e_st_text_id.getText();
        executeSQlQuary(query3, "delete");
    }//GEN-LAST:event_deleteActionPerformed

    private void resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultActionPerformed
       main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(Result);
       main.repaint();
       main.revalidate();
    }//GEN-LAST:event_resultActionPerformed

    private void A_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A_teacherActionPerformed
        main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(teacher_details);
       main.repaint();
       main.revalidate();
    }//GEN-LAST:event_A_teacherActionPerformed

    private void teacher_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacher_tableMouseClicked
       
         int i= teacher_table.getSelectedRow();
        TableModel model = teacher_table.getModel();
        t_text_id.setText(model.getValueAt(i,0).toString());
        t_text_name.setText(model.getValueAt(i,1).toString());
        t_password.setText(model.getValueAt(i,2).toString());
        t_text_email.setText(model.getValueAt(i,3).toString());
        t_text_age.setText(model.getValueAt(i,4).toString());
        contact_num.setText(model.getValueAt(i,5).toString());
    }//GEN-LAST:event_teacher_tableMouseClicked

    private void student_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_detailsActionPerformed
       main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(student_dets);
       main.repaint();
       main.revalidate();    
    }//GEN-LAST:event_student_detailsActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(welcome);
       main.repaint();
       main.revalidate();
    }//GEN-LAST:event_homeActionPerformed

    private void student_informationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_informationMouseClicked
        int i= student_information.getSelectedRow();
        TableModel model = student_table.getModel();
        la_student_id.setText(model.getValueAt(i,0).toString());
        la_student_name.setText(model.getValueAt(i,1).toString());
        f_NAME.setText(model.getValueAt(i,2).toString());
        m_name.setText(model.getValueAt(i,3).toString());
        la_student_contact.setText(model.getValueAt(i,5).toString());
        la_student_age.setText(model.getValueAt(i,6).toString());
         address1.setText(model.getValueAt(i,7).toString());
        g_nder.setText(model.getValueAt(i,8).toString());
         bloood.setText(model.getValueAt(i,9).toString());
        
    }//GEN-LAST:event_student_informationMouseClicked

    private void teacher_informationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacher_informationMouseClicked
        int i= teacher_information.getSelectedRow();
        TableModel model = teacher_table.getModel();
        la_teacher_id.setText(model.getValueAt(i,0).toString());
        la_teacher_name.setText(model.getValueAt(i,1).toString());
        la_email1.setText(model.getValueAt(i,3).toString());
        la_age.setText(model.getValueAt(i,4).toString());
        M_NUMBER.setText(model.getValueAt(i,5).toString());
        R_NUMBER.setText(model.getValueAt(i,6).toString());
        GEND.setText(model.getValueAt(i,7).toString());
    }//GEN-LAST:event_teacher_informationMouseClicked

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
            Connection con = null; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collage_management_system","root","");
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          finally{
             try {
                 con.close();
                 login log = new login();
                 log.setVisible(true);
                 setVisible(false);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
     
    }//GEN-LAST:event_logoutActionPerformed

    private void examActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examActionPerformed
       main.removeAll();
       main.repaint();
       main.revalidate();
       main.add(number_submit);
       main.repaint();
       main.revalidate();
    }//GEN-LAST:event_examActionPerformed

    private void grade_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grade_submitActionPerformed
         String query3= "INSERT INTO student_Result(student_name,student_id,First_term,mid_term,Final_exam,Total_mark,subject_Grade,subject) VALUES('"+e_st_text_name.getText()+"','"+e_st_text_id.getText()+"','"+ e_st_text_first_term.getText()+"','"+e_st_text_mid_term.getText()+"','"+e_st_text_final_term.getText()+"','"+e_st_text_total_mark.getText()+"','"+e_st_text_subject_grd.getText()+"','"+subject.getSelectedItem().toString()+"')";
        executeSQlQuary3(query3, "Inserted");
    }//GEN-LAST:event_grade_submitActionPerformed

    private void student_resultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_resultMouseClicked
        int i= student_result.getSelectedRow();
        TableModel model = student_result1.getModel();
        r_student_id_v.setText(model.getValueAt(i,0).toString());
        r_student_name.setText(model.getValueAt(i,1).toString());
        r_student_1st.setText(model.getValueAt(i,2).toString());
        r_student_2st.setText(model.getValueAt(i,3).toString());
        r_student_final.setText(model.getValueAt(i,4).toString());
        r_student_total.setText(model.getValueAt(i,5).toString());
        r_student_sub_grd.setText(model.getValueAt(i,6).toString());
        r_student__sub.setText(model.getValueAt(i,7).toString());
    
    }//GEN-LAST:event_student_resultMouseClicked

    private void student_result1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_result1MouseClicked
        int i= student_result1.getSelectedRow();
        TableModel model = student_result1.getModel();
        e_st_text_id.setText(model.getValueAt(i,0).toString());
        e_st_text_name.setText(model.getValueAt(i,1).toString());
        e_st_text_first_term.setText(model.getValueAt(i,2).toString());
        e_st_text_mid_term.setText(model.getValueAt(i,3).toString());
        e_st_text_final_term.setText(model.getValueAt(i,4).toString());
        e_st_text_total_mark.setText(model.getValueAt(i,5).toString());
        e_st_text_subject_grd.setText(model.getValueAt(i,6).toString());
    }//GEN-LAST:event_student_result1MouseClicked

    private void subjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectActionPerformed

    private void e_st_text_total_markMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_e_st_text_total_markMouseClicked
      
   int s= Integer.parseInt(e_st_text_first_term.getText()) ;
   int p=Integer.parseInt(e_st_text_mid_term.getText()) ;
   int q=Integer.parseInt(e_st_text_final_term.getText()) ; 
   
     int r=s+p+q;
   
        e_st_text_total_mark.setText(Integer.toString(r));
        
       
    }//GEN-LAST:event_e_st_text_total_markMouseClicked

    private void e_st_text_total_markActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e_st_text_total_markActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_e_st_text_total_markActionPerformed

    private void e_st_text_subject_grdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_e_st_text_subject_grdMouseClicked
         int T= Integer.parseInt(e_st_text_total_mark.getText()) ;
        if(T>=80 && T<100)
        {
            e_st_text_subject_grd.setText("A+"); 
        }
        else if(T>=70 && T<=79)
                {
                  e_st_text_subject_grd.setText("A");   
                }
        else if(T>=60 && T<=69)
                {
                  e_st_text_subject_grd.setText("A-");   
                }
        else if(T>=50 && T<=59)
                {
                  e_st_text_subject_grd.setText("B");   
                }
          else if(T>=40 && T<=49)
                {
                  e_st_text_subject_grd.setText("C");   
                }
        else
          {
               e_st_text_subject_grd.setText("Fail");  
          }
    }//GEN-LAST:event_e_st_text_subject_grdMouseClicked

    private void contact_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact_numActionPerformed

    private void DOWNLOADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOWNLOADActionPerformed
        
        try{
            MessageFormat header = new MessageFormat("Customer Details");
            MessageFormat footer = new MessageFormat("");
            student_table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_DOWNLOADActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       finduser();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        finduser2();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void T_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T_searchActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try{
            MessageFormat header = new MessageFormat("Customer Details");
            MessageFormat footer = new MessageFormat("");
            teacher_table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try{
            MessageFormat header = new MessageFormat("Customer Details");
            MessageFormat footer = new MessageFormat("");
            student_result1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        finduser3();
    }//GEN-LAST:event_jButton6ActionPerformed

    /*
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A_teacher;
    private javax.swing.JButton DOWNLOAD;
    private javax.swing.JTextField F_name;
    private javax.swing.JLabel GEND;
    private javax.swing.JLabel M_NUMBER;
    private javax.swing.JLabel R_NUMBER;
    private javax.swing.JPanel Result;
    private javax.swing.JTextField S_text_name;
    private javax.swing.JTextField T_search;
    private javax.swing.JButton Update;
    private javax.swing.JTextField address;
    private javax.swing.JLabel address1;
    private javax.swing.JLabel addresss;
    private javax.swing.JComboBox<String> blood;
    private javax.swing.JLabel bloood;
    private javax.swing.JTextField contact_num;
    private javax.swing.JButton delete;
    private javax.swing.JTextField e_st_text_final_term;
    private javax.swing.JTextField e_st_text_first_term;
    private javax.swing.JTextField e_st_text_id;
    private javax.swing.JTextField e_st_text_mid_term;
    private javax.swing.JTextField e_st_text_name;
    private javax.swing.JTextField e_st_text_subject_grd;
    private javax.swing.JTextField e_st_text_total_mark;
    private javax.swing.JButton exam;
    private javax.swing.JLabel f_NAME;
    private javax.swing.JLabel g_nder;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JComboBox<String> genderr;
    private javax.swing.JButton grade_submit;
    private javax.swing.JButton home;
    private javax.swing.JButton insert;
    private javax.swing.JPanel insert_pnl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel la_age;
    private javax.swing.JLabel la_email1;
    private javax.swing.JLabel la_student_age;
    private javax.swing.JLabel la_student_contact;
    private javax.swing.JLabel la_student_id;
    private javax.swing.JLabel la_student_name;
    private javax.swing.JLabel la_teacher_id;
    private javax.swing.JLabel la_teacher_name;
    private javax.swing.JButton logout;
    private javax.swing.JLabel m_name;
    private javax.swing.JPanel main;
    private javax.swing.JPanel menu_panel;
    private javax.swing.JTextField mother_name;
    private javax.swing.JPanel number_submit;
    private javax.swing.JLabel r_student_1st;
    private javax.swing.JLabel r_student_2st;
    private javax.swing.JLabel r_student__sub;
    private javax.swing.JLabel r_student_final;
    private javax.swing.JLabel r_student_id_v;
    private javax.swing.JLabel r_student_name;
    private javax.swing.JLabel r_student_sub_grd;
    private javax.swing.JLabel r_student_total;
    private javax.swing.JButton result;
    private javax.swing.JTextField result_search;
    private javax.swing.JComboBox<String> room;
    private javax.swing.JTextField s_text_age;
    private javax.swing.JTextField s_text_contact;
    private javax.swing.JTextField s_text_id;
    private javax.swing.JPasswordField s_text_password;
    private javax.swing.JTextField search;
    private javax.swing.JPanel student;
    private javax.swing.JButton student_details;
    private javax.swing.JPanel student_dets;
    private javax.swing.JTable student_information;
    private javax.swing.JTable student_result;
    private javax.swing.JTable student_result1;
    private javax.swing.JTable student_table;
    private javax.swing.JComboBox<String> subject;
    private javax.swing.JPasswordField t_password;
    private javax.swing.JTextField t_text_age;
    private javax.swing.JTextField t_text_email;
    private javax.swing.JTextField t_text_id;
    private javax.swing.JTextField t_text_name;
    private javax.swing.JPanel teacher;
    private javax.swing.JPanel teacher_details;
    private javax.swing.JTable teacher_information;
    private javax.swing.JTable teacher_table;
    private javax.swing.JPanel welcome;
    // End of variables declaration//GEN-END:variables

    //student result.................

   private Image fitimage(Image img , int w , int h)
{
    BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = resizedimage.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(img, 0, 0,w,h,null);
    g2.dispose();
    return resizedimage;
}
}
