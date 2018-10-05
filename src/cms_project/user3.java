/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_project;

/**
 *
 * @author MINHAZUL ISLAM MIN
 */
public class user3 {
    
    private int r_id;
    private String r_name;
    private int r_f_exam;
    private int r_second_exam;
    private int final_mark;
    private int total_mark;
    private String s_subject_g;
     private String s_subject;
   
    
     
    public user3(int ID,String Name,String Subject_grd,int r_firt_exam,int r_sec_exam,int final_result,int total_num,String sub)
    {
        this.r_id=ID;
        this.r_name=Name;
        this.s_subject_g  = Subject_grd;
        this.r_f_exam=r_firt_exam;
        this.r_second_exam=r_sec_exam;
        this.final_mark=final_result;
        this.total_mark = total_num;
        this.s_subject = sub;
    }

 

    public int getID()
    {
        return r_id;
    }
     public String getName() 
    {
        return r_name;
    }
  
      public int getr_firt_exam()
    {
        return r_f_exam;
    }
       public int getr_sec_exam()
    {
        return r_second_exam;
    }
          public int getfinal_result()
    {
        return final_mark;
    }
              public int gettotal_num()
    {
        return total_mark;
    }
     public String getSubject_grd()
    {
        return s_subject_g;
    }
     
     public String getSubject()
    {
        return s_subject;
    }
}


